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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormularioCita extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNumCita;
	private JLabel lblCita;
	private JLabel lblPaciente;
	private JTextField txtPaciente;
	private JLabel lblMedico;
	private JTextField txtMedico;
	private JLabel lblConsultorio;
	private JTextField txtConsultorio;
	private JLabel lblFecha;
	private JTextField txtFecha;
	private JLabel lblHora;
	private JTextField txtHora;
	private JLabel lblEstado;
	private JTextField txtMotivo;
	private JLabel lblMotivo;
	private JComboBox cboEstado;
	private JButton btnCancelar;
	private JButton btnAceptar;

	/**
	 * Create the dialog.
	 */
	public FormularioCita() {
		setTitle("Cita");
		setBounds(100, 100, 368, 321);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		txtNumCita = new JTextField();
		txtNumCita.setBounds(157, 11, 186, 20);
		contentPanel.add(txtNumCita);
		txtNumCita.setColumns(10);
		
		lblCita = new JLabel("Número de Cita:");
		lblCita.setBounds(10, 14, 137, 14);
		contentPanel.add(lblCita);
		
		lblPaciente = new JLabel("Codigo de Paciente:");
		lblPaciente.setBounds(10, 45, 137, 14);
		contentPanel.add(lblPaciente);
		
		txtPaciente = new JTextField();
		txtPaciente.setColumns(10);
		txtPaciente.setBounds(157, 42, 186, 20);
		contentPanel.add(txtPaciente);
		
		lblMedico = new JLabel("Código de Médico:");
		lblMedico.setBounds(10, 73, 137, 14);
		contentPanel.add(lblMedico);
		
		txtMedico = new JTextField();
		txtMedico.setColumns(10);
		txtMedico.setBounds(157, 70, 186, 20);
		contentPanel.add(txtMedico);
		
		lblConsultorio = new JLabel("Código de Consultorio:");
		lblConsultorio.setBounds(10, 101, 137, 14);
		contentPanel.add(lblConsultorio);
		
		txtConsultorio = new JTextField();
		txtConsultorio.setColumns(10);
		txtConsultorio.setBounds(157, 98, 186, 20);
		contentPanel.add(txtConsultorio);
		
		lblFecha = new JLabel("Fecha (dd/mm/aa):");
		lblFecha.setBounds(10, 129, 137, 14);
		contentPanel.add(lblFecha);
		
		txtFecha = new JTextField();
		txtFecha.setColumns(10);
		txtFecha.setBounds(157, 126, 186, 20);
		contentPanel.add(txtFecha);
		
		lblHora = new JLabel("Hora (hh:mm):");
		lblHora.setBounds(10, 157, 137, 14);
		contentPanel.add(lblHora);
		
		txtHora = new JTextField();
		txtHora.setColumns(10);
		txtHora.setBounds(157, 154, 186, 20);
		contentPanel.add(txtHora);
		
		lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(10, 185, 137, 14);
		contentPanel.add(lblEstado);
		
		txtMotivo = new JTextField();
		txtMotivo.setColumns(10);
		txtMotivo.setBounds(157, 210, 186, 20);
		contentPanel.add(txtMotivo);
		
		lblMotivo = new JLabel("Motivo:");
		lblMotivo.setBounds(10, 213, 137, 14);
		contentPanel.add(lblMotivo);
		
		cboEstado = new JComboBox();
		cboEstado.setBounds(157, 181, 186, 22);
		contentPanel.add(cboEstado);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnAceptar = new JButton("Aceptar");
				btnAceptar.addActionListener(this);
				btnAceptar.setActionCommand("OK");
				buttonPane.add(btnAceptar);
				getRootPane().setDefaultButton(btnAceptar);
			}
			{
				btnCancelar = new JButton("Cancelar");
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
