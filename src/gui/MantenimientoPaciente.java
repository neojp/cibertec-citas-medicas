package gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import arreglo.ArregloPaciente;
import clases.Cita;
import clases.Paciente;
import clases.Paciente;
import libreria.Libreria;

import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class MantenimientoPaciente extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton btnNuevo;
	private JButton btnEditar;
	private JButton btnEliminar;
	private JButton btnBuscarDNI;
	private JScrollPane scp;
	private JButton btnBuscarCodigo;
	private JLabel lblBuscar;
	private JTable tblTabla;
	private DefaultTableModel modelo;
	private ArregloPaciente arr = Principal.getArrPacientes();
	private JPanel pnlOpciones;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MantenimientoPaciente dialog = new MantenimientoPaciente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MantenimientoPaciente() {
		setResizable(false);
		setTitle("Mantenimiento - Paciente");
		setBounds(100, 100, 608, 386);
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

		btnBuscarDNI = new JButton("DNI");
		btnBuscarDNI.addActionListener(this);
		btnBuscarDNI.setBounds(493, 11, 89, 23);
		getContentPane().add(btnBuscarDNI);

		scp = new JScrollPane();
		scp.setBounds(10, 45, 572, 233);
		getContentPane().add(scp);

		modelo = Libreria.crearModeloTabla(new String[] { 
			"Código", "Nombres", "Apellidos", "DNI", "Edad", "Celular", "Correo", "Estado"
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
		lblBuscar.setBounds(321, 15, 69, 14);
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
		if (e.getSource() == btnBuscarDNI) {
			actionPerformedBtnBuscarDNI(e);
		}
		if (e.getSource() == btnNuevo) {
			actionPerformedBtnNuevo(e);
		}
	}

	// TODO: filtrar la tabla por DNI
	protected void actionPerformedBtnBuscarDNI(ActionEvent e) {
		// inicializar el JDialog en modo modal y espera a que se oculte
		FormularioBuscarDNI ventana = new FormularioBuscarDNI();
		ventana.setLocationRelativeTo(this);
		ventana.setModal(true);
		ventana.setVisible(true);

		if (ventana.getEmpezarBusqueda()) {
			// este codigo espera a que la ventana se oculte
			// se obtiene el DNI del JTextField en la ventana
			String dni = ventana.leerDNI();
			System.out.println("Iniciar busqueda con CMP: " + dni);
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
		FormularioPaciente ventana = new FormularioPaciente();
		
		// posicionar ventana y esperar a que se cierre
		ventana.setLocationRelativeTo(this);
		ventana.setModal(true);
		ventana.setVisible(true);
		
		// la ventana fue cerrada, revisar si se presionó el boton de aceptar
		if (ventana.getSuccess()) {
			// agregar a la lista y grabar al archivo de texto
			arr.adicionar(ventana.getPaciente());
			
			// actualizar tabla
			listar();
			
			// cerrar ventana
			ventana.dispose();
		}
	}

	// abre el formulario de editar con datos del paciente seleccionado 
	protected void actionPerformedBtnEditar(ActionEvent e) {
		// obtener paciente de la fila seleccionada en la tabla
		Paciente paciente = obtenerPaciente();
		if (paciente != null) {
			// abrir formulario en modo editar
			FormularioPaciente ventana = new FormularioPaciente("editar");

			// llenar formulario con datos del paciente
			ventana.llenarFormulario(paciente);
			
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

	// elimina el paciente seleccionado
	protected void actionPerformedBtnEliminar(ActionEvent e) {
		// obtener paciente de la fila seleccionada en la tabla
		Paciente paciente = obtenerPaciente();
		if (paciente != null) {
			// mostrar un dialogo de confirmación antes de eliminarlo
			int confirmar = JOptionPane.showConfirmDialog(
				this, 
				"¿Está seguro que quiere borrar este paciente?", 
				"Confirmar Eliminación", 
				JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE
			);

			if (confirmar == 0) {
				// validar si existen citas futuras
				ArrayList<Cita> citasFuturas = Principal.getArrCitas().buscarFuturasPorPaciente(paciente.getCodPaciente());
				if (citasFuturas != null) {
					String msg = "El paciente no puede ser eliminado porque tiene " + citasFuturas.size();
					if (citasFuturas.size() == 1) msg += " cita futura";
					else msg += " citas futuras";

					JOptionPane.showMessageDialog(this, msg, "Error de validación", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				// eliminar las citas relacionadas a este paciente
				ArrayList<Cita> citas = Principal.getArrCitas().buscarCodPaciente(paciente.getCodPaciente());
				if (citas != null)
					for (int i = 0; i < citas.size(); i++)
						Principal.getArrCitas().eliminar(citas.get(i));
				
				// eliminar el paciente
				arr.eliminar(paciente);
				
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
		
		// iterar el arreglo y llenar la tabla con datos del paciente
		for (int i = 0; i < arr.tamano(); i++) {
			// crear fila con datos del paciente
			Object[] fila = {
				arr.obtener(i).getCodPaciente(),
				arr.obtener(i).getNombres(),
				arr.obtener(i).getApellidos(),
				arr.obtener(i).getDni(),
				arr.obtener(i).getEdad(),
				arr.obtener(i).getCelular(),
				arr.obtener(i).getCorreo(),
				Paciente.estados[arr.obtener(i).getEstado()] // mostrar el label del estado
			};
			
			// agregar fila
			modelo.addRow(fila);
		}
	}
	
	// obtiene el paciente seleccionado de la tabla por su índice
	private Paciente obtenerPaciente() {
		// obtener el índice de la fila seleccionada
		int indice = tblTabla.getSelectedRow();
		if (indice != -1) {
			// obtener paciente por indice
			return arr.obtener(indice);
		}
		return null;
	}
}
