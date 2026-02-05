package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

public class MantenimientoConsultorio extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton btnNewButton;
	private JButton btnEditar;
	private JButton btnEditar_1;
	private JButton btnEditar_2;
	private JButton btnBuscarPorCodigo;
	private JLabel lblNewLabel;
	private JSeparator separator;
	private JScrollPane scrollPane;
	private JButton btnBuscarPorCodigo_1;
	private JLabel lblNewLabel_1;
	private JTable table;

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
		setTitle("Mantenimiento - Medico");
		setBounds(100, 100, 608, 387);
		getContentPane().setLayout(null);
		
		btnNewButton = new JButton("Agregar Nuevo");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(10, 11, 138, 23);
		getContentPane().add(btnNewButton);
		
		btnEditar = new JButton("Consultar");
		btnEditar.addActionListener(this);
		btnEditar.setBounds(10, 314, 89, 23);
		getContentPane().add(btnEditar);
		
		btnEditar_1 = new JButton("Editar");
		btnEditar_1.setBounds(109, 314, 89, 23);
		getContentPane().add(btnEditar_1);
		
		btnEditar_2 = new JButton("Eliminar");
		btnEditar_2.setBounds(209, 314, 89, 23);
		getContentPane().add(btnEditar_2);
		
		btnBuscarPorCodigo = new JButton("CMP");
		btnBuscarPorCodigo.addActionListener(this);
		btnBuscarPorCodigo.setBounds(493, 11, 89, 23);
		getContentPane().add(btnBuscarPorCodigo);
		
		lblNewLabel = new JLabel("Opciones de las filas seleccionadas");
		lblNewLabel.setBounds(10, 289, 288, 14);
		getContentPane().add(lblNewLabel);
		
		separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 160));
		separator.setForeground(new Color(255, 0, 128));
		separator.setBounds(10, 49, 411, 0);
		getContentPane().add(separator);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 572, 233);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Código", "Nombre", "Piso", "Ubicación", "Capacidad", "Estado"
			}
		));
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		
		btnBuscarPorCodigo_1 = new JButton("Código");
		btnBuscarPorCodigo_1.setBounds(394, 11, 89, 23);
		getContentPane().add(btnBuscarPorCodigo_1);
		
		lblNewLabel_1 = new JLabel("Buscar por:");
		lblNewLabel_1.setBounds(332, 15, 58, 14);
		getContentPane().add(lblNewLabel_1);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBuscarPorCodigo) {
			actionPerformedBtnBuscarPorCodigo(e);
		}
		if (e.getSource() == btnEditar) {
			actionPerformedBtnEditar(e);
		}
		if (e.getSource() == btnNewButton) {
			actionPerformedBtnNewButton(e);
		}
	}
	protected void actionPerformedBtnNewButton(ActionEvent e) {
	}
	protected void actionPerformedBtnEditar(ActionEvent e) {
	}
	protected void actionPerformedBtnBuscarPorCodigo(ActionEvent e) {
	}
}
