package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import clases.Medico;
import arreglos.ArregloMedico;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;


public class MantenimientoM extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTable tblMedicos;
	private JButton btnCancelar;
	private JButton btnAgregar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private DefaultTableModel modelo;
	private JTextField txtCodMedico, txtNombre, txtApellidos, txtCmp;
	private JComboBox<String> cboEspecialidad, cboEstado;

	
	// INSTANCIA DEL ARREGLO QUE MANEJA EL ARCHIVO TXT Y EL ARRAYLIST
		private ArregloMedico objArreglo = new ArregloMedico();
		
	public static void main(String[] args) {
		try {
			MantenimientoM dialog = new MantenimientoM();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MantenimientoM() {
		setTitle("MANTENIMIENTO DE MÉDICOS");
	    setSize(812, 600);
	    setLocationRelativeTo(null);
	    setResizable(false);
	    getContentPane().setLayout(null);
		
		JLabel lblCod = new JLabel("CODIGO : ");
		lblCod.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCod.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCod.setBounds(51, 30, 93, 25);
		getContentPane().add(lblCod);
		
		txtCodMedico = new JTextField(); 
	    txtCodMedico.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    txtCodMedico.setEditable(false);
	    txtCodMedico.setBounds(154, 30, 100, 25);
	    getContentPane().add(txtCodMedico);
		
		JLabel lblNom = new JLabel("NOMBRES : ");
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNom.setBounds(51, 65, 93, 25);
		getContentPane().add(lblNom);
		
		txtNombre = new JTextField(); 
	    txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    txtNombre.setBounds(154, 65, 243, 25);
	    getContentPane().add(txtNombre);
		
		JLabel lblApe = new JLabel("APELLIDOS :");
		lblApe.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblApe.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApe.setBounds(407, 66, 100, 25);
		getContentPane().add(lblApe);
		
		txtApellidos = new JTextField(); 
	    txtApellidos.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    txtApellidos.setBounds(517, 66, 266, 25);
	    getContentPane().add(txtApellidos);
		
		JLabel lblEsp = new JLabel("ESPECIALIDAD : ");
		lblEsp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEsp.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEsp.setBounds(24, 110, 120, 25);
		getContentPane().add(lblEsp);
		
		cboEspecialidad = new JComboBox<String>(Medico.especialidades); 
		cboEspecialidad.setModel(new DefaultComboBoxModel(new String[] {"SELECCIONE >>>>", "ANESTESIOLOGÍA", "CARDIOLOGÍA", "CIRUGÍA GENERAL", "CIRUGÍA PLÁSTICA", "DERMATOLOGÍA", "ENDOCRINOLOGÍA", "GASTROENTEROLOGÍA", "GINECOLOGÍA Y OBSTETRICIA", "MEDICINA FAMILIAR", "MEDICINA FÍSICA Y REHABILITACIÓN", "MEDICINA GENERAL", "MEDICINA INTERNA", "NEFROLOGÍA", "NEUMOLOGÍA", "NEUROLOGÍA", "OFTALMOLOGÍA", "ONCOLOGÍA", "OTORRINOLARINGOLOGÍA", "PEDIATRÍA", "PSIQUIATRÍA", "RADIOLOGÍA", "TRAUMATOLOGÍA", "UROLOGÍA"}));
	    cboEspecialidad.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    cboEspecialidad.setBounds(154, 110, 243, 25);
	    getContentPane().add(cboEspecialidad);
		
		JLabel lblCmp = new JLabel("CMP :");
		lblCmp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCmp.setBounds(439, 110, 50, 25);
		getContentPane().add(lblCmp);
		
		txtCmp = new JTextField(); 
	    txtCmp.setText("");
	    txtCmp.setBounds(486, 110, 93, 25);
	    getContentPane().add(txtCmp);
		
		JLabel lblEst = new JLabel("ESTADO :");
		lblEst.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEst.setBounds(589, 110, 60, 25);
		getContentPane().add(lblEst);
		
		cboEstado = new JComboBox<String>();
	    cboEstado.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    cboEstado.setModel(new DefaultComboBoxModel(new String[] {"ACTIVO", "INACTIVO"}));
	    cboEstado.setBounds(659, 110, 124, 25);
	    getContentPane().add(cboEstado);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 155, 758, 353);
		getContentPane().add(scrollPane);
		
		tblMedicos = new JTable();
		tblMedicos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		modelo = new DefaultTableModel(
				new Object[][] {},
				new String[] {"CÓDIGO","NOMBRES","APELLIDOS","ESPECIALIDAD","CMP","ESTADO"}
			) {
				@Override
				public boolean isCellEditable(int row, int column) { return false; }};
		
		tblMedicos.setModel(modelo); 
		scrollPane.setViewportView(tblMedicos);
		// EVENTO PARA SELECCIONAR FILA
				tblMedicos.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) { seleccionarFila(); }
				});
		
		
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(638, 518, 145, 35);
		getContentPane().add(btnCancelar);
		
		btnAgregar = new JButton("AGREGAR");
		btnAgregar.addActionListener(this);
		btnAgregar.setForeground(Color.WHITE);
		btnAgregar.setEnabled(true);
		btnAgregar.setBackground(new Color(0, 102, 204));
		btnAgregar.setBounds(24, 518, 145, 35);
		getContentPane().add(btnAgregar);
		
		btnModificar = new JButton("MODIFICAR");
		btnModificar.addActionListener(this);
		btnModificar.setEnabled(false);
		btnModificar.setBounds(243, 518, 145, 35);
		getContentPane().add(btnModificar);
		
		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addActionListener(this);
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(463, 518, 145, 35);
		getContentPane().add(btnEliminar);
		
		listar();
		limpiar();
		

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(e);
		}
		if (e.getSource() == btnModificar) {
			actionPerformedBtnModificar(e);
		}
		if (e.getSource() == btnAgregar) {
			actionPerformedBtnAgregar(e);
		}
		if (e.getSource() == btnCancelar) {
			actionPerformedBtnNuevo(e);
		}
	}
	protected void actionPerformedBtnNuevo(ActionEvent e) {
		limpiar();
		
	}
	protected void actionPerformedBtnAgregar(ActionEvent e) {
		try {
		    // VALIDAR SI LA CAJA DE CODIGO ESTA VACIA 
		    String strCod = txtCodMedico.getText().trim();
		    if (strCod.isEmpty()) {
		        MENSAJEERROR("EL CÓDIGO NO HA SIDO GENERADO. PRESIONA CANCELAR.");
		        return;
		    }
		    
		    int cod = Integer.parseInt(strCod);
		    String nom = txtNombre.getText().trim().toUpperCase();
		    String ape = txtApellidos.getText().trim().toUpperCase();
		    String cmp = txtCmp.getText().trim();

		    // VALIDACIÓN DE CAMPOS OBLIGATORIOS
		    if (nom.isEmpty() || ape.isEmpty() || cmp.isEmpty() || 
		        cboEspecialidad.getSelectedIndex() == 0) {
		        MENSAJEERROR("TODOS LOS CAMPOS SON OBLIGATORIOS.");
		        txtNombre.requestFocus();
		        return;
		    }
		    
		    // VALIDACION DE EL CMP NO PUEDE PASAR LOS 8 DIGITOS 
		    if (cmp.length() > 8) {
		        MENSAJEERROR("EL CMP NO PUEDE TENER MÁS DE 8 DÍGITOS.");
		        txtCmp.requestFocus();
		        return;
		    }
		    // ------------------------------------------------

		    //  EL CMP SOLO ASEPTA NUMEROS
		    try {
		        Integer.parseInt(cmp);
		    } catch (NumberFormatException nfe) {
		        MENSAJEERROR("EL CMP DEBE SER UN NÚMERO VÁLIDO.");
		        txtCmp.requestFocus();
		        return;
		    }

		    String esp = cboEspecialidad.getSelectedItem().toString();
		    int est = cboEstado.getSelectedIndex(); 

		    //  SI TODO ESTABIEN SE GUARDA TODO 
		    objArreglo.adicionar(new Medico(cod, nom, ape, esp, cmp, est));
		    
		    MENSAJEINFORMACION("MÉDICO REGISTRADO EN BD  CON CÓDIGO: " + cod);
		    listar(); 
		    limpiar(); 

		} catch (Exception ex) {
		    MENSAJEERROR("ERROR CRÍTICO: " + ex.getMessage());
		    ex.printStackTrace();
		}
		
	}
	protected void actionPerformedBtnModificar(ActionEvent e) {
		try {
			int cod = Integer.parseInt(txtCodMedico.getText());
			Medico m = objArreglo.buscar(cod);
			if (m != null) {
				String nom = txtNombre.getText().trim().toUpperCase();
				String ape = txtApellidos.getText().trim().toUpperCase();
				String cmp = txtCmp.getText().trim();

				if (nom.isEmpty() || ape.isEmpty() || cmp.length() != 8) {
					MENSAJEERROR("VALIDE LOS DATOS (EL CMP DEBE SER DE 8 DÍGITOS).");
					return;
				}

				m.setNombres(nom);
				m.setApellidos(ape);
				m.setEspecialidad(cboEspecialidad.getSelectedItem().toString());
				m.setCmp(cmp);
				m.setEstado(cboEstado.getSelectedIndex());

				objArreglo.grabarArchivo();
				MENSAJEINFORMACION("REGISTRO ACTUALIZADO.");
				listar();
				limpiar();
			}
		} catch (Exception ex) { MENSAJEERROR("ERROR AL MODIFICAR."); }
	}
	protected void actionPerformedBtnEliminar(ActionEvent e) {
		try {
			int cod = Integer.parseInt(txtCodMedico.getText());
			Medico m = objArreglo.buscar(cod);
			if (m != null) {
				int rpta = JOptionPane.showConfirmDialog(this, "¿DESEA ELIMINAR ESTE MÉDICO?", "SISTEMA", JOptionPane.YES_NO_OPTION);
				if (rpta == JOptionPane.YES_OPTION) {
					objArreglo.eliminar(m);
					listar();
					limpiar();
				}
			}
		} catch (Exception ex) { MENSAJEERROR("SELECCIONE UN REGISTRO."); }
		
	}
	
	// MÉTODO QUE ACTUALIZA Y MUESTRA EN LA TABLA TODOS LOS MÉDICOS REGISTRADOS
		private void listar() {
			modelo.setRowCount(0); 
			for (int i = 0; i < objArreglo.tamano(); i++) {
				Medico m = objArreglo.obtener(i);
				modelo.addRow(new Object[] { 
					m.getCodMedico(), m.getNombres(), m.getApellidos(), 
					m.getEspecialidad(), m.getCmp(), 
					m.getEstado() == 0 ? "ACTIVO" : "INACTIVO"
				});
			}
		}
		
		private void seleccionarFila() {
			int fila = tblMedicos.getSelectedRow();
			if (fila != -1) {
				txtCodMedico.setText(modelo.getValueAt(fila, 0).toString());
				txtNombre.setText(modelo.getValueAt(fila, 1).toString());
				txtApellidos.setText(modelo.getValueAt(fila, 2).toString());
				cboEspecialidad.setSelectedItem(modelo.getValueAt(fila, 3).toString());
				txtCmp.setText(modelo.getValueAt(fila, 4).toString());
				cboEstado.setSelectedIndex(modelo.getValueAt(fila, 5).toString().equals("ACTIVO") ? 0 : 1);
				
				btnAgregar.setEnabled(false);
				btnModificar.setEnabled(true);
				btnEliminar.setEnabled(true);
			}
		}
		private void limpiar() {
		    
			txtCodMedico.setText("" + objArreglo.proximoCodigo());
		    txtNombre.setText("");
		    txtApellidos.setText(""); 
		    txtCmp.setText("");
		    cboEspecialidad.setSelectedIndex(0);
		    cboEstado.setSelectedIndex(0); 
		    txtNombre.requestFocus();
		    
		    btnModificar.setEnabled(false);
			btnEliminar.setEnabled(false);
			btnAgregar.setEnabled(true);
			
		    
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
