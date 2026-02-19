package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Consultorio;
import clases.Paciente;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	
	// variables privadas
	private Paciente paciente;
	private Boolean isSuccess = false;
	private String action = "agregar";

	/**
	 * Constructor por defecto que inicializa el formulario en modo agregar.
	 */
	public FormularioPaciente() {
		this("agregar");
	}

	/**
	 * Muestra una ventana modal con un formulario para el Paciente.
	 * * @param action El tipo de operación a realizar. 
	 * Valores permitidos: "agregar" o "editar".
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
		
		// agregar código autogenerado en el formulario
		txtCodPaciente.setText(Paciente.generarCodPaciente() + "");
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAceptar) {
			actionPerformedBtnAceptar(e);
		}
		if (e.getSource() == btnCancelar) {
			actionPerformedBtnCancelar(e);
		}
	}
	protected void actionPerformedBtnCancelar(ActionEvent e) {
		// al presionar el botón cancelar se ignora todo y se cierra la ventana
		dispose();
	}
	protected void actionPerformedBtnAceptar(ActionEvent e) {
		// validar formulario y detener el código acá si hay un error
		if (!validarFormulario()) {
			return;
		}
		
		// usar isSuccess para avisar a la ventana padre que se presionó este botón
		this.isSuccess = true;
		
		// si la acción es agregar, crea un nuevo objeto de consultorio
		if ("agregar".equals(action))
			this.paciente = new Paciente();
		
		// actualizar el objeto consultorio con los datos del formulario
		this.paciente.setNombres(leerNombres());
		this.paciente.setApellidos(leerApellidos());
		this.paciente.setEdad(leerEdad());
		this.paciente.setCelular(leerCelular());
		this.paciente.setCorreo(leerCorreo());
		this.paciente.setEstado(cboEstado.getSelectedIndex());
		
		// solo se edita el DNI al agregar
		if ("agregar".equals(action))
			this.paciente.setDni(leerDni());
		
		// ocultar la ventana modal y continuar con el código de la ventana padre
		setVisible(false);
	}
	
	// getters
	public Paciente getPaciente() {
		return this.paciente;
	}
	public boolean getSuccess() {
		return this.isSuccess;
	}
	
	// método público para llenar el formulario cuando se esta editando
	public void llenarFormulario(Paciente x) {
		// agregar paciente a variable global privada
		this.paciente = x;

		// llenar campos del formulario con datos de paciente
		txtCodPaciente.setText(paciente.getCodPaciente() + ""); // convertir código en String
		txtNombres.setText(paciente.getNombres());
		txtApellidos.setText(paciente.getApellidos());
		txtDni.setText(paciente.getDni());
		spnEdad.setValue(paciente.getEdad());
		txtCelular.setText(paciente.getCelular());
		txtCorreo.setText(paciente.getCorreo());
		cboEstado.setSelectedIndex(paciente.getEstado());
	}
	
	// validar campos vacíos en formulario
	private boolean validarFormulario() {
		try {
			// validar si los campos estan vacíos 
			if (
				leerNombres().isEmpty() ||
				leerApellidos().isEmpty() ||
				leerDni().isEmpty() ||
				leerCelular().isEmpty() ||
				leerCorreo().isEmpty()
			)
				throw new Exception("Campo no puede estar vacío");
			
			// si es un nuevo paciente, validar que el DNI sea único
			if (action.equals("agregar")) {
				// validar solo números
				if (!leerDni().matches("\\d+"))
					throw new Exception("DNI debe ser solo números");
				
				// validar que sean 8 números
				if (leerDni().length() != 8)
					throw new Exception("DNI debe ser 8 números");

				// validar que sea único
				if (!Paciente.validarDniUnico(leerDni()))
					throw new Exception("DNI debe ser único y no puede ser repetido en la lista");
			}
			
			// validar edad >= 0
			if (!Paciente.validarEdad(leerEdad()))
				throw new Exception("Edad debe ser mayor o igual a 0");
			
			// validar celular (solo permite números, espacios, puntos, guiones y paréntesis)
			if (!leerCelular().matches("^[0-9\\+\\-\\(\\)\\.\\s]+$"))
				throw new Exception("Celular inválido, solo se permiten números, espacios, puntos, guiones y paréntesis");
			
			// validar que el celular tenga al menos 9 números
			if (((String) leerCelular().replaceAll("[^0-9]", "")).length() < 9)
				throw new Exception("Celular debe tener al menos 9 números");
			
			// validar correo (solo se permiten letras, números, puntos, guiones y símbolos de suma)
			if (!leerCorreo().matches("^[a-zA-Z0-9\\+._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z]{2,4}$"))
				throw new Exception("Correo inválido, solo se permiten números, puntos, símbolo de suma y guiones");

		} catch(Exception e) {
			// mostrar error de validación
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error de validación", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	// métodos para leer campos de texto 
	private String leerNombres() {
		return txtNombres.getText().trim();
	}

	private String leerApellidos() {
		return txtApellidos.getText().trim();
	}

	private String leerCorreo() {
		return txtCorreo.getText().trim();
	}

	private String leerCelular() {
		return txtCelular.getText().trim();
	}

	private String leerDni() {
		return txtDni.getText().trim();
	}

	private int leerEdad() {
		return (Integer) spnEdad.getValue();
	}
}
