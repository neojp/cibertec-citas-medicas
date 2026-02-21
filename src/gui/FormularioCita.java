package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import arreglo.ArregloCita;
import arreglo.ArregloConsultorio;
import arreglo.ArregloMedico;
import arreglo.ArregloPaciente;
import clases.Cita;
import libreria.CustomComboBoxItem;
import libreria.Libreria;

import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
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
	private JComboBox<String> cboEstado;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JSpinner spnHora;
	private JSpinner spnFecha;
	private JComboBox<CustomComboBoxItem> cboConsultorio;
	private JComboBox<CustomComboBoxItem> cboMedico;
	private JComboBox<CustomComboBoxItem> cboPaciente;
	private JScrollPane scp;
	private JTextArea txtMotivo;
	
	// variables privadas
	private Cita cita;
	private Boolean isSuccess = false;
	private String action = "agregar";

	/**
	 * Create the dialog.
	 */
	public FormularioCita() {
		this("agregar");
	}
	/**
	 * Muestra una ventana modal con un formulario para la cita.
	 * * @param action El tipo de operación a realizar. 
	 * Valores permitidos: "agregar" o "editar".
	 */
	public FormularioCita(String action) {
		this.action = action;

		if ("agregar".equals(action))
			setTitle("Agregar Cita");
		else
			setTitle("Editar Cita");

		setBounds(100, 100, 480, 387);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		txtNumCita = new JTextField();
		txtNumCita.setEditable(false);
		txtNumCita.setBounds(157, 11, 297, 20);
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
		
		lblFecha = new JLabel("Fecha (dd/mm/aaaa):");
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
		
		cboEstado = new JComboBox<String>();
		if (action == "crear") cboEstado.setEnabled(false);
		lblEstado.setLabelFor(cboEstado);
		cboEstado.setModel(new DefaultComboBoxModel<String>(Cita.estados));
		cboEstado.setBounds(157, 181, 297, 22);
		contentPanel.add(cboEstado);
		
		spnHora = new JSpinner();
		lblHora.setLabelFor(spnHora);
		spnHora.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_YEAR));
		
		// cambiar el formato del spinner a hora:minuto
		JSpinner.DateEditor horaEditor = new JSpinner.DateEditor(spnHora, "HH:mm");
		spnHora.setEditor(horaEditor);
				
		spnHora.setBounds(157, 154, 102, 20);
		contentPanel.add(spnHora);
		
		spnFecha = new JSpinner();
		lblFecha.setLabelFor(spnFecha);
		spnFecha.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_YEAR));
		
		// cambiar el formato del spinner a día/mes/año
		JSpinner.DateEditor fechaEditor = new JSpinner.DateEditor(spnFecha, "dd/MM/yyyy");
		spnFecha.setEditor(fechaEditor);
				
		spnFecha.setBounds(157, 126, 102, 20);
		contentPanel.add(spnFecha);
		
		cboConsultorio = new JComboBox<CustomComboBoxItem>();
		lblConsultorio.setLabelFor(cboConsultorio);
		
		// llenar formulario con datos de consultorio
		ArregloConsultorio arrConsultorio = new ArregloConsultorio();
		arrConsultorio.ordenarPorNombre();
		for (int i = 0; i < arrConsultorio.tamano(); i++)
			if (arrConsultorio.obtener(i).getEstado() == 1)
				cboConsultorio.addItem(
					new CustomComboBoxItem(
						arrConsultorio.obtener(i).getCodConsultorio(),
						arrConsultorio.obtener(i).getNombre()
					)
				);

		cboConsultorio.setBounds(157, 97, 297, 22);
		contentPanel.add(cboConsultorio);
		
		cboMedico = new JComboBox<CustomComboBoxItem>();
		lblMedico.setLabelFor(cboMedico);
		// llenar formulario con datos de médico
		ArregloMedico arrMedico = new ArregloMedico();
		arrMedico.ordenarPorNombreCompleto();
		for (int i = 0; i < arrMedico.tamano(); i++)
			if (arrMedico.obtener(i).getEstado() == 1)
				cboMedico.addItem(
					new CustomComboBoxItem(
						arrMedico.obtener(i).getCodMedico(),
						arrMedico.obtener(i).getNombreCompleto()
					)
				);
		cboMedico.setBounds(157, 69, 297, 22);
		contentPanel.add(cboMedico);
		
		cboPaciente = new JComboBox<CustomComboBoxItem>();
		lblPaciente.setLabelFor(cboPaciente);
		// llenar formulario con datos de paciente
		ArregloPaciente arrPaciente = new ArregloPaciente();
		arrPaciente.ordenarPorNombreCompleto();
		for (int i = 0; i < arrPaciente.tamano(); i++)
			if (arrPaciente.obtener(i).getEstado() == 1)
				cboPaciente.addItem(
					new CustomComboBoxItem(
						arrPaciente.obtener(i).getCodPaciente(),
						arrPaciente.obtener(i).getNombreCompleto()
					)
				);
		cboPaciente.setBounds(157, 41, 297, 22);
		contentPanel.add(cboPaciente);
		
		scp = new JScrollPane();
		scp.setBounds(157, 213, 297, 91);
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

		// agregar número autogenerado en el formulario
		txtNumCita.setText(Cita.generarNumCita() + "");

		if (action.equals("agregar"))
			cboEstado.setEnabled(false);
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
	
	// TODO:
	protected void actionPerformedBtnAceptar(ActionEvent e) {
		// validar formulario y detener el código acá si hay un error
		if (!validarFormulario()) {
			return;
		}
		
		// usar isSuccess para avisar a la ventana padre que se presionó este botón
		this.isSuccess = true;
		
		// si la acción es agregar, crea un nuevo objeto de cita
		if ("agregar".equals(action))
			this.cita = new Cita();
		
		// actualizar el objeto consultorio con los datos del formulario
		this.cita.setCodPaciente(leerCodPaciente());
		this.cita.setCodMedico(leerCodMedico());
		this.cita.setCodConsultorio(leerCodConsultorio());
		this.cita.setEstado(cboEstado.getSelectedIndex());
		this.cita.setMotivo(leerMotivo());
		this.cita.setFecha(leerFecha());
		this.cita.setHora(leerHora());
		
		// ocultar la ventana modal y continuar con el código de la ventana padre
		setVisible(false);
	}
	
	// getters
	public Cita getCita() {
		return this.cita;
	}
	public boolean getSuccess() {
		return this.isSuccess;
	}
	
	// método público para llenar el formulario cuando se esta editando
	public void llenarFormulario(Cita x) {
		// agregar paciente a variable global privada
		this.cita = x;

		// llenar campos del formulario con datos de paciente
		txtNumCita.setText(cita.getNumCita() + ""); // convertir código en String
		txtMotivo.setText(cita.getMotivo());
		cboEstado.setSelectedIndex(cita.getEstado());
		spnFecha.setValue(Libreria.strToDate(cita.getFecha()));
		spnHora.setValue(Libreria.strToTime(cita.getHora()));
		
		// llenar comboboxes - crear combo items
		CustomComboBoxItem pacienteItem = new CustomComboBoxItem(cita.getCodPaciente(), cita.getPaciente().getNombreCompleto());
		CustomComboBoxItem medicoItem = new CustomComboBoxItem(cita.getCodMedico(), cita.getMedico().getNombreCompleto());
		CustomComboBoxItem consultorioItem = new CustomComboBoxItem(cita.getCodConsultorio(), cita.getConsultorio().getNombre());

		// seleccionar item del combobox y agregarlo si no existe (util en editar con datos huerfanos o datos en estado inactivo)
		seleccionarCboItem(cboPaciente, pacienteItem);
		seleccionarCboItem(cboMedico, medicoItem);
		seleccionarCboItem(cboConsultorio, consultorioItem);
	}

	// selecciona un item en el cbo y lo agrega si no existe (posiblemente porque esta desactivado; esto solo ocurre en editar)
	private void seleccionarCboItem(JComboBox<CustomComboBoxItem> cbo, CustomComboBoxItem item) {
		// revisar si el cbo tiene ese item
		boolean existe = false;
		for (int i = 0; i < cbo.getItemCount(); i++) {
			if (cbo.getItemAt(i).getValue() == item.getValue()) {
				existe = true;
				break;
			}
		}
		
		// si no existe, agregarlo
		if (!existe) {
			cbo.addItem(item);
		}
		
		// seleccionar item
		cbo.setSelectedItem(item);
	}
	
	// validar campos vacíos en formulario
	private boolean validarFormulario() {
		try {
			// validar si codPaciente está vacío
			if (leerCodPaciente() == -1)
				throw new Exception("Paciente no puede estar vacío");
			
			// validar si codMedico está vacío
			if (leerCodMedico() == -1)
				throw new Exception("Médico no puede estar vacío");
			
			// validar si codConsultorio está vacío
			if (leerCodConsultorio() == -1)
				throw new Exception("Consultorio no puede estar vacío");
			
			// validar que estado sea pendiente al agregar
			if (action.equals("agregar") && leerEstado() != 0)
				throw new Exception("Estado debe ser pendiente al agregar una nueva cita");
			
			// validar por disponibilidad de citas			
			// obtener todas las citas relacionadas al paciente, medico y consultorio relacionado a los datos del formulario
			ArregloCita arr = Principal.getArrCitas();
			ArrayList<Cita> citasPorMedico = arr.buscarCodMedico(leerCodMedico());
			ArrayList<Cita> citasPorConsultorio = arr.buscarCodConsultorio(leerCodConsultorio());
			ArrayList<Cita> citasPorPaciente = arr.buscarCodPaciente(leerCodPaciente());
			
			// generar un objeto de fecha con los datos del formulario
			DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
			LocalDateTime fechaForm = LocalDateTime.parse(leerFecha() + " " + leerHora(), formatoFecha);
			
			// validar por disponibilidad de paciente
			if (citasPorPaciente != null)
				for (int i = 0; i < citasPorPaciente.size(); i++) {
					Cita cita = citasPorPaciente.get(i);
					
					// ignorar si estamos editando esta cita
					if (action.equals("editar") && cita.getNumCita() == leerNumCita()) continue;
					
					// solo revisar citas pendientes
					if (cita.getEstado() == 0) {
						// generar un objeto de fecha con los datos de la cita
						LocalDateTime fechaInicio = LocalDateTime.parse(cita.getFecha() + " " + cita.getHora(), formatoFecha);
						
						// generar un nuevo objeto de fecha con 1 hora más
						LocalDateTime fechaFin = fechaInicio.plusHours(1);
						
						// comparar si la fecha y hora del formulario esta dentro del rango de 1hr de la cita 
						if (fechaForm.equals(fechaInicio) || fechaForm.isAfter(fechaInicio) && fechaForm.isBefore(fechaFin)) {
							throw new Exception("El paciente tiene una cita en este momento");
						}
					}
				}
			
			// validar por disponibilidad de médico
			if (citasPorMedico != null)
				for (int i = 0; i < citasPorMedico.size(); i++) {
					Cita cita = citasPorMedico.get(i);
					
					// ignorar si estamos editando esta cita
					if (action.equals("editar") && cita.getNumCita() == leerNumCita()) continue;
					
					// solo revisar citas pendientes
					if (cita.getEstado() == 0) {
						// generar un objeto de fecha con los datos de la cita
						LocalDateTime fechaInicio = LocalDateTime.parse(cita.getFecha() + " " + cita.getHora(), formatoFecha);
						
						// generar un nuevo objeto de fecha con 1 hora más
						LocalDateTime fechaFin = fechaInicio.plusHours(1);
						
						// comparar si la fecha y hora del formulario esta dentro del rango de 1hr de la cita 
						if (fechaForm.equals(fechaInicio) || fechaForm.isAfter(fechaInicio) && fechaForm.isBefore(fechaFin)) {
							throw new Exception("El médico tiene una cita en este momento");
						}
					}
				}
			
			// validar por disponibilidad de consultorio
			if (citasPorConsultorio != null)
				for (int i = 0; i < citasPorConsultorio.size(); i++) {
					Cita cita = citasPorConsultorio.get(i);
					
					// ignorar si estamos editando esta cita
					if (action.equals("editar") && cita.getNumCita() == leerNumCita()) continue;
					
					// solo revisar citas pendientes
					if (cita.getEstado() == 0) {
						// generar un objeto de fecha con los datos de la cita
						LocalDateTime fechaInicio = LocalDateTime.parse(cita.getFecha() + " " + cita.getHora(), formatoFecha);
						
						// generar un nuevo objeto de fecha con 1 hora más
						LocalDateTime fechaFin = fechaInicio.plusHours(1);
						
						// comparar si la fecha y hora del formulario esta dentro del rango de 1hr de la cita 
						if (fechaForm.equals(fechaInicio) || fechaForm.isAfter(fechaInicio) && fechaForm.isBefore(fechaFin)) {
							throw new Exception("El consultorio tiene una cita en este momento");
						}
					}
				}

		} catch(Exception e) {
			// mostrar error de validación
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error de validación", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	// leer la fecha y darle formato (dd/MM/yyyy)
	private String leerFecha() {
		// obtener el editor del spinner
		JSpinner.DateEditor editor = (JSpinner.DateEditor) spnFecha.getEditor();

		// formatear la fecha
		return editor.getFormat().format(spnFecha.getValue());
	}
	
	// leer la hora y darle formato (HH:mm)
	private String leerHora() {
		// obtener el editor del spinner
		JSpinner.DateEditor editor = (JSpinner.DateEditor) spnHora.getEditor();

		// formatear la fecha
		return editor.getFormat().format(spnHora.getValue());
	}
	
	private String leerMotivo() {
		return txtMotivo.getText().trim();
	}
	
	private int leerCodPaciente() {
		CustomComboBoxItem pacienteItem = (CustomComboBoxItem) cboPaciente.getSelectedItem();
		if (pacienteItem != null)
			return pacienteItem.getValue();
		return -1;
	}
	
	private int leerCodMedico() {
		CustomComboBoxItem medicoItem = (CustomComboBoxItem) cboMedico.getSelectedItem();
		if (medicoItem != null)
			return medicoItem.getValue();
		return -1;
	}
	
	private int leerCodConsultorio() {
		CustomComboBoxItem consultorioItem = (CustomComboBoxItem) cboConsultorio.getSelectedItem();
		if (consultorioItem != null)
			return consultorioItem.getValue();
		return -1;
	}
	
	private int leerEstado() {
		return cboEstado.getSelectedIndex();
	}
	
	private int leerNumCita() {
		return Integer.parseInt(txtNumCita.getText().trim());
	}
}
