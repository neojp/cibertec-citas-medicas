package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import arreglos.ArregloCitas;
import arreglos.ArregloMedico;
import clases.Cita;
import clases.Medico;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Frm_RegistroCitas extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel lblNum;
	private JTextField txtNumCita;
	private JLabel lblPac;
	private JComboBox cboPaciente;
	private JLabel lblMed;
	private JComboBox cboMedico;
	private JComboBox cboConsultorio;
	private JLabel lblFec;
	private JTextField txtFecha;
	private JLabel lblHor;
	private JTextField txtHora;
	private JLabel blMot;
	private JTextField txtMotivo;
	private JLabel lblEst;
	private JComboBox cboEstado;
	private JButton btnAdicionar;
	private JButton btnModificar;
	private JButton btnCancelar;
	private JButton btnLimpiar;
	private JScrollPane scrollPane;
	private JTable tblCitas;
	
	// INSTANCIAS DE LOS ARREGLOS
		private ArregloCitas objCita = new ArregloCitas();
		private ArregloMedico objMed = new ArregloMedico();

	
	
	public static void main(String[] args) {
		try {
			Frm_RegistroCitas dialog = new Frm_RegistroCitas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Frm_RegistroCitas() {
		setTitle("REGISTRO Y CONTROL DE CITAS MÉDICAS");
		setBounds(100, 100, 783, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);
		
		lblNum = new JLabel("N° Cita: ");
		lblNum.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNum.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNum.setBounds(20, 20, 80, 25);
		getContentPane().add(lblNum);
		
		txtNumCita = new JTextField();
		txtNumCita.setEditable(false);
        txtNumCita.setBounds(110, 20, 100, 25);
		getContentPane().add(txtNumCita);
		txtNumCita.setColumns(10);
		
		lblPac = new JLabel("Paciente: ");
		lblPac.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPac.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPac.setBounds(20, 60, 80, 25);
		getContentPane().add(lblPac);
		
		cboPaciente = new JComboBox();
		cboPaciente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cboPaciente.setBounds(110, 60, 219, 25);
		getContentPane().add(cboPaciente);
		
		lblMed = new JLabel("Médico :");
		lblMed.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMed.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMed.setBounds(377, 60, 80, 25);
		getContentPane().add(lblMed);
		
		cboMedico = new JComboBox();
		cboMedico.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cboMedico.setBounds(467, 60, 268, 25);
		getContentPane().add(cboMedico);
		
		JLabel lblCon = new JLabel("Consultorio:");
		lblCon.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCon.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCon.setBounds(20, 100, 80, 25);
        getContentPane().add(lblCon);
        
        cboConsultorio = new JComboBox();
        cboConsultorio.setFont(new Font("Tahoma", Font.PLAIN, 13));
        cboConsultorio.setBounds(110, 100, 219, 25);
        getContentPane().add(cboConsultorio);
        
        lblFec = new JLabel("Fecha (dd/mm/aaaa) :");
        lblFec.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFec.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblFec.setBounds(354, 100, 130, 25);
        getContentPane().add(lblFec);
        
        txtFecha = new JTextField();
        txtFecha.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtFecha.setBounds(494, 100, 100, 25);
        getContentPane().add(txtFecha);
        txtFecha.setColumns(10);
        
        lblHor = new JLabel("Hora :");
        lblHor.setHorizontalAlignment(SwingConstants.RIGHT);
        lblHor.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblHor.setBounds(604, 100, 51, 25);
        getContentPane().add(lblHor);
        
        txtHora = new JTextField();
        txtHora.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtHora.setBounds(665, 101, 70, 25);
        getContentPane().add(txtHora);
        txtHora.setColumns(10);
        
        blMot = new JLabel("Motivo: ");
        blMot.setFont(new Font("Tahoma", Font.PLAIN, 13));
        blMot.setHorizontalAlignment(SwingConstants.RIGHT);
        blMot.setBounds(20, 140, 80, 25);
        getContentPane().add(blMot);
        
        txtMotivo = new JTextField();
        txtMotivo.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtMotivo.setBounds(110, 140, 350, 25);
        getContentPane().add(txtMotivo);
        txtMotivo.setColumns(10);
        
        lblEst = new JLabel("Estado :");
        lblEst.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblEst.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEst.setBounds(480, 140, 60, 25);
        getContentPane().add(lblEst);
        
        cboEstado = new JComboBox();
        cboEstado.setFont(new Font("Tahoma", Font.PLAIN, 13));
        cboEstado.setModel(new DefaultComboBoxModel(new String[] {"PENDIENTE","ATENDIDA","CANCELADA"}));
        cboEstado.setEnabled(false);
        cboEstado.setBounds(550, 141, 190, 25);
        getContentPane().add(cboEstado);
        
        btnAdicionar = new JButton("REGISTRAR");    
        btnAdicionar.addActionListener(this);
        btnAdicionar.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnAdicionar.setBackground(new Color(46, 204, 113));
        btnAdicionar.setForeground(Color.WHITE);
        btnAdicionar.setBounds(50, 200, 150, 35);
        getContentPane().add(btnAdicionar);
        
        btnModificar = new JButton("REPROGRAMAR");
        btnModificar.addActionListener(this);
        btnModificar.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnModificar.setBackground(new Color(52, 152, 219));
        btnModificar.setForeground(Color.WHITE);
        btnModificar.setBounds(230, 200, 150, 35);
        getContentPane().add(btnModificar);
        
        btnCancelar = new JButton("CANCELAR CITA");
        btnCancelar.addActionListener(this);
        btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnCancelar.setBackground(new Color(231, 76, 60));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setBounds(410, 200, 150, 35);
        getContentPane().add(btnCancelar);
        
        btnLimpiar = new JButton("NUEVO / LIMPIAR");
        btnLimpiar.addActionListener(this);
        btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnLimpiar.setBounds(590, 200, 169, 35);
        getContentPane().add(btnLimpiar);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 260, 722, 280);
        getContentPane().add(scrollPane);
        
        tblCitas = new JTable();
        tblCitas.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"N° CITA","PACIENTE","MÉDICO","CONSULTORIO","FECHA","HORA","ESTADO"
        	}
        ) {
        	boolean[] columnEditables = new boolean[] {
        		false,false,false,false,false,false,false
        	};
        	public boolean isCellEditable(int row, int column) {
        		return columnEditables[column];
        	}
        });
        
        tblCitas.setRowHeight(25);
        tblCitas.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 13));
        tblCitas.setSelectionBackground(new Color(52, 152, 219));
        scrollPane.setViewportView(tblCitas);
        
        tblCitas.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting() && tblCitas.getSelectedRow() != -1) {

                btnAdicionar.setEnabled(false);
                btnModificar.setEnabled(true);
                btnCancelar.setEnabled(true);
            }
        });
	}
	

	
	
	
	

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLimpiar) {
			actionPerformedBtnLimpiar(e);
		}
		if (e.getSource() == btnCancelar) {
			actionPerformedBtnCancelar(e);
		}
		if (e.getSource() == btnModificar) {
			actionPerformedBtnModificar(e);
		}
		if (e.getSource() == btnAdicionar) {
			actionPerformedBtnAdicionar(e);
		}
	}
	protected void actionPerformedBtnAdicionar(ActionEvent e) {
		
	}
	protected void actionPerformedBtnModificar(ActionEvent e) {
		
	}
	protected void actionPerformedBtnCancelar(ActionEvent e) {
		
	}
	protected void actionPerformedBtnLimpiar(ActionEvent e) {
		
	}
	
	private void cargarCombos() {
		cboMedico.addItem("SELECCIONE MÉDICO");
		for (int i = 0; i < objMed.tamano(); i++) {
			Medico m = objMed.obtener(i);
			cboMedico.addItem(m.getCodMedico() + " - " + m.getNombres() + " " + m.getApellidos());
		}
		
		//PACIENTE
		cboPaciente.addItem("SELECCIONE PACIENTE");
		// for(int i=0; i < objPac.tamano(); i++) ...
		
		cboConsultorio.setModel(new DefaultComboBoxModel<>(new String[] {"SELECCIONE", "CONS. 101", "CONS. 102", "CONS. 201"}));
	}
	

	
	
	
	
			
	
		
		
		
		
		
		
		
		
	
	
	//METODO ESTADO INICIAR DE CAJAS DE TEXTO Y LOS COMBOS
		private void estadoInicial() {
		    txtNumCita.setEditable(false);
		    cboEstado.setEnabled(false);
		    btnAdicionar.setEnabled(true);
		    btnModificar.setEnabled(false);
		    btnCancelar.setEnabled(false);
		    tblCitas.clearSelection();
		}
	
	
	//METODO LIMPIAR
		private void limpiar() {

		    txtNumCita.setText("");
		    txtFecha.setText("");
		    txtHora.setText("");
		    txtMotivo.setText("");

		    cboPaciente.setSelectedIndex(0);
		    cboMedico.setSelectedIndex(0);
		    cboConsultorio.setSelectedIndex(0);
		    cboEstado.setSelectedIndex(0);
		    estadoInicial();
		}
		
		//METODO PARA MENSAJE ERROR
				private void MENSAJEERROR(String texto) {
					JOptionPane.showMessageDialog(this, texto, "ERROR", JOptionPane.ERROR_MESSAGE);
						}
				//METODO PARA MENSAJE DE CONFIRMACION
				private void MENSAJEINFORMACION(String texto) {
						    JOptionPane.showMessageDialog(this, texto, "SISTEMA", JOptionPane.INFORMATION_MESSAGE);
						}
}
