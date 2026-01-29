package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar mnBar;
	private JMenu mnMantenimiento;
	private JMenu mnRegistro;
	private JMenu mnConsulta;
	private JMenu mnReporte;
	private JMenu mnAyuda;
	private JMenuItem mntmPaciente;
	private JMenuItem mntmMedico;
	private JMenuItem mntmConsultorio;
	private JMenuItem mntmRegistro;
	private JMenuItem mntmConsulta;
	private JMenuItem mntmReporte;
	private JMenuItem mntmAcerca;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setTitle("Sistema de Reserva de Citas Médicas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 686, 480);
		
		mnBar = new JMenuBar();
		setJMenuBar(mnBar);
		
		mnMantenimiento = new JMenu("Mantenimiento");
		mnBar.add(mnMantenimiento);
		
		mntmPaciente = new JMenuItem("Paciente");
		mntmPaciente.addActionListener(this);
		mnMantenimiento.add(mntmPaciente);
		
		mntmMedico = new JMenuItem("Medico");
		mntmMedico.addActionListener(this);
		mnMantenimiento.add(mntmMedico);
		
		mntmConsultorio = new JMenuItem("Consultorio");
		mntmConsultorio.addActionListener(this);
		mnMantenimiento.add(mntmConsultorio);
		
		mnRegistro = new JMenu("Registro");
		mnRegistro.addActionListener(this);
		mnBar.add(mnRegistro);
		
		mntmRegistro = new JMenuItem("Registro");
		mntmRegistro.addActionListener(this);
		mnRegistro.add(mntmRegistro);
		
		mnConsulta = new JMenu("Consulta");
		mnConsulta.addActionListener(this);
		mnBar.add(mnConsulta);
		
		mntmConsulta = new JMenuItem("Consulta");
		mntmConsulta.addActionListener(this);
		mnConsulta.add(mntmConsulta);
		
		mnReporte = new JMenu("Reporte");
		mnReporte.addActionListener(this);
		mnBar.add(mnReporte);
		
		mntmReporte = new JMenuItem("Reporte");
		mntmReporte.addActionListener(this);
		mnReporte.add(mntmReporte);
		
		mnAyuda = new JMenu("Ayuda");
		mnAyuda.addActionListener(this);
		mnBar.add(mnAyuda);
		
		mntmAcerca = new JMenuItem("Acerca de");
		mntmAcerca.addActionListener(this);
		mnAyuda.add(mntmAcerca);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmAcerca) {
			actionPerformedMntmAcerca(e);
		}
		if (e.getSource() == mntmReporte) {
			actionPerformedMntmReporte(e);
		}
		if (e.getSource() == mntmConsulta) {
			actionPerformedMntmConsulta(e);
		}
		if (e.getSource() == mntmRegistro) {
			actionPerformedMntmRegistro(e);
		}
		if (e.getSource() == mntmConsultorio) {
			actionPerformedMntmConsultorio(e);
		}
		if (e.getSource() == mntmMedico) {
			actionPerformedMntmMedico(e);
		}
		if (e.getSource() == mntmPaciente) {
			actionPerformedMntmPaciente(e);
		}
	}
	protected void actionPerformedMntmPaciente(ActionEvent e) {
		System.out.println("paciente");
	}
	protected void actionPerformedMntmMedico(ActionEvent e) {
		System.out.println("medico");
	}
	protected void actionPerformedMntmConsultorio(ActionEvent e) {
		System.out.println("consultorio");
	}
	protected void actionPerformedMntmRegistro(ActionEvent e) {
		System.out.println("registro");
	}
	protected void actionPerformedMntmConsulta(ActionEvent e) {
		System.out.println("consulta");
	}
	protected void actionPerformedMntmReporte(ActionEvent e) {
		System.out.println("reporte");
	}
	protected void actionPerformedMntmAcerca(ActionEvent e) {
		System.out.println("acerca");
		Acerca acer = new Acerca();
		acer.setLocationRelativeTo(this);
		acer.setModal(true);
		acer.setVisible(true);
	}
}
