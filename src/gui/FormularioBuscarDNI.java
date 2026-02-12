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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormularioBuscarDNI extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtDNI;
	private JLabel lblDNI;
	private JButton btnBuscar;
	
	private Boolean empezarBusqueda = false;

	/**
	 * Create the dialog.
	 */
	public FormularioBuscarDNI() {
		setTitle("Buscar por DNI");
		setBounds(100, 100, 198, 110);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		txtDNI = new JTextField();
		txtDNI.setBounds(88, 11, 86, 20);
		contentPanel.add(txtDNI);
		txtDNI.setColumns(10);
		
		lblDNI = new JLabel("DNI");
		lblDNI.setLabelFor(txtDNI);
		lblDNI.setBounds(10, 14, 68, 14);
		contentPanel.add(lblDNI);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnBuscar = new JButton("Buscar");
				btnBuscar.addActionListener(this);
				btnBuscar.setActionCommand("OK");
				buttonPane.add(btnBuscar);
				getRootPane().setDefaultButton(btnBuscar);
			}
		}
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(e);
		}
	}
	protected void actionPerformedBtnBuscar(ActionEvent e) {
		empezarBusqueda = true;
		setVisible(false);
		System.out.println("Buscar -> " + leerDNI());
	}
	
	public String leerDNI() {
		return txtDNI.getText();
	}
	
	public Boolean getEmpezarBusqueda() {
		return empezarBusqueda;
	}
}
