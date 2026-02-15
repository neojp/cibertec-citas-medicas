package gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
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
		
		tblTabla = new JTable();
		tblTabla.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Nombres", "Apellidos", "Especialidad", "CMP", "Estado"
			}
		));
		tblTabla.setFillsViewportHeight(true);
		scp.setViewportView(tblTabla);
		
		btnBuscarCodigo = new JButton("Código");
		btnBuscarCodigo.addActionListener(this);
		btnBuscarCodigo.setBounds(394, 11, 89, 23);
		getContentPane().add(btnBuscarCodigo);
		
		lblBuscar = new JLabel("Buscar por:");
		lblBuscar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBuscar.setBounds(323, 15, 66, 14);
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
		if (e.getSource() == btnBuscarNombre) {
			actionPerformedBtnBuscarNombre(e);
		}
		if (e.getSource() == btnNuevo) {
			actionPerformedBtnNuevo(e);
		}
	}
	protected void actionPerformedBtnNuevo(ActionEvent e) {
		// codigo por Braulio
		// CrearEditar creareditar = new CrearEditar("agregar", "medico");
		// creareditar.setLocationRelativeTo(this);
		// creareditar.setModal(true);
		// creareditar.setVisible(true);
		
		// codigo por Javier
		Frm_AgregarMedico ventana = new Frm_AgregarMedico();
		ventana.setLocationRelativeTo(this);
		ventana.setModal(true);
		ventana.setVisible(true);
		
		// codigo por Joan
		// FormularioMedico ventana = new FormularioMedico();
		// ventana.setLocationRelativeTo(this);
		// ventana.setModal(true);
		// ventana.setVisible(true);
	}
	protected void actionPerformedBtnConsultar(ActionEvent e) {
	}
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
	protected void actionPerformedBtnEliminar(ActionEvent e) {
	}
	protected void actionPerformedBtnEditar(ActionEvent e) {
		FormularioMedico ventana = new FormularioMedico("editar");
		ventana.setLocationRelativeTo(this);
		ventana.setModal(true);
		ventana.setVisible(true);
	}
}
