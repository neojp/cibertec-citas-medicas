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

public class FormularioPaciente extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JLabel lblNewLabel;
	private JLabel lblNombres;
	private JTextField textField_1;
	private JLabel lblNewLabel_2;
	private JTextField textField_2;
	private JLabel lblNewLabel_3;
	private JTextField textField_3;
	private JLabel lblNewLabel_4;
	private JTextField textField_4;
	private JLabel lblNewLabel_1;
	private JTextField textField_5;
	private JLabel lblNewLabel_5;
	private JTextField textField_6;
	private JLabel lblNewLabel_6;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FormularioPaciente dialog = new FormularioPaciente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FormularioPaciente() {
		setTitle("Paciente");
		setBounds(100, 100, 450, 321);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(88, 11, 86, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		lblNewLabel = new JLabel("Código");
		lblNewLabel.setBounds(10, 14, 68, 14);
		contentPanel.add(lblNewLabel);
		
		lblNombres = new JLabel("Nombres");
		lblNombres.setBounds(10, 45, 68, 14);
		contentPanel.add(lblNombres);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(88, 42, 86, 20);
		contentPanel.add(textField_1);
		
		lblNewLabel_2 = new JLabel("Apellidos");
		lblNewLabel_2.setBounds(10, 73, 68, 14);
		contentPanel.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(88, 70, 86, 20);
		contentPanel.add(textField_2);
		
		lblNewLabel_3 = new JLabel("DNI");
		lblNewLabel_3.setBounds(10, 101, 68, 14);
		contentPanel.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(88, 98, 86, 20);
		contentPanel.add(textField_3);
		
		lblNewLabel_4 = new JLabel("Edad");
		lblNewLabel_4.setBounds(10, 129, 68, 14);
		contentPanel.add(lblNewLabel_4);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(88, 126, 86, 20);
		contentPanel.add(textField_4);
		
		lblNewLabel_1 = new JLabel("Celular");
		lblNewLabel_1.setBounds(10, 157, 68, 14);
		contentPanel.add(lblNewLabel_1);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(88, 154, 86, 20);
		contentPanel.add(textField_5);
		
		lblNewLabel_5 = new JLabel("Correo");
		lblNewLabel_5.setBounds(10, 185, 68, 14);
		contentPanel.add(lblNewLabel_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(88, 182, 86, 20);
		contentPanel.add(textField_6);
		
		lblNewLabel_6 = new JLabel("Estado");
		lblNewLabel_6.setBounds(10, 213, 68, 14);
		contentPanel.add(lblNewLabel_6);
		
		comboBox = new JComboBox();
		comboBox.setBounds(88, 213, 86, 22);
		contentPanel.add(comboBox);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
