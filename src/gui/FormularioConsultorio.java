package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Cita;
import clases.Consultorio;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class FormularioConsultorio extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodConsultorio;
	private JLabel lblCodConsultorio;
	private JLabel lblNombre;
	private JLabel lblPiso;
	private JLabel lblUbicacion;
	private JLabel lblCapacidad;
	private JLabel lblEstado;
	private JComboBox cboEstado;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JSpinner spnCapacidad;
	private JTextField txtNombre;
	private JSpinner spnPiso;
	private JTextField txtUbicacion;

	/**
	 * Create the dialog.
	 */
	public FormularioConsultorio() {
		this("agregar");
	}
	/**
	 * 
	 * @param action "agregar" "editar"
	 */
	public FormularioConsultorio(String action) {
		setTitle("Consultorio");
		setBounds(100, 100, 368, 261);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		txtCodConsultorio = new JTextField();
		txtCodConsultorio.setEditable(false);
		txtCodConsultorio.setBounds(157, 11, 186, 20);
		contentPanel.add(txtCodConsultorio);
		txtCodConsultorio.setColumns(10);
		
		lblCodConsultorio = new JLabel("Código de Consultorio:");
		lblCodConsultorio.setBounds(10, 14, 137, 14);
		contentPanel.add(lblCodConsultorio);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 45, 137, 14);
		contentPanel.add(lblNombre);
		
		lblPiso = new JLabel("Piso:");
		lblPiso.setBounds(10, 73, 137, 14);
		contentPanel.add(lblPiso);
		
		lblUbicacion = new JLabel("Consultorio:");
		lblUbicacion.setBounds(10, 101, 137, 14);
		contentPanel.add(lblUbicacion);
		
		lblCapacidad = new JLabel("Capacidad:");
		lblCapacidad.setBounds(10, 129, 137, 14);
		contentPanel.add(lblCapacidad);
		
		lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(10, 158, 137, 14);
		contentPanel.add(lblEstado);
		
		cboEstado = new JComboBox();
		lblEstado.setLabelFor(cboEstado);
		cboEstado.setModel(new DefaultComboBoxModel(Consultorio.estados));
		cboEstado.setBounds(157, 154, 186, 22);
		contentPanel.add(cboEstado);
		
		spnCapacidad = new JSpinner();
		spnCapacidad.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		spnCapacidad.setBounds(157, 126, 45, 20);
		contentPanel.add(spnCapacidad);
		
		txtNombre = new JTextField();
		lblNombre.setLabelFor(txtNombre);
		txtNombre.setColumns(10);
		txtNombre.setBounds(157, 42, 186, 20);
		contentPanel.add(txtNombre);
		
		spnPiso = new JSpinner();
		lblPiso.setLabelFor(spnPiso);
		spnPiso.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		spnPiso.setBounds(157, 70, 45, 20);
		contentPanel.add(spnPiso);
		
		txtUbicacion = new JTextField();
		lblUbicacion.setLabelFor(txtUbicacion);
		txtUbicacion.setColumns(10);
		txtUbicacion.setBounds(157, 98, 186, 20);
		contentPanel.add(txtUbicacion);
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
