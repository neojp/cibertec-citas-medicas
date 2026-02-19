package gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import arreglo.ArregloConsultorio;
import arreglo.ArregloMedico;
import clases.Cita;
import clases.Consultorio;
import clases.Medico;
import libreria.Libreria;

import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class MantenimientoMedico extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton btnNuevo;
	private JButton btnEditar;
	private JButton btnEliminar;
	private JButton btnBuscarNombre;
	private JScrollPane scp;
	private JButton btnBuscarCodigo;
	private JLabel lblBuscar;
	private JTable tblTabla;
	private DefaultTableModel modelo;
	private ArregloMedico arr = Principal.getArrMedicos();
	private JPanel pnlOpciones;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MantenimientoMedico dialog = new MantenimientoMedico();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MantenimientoMedico() {
		setResizable(false);
		setTitle("Mantenimiento - Médico");
		setBounds(100, 100, 608, 387);
		getContentPane().setLayout(null);
		
		btnNuevo = new JButton("Agregar Nuevo");
		btnNuevo.addActionListener(this);
		btnNuevo.setBounds(10, 11, 138, 23);
		getContentPane().add(btnNuevo);
		
		pnlOpciones = new JPanel();
		pnlOpciones.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Opciones de filas seleccionadas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlOpciones.setBounds(10, 289, 572, 48);
		getContentPane().add(pnlOpciones);
		pnlOpciones.setLayout(null);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(10, 16, 89, 23);
		pnlOpciones.add(btnEditar);
		btnEditar.addActionListener(this);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(109, 16, 89, 23);
		pnlOpciones.add(btnEliminar);
		btnEliminar.addActionListener(this);
		
		btnBuscarNombre = new JButton("Nombre");
		btnBuscarNombre.addActionListener(this);
		btnBuscarNombre.setBounds(493, 11, 89, 23);
		getContentPane().add(btnBuscarNombre);
		
		scp = new JScrollPane();
		scp.setBounds(10, 45, 572, 233);
		getContentPane().add(scp);
		
		modelo = Libreria.crearModeloTabla(new String[] {
			"C\u00F3digo", "Nombres", "Apellidos", "Especialidad", "CMP", "Estado"
		});
		tblTabla = Libreria.crearTabla();
		tblTabla.setModel(modelo);
		scp.setViewportView(tblTabla);
		
		btnBuscarCodigo = new JButton("Código");
		btnBuscarCodigo.addActionListener(this);
		btnBuscarCodigo.setBounds(394, 11, 89, 23);
		getContentPane().add(btnBuscarCodigo);
		
		lblBuscar = new JLabel("Buscar por:");
		lblBuscar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBuscar.setBounds(323, 15, 66, 14);
		getContentPane().add(lblBuscar);
		
		listar();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(e);
		}
		if (e.getSource() == btnEditar) {
			actionPerformedBtnEditar(e);
		}
		if (e.getSource() == btnBuscarCodigo) {
			actionPerformedBtnBuscarCodigo(e);
		}
		if (e.getSource() == btnBuscarNombre) {
			actionPerformedBtnBuscarNombre(e);
		}
		if (e.getSource() == btnNuevo) {
			actionPerformedBtnNuevo(e);
		}
	}
	
	// abre el formulario de agregar
	protected void actionPerformedBtnNuevo(ActionEvent e) {
		// abrir formulario en modo agregar
		FormularioMedico ventana = new FormularioMedico();
		
		// posicionar ventana y esperar a que se cierre
		ventana.setLocationRelativeTo(this);
		ventana.setModal(true);
		ventana.setVisible(true);
		
		// la ventana fue cerrada, revisar si se presiono el boton de aceptar
		if (ventana.getSuccess()) {
			// agregar a la lista y grabar al archivo de texto
			arr.adicionar(ventana.getMedico());
			
			// actualizar tabla
			listar();
			
			// cerrar ventana
			ventana.dispose();
		}
	}
	
	// abre el formulario de editar con datos del médico seleccionado 
	protected void actionPerformedBtnEditar(ActionEvent e) {
		// obtener médico de la fila seleccionada en la tabla
		Medico medico = obtenerMedico();
		if (medico != null) {
			// abrir formulario en modo editar
			FormularioMedico ventana = new FormularioMedico("editar");

			// llenar formulario con datos del médico
			ventana.llenarFormulario(medico);
			
			// posicionar ventana y esperar a que se cierre
			ventana.setLocationRelativeTo(this);
			ventana.setModal(true);
			ventana.setVisible(true);
			
			// la ventana fue cerrada, revisar si se presionó el botón de aceptar
			if (ventana.getSuccess()) {
				// grabar al archivo de texto
				arr.grabar();
				
				// actualizar tabla
				listar();
				
				// cerrar ventana
				ventana.dispose();
			}
		} else {
			JOptionPane.showMessageDialog(this, "Seleccione una fila", "Anuncio", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	// elimina el médico seleccionado
	protected void actionPerformedBtnEliminar(ActionEvent e) {
		// obtener médico de la fila seleccionada en la tabla
		Medico medico = obtenerMedico();
		if (medico != null) {
			// mostrar un dialogo de confirmación antes de eliminarlo
			int confirmar = JOptionPane.showConfirmDialog(
				this, 
				"¿Está seguro que quiere borrar este médico?", 
				"Confirmar Eliminación", 
				JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE
			);

			if (confirmar == 0) {
				// TODO: validar si existen citas futuras
				ArrayList<Cita> citasFuturas = Principal.getArrCitas().buscarFuturasPorMedico(medico.getCodMedico());
				if (citasFuturas != null) {
					String msg = "El médico no puede ser eliminado porque tiene " + citasFuturas.size();
					if (citasFuturas.size() == 1) msg += " cita futura";
					else msg += " citas futuras";

					JOptionPane.showMessageDialog(this, msg, "Error de validación", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				// eliminar las citas relacionadas a este médico
				ArrayList<Cita> citas = Principal.getArrCitas().buscarCodMedico(medico.getCodMedico());
				if (citas != null)
					for (int i = 0; i < citas.size(); i++)
						Principal.getArrCitas().eliminar(citas.get(i));
				
				// eliminar el médico
				arr.eliminar(medico);
				
				// grabar al archivo de texto
				arr.grabar();
				
				// actualizar la tabla
				listar();
			}
		} else {
			JOptionPane.showMessageDialog(this, "Seleccione una fila", "Anuncio", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	// actualizar tabla con contenido
	void listar() {
		// limpiar tabla
		modelo.setRowCount(0);
		
		// iterar el arreglo y llenar la tabla con datos del médico
		for (int i = 0; i < arr.tamano(); i++) {
			// crear fila con datos del médico
			Object[] fila = {
				arr.obtener(i).getCodMedico(),
				arr.obtener(i).getNombres(),
				arr.obtener(i).getApellidos(),
				arr.obtener(i).getEspecialidad(),
				arr.obtener(i).getCmp(),
				Medico.estados[arr.obtener(i).getEstado()] // mostrar el label del estado
			};
			
			// agregar fila
			modelo.addRow(fila);
		}
	}
	
	// obtiene el médico seleccionado de la tabla por su índice
	private Medico obtenerMedico() {
		// obtener el índice de la fila seleccionada
		int indice = tblTabla.getSelectedRow();
		if (indice != -1) {
			// obtener médico por indice
			return arr.obtener(indice);
		}
		return null;
	}
	
	// TODO: filtrar la tabla por nombre
	protected void actionPerformedBtnBuscarNombre(ActionEvent e) {
		// inicializar el JDialog en modo modal y espera a que se oculte
		FormularioBuscarNombre ventana = new FormularioBuscarNombre();
		ventana.setLocationRelativeTo(this);
		ventana.setModal(true);
		ventana.setVisible(true);
		
		if (ventana.getEmpezarBusqueda()) {
			// este codigo espera a que la ventana se oculte
			// se obtiene el CMP del JTextField en la ventana
			String nombre = ventana.leerNombre();
			System.out.println("Iniciar busqueda con nombre: " + nombre);
		}
		
		// y ahora se cierra la ventana
		ventana.dispose();
	}
	// TODO: filtrar la tabla por codigo
	protected void actionPerformedBtnBuscarCodigo(ActionEvent e) {
		// inicializar el JDialog en modo modal y espera a que se oculte
		FormularioBuscarCodigo ventana = new FormularioBuscarCodigo();
		ventana.setLocationRelativeTo(this);
		ventana.setModal(true);
		ventana.setVisible(true);
		
		if (ventana.getEmpezarBusqueda()) {
			// este codigo espera a que la ventana se oculte
			// se obtiene el codigo del JTextField en la ventana
			String codigo = ventana.leerCodigo();
			System.out.println("Iniciar busqueda con codigo: " + codigo);
		}
		
		// y ahora se cierra la ventana
		ventana.dispose();
	}
}
