package gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import arreglo.ArregloPaciente;

import javax.swing.SwingConstants;

public class MantenimientoPaciente extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton btnNuevo;
	private JButton btnConsultar;
	private JButton btnEditar;
	private JButton btnEliminar;
	private JButton btnBuscarDNI;
	private JLabel lblOpciones;
	private JScrollPane scrollPane;
	private JButton btnBuscarCodigo;
	private JLabel lblBuscar;
	private JTable tblTabla;
	private DefaultTableModel modelo;
	private ArregloPaciente arr = new ArregloPaciente();

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
		setTitle("Mantenimiento - Paciente");
		setBounds(100, 100, 608, 387);
		getContentPane().setLayout(null);

		btnNuevo = new JButton("Agregar Nuevo");
		btnNuevo.addActionListener(this);
		btnNuevo.setBounds(10, 11, 138, 23);
		getContentPane().add(btnNuevo);

		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(this);
		btnConsultar.setBounds(10, 314, 89, 23);
		getContentPane().add(btnConsultar);

		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(this);

		btnEditar.setBounds(109, 314, 89, 23);
		getContentPane().add(btnEditar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(209, 314, 89, 23);
		getContentPane().add(btnEliminar);

		btnBuscarDNI = new JButton("DNI");
		btnBuscarDNI.addActionListener(this);
		btnBuscarDNI.setBounds(493, 11, 89, 23);
		getContentPane().add(btnBuscarDNI);

		lblOpciones = new JLabel("Opciones de las filas seleccionadas");
		lblOpciones.setBounds(10, 289, 288, 14);
		getContentPane().add(lblOpciones);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 572, 233);
		getContentPane().add(scrollPane);

		modelo = new DefaultTableModel(new Object[][] {},
				new String[] { "Código", "Nombres", "Apellidos", "DNI", "Edad", "Celular", "Correo", "Estado" });
		tblTabla = new JTable();
		tblTabla.setModel(modelo);
		tblTabla.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblTabla);

		btnBuscarCodigo = new JButton("Código");
		btnBuscarCodigo.addActionListener(this);
		btnBuscarCodigo.setBounds(394, 11, 89, 23);
		getContentPane().add(btnBuscarCodigo);

		lblBuscar = new JLabel("Buscar por:");
		lblBuscar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBuscar.setBounds(321, 15, 69, 14);
		getContentPane().add(lblBuscar);
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
		if (e.getSource() == btnConsultar) {
			actionPerformedBtnConsultar(e);
		}
		if (e.getSource() == btnNuevo) {
			actionPerformedBtnNuevo(e);
		}
	}

	protected void actionPerformedBtnNuevo(ActionEvent e) {
		CrearEditar creareditar = new CrearEditar("agregar", "paciente");
		creareditar.setLocationRelativeTo(this);
		creareditar.setModal(true);
		creareditar.setVisible(true);
	}

	protected void actionPerformedBtnConsultar(ActionEvent e) {
		System.out.println("Consultar");
		modelo.setRowCount(0);
		for (int i = 0; i < arr.tamano(); i++) {
			Object[] fila = {
					arr.obtener(i).getCodPaciente(),
					arr.obtener(i).getNombres(),
					arr.obtener(i).getApellidos(),
					arr.obtener(i).getDni(),
					arr.obtener(i).getEdad(),
					arr.obtener(i).getCelular(),
					arr.obtener(i).getCorreo(),
					arr.obtener(i).getEstado()
			};
			modelo.addRow(fila);
		}
	}

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

	protected void actionPerformedBtnEditar(ActionEvent e) {
		CrearEditar creareditar = new CrearEditar("editar", "paciente");
		creareditar.setLocationRelativeTo(this);
		creareditar.setModal(true);
		creareditar.setVisible(true);
	}

	protected void actionPerformedBtnEliminar(ActionEvent e) {
	}
}
