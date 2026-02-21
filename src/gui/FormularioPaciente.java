package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Paciente;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Color;

public class FormularioPaciente extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodPaciente;
	private JLabel lblCodPaciente;
	private JLabel lblNombres;
	private JLabel lblApellidos;
	private JLabel lblDni;
	private JLabel lblEdad;
	private JLabel lblEstado;
	private JComboBox<String> cboEstado;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtDni;
	private JSpinner spnEdad;
	private JLabel lblCelular;
	private JTextField txtCelular;
	private JLabel lblCorreo;
	private JTextField txtCorreo;
	private boolean isSuccess = false;	
	private Paciente paciente;
	private String action;

	/**
	 * Create the dialog.
	 */
	public FormularioPaciente() {
		this("agregar");
	}
	/**
	 * 
	 * @param action "agregar" "editar"
	 */
	public FormularioPaciente(String action) {
		this.action = action;
		
		if ("agregar".equals(action))
			setTitle("Agregar Paciente");
		else
			setTitle("Editar Paciente");
		
		setBounds(100, 100, 368, 318);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		txtCodPaciente = new JTextField();
		txtCodPaciente.setEditable(false);
		txtCodPaciente.setBounds(157, 11, 186, 20);
		contentPanel.add(txtCodPaciente);
		txtCodPaciente.setColumns(10);
		
		lblCodPaciente = new JLabel("Código de Paciente:");
		lblCodPaciente.setBounds(10, 14, 137, 14);
		contentPanel.add(lblCodPaciente);
		
		lblNombres = new JLabel("Nombres:");
		lblNombres.setBounds(10, 45, 137, 14);
		contentPanel.add(lblNombres);
		
		lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(10, 73, 137, 14);
		contentPanel.add(lblApellidos);
		
		lblDni = new JLabel("DNI:");
		lblDni.setBounds(10, 101, 137, 14);
		contentPanel.add(lblDni);
		
		lblEdad = new JLabel("Edad:");
		lblEdad.setBounds(10, 129, 137, 14);
		contentPanel.add(lblEdad);
		
		lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(10, 214, 137, 14);
		contentPanel.add(lblEstado);
		
		cboEstado = new JComboBox<String>();
		lblEstado.setLabelFor(cboEstado);
		cboEstado.setModel(new DefaultComboBoxModel<String>(Paciente.estados));
		cboEstado.setBounds(157, 210, 186, 22);
		contentPanel.add(cboEstado);
		
		txtNombres = new JTextField();
		lblNombres.setLabelFor(txtNombres);
		txtNombres.setColumns(10);
		txtNombres.setBounds(157, 42, 186, 20);
		contentPanel.add(txtNombres);
		
		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(157, 70, 186, 20);
		contentPanel.add(txtApellidos);
		
		txtDni = new JTextField();
		txtDni.setColumns(10);
		txtDni.setBounds(157, 98, 186, 20);
		if (action == "editar") txtDni.setEditable(false);
		contentPanel.add(txtDni);
		
		spnEdad = new JSpinner();
		spnEdad.setModel(new SpinnerNumberModel(0, 0, 110, 1));
		spnEdad.setBounds(157, 126, 45, 20);
		contentPanel.add(spnEdad);
		
		lblCelular = new JLabel("Celular:");
		lblCelular.setBounds(10, 157, 137, 14);
		contentPanel.add(lblCelular);
		
		txtCelular = new JTextField();
		txtCelular.setColumns(10);
		txtCelular.setBounds(157, 154, 186, 20);
		contentPanel.add(txtCelular);
		
		lblCorreo = new JLabel("Correo:");
		lblCorreo.setBounds(10, 185, 137, 14);
		contentPanel.add(lblCorreo);
		
		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(157, 182, 186, 20);
		contentPanel.add(txtCorreo);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnAceptar = new JButton("Aceptar");
				btnAceptar.setForeground(new Color(255, 255, 255));
				btnAceptar.setBackground(new Color(0, 64, 128));
				btnAceptar.addActionListener(this);
				btnAceptar.setActionCommand("OK");
				buttonPane.add(btnAceptar);
				getRootPane().setDefaultButton(btnAceptar);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.setForeground(new Color(255, 255, 255));
				btnCancelar.setBackground(new Color(128, 64, 64));
				btnCancelar.addActionListener(this);
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		txtCodPaciente.setText(Paciente.genCodPaciente() + "");
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAceptar) {
			actionPerformedBtnAceptar(e);
		}
		if (e.getSource() == btnCancelar) {
			actionPerformedBtnCancelar(e);
		}
	}
	
	public Paciente getPaciente() {
		return paciente;
	}
	
	public void fillWithData(Paciente data) {
		this.paciente = data;
		
		txtCodPaciente.setText(data.getCodPaciente()+"");
		txtNombres.setText(data.getNombres());
		txtApellidos.setText(data.getApellidos());
		txtDni.setText(data.getDni());
		spnEdad.setValue(data.getEdad());
		txtCelular.setText(data.getCelular());
		txtCorreo.setText(data.getCorreo());
		cboEstado.setSelectedIndex(data.getEstado());
	}
	
	public boolean getSuccess() {
		return this.isSuccess;
	}
	
	
	protected void actionPerformedBtnCancelar(ActionEvent e) {
		setVisible(false);
	}
	protected void actionPerformedBtnAceptar(ActionEvent e) {
		setVisible(false);
		
		// avisa que se dio al botón aceptar.
		this.isSuccess = true;
		
		if (action.equals("agregar")) {
			this.paciente = new Paciente();
		}
		
		this.paciente.setNombres(txtNombres.getText());
		this.paciente.setApellidos(txtApellidos.getText());
		this.paciente.setEdad((int) spnEdad.getValue());
		this.paciente.setDni(txtDni.getText());
		this.paciente.setCelular(txtCelular.getText());
		this.paciente.setCorreo(txtCorreo.getText());
		this.paciente.setEstado(cboEstado.getSelectedIndex());
	}
}
