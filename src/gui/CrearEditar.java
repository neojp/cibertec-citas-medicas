package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Medico;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Dimension;

import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CrearEditar extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private JTextField txtCodigo, txtNombre, txtNombres, txtApellidos, txtDNI, txtEdad, txtPiso,
					txtEspecialidad, txtCMP, txtCelular, txtCorreo, txtUbicacion, txtCapacidad;
	
	private JComboBox<String> cboEspecialidad, cboEstado;
	
	private JButton btnAgregar, btnCancelar, btnEditar;
	private JPanel buttonPane;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDialog.setDefaultLookAndFeelDecorated(true);
			CrearEditar dialog = new CrearEditar("editar", "paciente");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CrearEditar(String action, String type) {
		boolean esPaciente = type == "paciente";
		boolean esMedico = type == "medico";
		boolean esConsultorio = type == "consultorio";
		
		setTitle(action.toUpperCase() + " " + type.toUpperCase());
		setBounds(100, 100, 450, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		
		// creacion de filas
		txtCodigo = new JTextField(15);
		contentPanel.add(crearFila("Código " + type + ":", txtCodigo));
		contentPanel.add(Box.createVerticalStrut(8));
		
		if (esPaciente || esMedico) {
			txtNombres = new JTextField(15);
			contentPanel.add(crearFila("Nombres", txtNombres));
			contentPanel.add(Box.createVerticalStrut(8));
			
			txtApellidos = new JTextField(15);
			contentPanel.add(crearFila("Apellidos", txtApellidos));
			contentPanel.add(Box.createVerticalStrut(8));
		}
		
		if (esPaciente) {
			txtDNI = new JTextField(15);
			contentPanel.add(crearFila("DNI", txtDNI));
			contentPanel.add(Box.createVerticalStrut(8));
			
			if (action == "editar") {
				txtDNI.setEditable(false);
			}
			
			txtEdad = new JTextField(15);
			contentPanel.add(crearFila("Edad:", txtEdad));
			contentPanel.add(Box.createVerticalStrut(8));
			
			txtCelular = new JTextField(15);
			contentPanel.add(crearFila("Celular:", txtCelular));
			contentPanel.add(Box.createVerticalStrut(8));

			txtCorreo = new JTextField(15);
			contentPanel.add(crearFila("Correo:", txtCorreo));
			contentPanel.add(Box.createVerticalStrut(8));
			
		}
		
		if (esMedico) {
			txtEspecialidad = new JTextField(15);
			cboEspecialidad = new JComboBox<String>(Medico.especialidades);

			contentPanel.add(crearFila("Especialidad:", cboEspecialidad));
			contentPanel.add(Box.createVerticalStrut(8));

			txtCMP = new JTextField(15);
			contentPanel.add(crearFila("CMP:", txtCMP));
			contentPanel.add(Box.createVerticalStrut(8));
		}
		
		if (esConsultorio) {
			txtNombre = new JTextField(15);
			contentPanel.add(crearFila("Nombre:", txtNombre));
			contentPanel.add(Box.createVerticalStrut(8));

			txtPiso = new JTextField(15);
			contentPanel.add(crearFila("Piso:", txtPiso));
			contentPanel.add(Box.createVerticalStrut(8));
			
			txtUbicacion = new JTextField(15);
			contentPanel.add(crearFila("Ubicación:", txtUbicacion));
			contentPanel.add(Box.createVerticalStrut(8));

			txtCapacidad = new JTextField(15);
			contentPanel.add(crearFila("Capacidad:", txtCapacidad));
			contentPanel.add(Box.createVerticalStrut(8));
		}
		
		cboEstado = new JComboBox<String>(new String[] {"Activo", "Inactivo"});
		contentPanel.add(crearFila("Estado:", cboEstado));
		contentPanel.add(Box.createVerticalStrut(8));
		
		
		buttonPane = new JPanel();
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
		buttonPane.setBorder(new EmptyBorder(10,5,5,10));
		
		if(action == "agregar") {
			btnAgregar = new JButton("AGREGAR");
			btnAgregar.setActionCommand("AGREGAR");
			btnAgregar.setBackground(new Color(0,64,128));
			btnAgregar.setForeground(new Color(255,255,255));
			btnAgregar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
			buttonPane.add(btnAgregar);
			getRootPane().setDefaultButton(btnAgregar);
		} else {
			btnEditar = new JButton("GUARDAR");
			btnEditar.setActionCommand("GUARDAR");
			btnEditar.setBackground(new Color(0,64,128));
			btnEditar.setForeground(new Color(255,255,255));
			btnEditar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
			buttonPane.add(btnEditar);
			getRootPane().setDefaultButton(btnEditar);
		}

		buttonPane.add(Box.createHorizontalStrut(10));

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(this);
		btnCancelar.setActionCommand("CANCELAR");
		btnCancelar.setBackground(new Color(128,64,64));
		btnCancelar.setForeground(new Color(255,255,255));
		btnCancelar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
		buttonPane.add(btnCancelar);


	}
	
	private JPanel crearFila(String label, JTextField campo) {
		JPanel fila = new JPanel();
		fila.setLayout(new BoxLayout(fila, BoxLayout.X_AXIS));
		
		// Se define el alto y largo de los labels.
		JLabel lbl = new JLabel(label);
		lbl.setPreferredSize(new Dimension(200,25));
		lbl.setMinimumSize(new Dimension(200,26));
		lbl.setMaximumSize(new Dimension(200,25));
		
		// Se define el alto del textfield
		campo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
		campo.setVisible(true);
		
		
		fila.add(lbl);
		fila.add(Box.createHorizontalStrut(10));
		fila.add(campo);
		
		fila.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
		
		return fila;
	}
	
	private JPanel crearFila(String label, JComboBox<String> campo) {
		JPanel fila = new JPanel();
		fila.setLayout(new BoxLayout(fila, BoxLayout.X_AXIS));
		
		// Se define el alto y largo de los labels.
		JLabel lbl = new JLabel(label);
		lbl.setPreferredSize(new Dimension(200,25));
		lbl.setMinimumSize(new Dimension(200,26));
		lbl.setMaximumSize(new Dimension(200,25));
		
		// Se define el alto del textfield
		campo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
		campo.setVisible(true);
		
		
		fila.add(lbl);
		fila.add(Box.createHorizontalStrut(10));
		fila.add(campo);
		
		fila.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
		
		return fila;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancelar) {
			actionPerformedBtnCancelar(e);
		}
	}
	protected void actionPerformedBtnCancelar(ActionEvent e) {
		this.dispose();
	}
}
