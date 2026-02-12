package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

public class CitasporPaciente extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JLabel lblDNI;
	private JScrollPane scrollPane;
	private JTextField txtS;
	private JLabel lblCodigo;
	private JTextField txtCodigo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CitasporPaciente dialog = new CitasporPaciente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CitasporPaciente() {
		setTitle("Citas por Paciente");
		setBounds(100, 100, 450, 365);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(98, 42, 86, 20);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		
		lblDNI = new JLabel("DNI:");
		lblDNI.setBounds(20, 45, 68, 14);
		contentPanel.add(lblDNI);
		
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
		
		lblCodigo = new JLabel("Código:");
		lblCodigo.setBounds(20, 14, 68, 14);
		contentPanel.add(lblCodigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(98, 11, 86, 20);
		contentPanel.add(txtCodigo);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}
}
