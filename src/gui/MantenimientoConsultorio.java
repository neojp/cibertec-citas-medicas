package gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import arreglo.ArregloConsultorio;
import clases.Cita;
import clases.Consultorio;
import libreria.Libreria;

import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class MantenimientoConsultorio extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton btnNuevo;
	private JButton btnEditar;
	private JButton btnEliminar;
	private JButton btnBuscarCMP;
	private JScrollPane scp;
	private JButton btnBuscarCodigo;
	private JLabel lblBuscar;
	private JTable tblTabla;
	private DefaultTableModel modelo;
	private ArregloConsultorio arr = Principal.getArrConsultorios();
	private JPanel pnlOpciones;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MantenimientoConsultorio dialog = new MantenimientoConsultorio();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MantenimientoConsultorio() {
		setResizable(false);
		setTitle("Mantenimiento - Consultorio");
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
		
		btnBuscarCMP = new JButton("CMP");
		btnBuscarCMP.addActionListener(this);
		btnBuscarCMP.setBounds(493, 11, 89, 23);
		getContentPane().add(btnBuscarCMP);
		
		scp = new JScrollPane();
		scp.setBounds(10, 45, 572, 233);
		getContentPane().add(scp);
		
		modelo = Libreria.crearModeloTabla(new String[] {
			"Código",
			"Nombre",
			"Piso",
			"Ubicación",
			"Capacidad",
			"Estado"
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
		lblBuscar.setBounds(304, 15, 79, 14);
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
		if (e.getSource() == btnBuscarCMP) {
			actionPerformedBtnBuscarCMP(e);
		}
		if (e.getSource() == btnNuevo) {
			actionPerformedBtnNuevo(e);
		}
	}
	
	// TODO: filtrar la tabla por CMP
	protected void actionPerformedBtnBuscarCMP(ActionEvent e) {
		// inicializar el JDialog en modo modal y espera a que se oculte
		FormularioBuscarCMP ventana = new FormularioBuscarCMP();
		ventana.setLocationRelativeTo(this);
		ventana.setModal(true);
		ventana.setVisible(true);
		
		if (ventana.getEmpezarBusqueda()) {
			// este codigo espera a que la ventana se oculte
			// se obtiene el CMP del JTextField en la ventana
			String cmp = ventana.leerCMP();
			System.out.println("Iniciar busqueda con CMP: " + cmp);
		}
		
		// y ahora se cierra la ventana
		ventana.dispose();
	}

	// TODO: filtrar la tabla por código
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
	
	// abre el formulario de agregar
	protected void actionPerformedBtnNuevo(ActionEvent e) {
		// abrir formulario en modo agregar
		FormularioConsultorio ventana = new FormularioConsultorio();
		
		// posicionar ventana y esperar a que se cierre
		ventana.setLocationRelativeTo(this);
		ventana.setModal(true);
		ventana.setVisible(true);
		
		// la ventana fue cerrada, revisar si se presiono el boton de aceptar
		if (ventana.getSuccess()) {
			// agregar a la lista y grabar al archivo de texto
			arr.adicionar(ventana.getConsultorio());
			
			// actualizar tabla
			listar();
		}
	}
	
	// abre el formulario de editar con datos del consultorio seleccionado 
	protected void actionPerformedBtnEditar(ActionEvent e) {
		// obtener consultorio de la fila seleccionada en la tabla
		Consultorio consultorio = obtenerConsultorio();
		if (consultorio != null) {
			// abrir formulario en modo editar
			FormularioConsultorio ventana = new FormularioConsultorio("editar");

			// llenar formulario con datos del consultorio
			ventana.llenarFormulario(consultorio);
			
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
			}
		} else {
			JOptionPane.showMessageDialog(this, "Seleccione una fila", "Anuncio", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	// elimina el consultorio seleccionado
	protected void actionPerformedBtnEliminar(ActionEvent e) {
		// obtener consultorio de la fila seleccionada en la tabla
		Consultorio consultorio = obtenerConsultorio();
		if (consultorio != null) {
			// mostrar un dialogo de confirmación antes de eliminarlo
			int confirmar = JOptionPane.showConfirmDialog(
			    this, 
			    "¿Está seguro que quiere borrar este consultorio?", 
			    "Confirmar Eliminación", 
			    JOptionPane.YES_NO_OPTION,
			    JOptionPane.WARNING_MESSAGE
			);

			if (confirmar == 0) {
				// validar si existen citas en el consultorio
				ArrayList<Cita> citasFuturas = Principal.getArrCitas().buscarFuturasPorConsultorio(consultorio.getCodConsultorio());
				if (citasFuturas != null) {
					String msg = "El consultorio no puede ser eliminado porque tiene " + citasFuturas.size();
					if (citasFuturas.size() == 1) msg += " cita futura";
					else msg += " citas futuras";

					JOptionPane.showMessageDialog(this, msg, "Error de validación", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				// eliminar las citas relacionadas a este consultorio
				ArrayList<Cita> citas = Principal.getArrCitas().buscarCodConsultorio(consultorio.getCodConsultorio());
				if (citas != null)
					for (int i = 0; i < citas.size(); i++)
						Principal.getArrCitas().eliminar(citas.get(i));
				
				// eliminar el consultorio
				arr.eliminar(consultorio);
				
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
		
		// iterar el arreglo y llenar la tabla con datos del consultorio
		for (int i = 0; i < arr.tamano(); i++) {
			// crear fila con datos del consultorio
			Object[] fila = {
				arr.obtener(i).getCodConsultorio(),
				arr.obtener(i).getNombre(),
				arr.obtener(i).getPiso(),
				arr.obtener(i).getUbicacion(),
				arr.obtener(i).getCapacidad(),
				Consultorio.estados[arr.obtener(i).getEstado()] // mostrar el label del estado
			};
			
			// agregar fila
			modelo.addRow(fila);
		}
	}
	
	// obtiene el consultorio seleccionado de la tabla por su índice
	private Consultorio obtenerConsultorio() {
		// obtener el índice de la fila seleccionada
		int indice = tblTabla.getSelectedRow();
		if (indice != -1) {
			// obtener consultorio por indice
			return arr.obtener(indice);
		}
		return null;
	}
}
