package gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import arreglo.ArregloCita;
import arreglo.ArregloPaciente;
import clases.Cita;
import clases.Paciente;
import libreria.CustomComboBoxItem;
import libreria.Libreria;

import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JComboBox;

public class ConsultaPorPaciente extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JScrollPane scp;
	private JTable tblTabla;
	private DefaultTableModel modelo;
	private JComboBox<CustomComboBoxItem> cboPaciente;
	private JPanel panel;
	private JLabel lblPaciente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ConsultaPorPaciente dialog = new ConsultaPorPaciente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ConsultaPorPaciente() {
		setResizable(false);
		setTitle("Consulta de Citas por Paciente");
		setBounds(100, 100, 820, 386);
		getContentPane().setLayout(null);

		scp = new JScrollPane();
		scp.setBounds(10, 45, 784, 291);
		getContentPane().add(scp);

		modelo = Libreria.crearModeloTabla(new String[] { 
			"N\u00FAmero", "M\u00E9dico", "Consultorio", "Fecha", "Hora", "Estado", "Motivo"
		});
		tblTabla = Libreria.crearTabla();
		tblTabla.setModel(modelo);
		scp.setViewportView(tblTabla);
		
		panel = new JPanel();
		panel.setBounds(207, 11, 389, 23);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		cboPaciente = new JComboBox<CustomComboBoxItem>();
		cboPaciente.addActionListener(this);
		
		lblPaciente = new JLabel("Paciente:");
		lblPaciente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPaciente.setLabelFor(cboPaciente);
		lblPaciente.setBounds(0, 4, 89, 14);
		panel.add(lblPaciente);
		cboPaciente.setBounds(99, 0, 290, 22);
		panel.add(cboPaciente);
		
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
		
		listar();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cboPaciente) {
			actionPerformedCboPaciente(e);
		}
	}
	
	// actualizar tabla con contenido
	void listar() {
		// limpiar tabla
		modelo.setRowCount(0);
		
		// detenerse método si no hay paciente seleccionado
		if (cboPaciente.getSelectedIndex() == -1) return;
		
		int codPaciente = ((CustomComboBoxItem) cboPaciente.getSelectedItem()).getValue();
		
		ArregloCita arr = new ArregloCita();
		arr.ordenarPorFecha();
		ArrayList<Cita> citas = arr.buscarCodPaciente(codPaciente);
		
		if (citas == null) return;
		
		// iterar el arreglo y llenar la tabla con datos del paciente
		for (int i = 0; i < citas.size(); i++) {
			// crear fila con datos del paciente
			Object[] fila = {
					citas.get(i).getNumCita(),
					citas.get(i).getMedico().getNombreCompleto(),
					citas.get(i).getConsultorio().getNombre(),
					citas.get(i).getFecha(),
					citas.get(i).getHora(),
					Cita.estados[citas.get(i).getEstado()], // mostrar el label del estado
					citas.get(i).getMotivo()
			};
			
			// agregar fila
			modelo.addRow(fila);
		}
	}
	protected void actionPerformedCboPaciente(ActionEvent e) {
		listar();
	}
}
