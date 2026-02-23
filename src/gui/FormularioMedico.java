package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import clases.Medico;
import arreglo.ArregloMedico;

public class FormularioMedico extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private JTextField txtCodMedico;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtCmp;

	private JComboBox<String> cboEspecialidad;
	private JComboBox<String> cboEstado;

	private JButton btnAceptar;
	private JButton btnCancelar;

	private Medico medico;
	private boolean isSuccess = false;
	private String action = "agregar";

	// Usa el mismo arreglo que el Principal (ajusta si quieres)
	private ArregloMedico arregloMedico = new ArregloMedico();

	public FormularioMedico() {
		this("agregar");
	}

	public FormularioMedico(String action) {
		this.action = action;

		setTitle(action.equals("agregar") ? "Agregar Médico" : "Editar Médico");
		setBounds(100, 100, 370, 270);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());

		contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPanel.setLayout(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		// ===================== CAMPOS =====================

		JLabel lblCod = new JLabel("Código:");
		lblCod.setBounds(10, 15, 120, 20);
		contentPanel.add(lblCod);

		txtCodMedico = new JTextField();
		txtCodMedico.setEditable(false);
		txtCodMedico.setBounds(140, 15, 200, 20);
		contentPanel.add(txtCodMedico);

		JLabel lblNom = new JLabel("Nombres:");
		lblNom.setBounds(10, 45, 120, 20);
		contentPanel.add(lblNom);

		txtNombres = new JTextField();
		txtNombres.setBounds(140, 45, 200, 20);
		contentPanel.add(txtNombres);

		JLabel lblApe = new JLabel("Apellidos:");
		lblApe.setBounds(10, 75, 120, 20);
		contentPanel.add(lblApe);

		txtApellidos = new JTextField();
		txtApellidos.setBounds(140, 75, 200, 20);
		contentPanel.add(txtApellidos);

		JLabel lblEsp = new JLabel("Especialidad:");
		lblEsp.setBounds(10, 105, 120, 20);
		contentPanel.add(lblEsp);

		cboEspecialidad = new JComboBox<>(Medico.especialidades);
		cboEspecialidad.setBounds(140, 105, 200, 22);
		contentPanel.add(cboEspecialidad);

		JLabel lblCmp = new JLabel("CMP:");
		lblCmp.setBounds(10, 135, 120, 20);
		contentPanel.add(lblCmp);

		txtCmp = new JTextField();
		txtCmp.setBounds(140, 135, 200, 20);
		contentPanel.add(txtCmp);

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(10, 165, 120, 20);
		contentPanel.add(lblEstado);

		// ⚠ AQUI SE INICIALIZA ANTES DE USAR setBounds
		cboEstado = new JComboBox<>(Medico.estados);
		cboEstado.setBounds(140, 165, 200, 22);
		contentPanel.add(cboEstado);

		// ===================== BOTONES =====================

		JPanel buttonPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBackground(new Color(0, 64, 128));
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.addActionListener(this);
		buttonPane.add(btnAceptar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(128, 64, 64));
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.addActionListener(this);
		buttonPane.add(btnCancelar);

		// GENERAR CÓDIGO AUTOMÁTICO SOLO SI ES AGREGAR
		if (action.equals("agregar")) {
			txtCodMedico.setText(arregloMedico.proximoCodigo() + "");
		}
	}

	// ===================== PARA MODO EDITAR =====================
	// Lo llamas desde MantenimientoMedico para que solo cargue el código
	public void setCodigoMedico(int codigo) {
		txtCodMedico.setText(String.valueOf(codigo));
		txtCodMedico.setEditable(false); // no se puede cambiar
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAceptar)
			aceptar();
		else
			dispose();
	}

	private void aceptar() {

		if (!validarFormulario())
			return;

		isSuccess = true;

		if (action.equals("agregar")) {
			int codigo = Integer.parseInt(txtCodMedico.getText());
			medico = new Medico(
					codigo,
					leerNombres(),
					leerApellidos(),
					Medico.especialidades[cboEspecialidad.getSelectedIndex()],
					leerCmp(),
					cboEstado.getSelectedIndex()
			);
		} else {
			medico.setNombres(leerNombres());
			medico.setApellidos(leerApellidos());
			medico.setEspecialidad(Medico.especialidades[cboEspecialidad.getSelectedIndex()]);
			medico.setCmp(leerCmp());
			medico.setEstado(cboEstado.getSelectedIndex());
		}

		setVisible(false);
	}

	// (Si algún día quieres llenar todo el formulario para editar, puedes usar esto)
	public void llenarFormulario(Medico m) {
		this.medico = m;

		txtCodMedico.setText(m.getCodMedico() + "");
		txtNombres.setText(m.getNombres());
		txtApellidos.setText(m.getApellidos());
		txtCmp.setText(m.getCmp());
		cboEstado.setSelectedIndex(m.getEstado());

		for (int i = 0; i < Medico.especialidades.length; i++) {
			if (Medico.especialidades[i].equalsIgnoreCase(m.getEspecialidad())) {
				cboEspecialidad.setSelectedIndex(i);
				break;
			}
		}
	}

	private boolean validarFormulario() {
		try {
			if (leerNombres().isEmpty() || leerApellidos().isEmpty())
				throw new Exception("Nombre y Apellidos no pueden estar vacíos.");

			String cmp = leerCmp();
			if (!cmp.matches("\\d{5,6}"))
				throw new Exception("CMP debe tener 5 o 6 números.");

			Medico existente = arregloMedico.buscarPorCmp(cmp);

			if (action.equals("agregar") && existente != null)
				throw new Exception("El CMP ya existe.");

			if (action.equals("editar") &&
				existente != null &&
				existente.getCodMedico() != medico.getCodMedico())
				throw new Exception("El CMP ya pertenece a otro médico.");

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(),
					"Error de validación", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	private String leerNombres() {
		return txtNombres.getText().trim();
	}

	private String leerApellidos() {
		return txtApellidos.getText().trim();
	}

	private String leerCmp() {
		return txtCmp.getText().trim();
	}

	public Medico getMedico() {
		return medico;
	}

	public boolean getSuccess() {
		return isSuccess;
	}
}