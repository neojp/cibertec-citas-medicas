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

public class ConsultaCitas extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnOk, btnBuscar;
	private JPanel panelTipo, bodyPanel, buttonPane, panelBuscar;
	private JLabel lblNewLabel, lblCodigo;
	private JTextField txtCodigo;
	private JComboBox<String> cboConsultar;
	private JTable tblTabla;
	private JScrollPane scp;

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
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		// SELECCIONA TIPO DE CONSULTA
		panelTipo = new JPanel();
		contentPanel.add(panelTipo);
		panelTipo.setLayout(new BoxLayout(panelTipo, BoxLayout.X_AXIS));
		panelTipo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

		lblNewLabel = new JLabel("Consultar por: ");
		panelTipo.add(lblNewLabel);

		panelTipo.add(Box.createHorizontalStrut(100));

		cboConsultar = new JComboBox<String>(new String[] { "Paciente", "Médico", "Consultorio", "Fecha"});
		panelTipo.add(cboConsultar);
		
		contentPanel.add(Box.createVerticalStrut(10));
		
		// BUQUEDA		
		panelBuscar = new JPanel();
		contentPanel.add(panelBuscar);
		panelBuscar.setLayout(new BoxLayout(panelBuscar, BoxLayout.X_AXIS));
		panelBuscar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
		
		lblCodigo = new JLabel("Código paciente:");
		panelBuscar.add(lblCodigo);
		panelBuscar.add(Box.createHorizontalStrut(10));
		
		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
		panelBuscar.add(txtCodigo);
		
		panelBuscar.add(Box.createHorizontalStrut(10));
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setMaximumSize(new Dimension(150, 30));
		btnBuscar.setBackground(new Color(64,64,128));
		btnBuscar.setForeground(new Color(255,255,255));
		panelBuscar.add(btnBuscar);
		
		
		
		// CUERPO
		bodyPanel = new JPanel();
		contentPanel.add(bodyPanel);
		bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
		
		String[] headerTable = {"Estado", "Fecha", "hora", "Médico", "Consultorio"};
		String[][] tempData = {
			{"activo", "04/03/26","14:30","Luis Manco", "20A"},
			{"inactivo", "05/04/26", "15:00", "Maria Gomez", "11B"}
		};
		
		DefaultTableModel modelo = new DefaultTableModel(tempData, headerTable);
		tblTabla = new JTable(modelo);
		scp = new JScrollPane(tblTabla);
		scp.setBorder(new EmptyBorder(20,0,20,0));
		
		bodyPanel.add(scp);
				
		
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
		if (e.getSource() == btnOk) {
			actionPerformedBtnOk(e);
		}
	}
	protected void actionPerformedBtnOk(ActionEvent e) {
		this.dispose();
	}
}
