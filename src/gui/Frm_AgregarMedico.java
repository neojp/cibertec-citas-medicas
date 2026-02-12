package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Frm_AgregarMedico extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField txtCodMedico;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtEspecialidad;
	private JTextField txtCmp;
	private JButton btnCancelarRegistro;
	private JTable tblBDMedico;
	private JTable tblBDMedicos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Frm_AgregarMedico dialog = new Frm_AgregarMedico();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Frm_AgregarMedico() {
		setTitle("< Registrar Medico >");
		setBounds(100, 100, 735, 306);
		getContentPane().setLayout(null);
		
		JLabel lblCodMedico = new JLabel("Código del médico :");
		lblCodMedico.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCodMedico.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCodMedico.setBounds(50, 37, 106, 22);
		getContentPane().add(lblCodMedico);
		
		JLabel lblNombres = new JLabel("Nombres :");
		lblNombres.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNombres.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombres.setBounds(50, 69, 106, 22);
		getContentPane().add(lblNombres);
		
		JLabel lblApellidos = new JLabel("Apellidos :");
		lblApellidos.setHorizontalAlignment(SwingConstants.TRAILING);
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblApellidos.setBounds(50, 102, 106, 22);
		getContentPane().add(lblApellidos);
		
		JLabel lblEspecialidad = new JLabel("Especialidad :");
		lblEspecialidad.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEspecialidad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEspecialidad.setBounds(310, 37, 97, 22);
		getContentPane().add(lblEspecialidad);
		
		JLabel lblCmp = new JLabel("CMP :");
		lblCmp.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCmp.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCmp.setBounds(301, 69, 106, 22);
		getContentPane().add(lblCmp);
		
		JLabel lblEstado = new JLabel("Estado :");
		lblEstado.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEstado.setBounds(301, 102, 106, 22);
		getContentPane().add(lblEstado);
		
		txtCodMedico = new JTextField();
		txtCodMedico.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCodMedico.setBounds(166, 39, 141, 18);
		getContentPane().add(txtCodMedico);
		txtCodMedico.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNombre.setColumns(10);
		txtNombre.setBounds(166, 71, 195, 18);
		getContentPane().add(txtNombre);
		
		txtApellidos = new JTextField();
		txtApellidos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(166, 109, 195, 18);
		getContentPane().add(txtApellidos);
		
		txtEspecialidad = new JTextField();
		txtEspecialidad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtEspecialidad.setColumns(10);
		txtEspecialidad.setBounds(417, 40, 155, 18);
		getContentPane().add(txtEspecialidad);
		
		txtCmp = new JTextField();
		txtCmp.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCmp.setColumns(10);
		txtCmp.setBounds(417, 71, 155, 18);
		getContentPane().add(txtCmp);
		
		JComboBox cboEstado = new JComboBox();
		cboEstado.setModel(new DefaultComboBoxModel(new String[] {"Seleccione >>>", "ACTIVO", "DESACTIVO"}));
		cboEstado.setSelectedIndex(0);
		cboEstado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cboEstado.setBounds(417, 103, 155, 20);
		getContentPane().add(cboEstado);
		
		JButton btnAgregar = new JButton("AGREGAR ");
		btnAgregar.setBackground(new Color(0, 128, 0));
		btnAgregar.setBounds(582, 37, 114, 31);
		getContentPane().add(btnAgregar);
		
		btnCancelarRegistro = new JButton("CANCELAR ");
		btnCancelarRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancelarRegistro.setBackground(new Color(178, 34, 34));
		btnCancelarRegistro.setBounds(582, 78, 116, 33);
		getContentPane().add(btnCancelarRegistro);
		
		JLabel lblNewLabel = new JLabel("REGISTRO DE PERSONAL MEDICO");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(77, 10, 495, 20);
		getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 137, 676, 122);
		getContentPane().add(scrollPane);
		
		tblBDMedicos = new JTable();
		//scrollPane.setViewportView(tblBDMedicos);
		
		tblBDMedico = new JTable();
		tblBDMedico.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CODIGO", "NOMBRES", "APELLIDOS", "ESPECIALIDAD", "CMP", "ESTADO"
			}
		));
		tblBDMedico.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblBDMedico);
		
		
		
	}
}
