package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormularioBuscarCMP extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCMP;
	private JLabel lblCMP;
	private JButton btnBuscar;
	
	private Boolean empezarBusqueda = false;

	/**
	 * Create the dialog.
	 */
	public FormularioBuscarCMP() {
		setTitle("Buscar por CMP");
		setBounds(100, 100, 198, 110);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		txtCMP = new JTextField();
		txtCMP.setBounds(88, 11, 86, 20);
		contentPanel.add(txtCMP);
		txtCMP.setColumns(10);
		
		lblCMP = new JLabel("CMP");
		lblCMP.setLabelFor(txtCMP);
		lblCMP.setBounds(10, 14, 68, 14);
		contentPanel.add(lblCMP);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnBuscar = new JButton("Buscar");
				btnBuscar.setBackground(new Color(64,64,128));
				btnBuscar.setForeground(new Color(255,255,255));
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
		System.out.println("Buscar -> " + leerCMP());
	}
	
	public String leerCMP() {
		return txtCMP.getText();
	}
	
	public Boolean getEmpezarBusqueda() {
		return empezarBusqueda;
	}
}
