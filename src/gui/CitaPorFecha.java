package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class CitaPorFecha extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JLabel lblHoras;
	private JScrollPane scrollPane;
	private JTextField txtS;
	private JLabel lblFecha;
	private JTextField txtCodigo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CitaPorFecha dialog = new CitaPorFecha();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CitaPorFecha() {
		setTitle("Citas por Fecha");
		setBounds(100, 100, 450, 365);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(146, 41, 86, 20);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		
		lblHoras = new JLabel("Hora (hh:mm):");
		lblHoras.setBounds(20, 45, 116, 14);
		contentPanel.add(lblHoras);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 74, 375, 199);
		contentPanel.add(scrollPane);
		
		txtS = new JTextField();
		scrollPane.setViewportView(txtS);
		txtS.setColumns(10);
		{
			JButton btnBuscar = new JButton("Buscar");
			btnBuscar.setBounds(315, 10, 86, 23);
			contentPanel.add(btnBuscar);
			btnBuscar.setActionCommand("OK");
			getRootPane().setDefaultButton(btnBuscar);
		}
		
		lblFecha = new JLabel("Fecha (dd/mm/aa):");
		lblFecha.setBounds(20, 14, 116, 14);
		contentPanel.add(lblFecha);
		
		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(146, 10, 86, 20);
		contentPanel.add(txtCodigo);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}
}
