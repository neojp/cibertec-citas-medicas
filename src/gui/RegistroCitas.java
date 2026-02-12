package gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;

public class RegistroCitas extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton btnNuevo;
	private JButton btnConsultar;
	private JButton btnEditar;
	private JButton btnEliminar;
	private JLabel lblOpciones;
	private JScrollPane scrollPane;
	private JTable tblTabla;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistroCitas dialog = new RegistroCitas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistroCitas() {
		setTitle("Registro de Citas");
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
		
		lblOpciones = new JLabel("Opciones de las filas seleccionadas");
		lblOpciones.setBounds(10, 289, 288, 14);
		getContentPane().add(lblOpciones);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 572, 233);
		getContentPane().add(scrollPane);
		
		tblTabla = new JTable();
		tblTabla.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N\u00FAmero", "Paciente", "M\u00E9dico", "Consultorio", "Fecha", "Hora", "Estado", "Motivo"
			}
		));
		tblTabla.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblTabla);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(e);
		}
		if (e.getSource() == btnEditar) {
			actionPerformedBtnEditar(e);
		}
		if (e.getSource() == btnConsultar) {
			actionPerformedBtnConsultar(e);
		}
		if (e.getSource() == btnNuevo) {
			actionPerformedBtnNuevo(e);
		}
	}
	protected void actionPerformedBtnNuevo(ActionEvent e) {
		FormularioCita ventana = new FormularioCita();
		ventana.setLocationRelativeTo(this);
		ventana.setModal(true);
		ventana.setVisible(true);
	}
	protected void actionPerformedBtnConsultar(ActionEvent e) {
	}
	protected void actionPerformedBtnEditar(ActionEvent e) {
		FormularioCita ventana = new FormularioCita();
		ventana.setLocationRelativeTo(this);
		ventana.setModal(true);
		ventana.setVisible(true);
	}
	protected void actionPerformedBtnEliminar(ActionEvent e) {
	}
}
