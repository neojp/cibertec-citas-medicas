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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ConsultaCitas extends JDialog implements ActionListener, ItemListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnOk, btnBuscar;
	private JPanel panelTipo, bodyPanel, buttonPane, panelBuscar;
	private JLabel lblConsultBy, lblCodigo;
	private String consultType =  "paciente";
	private JTextField txtCodigo;
	private JComboBox<String> cboConsultar;
	private JTable tblTabla = new JTable();
	private DefaultTableModel tableModel;
	private JScrollPane scpTable;
	private Object[] headerTableP = {"Estado", "Fecha", "hora", "Médico", "Consultorio"};
	private Object[] headerTableM = {"Estado", "Fecha", "Paciente", "Consultorio"};
	private Object[] headerTableC = {"Fecha", "Ocupación", "Médico"};

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
		
		lblCodigo = new JLabel("Código "+consultType+":");
		panelBuscar.add(lblCodigo);
		panelBuscar.add(Box.createHorizontalStrut(10));
		
		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
		panelBuscar.add(txtCodigo);
		
		panelBuscar.add(Box.createHorizontalStrut(10));
		
		btnBuscar = new JButton("Consultar");
		btnBuscar.addActionListener(this);
		btnBuscar.setMaximumSize(new Dimension(150, 30));
		btnBuscar.setBackground(new Color(64,64,128));
		btnBuscar.setForeground(new Color(255,255,255));
		panelBuscar.add(btnBuscar);
		
		
		
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
		getRootPane().setDefaultButton(btnOk);
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
			case 0: consultType = "paciente"; break;
			case 1: consultType = "medico"; break;
			case 2: consultType = "consultorio"; break;
			default: consultType = "fecha";
		}
		
		changeInterface();
		System.out.println("Indice actual: "+index);
	}
	
	private void changeInterface() {
		lblCodigo.setText("Código "+this.consultType+":");
		Object[] newHeader = null;
		Object[][] newData = null;
		
		System.out.println("tipo: " + this.consultType);
		
		switch (this.consultType) {
			case "paciente": { newHeader = headerTableP; break; }
			case "medico": { newHeader = headerTableM; break;}
			case "consultorio": {newHeader = headerTableC; break;}
			default: {newHeader = null;}
		}
		
		tableModel.setDataVector(newData, newHeader);
	}
	
	private void showResultSearch() {
		tableModel.addRow(new Object[] {"hola","hola","hola","hola","hola"});
	}
	protected void actionPerformedBtnBuscar(ActionEvent e) {
		showResultSearch();
	}
}