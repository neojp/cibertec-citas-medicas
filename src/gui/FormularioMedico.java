package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Consultorio;
import clases.Medico;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class FormularioMedico extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodMedico;
	private JLabel lblCodMedico;
	private JLabel lblNombres;
	private JLabel lblApellidos;
	private JLabel lblEspecialidad;
	private JLabel lblCmp;
	private JLabel lblEstado;
	private JComboBox<String> cboEstado;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JComboBox<String> cboEspecialidad;
	private JTextField txtCmp;
	
	// variables privadas
	private Medico medico;
	private Boolean isSuccess = false;
	private String action = "agregar";

	/**
	 * Constructor por defecto que inicializa el formulario en modo agregar.
	 */
	public FormularioMedico() {
		this("agregar");
	}

	/**
	 * Muestra una ventana modal con un formulario para el Médico.
	 * * @param action El tipo de operación a realizar. 
	 * Valores permitidos: "agregar" o "editar".
	 */
	public FormularioMedico(String action) {
		this.action = action;

		if ("agregar".equals(action))
			setTitle("Agregar Médico");
		else
			setTitle("Editar Médico");

		setBounds(100, 100, 368, 261);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		txtCodMedico = new JTextField();
		txtCodMedico.setEditable(false);
		txtCodMedico.setBounds(157, 11, 186, 20);
		contentPanel.add(txtCodMedico);
		txtCodMedico.setColumns(10);
		
		lblCodMedico = new JLabel("Código de Médico:");
		lblCodMedico.setBounds(10, 14, 137, 14);
		contentPanel.add(lblCodMedico);
		
		lblNombres = new JLabel("Nombres:");
		lblNombres.setBounds(10, 45, 137, 14);
		contentPanel.add(lblNombres);
		
		lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(10, 73, 137, 14);
		contentPanel.add(lblApellidos);
		
		lblEspecialidad = new JLabel("Especialidad:");
		lblEspecialidad.setBounds(10, 101, 137, 14);
		contentPanel.add(lblEspecialidad);
		
		lblCmp = new JLabel("CMP:");
		lblCmp.setBounds(10, 129, 137, 14);
		contentPanel.add(lblCmp);
		
		lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(10, 158, 137, 14);
		contentPanel.add(lblEstado);
		
		cboEstado = new JComboBox<String>();
		lblEstado.setLabelFor(cboEstado);
		cboEstado.setModel(new DefaultComboBoxModel<String>(Medico.estados));
		cboEstado.setBounds(157, 154, 186, 22);
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
		
		cboEspecialidad = new JComboBox<String>();
		cboEspecialidad.setModel(new DefaultComboBoxModel<String>(Medico.especialidades));
		cboEspecialidad.setBounds(157, 97, 186, 22);
		contentPanel.add(cboEspecialidad);
		
		txtCmp = new JTextField();
		txtCmp.setColumns(10);
		txtCmp.setBounds(157, 126, 186, 20);
		contentPanel.add(txtCmp);
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
		txtCodMedico.setText(Medico.generarCodMedico() + "");
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
		
		// si la acción es agregar, crea un nuevo objeto de médico
		if ("agregar".equals(action))
			this.medico = new Medico();
		
		// actualizar el objeto médico con los datos del formulario
		this.medico.setNombres(leerNombres());
		this.medico.setApellidos(leerApellidos());
		this.medico.setEspecialidad(Medico.especialidades[cboEspecialidad.getSelectedIndex()]);
		this.medico.setCmp(leerCmp());
		this.medico.setEstado(cboEstado.getSelectedIndex());
		
		// ocultar la ventana modal y continuar con el código de la ventana padre
		setVisible(false);
	}
	
	// getters
	public Medico getMedico() {
		return this.medico;
	}
	public boolean getSuccess() {
		return this.isSuccess;
	}
	
	// método público para llenar el formulario cuando se esta editando
	public void llenarFormulario(Medico x) {
		// agregar médico a variable global privada
		this.medico = x;

		// llenar campos del formulario con datos de médico
		txtCodMedico.setText(medico.getCodMedico() + ""); // convertir código en String
		txtNombres.setText(medico.getNombres());
		txtApellidos.setText(medico.getApellidos());
		cboEspecialidad.setSelectedIndex(medico.getEspecialidadIndex());
		txtCmp.setText(medico.getCmp());
		cboEstado.setSelectedIndex(medico.getEstado());
	}
	
	// validar campos vacíos en formulario
	private boolean validarFormulario() {
		try {
			// validar si los campos estan vacíos 
			if (
				leerNombres().isEmpty() ||
				leerApellidos().isEmpty()
			)
				throw new Exception("Campo no puede estar vacío");
			
			// validar el cmp si está disponible
			String cmp = leerCmp();
			if (!cmp.isEmpty()) {
				// validar que sea de 5 a 6 caracteres
				if (cmp.length() < 5 || cmp.length() > 6)
					throw new Exception("CMP debe ser de 5 a 6 números");
			
				// validar que sean números
				if (!cmp.matches("\\d+"))
					throw new Exception("CMP debe ser solo números");
				
				// si es un nuevo médico, validar que sea único
				if (action.equals("agregar") && !Medico.validarCmpUnico(cmp)) {
					throw new Exception("CMP debe ser único y no puede ser repetido en la lista");
				}
				
				// si estamos editando, validar que sea único si no es el mismo
				if (action.equals("editar") && !medico.getCmp().equalsIgnoreCase(cmp) && !Medico.validarCmpUnico(cmp)) {
					throw new Exception("CMP debe ser único y no puede ser repetido en la lista");
				}
			}
			
			// TODO: validar si el nombre se repite
		} catch(Exception e) {
			// mostrar error de validación
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error de validación", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	// métodos para leer campos de texto 
	private String leerCmp() {
		return txtCmp.getText().trim();
	}

	private String leerApellidos() {
		return txtApellidos.getText().trim();
	}

	private String leerNombres() {
		return txtNombres.getText().trim();
	}
}
