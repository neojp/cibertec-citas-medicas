package gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

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
		scrollPane.setBounds(10, 45, 572, 270);
		getContentPane().add(scrollPane);
		
		tblTabla = new JTable();
		tblTabla.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Código", "Nombres", "Apellidos", "DNI", "Edad", "Celular", "Correo", "Estado"
			}
		));
		tblTabla.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblTabla);
		
		btnBuscarCodigo = new JButton("Código");
		btnBuscarCodigo.addActionListener(this);
		btnBuscarCodigo.setBounds(394, 11, 89, 23);
		getContentPane().add(btnBuscarCodigo);
		
		lblBuscar = new JLabel("Buscar por:");
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
	}
	protected void actionPerformedBtnConsultar(ActionEvent e) {
	}
	protected void actionPerformedBtnBuscarDNI(ActionEvent e) {
	}
	protected void actionPerformedBtnBuscarCodigo(ActionEvent e) {
	}
	protected void actionPerformedBtnEditar(ActionEvent e) {
	}
	protected void actionPerformedBtnEliminar(ActionEvent e) {
	}
}
