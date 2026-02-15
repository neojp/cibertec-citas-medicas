package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Cita;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class FormularioCita extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNumCita;
	private JLabel lblCita;
	private JLabel lblPaciente;
	private JLabel lblMedico;
	private JLabel lblConsultorio;
	private JLabel lblFecha;
	private JLabel lblHora;
	private JLabel lblEstado;
	private JLabel lblMotivo;
	private JComboBox cboEstado;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JSpinner spnHoras;
	private JSpinner spnMinutos;
	private JSpinner spnDia;
	private JSpinner spnMes;
	private JSpinner spnAno;
	private JComboBox cboConsultorio;
	private JComboBox cboMedico;
	private JComboBox cboPaciente;
	private JScrollPane scp;
	private JTextArea txtMotivo;

	/**
	 * Create the dialog.
	 */
	public FormularioCita() {
		this("crear");
	}
	/**
	 * 
	 * @param action "crear" "editar"
	 */
	public FormularioCita(String action) {
		setTitle("Cita");
		setBounds(100, 100, 368, 387);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		txtNumCita = new JTextField();
		txtNumCita.setEditable(false);
		txtNumCita.setBounds(157, 11, 186, 20);
		contentPanel.add(txtNumCita);
		txtNumCita.setColumns(10);
		
		lblCita = new JLabel("Número de Cita:");
		lblCita.setBounds(10, 14, 137, 14);
		contentPanel.add(lblCita);
		
		lblPaciente = new JLabel("Paciente:");
		lblPaciente.setBounds(10, 45, 137, 14);
		contentPanel.add(lblPaciente);
		
		lblMedico = new JLabel("Médico:");
		lblMedico.setBounds(10, 73, 137, 14);
		contentPanel.add(lblMedico);
		
		lblConsultorio = new JLabel("Consultorio:");
		lblConsultorio.setBounds(10, 101, 137, 14);
		contentPanel.add(lblConsultorio);
		
		lblFecha = new JLabel("Fecha (dd/mm/aa):");
		lblFecha.setBounds(10, 129, 137, 14);
		contentPanel.add(lblFecha);
		
		lblHora = new JLabel("Hora (hh:mm):");
		lblHora.setBounds(10, 157, 137, 14);
		contentPanel.add(lblHora);
		
		lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(10, 185, 137, 14);
		contentPanel.add(lblEstado);
		
		lblMotivo = new JLabel("Motivo:");
		lblMotivo.setBounds(10, 213, 137, 14);
		contentPanel.add(lblMotivo);
		
		cboEstado = new JComboBox();
		lblEstado.setLabelFor(cboEstado);
		cboEstado.setModel(new DefaultComboBoxModel(Cita.estados));
		cboEstado.setBounds(157, 181, 186, 22);
		contentPanel.add(cboEstado);
		
		spnHoras = new JSpinner();
		spnHoras.setModel(new SpinnerNumberModel(0, 0, 23, 1));
		spnHoras.setBounds(157, 154, 45, 20);
		contentPanel.add(spnHoras);
		
		spnMinutos = new JSpinner();
		spnMinutos.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		spnMinutos.setBounds(212, 154, 45, 20);
		contentPanel.add(spnMinutos);
		
		spnDia = new JSpinner();
		spnDia.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		spnDia.setBounds(157, 126, 45, 20);
		contentPanel.add(spnDia);
		
		spnMes = new JSpinner();
		spnMes.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		spnMes.setBounds(212, 126, 45, 20);
		contentPanel.add(spnMes);
		
		spnAno = new JSpinner();
		spnAno.setModel(new SpinnerNumberModel(26, 26, 30, 1));
		spnAno.setBounds(267, 126, 45, 20);
		contentPanel.add(spnAno);
		
		cboConsultorio = new JComboBox();
		lblConsultorio.setLabelFor(cboConsultorio);
		cboConsultorio.setModel(new DefaultComboBoxModel(new String[] {"Nombre de Consultorio 1", "Nombre de Consultorio 2", "Nombre de Consultorio 3"}));
		cboConsultorio.setBounds(157, 97, 186, 22);
		contentPanel.add(cboConsultorio);
		
		cboMedico = new JComboBox();
		lblMedico.setLabelFor(cboMedico);
		cboMedico.setModel(new DefaultComboBoxModel(new String[] {"Nombre de Médico 1", "Nombre de Médico 2", "Nombre de Médico 3"}));
		cboMedico.setBounds(157, 69, 186, 22);
		contentPanel.add(cboMedico);
		
		cboPaciente = new JComboBox();
		lblPaciente.setLabelFor(cboPaciente);
		cboPaciente.setModel(new DefaultComboBoxModel(new String[] {"Nombre de Paciente 1", "Nombre de Paciente 2", "Nombre de Paciente 3"}));
		cboPaciente.setBounds(157, 41, 186, 22);
		contentPanel.add(cboPaciente);
		
		scp = new JScrollPane();
		scp.setBounds(157, 213, 185, 91);
		contentPanel.add(scp);
		
		txtMotivo = new JTextArea();
		scp.setViewportView(txtMotivo);
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
