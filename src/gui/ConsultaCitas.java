package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import arreglo.ArregloCita;
import arreglo.ArregloMedico;
import arreglo.ArregloConsultorio;
import arreglo.ArregloPaciente;
import clases.Cita;
import libreria.CustomComboBoxItem;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ItemEvent;

public class ConsultaCitas extends JDialog implements ActionListener, ItemListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnOk, btnBuscar;
	private JPanel panelTipo, bodyPanel, buttonPane, panelBuscar;
	private JLabel lblConsultBy, lblCodigo;
	private String consultType =  "Paciente";
	private JComboBox<String> cboConsultar;
	private JTable tblTabla = new JTable();
	private DefaultTableModel tableModel;
	private JScrollPane scpTable;
	private JComboBox<CustomComboBoxItem> cboSearch;
	private JSpinner spnFecha;
	private Object[] headerTableP = {"Estado", "Fecha", "Hora", "Médico", "Consultorio"};
	private Object[] headerTableM = {"Estado", "Fecha", "Paciente", "Consultorio"};
	private Object[] headerTableC = {"Estado", "Fecha", "Ocupación", "Médico"};
	private Object[] headerTableF = {"Estado", "Hora", "Paciente", "Médico", "Consultorio"};
	private Object[] currentHeader = headerTableP;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDialog.setDefaultLookAndFeelDecorated(true);
			ConsultaCitas dialog = new ConsultaCitas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ConsultaCitas() {
		setTitle("Consulta de citas");
		setBounds(100, 100, 500, 380);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		// SELECCIONA TIPO DE CONSULTA
		panelTipo = new JPanel();
		contentPanel.add(panelTipo);
		panelTipo.setLayout(new BoxLayout(panelTipo, BoxLayout.X_AXIS));
		panelTipo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

		lblConsultBy = new JLabel("Consultar por: ");
		panelTipo.add(lblConsultBy);

		panelTipo.add(Box.createHorizontalStrut(100));

		cboConsultar = new JComboBox<String>(new String[] { "Paciente", "Médico", "Consultorio", "Fecha"});
		cboConsultar.addItemListener(this);
		cboConsultar.addActionListener(this);
		panelTipo.add(cboConsultar);
		
		contentPanel.add(Box.createVerticalStrut(10));
		
		// BUQUEDA		
		panelBuscar = new JPanel();
		contentPanel.add(panelBuscar);
		panelBuscar.setLayout(new BoxLayout(panelBuscar, BoxLayout.X_AXIS));
		panelBuscar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
		
		lblCodigo = new JLabel(consultType + ":");
		panelBuscar.add(lblCodigo);
		panelBuscar.add(Box.createHorizontalStrut(10));

			
		// cambiar el formato del spinner a día/mes/año
		spnFecha = new JSpinner();
		spnFecha.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_YEAR));
		JSpinner.DateEditor fechaEditor = new JSpinner.DateEditor(spnFecha, "dd/MM/yyyy");
		spnFecha.setEditor(fechaEditor);
		panelBuscar.add(spnFecha);
		spnFecha.setVisible(false);
		
		//cbo para buscar por paciente, médico o consultorio
		cboSearch = new JComboBox<CustomComboBoxItem>();
		cboSearch.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
		//por defecto paciente
		ArregloPaciente arrConsultorio = new ArregloPaciente();
		arrConsultorio.ordenarPorNombreCompleto();
		for (int i = 0; i < arrConsultorio.tamano(); i++)
			if (arrConsultorio.obtener(i).getEstado() == 1)
				cboSearch.addItem(
					new CustomComboBoxItem(
						arrConsultorio.obtener(i).getCodPaciente(),
						arrConsultorio.obtener(i).getNombreCompleto()
					)
				);
		panelBuscar.add(cboSearch);
		
		panelBuscar.add(Box.createHorizontalStrut(10));
		
		btnBuscar = new JButton("Consultar");
		btnBuscar.addActionListener(this);
		btnBuscar.setMaximumSize(new Dimension(150, 30));
		btnBuscar.setBackground(new Color(64,64,128));
		btnBuscar.setForeground(new Color(255,255,255));
		panelBuscar.add(btnBuscar);
		getRootPane().setDefaultButton(btnBuscar);
		
		
		
		// CUERPO
		bodyPanel = new JPanel();
		contentPanel.add(bodyPanel);
		bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
		
		tableModel = new DefaultTableModel(null,headerTableP);
		tblTabla.setModel(tableModel);
		scpTable = new JScrollPane(tblTabla);
		scpTable.setBorder(new EmptyBorder(20,0,20,0));
		
		bodyPanel.add(scpTable);
				
		
		// BOTONES
		buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
		buttonPane.setBorder(new EmptyBorder(5,5,5,5));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		btnOk = new JButton("OK");
		btnOk.addActionListener(this);
		btnOk.setActionCommand("OK");
		btnOk.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
		btnOk.setBackground(new Color(64,64,128));
		btnOk.setForeground(new Color(255,255,255));
		buttonPane.add(btnOk);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(e);
		}
		
		if (e.getSource() == btnOk) {
			actionPerformedBtnOk(e);
		}
	}
	protected void actionPerformedBtnOk(ActionEvent e) {
		this.dispose();
	}
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			itemStateChangedCboConsultar(e);
		}
	}
	protected void itemStateChangedCboConsultar(ItemEvent e) {
		int index = cboConsultar.getSelectedIndex();
		switch (index) {
			case 0: consultType = "Paciente"; break;
			case 1: consultType = "Médico"; break;
			case 2: consultType = "Consultorio"; break;
			default: consultType = "Fecha";
		}
		
		changeInterface();
		System.out.println("Indice actual: "+index);
	}
	
	private void changeInterface() {
		if (this.consultType.equals("Fecha")) {
			spnFecha.setVisible(true);
			cboSearch.setVisible(false);
			lblCodigo.setText("Fecha (dd/mm/aa):");
			lblCodigo.setLabelFor(spnFecha);
		} else {
			lblCodigo.setText(this.consultType+":");
			spnFecha.setVisible(false);
			cboSearch.setVisible(true);
			lblCodigo.setLabelFor(cboSearch);
		}
		Object[][] newData = null;
		
		System.out.println("tipo: " + this.consultType);
		
		switch (this.consultType) {
			case "Paciente": { currentHeader = headerTableP;break;}
			case "Médico": { currentHeader = headerTableM; break;}
			case "Consultorio": {currentHeader = headerTableC; break;}
			case "Fecha": {currentHeader = headerTableF; break;}
			default: {currentHeader = null;}
		}
		
		loadItemsCboSearch();
		tableModel.setDataVector(newData, currentHeader);
	}
	
	private void showResultSearch() {
		ArrayList<Cita> citas = new ArrayList<Cita>();
		if (this.consultType.equals("Paciente")) {
			citas = new ArregloCita().buscarPorPaciente(cboSearch.getItemAt(cboSearch.getSelectedIndex()).getValue());
		} else if (this.consultType.equals("Medico")) {
			citas = new ArregloCita().buscarPorMedico(cboSearch.getItemAt(cboSearch.getSelectedIndex()).getValue());
		} else if (this.consultType.equals("Consultorio")) {
			citas = new ArregloCita().buscarPorConsultorio(cboSearch.getItemAt(cboSearch.getSelectedIndex()).getValue());
		} else {
			citas = new ArregloCita().buscarPorFecha(leerFecha());
		}

		if (citas == null || citas.isEmpty()) {
			tableModel.setRowCount(0);
			JOptionPane.showMessageDialog(this, "No se encontraron citas para el criterio seleccionado.", "Resultado de búsqueda", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		for (Cita cita : citas) {
			tableModel.addRow(cita.getToRow(currentHeader));
		}
	}

	private void loadItemsCboSearch() {
		cboSearch.removeAllItems();
		
		switch (this.consultType) {
			case "Paciente": {
				ArregloPaciente arr = new ArregloPaciente();
				arr.ordenarPorNombreCompleto();
				for (int i = 0; i < arr.tamano(); i++)
					if (arr.obtener(i).getEstado() == 1)
						cboSearch.addItem(new CustomComboBoxItem(
							arr.obtener(i).getCodPaciente(),
							arr.obtener(i).getNombreCompleto()
						));
				break;
			}
			case "Médico": {
				ArregloMedico arr = new ArregloMedico();
				arr.ordenarPorNombreCompleto();
				for (int i = 0; i < arr.tamano(); i++)
					if (arr.obtener(i).getEstado() == 1)
						cboSearch.addItem(new CustomComboBoxItem(
							arr.obtener(i).getCodMedico(),
							arr.obtener(i).getNombreCompleto()
						));
				break;
			}
			case "Consultorio": {
				ArregloConsultorio arr = new ArregloConsultorio();
				for (int i = 0; i < arr.tamano(); i++)
					if (arr.obtener(i).getEstado() == 1)
						cboSearch.addItem(new CustomComboBoxItem(
							arr.obtener(i).getCodConsultorio(),
							arr.obtener(i).getNombre()
						));
				break;
			}
			case "Fecha": {
				cboSearch.setVisible(false);
				break;
			}
		}
	}

	protected void actionPerformedBtnBuscar(ActionEvent e) {
		showResultSearch();
	}

		// leer la fecha y darle formato (dd/MM/yyyy)
	private String leerFecha() {
		// obtener el editor del spinner
		JSpinner.DateEditor editor = (JSpinner.DateEditor) spnFecha.getEditor();

		// formatear la fecha
		return editor.getFormat().format(spnFecha.getValue());
	}
}