package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Cita;
import clases.Medico;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

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
	private JComboBox cboEstado;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JComboBox cboEspecialidad;
	private JTextField txtCmp;

	/**
	 * Create the dialog.
	 */
	public FormularioMedico() {
		this("agregar");
	}
	/**
	 * 
	 * @param action "agregar" "editar"
	 */
	public FormularioMedico(String action) {
		setTitle("Médico");
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
		
		cboEstado = new JComboBox();
		lblEstado.setLabelFor(cboEstado);
		cboEstado.setModel(new DefaultComboBoxModel(Medico.estados));
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
		
		cboEspecialidad = new JComboBox();
		cboEspecialidad.setModel(new DefaultComboBoxModel(Medico.especialidades));
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
		setVisible(false);
	}
	protected void actionPerformedBtnAceptar(ActionEvent e) {
		
	}
}
