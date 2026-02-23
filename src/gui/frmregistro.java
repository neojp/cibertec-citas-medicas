/*package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import clases.Cita;
import clases.Medico;
// import clases.Paciente; // Asegúrate de tener esta clase
import arreglos.ArregloCitas;
import arreglos.ArregloMedico;
// import arreglos.ArregloPaciente; // Asegúrate de tener esta clase

public class Frm_RegistroCitas extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtNumCita, txtFecha, txtHora, txtMotivo;
	private JComboBox<String> cboPaciente, cboMedico, cboConsultorio, cboEstado;
	private JButton btnAdicionar, btnModificar, btnCancelar, btnLimpiar;
	private JTable tblCitas;
	private DefaultTableModel modelo;

	// INSTANCIAS DE LOS ARREGLOS
	private ArregloCitas objCita = new ArregloCitas();
	private ArregloMedico objMed = new ArregloMedico();
	// private ArregloPaciente objPac = new ArregloPaciente(); 

	public static void main(String[] args) {
		try {
			Frm_RegistroCitas dialog = new Frm_RegistroCitas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) { e.printStackTrace(); }
	}

	public Frm_RegistroCitas() {
		setTitle("REGISTRO Y CONTROL DE CITAS MÉDICAS");
		setBounds(100, 100, 783, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);

		// ... (Tus JLabels y JTextFields se mantienen igual) ...
		// NOTA: Asegúrate de inicializar todos los componentes antes de llamar a los métodos de abajo

		// --- CONFIGURACIÓN DE LA TABLA ---
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 260, 722, 280);
		getContentPane().add(scrollPane);

		tblCitas = new JTable();
		modelo = new DefaultTableModel(
			new Object[][] {},
			new String[] {"N° CITA","PACIENTE","MÉDICO","CONSULTORIO","FECHA","HORA","ESTADO"}
		) {
			public boolean isCellEditable(int row, int column) { return false; }
		};
		tblCitas.setModel(modelo);
		scrollPane.setViewportView(tblCitas);

		// --- EVENTOS ---
		btnAdicionar.addActionListener(this);
		btnModificar.addActionListener(this);
		btnCancelar.addActionListener(this);
		btnLimpiar.addActionListener(this);
		
		tblCitas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) { seleccionarFila(); }
		});

		// --- INICIALIZACIÓN ---
		cargarCombos(); // Carga médicos y pacientes
		listar();
		limpiar();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdicionar) registrarCita();
		if (e.getSource() == btnModificar) reprogramarCita();
		if (e.getSource() == btnCancelar) eliminarCita();
		if (e.getSource() == btnLimpiar) limpiar();
	}

	// 1. CARGAR DATOS EN LOS COMBOS
	private void cargarCombos() {
		cboMedico.addItem("SELECCIONE MÉDICO");
		for (int i = 0; i < objMed.tamano(); i++) {
			Medico m = objMed.obtener(i);
			cboMedico.addItem(m.getCodMedico() + " - " + m.getNombres() + " " + m.getApellidos());
		}
		
		// Aquí harías lo mismo con Pacientes
		cboPaciente.addItem("SELECCIONE PACIENTE");
		// for(int i=0; i < objPac.tamano(); i++) ...
		
		cboConsultorio.setModel(new DefaultComboBoxModel<>(new String[] {"SELECCIONE", "CONS. 101", "CONS. 102", "CONS. 201"}));
	}

	// 2. REGISTRAR
	private void registrarCita() {
		try {
			int num = objCita.proximoCodigo(); // Genera el 1001
			
			// Obtenemos códigos de los combos (Asumiendo formato "COD - NOMBRE")
			int codPac = obtenerCodigoDeCombo(cboPaciente);
			int codMed = obtenerCodigoDeCombo(cboMedico);
			int con = cboConsultorio.getSelectedIndex();
			String fec = txtFecha.getText().trim();
			String hor = txtHora.getText().trim();
			String mot = txtMotivo.getText().trim();

			if (codPac == -1 || codMed == -1 || fec.isEmpty()) {
				error("DEBE SELECCIONAR PACIENTE, MÉDICO Y FECHA.");
				return;
			}

			objCita.adicionar(new Cita(num, codPac, codMed, con, 0, fec, hor, mot));
			mensaje("CITA REGISTRADA CON ÉXITO: " + num);
			listar();
			limpiar();
		} catch (Exception e) { error("ERROR AL REGISTRAR."); }
	}

	// 3. SELECCIONAR FILA
	private void seleccionarFila() {
		int fila = tblCitas.getSelectedRow();
		if (fila != -1) {
			txtNumCita.setText(modelo.getValueAt(fila, 0).toString());
			// Para los combos, buscar el texto que coincida o por código
			txtFecha.setText(modelo.getValueAt(fila, 4).toString());
			txtHora.setText(modelo.getValueAt(fila, 5).toString());
			
			btnAdicionar.setEnabled(false);
			btnModificar.setEnabled(true);
			btnCancelar.setEnabled(true);
		}
	}

	// 4. LISTAR
	private void listar() {
		modelo.setRowCount(0);
		for (int i = 0; i < objCita.tamano(); i++) {
			Cita c = objCita.obtener(i);
			modelo.addRow(new Object[] {
				c.getNumCita(), c.getCodPaciente(), c.getCodMedico(),
				c.getCodConsultorio(), c.getFecha(), c.getHora(),
				Cita.ESTADOS[c.getEstado()]
			});
		}
	}

	// 5. LIMPIAR (Con código correlativo)
	private void limpiar() {
		txtNumCita.setText("" + objCita.proximoCodigo());
		txtFecha.setText("");
		txtHora.setText("");
		txtMotivo.setText("");
		cboPaciente.setSelectedIndex(0);
		cboMedico.setSelectedIndex(0);
		cboConsultorio.setSelectedIndex(0);
		cboEstado.setSelectedIndex(0);
		
		btnAdicionar.setEnabled(true);
		btnModificar.setEnabled(false);
		btnCancelar.setEnabled(false);
	}

	// MÉTODO AUXILIAR PARA EXTRAER EL CÓDIGO DEL TEXTO DEL COMBO
	private int obtenerCodigoDeCombo(JComboBox cb) {
		if (cb.getSelectedIndex() <= 0) return -1;
		String item = cb.getSelectedItem().toString();
		return Integer.parseInt(item.split(" - ")[0]);
	}

	private void mensaje(String s) { JOptionPane.showMessageDialog(this, s); }
	private void error(String s) { JOptionPane.showMessageDialog(this, s, "ERROR", 0); }
}*/