package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormularioBuscarCodigo extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JLabel lblCodigo;
	private JButton btnBuscar;
	
	private Boolean empezarBusqueda = false;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			FormularioBuscarCodigo dialog = new FormularioBuscarCodigo();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public FormularioBuscarCodigo() {
		setTitle("Buscar por Código");
		setBounds(100, 100, 280, 95);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblCodigo = new JLabel("Código");
		lblCodigo.setLabelFor(txtCodigo);
		lblCodigo.setBounds(10, 15, 70, 25);
		contentPanel.add(lblCodigo);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(90, 14, 180, 30);
		contentPanel.add(txtCodigo);
		txtCodigo.setColumns(10);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
		buttonPane.setBorder(new EmptyBorder(10,10,10,10));
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBackground(new Color(64,64,128));
		btnBuscar.setForeground(new Color(255,255,255));
		btnBuscar.addActionListener(this);
		btnBuscar.setActionCommand("OK");
		btnBuscar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
		buttonPane.add(btnBuscar);
		
		getContentPane().add(buttonPane,BorderLayout.SOUTH);
		getRootPane().setDefaultButton(btnBuscar);
		
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(e);
		}
	}

	protected void actionPerformedBtnBuscar(ActionEvent e) {
		empezarBusqueda = true;
		setVisible(false);
		System.out.println("Buscar -> " + leerCodigo());
	}
	
	public String leerCodigo() {
		return txtCodigo.getText();
	}
	
	public Boolean getEmpezarBusqueda() {
		return empezarBusqueda;
	}
}
