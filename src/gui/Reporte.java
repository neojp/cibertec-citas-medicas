package gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import arreglo.ArregloCitas;
import arreglo.ArregloConsultorio;
import arreglo.ArregloMedico2;
import arreglo.ArregloPaciente;
import clases.Cita;
import clases.Consultorio;
import clases.Medico;
import clases.Paciente;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.Color;

public class Reporte extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton btnGenerar;
	private JComboBox<String> cboReporte;
	private JTable tblTabla;
	private DefaultTableModel modelo;
	private JScrollPane scp;

	/*Declarar objetos globales
	private ArregloCitas ac= new ArregloCitas();
	private ArregloConsultorio aco=new ArregloConsultorio();
	private ArregloMedico am=new ArregloMedico();
	private ArregloPaciente ap=new ArregloPaciente();*/
	
	//Declarar objetos globales
	private ArregloCitas ac = Principal.getArrCitas();
	private ArregloConsultorio aco = Principal.getArrConsultorios();
	private ArregloMedico2 am = Principal.getArrMedicos();
	private ArregloPaciente ap = Principal.getArrPacientes();

	
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

		cboReporte = new JComboBox<String>();
		cboReporte.setModel(new DefaultComboBoxModel<String>(new String[] {
			"Por Paciente", "Por Médico", "Por Consultorio", "Por Fecha", 
			"Pacientes con citas Pendientes", "Citas por Médico", 
			"Citas por Consultorio", "Agenda del día"
		}));
		cboReporte.setBounds(300, 30, 260, 22);
		getContentPane().add(cboReporte);

		btnGenerar = new JButton("Generar");
		btnGenerar.setForeground(new Color(255, 255, 255));
		btnGenerar.setBackground(new Color(0, 64, 128));
		btnGenerar.setBounds(711, 30, 135, 23);
		btnGenerar.addActionListener(this);
		getContentPane().add(btnGenerar);

		modelo = new DefaultTableModel();
		tblTabla = new JTable(modelo);
		scp = new JScrollPane(tblTabla);
		scp.setBounds(36, 75, 810, 300);
		getContentPane().add(scp);
		
		generarReporte();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnGenerar) {
			generarReporte();
		}
	}

	private void generarReporte() {

	    int opcion = cboReporte.getSelectedIndex();
	    modelo.setRowCount(0);
	    modelo.setColumnCount(0);

	    java.time.format.DateTimeFormatter formatterFecha =
	            java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");

	    java.time.format.DateTimeFormatter formatterHora =
	            java.time.format.DateTimeFormatter.ofPattern("HH:mm");

	    switch (opcion) {


	    case 0: // ORDENAdo POR PACIENTE, FECHA y HORA


	        modelo.setColumnIdentifiers(new String[]{
	                "Código","Paciente", "Fecha", "Hora",
	                "Médico", "Consultorio", "Estado"
	        });

	        java.util.List<Cita> lista0 = new java.util.ArrayList<>();
	        for (int i = 0; i < ac.tamano(); i++)
	            lista0.add(ac.obtener(i));

	        lista0.sort((c1, c2) -> {

	            int cmpPaciente = Integer.compare(c1.getCodPaciente(), c2.getCodPaciente());
	            if (cmpPaciente != 0) return cmpPaciente;

	            java.time.LocalDate f1 = java.time.LocalDate.parse(c1.getFecha(), formatterFecha);
	            java.time.LocalDate f2 = java.time.LocalDate.parse(c2.getFecha(), formatterFecha);
	            int cmpFecha = f1.compareTo(f2);
	            if (cmpFecha != 0) return cmpFecha;

	            java.time.LocalTime h1 = java.time.LocalTime.parse(c1.getHora(), formatterHora);
	            java.time.LocalTime h2 = java.time.LocalTime.parse(c2.getHora(), formatterHora);
	            return h1.compareTo(h2);
	        });

	        for (Cita c : lista0) {
	            Paciente p = ap.buscarCodPaciente(c.getCodPaciente());
	            Medico m = am.buscarCodMedico(c.getCodMedico());
	            Consultorio co = aco.buscarCodConsultorio(c.getCodConsultorio());

	            modelo.addRow(new Object[]{
	                    c.getCodPaciente(),
	                    p.getNombres()+" "+p.getApellidos(),
	                    c.getFecha(),
	                    c.getHora(),
	                    m.getNombres()+" "+m.getApellidos(),
	                    co.getCodConsultorio(),
	                    Cita.estados[c.getEstado()]
	            });
	        }
	        break;

	  
	    case 1: // ORDENAdo POR CODIGO MEDICO y FECHA
	  

	        modelo.setColumnIdentifiers(new String[]{
	                "Código","Médico","Especialidad","Fecha","Estado"
	        });

	        java.util.List<Cita> lista1 = new java.util.ArrayList<>();
	        for (int i = 0; i < ac.tamano(); i++)
	            lista1.add(ac.obtener(i));

	        lista1.sort((c1, c2) -> {

	            int cmpMed = Integer.compare(c1.getCodMedico(), c2.getCodMedico());
	            if (cmpMed != 0) return cmpMed;

	            java.time.LocalDate f1 = java.time.LocalDate.parse(c1.getFecha(), formatterFecha);
	            java.time.LocalDate f2 = java.time.LocalDate.parse(c2.getFecha(), formatterFecha);
	            return f1.compareTo(f2);
	        });

	        for (Cita c : lista1) {
	            Medico m = am.buscarCodMedico(c.getCodMedico());

	            modelo.addRow(new Object[]{
	                    c.getCodMedico(),
	                    m.getNombres()+" "+m.getApellidos(),
	                    m.getEspecialidad(),
	                    c.getFecha(),
	                    Cita.estados[c.getEstado()]
	            });
	        }
	        break;

	    case 2: // ORDENADO POR CONSULTORIO, FECHA y HORA
	  

	        modelo.setColumnIdentifiers(new String[]{
	                "Consultorio","Fecha","Hora","Estado"
	        });

	        java.util.List<Cita> lista2 = new java.util.ArrayList<>();
	        for (int i = 0; i < ac.tamano(); i++)
	            lista2.add(ac.obtener(i));

	        lista2.sort((c1, c2) -> {

	            int cmpCons = Integer.compare(c1.getCodConsultorio(), c2.getCodConsultorio());
	            if (cmpCons != 0) return cmpCons;

	            java.time.LocalDate f1 = java.time.LocalDate.parse(c1.getFecha(), formatterFecha);
	            java.time.LocalDate f2 = java.time.LocalDate.parse(c2.getFecha(), formatterFecha);
	            int cmpFecha = f1.compareTo(f2);
	            if (cmpFecha != 0) return cmpFecha;

	            java.time.LocalTime h1 = java.time.LocalTime.parse(c1.getHora(), formatterHora);
	            java.time.LocalTime h2 = java.time.LocalTime.parse(c2.getHora(), formatterHora);
	            return h1.compareTo(h2);
	        });

	        for (Cita c : lista2) {
	            modelo.addRow(new Object[]{
	                    c.getCodConsultorio(),
	                    c.getFecha(),
	                    c.getHora(),
	                    Cita.estados[c.getEstado()]
	            });
	        }
	        break;

	   
	    case 3: // ORDENADO POR FECHA PROXIMA
	    

	        modelo.setColumnIdentifiers(new String[]{
	                "Fecha","Cod.Paciente","Paciente","Cod.Médico","Médico"
	        });

	        java.util.List<Cita> lista3 = new java.util.ArrayList<>();
	        for (int i = 0; i < ac.tamano(); i++)
	            lista3.add(ac.obtener(i));

	        lista3.sort((c1, c2) -> {
	            java.time.LocalDate f1 = java.time.LocalDate.parse(c1.getFecha(), formatterFecha);
	            java.time.LocalDate f2 = java.time.LocalDate.parse(c2.getFecha(), formatterFecha);
	            return f1.compareTo(f2);
	        });

	        for (Cita c : lista3) {
	            Paciente p = ap.buscarCodPaciente(c.getCodPaciente());
	            Medico m = am.buscarCodMedico(c.getCodMedico());

	            modelo.addRow(new Object[]{
	                    c.getFecha(),
	                    p.getCodPaciente(),
	                    p.getNombres()+" "+p.getApellidos(),
	                    m.getCodMedico(),
	                    m.getNombres()+" "+m.getApellidos()
	            });
	        }
	        break;

	   
	    case 4: // ORDENADO POR PACIENTE Y FECHA 
	   

	        modelo.setColumnIdentifiers(new String[]{
	                "Código","Paciente","Fecha","Especialidad"
	        });

	        java.util.List<Cita> lista4 = new java.util.ArrayList<>();

	        for (int i = 0; i < ac.tamano(); i++) {
	            if (ac.obtener(i).getEstado() == 0)
	                lista4.add(ac.obtener(i));
	        }

	        lista4.sort((c1, c2) -> {

	            int cmpPac = Integer.compare(c1.getCodPaciente(), c2.getCodPaciente());
	            if (cmpPac != 0) return cmpPac;

	            java.time.LocalDate f1 = java.time.LocalDate.parse(c1.getFecha(), formatterFecha);
	            java.time.LocalDate f2 = java.time.LocalDate.parse(c2.getFecha(), formatterFecha);
	            return f1.compareTo(f2);
	        });

	        for (Cita c : lista4) {
	            Paciente p = ap.buscarCodPaciente(c.getCodPaciente());
	            Medico m = am.buscarCodMedico(c.getCodMedico());

	            modelo.addRow(new Object[]{
	                    p.getCodPaciente(),
	                    p.getNombres()+" "+p.getApellidos(),
	                    c.getFecha(),
	                    m.getEspecialidad()
	            });
	        }
	        break;

	   
	    case 5: // ORDENADO POR CODIGO MEDICO Y ESTADO
	 

	        modelo.setColumnIdentifiers(new String[]{
	                "Código","Médico","Especialidad","Total","Estado"
	        });

	        java.util.List<Medico> listaMed = new java.util.ArrayList<>();
	        for (int i = 0; i < am.tamano(); i++)
	            listaMed.add(am.obtener(i));

	        listaMed.sort((m1, m2) ->
	                Integer.compare(m1.getCodMedico(), m2.getCodMedico())
	        );

	        for (Medico m : listaMed) {

	            int total = 0;
	            String estado = "N/A";

	            for (int j = 0; j < ac.tamano(); j++) {
	                Cita c = ac.obtener(j);
	                if (c.getCodMedico() == m.getCodMedico()) {
	                    total++;
	                    estado = Cita.estados[c.getEstado()];
	                }
	            }

	            modelo.addRow(new Object[]{
	                    m.getCodMedico(),
	                    m.getNombres()+" "+m.getApellidos(),
	                    m.getEspecialidad(),
	                    total,
	                    estado
	            });
	        }
	        break;

	   
	    case 6: // ORDENADO POR CONSULTORIO Y FECHA
	

	        modelo.setColumnIdentifiers(new String[]{
	                "Consultorio","Fecha","Total"
	        });

	        java.util.List<Cita> lista6 = new java.util.ArrayList<>();
	        for (int i = 0; i < ac.tamano(); i++)
	            lista6.add(ac.obtener(i));

	        lista6.sort((c1, c2) -> {

	            int cmp = Integer.compare(c1.getCodConsultorio(), c2.getCodConsultorio());
	            if (cmp != 0) return cmp;

	            java.time.LocalDate f1 = java.time.LocalDate.parse(c1.getFecha(), formatterFecha);
	            java.time.LocalDate f2 = java.time.LocalDate.parse(c2.getFecha(), formatterFecha);
	            return f1.compareTo(f2);
	        });

	        for (Cita c : lista6) {
	            modelo.addRow(new Object[]{
	                    c.getCodConsultorio(),
	                    c.getFecha(),
	                    1
	            });
	        }
	        break;

	   
	    case 7: // ORDENADO POR FECHA Y HORA
	   

	        modelo.setColumnIdentifiers(new String[]{
	                "Fecha","Hora","Paciente","Médico","Consultorio"
	        });

	        java.util.List<Cita> lista7 = new java.util.ArrayList<>();
	        for (int i = 0; i < ac.tamano(); i++)
	            lista7.add(ac.obtener(i));

	        lista7.sort((c1, c2) -> {

	            java.time.LocalDate f1 = java.time.LocalDate.parse(c1.getFecha(), formatterFecha);
	            java.time.LocalDate f2 = java.time.LocalDate.parse(c2.getFecha(), formatterFecha);
	            int cmpFecha = f1.compareTo(f2);
	            if (cmpFecha != 0) return cmpFecha;

	            java.time.LocalTime h1 = java.time.LocalTime.parse(c1.getHora(), formatterHora);
	            java.time.LocalTime h2 = java.time.LocalTime.parse(c2.getHora(), formatterHora);
	            return h1.compareTo(h2);
	        });

	        for (Cita c : lista7) {
	            modelo.addRow(new Object[]{
	                    c.getFecha(),
	                    c.getHora(),
	                    c.getCodPaciente(),
	                    c.getCodMedico(),
	                    c.getCodConsultorio()
	            });
	        }
	        break;
	    }
	}
}