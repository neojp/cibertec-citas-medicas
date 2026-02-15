package gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.Color;

public class Reporte extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton btnGenerar;
	private JComboBox<String> cmbReporte;
	private JTable table;
	private DefaultTableModel modelo;
	private JScrollPane scrollPane;

		public static void main(String[] args) {
		try {
			Reporte dialog = new Reporte();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Reporte() {
		setTitle("Reporte");
		setBounds(100, 100, 900, 450);
		getContentPane().setLayout(null);

		JLabel lblReporte = new JLabel("Tipo de Reporte:");
		lblReporte.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblReporte.setBounds(155, 31, 135, 20);
		getContentPane().add(lblReporte);

		cmbReporte = new JComboBox<String>();
		cmbReporte.setModel(new DefaultComboBoxModel<String>(new String[] {
			"Por Paciente", "Por Médico", "Por Consultorio", "Por Fecha", 
			"Pacientes con citas Pendientes", "Citas por Médico", 
			"Citas por Consultorio", "Agenda del día"
		}));
		cmbReporte.setBounds(300, 30, 260, 22);
		getContentPane().add(cmbReporte);

		btnGenerar = new JButton("Generar");
		btnGenerar.setForeground(new Color(255, 255, 255));
		btnGenerar.setBackground(new Color(0, 64, 128));
		btnGenerar.setBounds(711, 30, 135, 23);
		btnGenerar.addActionListener(this);
		getContentPane().add(btnGenerar);

		modelo = new DefaultTableModel();
		table = new JTable(modelo);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(36, 75, 810, 300);
		getContentPane().add(scrollPane);
		
		generarReporte();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnGenerar) {
			generarReporte();
		}
	}

	private void generarReporte() {
		int opcion = cmbReporte.getSelectedIndex();
		modelo.setRowCount(0);
		modelo.setColumnCount(0);

		switch (opcion) {
			case 0: // Por Paciente
				modelo.setColumnIdentifiers(new String[]{"Código","Paciente", "Fecha", "Hora", "Médico", "Consultorio", "Estado"});
				break;
			case 1: // Por Médico
				modelo.setColumnIdentifiers(new String[]{"Código","Médico", "Especialidad", "Fecha", "Estado"});
				break;
			case 2: // Por Consultorio
			    modelo.setColumnIdentifiers(new String[]{"Consultorio", "Fecha"});
			    break;	    
			case 3: // Por Fecha
				modelo.setColumnIdentifiers(new String[]{"Fecha", "Cod. Paciente", "Paciente","Cod. Médico","Médico","Especialidad"});
				break;
			case 4: // Pacientes con citas Pendientes//NO SE SI COLOCARLE MOTIVO
				modelo.setColumnIdentifiers(new String[]{"Código", "Paciente","Fecha Cita", "Especialidad"});
				break;
			case 5: // Citas por médico 
				modelo.setColumnIdentifiers(new String[]{"Código","Médico", "Especialidad", "Total", "Estado"});
				break;   
			case 6: // Citas por consultorio 
			    modelo.setColumnIdentifiers(new String[]{"Cod Consultorio", "Fecha","Total Citas"});
			    break;			    
								
			case 7: // Agenda por día
				modelo.setColumnIdentifiers(new String[]{"Hora", "Cod. Paciente","Paciente","Cod.Médico", "Médico", "Consultorio"});
				break;
		}
	}

	
}