package gui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

import clases.Medico;
import arreglo.ArregloMedico;

public class Frm_MantMedico extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodMedico;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtCmp;
	private DefaultTableModel modelo;
	private JTable tblBDMedico;
	private JComboBox<String> cboEspecialidad;
	private JComboBox<String> cboEstado;
	private JButton btnAgregar;
	private ArregloMedico objArreglo = new ArregloMedico();
	private JButton btnCancelarRegistro;
	private JButton btnModificar;
	private JButton btnEliminar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Frm_MantMedico dialog = new Frm_MantMedico();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public Frm_MantMedico() {
		setTitle("GESTIÓN DE MÉDICOS"); 
		setBounds(100, 100, 690, 508);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblCod = new JLabel("Código:");
			lblCod.setBounds(34, 37, 80, 20);
			lblCod.setHorizontalAlignment(SwingConstants.RIGHT);
			lblCod.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPanel.add(lblCod);
		}
		{
			txtCodMedico = new JTextField("");
			txtCodMedico.setBounds(119, 37, 80, 20);
			txtCodMedico.setHorizontalAlignment(SwingConstants.CENTER);
			txtCodMedico.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPanel.add(txtCodMedico);
			txtCodMedico.setEditable(false);
		}
		{
			JLabel lblNom = new JLabel("Nombres:");
			lblNom.setBounds(34, 72, 80, 20);
			lblNom.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNom.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPanel.add(lblNom);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setBounds(119, 72, 170, 20);
			txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPanel.add(txtNombre);
		}
		{
			JLabel lblApe = new JLabel("Apellidos:");
			lblApe.setBounds(34, 107, 80, 20);
			lblApe.setHorizontalAlignment(SwingConstants.RIGHT);
			lblApe.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPanel.add(lblApe);
		}
		{
			txtApellidos = new JTextField();
			txtApellidos.setBounds(119, 107, 170, 20);
			txtApellidos.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPanel.add(txtApellidos);
		}
		{
			JLabel lblEsp = new JLabel("Especialidad:");
			lblEsp.setBounds(299, 37, 90, 20);
			lblEsp.setHorizontalAlignment(SwingConstants.RIGHT);
			lblEsp.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPanel.add(lblEsp);
		}
		{
			cboEspecialidad = new JComboBox<String>();
			cboEspecialidad.setBounds(394, 37, 247, 20);
			cboEspecialidad.addItem("SELECCIONE >>>");
			for(String esp : Medico.especialidades) {
				cboEspecialidad.addItem(esp);
			}
			cboEspecialidad.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPanel.add(cboEspecialidad);
		}
		{
			JLabel lblC = new JLabel("CMP:");
			lblC.setBounds(321, 72, 68, 20);
			lblC.setHorizontalAlignment(SwingConstants.RIGHT);
			lblC.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPanel.add(lblC);
		}
		{
			txtCmp = new JTextField();
			txtCmp.setBounds(394, 72, 220, 20);
			txtCmp.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPanel.add(txtCmp);
			
			// VALIDACION DEL CAMPO CODIGO DE MEDICO SOLO NUMEROS Y MAXIMO 8 LETRAS
	        txtCmp.addKeyListener(new KeyAdapter() {
	            @Override
	            public void keyTyped(KeyEvent e) {

	                char c = e.getKeyChar();

	                // SOLO NUMEROS 
	                if (!Character.isDigit(c)) {
	                    e.consume();
	                }

	                // MAXIMO DE 8 DIGITOS 
	                if (txtCmp.getText().length() >= 8) {
	                    e.consume();
	                }
	            }
	        });
		}
		{
			JLabel lblEst = new JLabel("Estado:");
			lblEst.setBounds(315, 107, 74, 20);
			lblEst.setHorizontalAlignment(SwingConstants.RIGHT);
			lblEst.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPanel.add(lblEst);
		}
		{
			cboEstado = new JComboBox<String>();
			cboEstado.setBounds(394, 107, 150, 20);	
			cboEstado.setModel(new DefaultComboBoxModel<>(new String[] {"SELECCIONE >>>", "INACTIVO", "ACTIVO"}));
			cboEstado.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPanel.add(cboEstado);
		}
		// BOTONES DEL SISTEMA 
		{
			btnAgregar = new JButton("AGREGAR");
			btnAgregar.addActionListener(this);
			btnAgregar.setBounds(34, 152, 130, 35);
			btnAgregar.setForeground(Color.WHITE);
			btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnAgregar.setFocusPainted(false);
			btnAgregar.setBackground(new Color(0, 102, 204));
			contentPanel.add(btnAgregar);
		}
		{
			btnModificar = new JButton("MODIFICAR");
			btnModificar.addActionListener(this);
			btnModificar.setBounds(195, 153, 130, 35);
			btnModificar.setEnabled(false);
			btnModificar.setForeground(Color.WHITE);
			btnModificar.setBackground(new Color(70, 130, 180));
			contentPanel.add(btnModificar);
		}
		{
			btnEliminar = new JButton("ELIMINAR");
			btnEliminar.addActionListener(this);
			btnEliminar.setBounds(358, 153, 130, 35);
			btnEliminar.setEnabled(false);
			btnEliminar.setForeground(Color.WHITE);
			btnEliminar.setBackground(new Color(220, 20, 60));
			contentPanel.add(btnEliminar);
		}
		{
			btnCancelarRegistro = new JButton("CANCELAR");
			btnCancelarRegistro.addActionListener(this);
			btnCancelarRegistro.setBounds(511, 153, 130, 35);
			btnCancelarRegistro.setForeground(Color.WHITE);
			btnCancelarRegistro.setBackground(new Color(128, 64, 64));
			contentPanel.add(btnCancelarRegistro);
		}
		{
			JScrollPane scpBD = new JScrollPane();
			scpBD.setBounds(10, 229, 645, 232);
			contentPanel.add(scpBD);
			{
				tblBDMedico = new JTable();
				modelo = new DefaultTableModel(
						new Object[][] {},
						new String[] { "CODIGO", "NOMBRES", "APELLIDOS", "ESPECIALIDAD", "CMP", "ESTADO" }
					);
					tblBDMedico.setModel(modelo);
				scpBD.setViewportView(tblBDMedico);
			}
		}
		
		tblBDMedico.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseClicked(java.awt.event.MouseEvent e) {
		        seleccionarFila();
		        }});
		//INICIALIZAR LOS EVENTOS 
		
		LISTAR();
        LIMPIAR();
        txtCodMedico.setText(Medico.generarCodMedico() + "");
}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(e);
		}
		if (e.getSource() == btnModificar) {
			actionPerformedBtnModificar(e);
		}
		if (e.getSource() == btnCancelarRegistro) {
			actionPerformedBtnCancelarRegistro(e);
		}
		if (e.getSource() == btnAgregar) {
			actionPerformedBtnAgregar(e);
		}
	}
	protected void actionPerformedBtnAgregar(ActionEvent e) {
		
		try {
		    String nom = txtNombre.getText().trim().toUpperCase();
		    String ape = txtApellidos.getText().trim().toUpperCase();
		    String cmp = txtCmp.getText().trim();

		    if (nom.isEmpty() || ape.isEmpty() || cmp.isEmpty()
		            || cboEspecialidad.getSelectedIndex() == 0
		            || cboEstado.getSelectedIndex() == 0) {

		        MENSAJEERROR("DEBES COMPLETAR TODOS LOS CAMPOS");
		        return;
		    }

		    String esp = cboEspecialidad.getSelectedItem().toString();
		    int est = cboEstado.getSelectedIndex();

		    objArreglo.adicionar(new Medico(nom, ape, esp, cmp, est - 1));

		    LISTAR();
		    LIMPIAR();
		    txtCodMedico.setText(Medico.generarCodMedico() + ""); // ACTUALIZA EL CODIGO PARA EL PROXIMO MEDICO

		    MENSAJEINFORMACION("MEDICO REGISTRADO CORRECTAMENTE EN BD.");

		} catch (Exception ex) {
		    MENSAJEERROR("ERROR: " + ex.getMessage());
		}		
	}
	
	//CARGA TODOS LOS MÉDICOS ALMACENADOS EN EL ARREGLO Y LOS MUESTRA EN LA TABLA (JTABLE)
	private void LISTAR() {
	    modelo.setRowCount(0);
	    for (int i = 0; i < objArreglo.tamano(); i++) {
	        Medico m = objArreglo.obtener(i);
	        modelo.addRow(new Object[] {
	            m.getCodMedico(),
	            m.getNombres(),
	            m.getApellidos(),
	            m.getEspecialidad(),
	            m.getCmp(),
	            Medico.estados[m.getEstado()]
	        });
	    }
	}

	
	
	protected void actionPerformedBtnCancelarRegistro(ActionEvent e) {
		if (e.getSource() == btnCancelarRegistro) {
		    LIMPIAR();
		    btnAgregar.setEnabled(true);
		    txtCodMedico.setText(Medico.generarCodMedico() + ""); // ACTUALIZA EL CODIGO PARA EL PROXIMO MEDICO
		}

	}
	protected void actionPerformedBtnModificar(ActionEvent e) {
		try {

	        String cod = txtCodMedico.getText();
	        String nom = txtNombre.getText().trim().toUpperCase();
	        String ape = txtApellidos.getText().trim().toUpperCase();
	        String cmp = txtCmp.getText().trim();
	        int espIndex = cboEspecialidad.getSelectedIndex();
	        int estIndex = cboEstado.getSelectedIndex();

	        // VALIDACIÓN
	        if (cod.isEmpty() ||
	            nom.isEmpty() ||
	            ape.isEmpty() ||
	            cmp.isEmpty() ||
	            espIndex == 0 ||
	            estIndex == 0) {

	            MENSAJEERROR("DEBE SELECCIONAR UN REGISTRO Y COMPLETAR TODOS LOS CAMPOS");
	            return;
	        }

	        // BUSCAR MÉDICO
	        Medico m = objArreglo.buscarCodMedico(Integer.parseInt(cod));

	        if (m != null) {

	            // ACTUALIZAR DATOS
	            m.setNombres(nom);
	            m.setApellidos(ape);
	            m.setEspecialidad(cboEspecialidad.getSelectedItem().toString());
	            m.setCmp(cmp);
	            m.setEstado(estIndex - 1);

	            // GUARDAR CAMBIOS
	            objArreglo.actualizar();
	            btnAgregar.setEnabled(true);
	            txtCodMedico.setText(Medico.generarCodMedico() + ""); // ACTUALIZA EL CODIGO PARA EL PROXIMO MEDICO
	            MENSAJEINFORMACION("MÉDICO ACTUALIZADO CORRECTAMENTE...");
	            // REFRESCAR TABLA
	            LISTAR();
	            LIMPIAR();
	        } else {
	            MENSAJEERROR("NO SE ENCONTRÓ EL MÉDICO.");
	        }

	    } catch (Exception ex) {
	        MENSAJEERROR("ERROR AL ACTUALIZAR: " + ex.getMessage());
	    }	
	}
	
	// METODO QUE PERMITE SELECCIONAR UNA FILA DEL JTABLE
	private void seleccionarFila() {
	    int fila = tblBDMedico.getSelectedRow();
	    if (fila != -1) {

	        txtCodMedico.setText(tblBDMedico.getValueAt(fila, 0).toString());
	        txtNombre.setText(tblBDMedico.getValueAt(fila, 1).toString());
	        txtApellidos.setText(tblBDMedico.getValueAt(fila, 2).toString());
	        cboEspecialidad.setSelectedItem(tblBDMedico.getValueAt(fila, 3).toString());
	        txtCmp.setText(tblBDMedico.getValueAt(fila, 4).toString());

	        // AJUSTAMOS EL ESTADO DEL MEDICO SEGUN EL VALOR MOSTRADO 
	        String estadoTexto = tblBDMedico.getValueAt(fila, 5).toString();
	        if (estadoTexto.equals("INACTIVO"))
	            cboEstado.setSelectedIndex(1);
	        else
	            cboEstado.setSelectedIndex(2);
	        btnAgregar.setEnabled(false);
	        btnModificar.setEnabled(true);
	        btnEliminar.setEnabled(true);
	     }
	}

	protected void actionPerformedBtnEliminar(ActionEvent e) {
		
		try {
	        String cod = txtCodMedico.getText();
	        if (cod.isEmpty()) {
	            MENSAJEERROR("DEBE SELECCIONAR UN REGISTRO PARA ELIMINAR...");
	            return;
	        }

	        int respuesta = JOptionPane.showConfirmDialog(
	                this,
	                "ESTÁ SEGURO DE ELIMINAR ESTE MÉDICO?",
	                "CONFIRMAR ELIMINACIÓN",
	                JOptionPane.YES_NO_OPTION
	        );

	        if (respuesta == JOptionPane.YES_OPTION) {
				Medico medico = objArreglo.buscarCodMedico(Integer.parseInt(cod));
	            objArreglo.eliminar(medico);
	            txtCodMedico.setText(Medico.generarCodMedico() + ""); // ACTUALIZA EL CODIGO PARA EL PROXIMO MEDICO

	            MENSAJEINFORMACION("MÉDICO ELIMINADO CORRECTAMENTE (´;︵;`)");
	            LISTAR();
	            LIMPIAR();
	            txtCodMedico.setText(Medico.generarCodMedico() + ""); // ACTUALIZA EL CODIGO PARA EL PROXIMO MEDICO
	        }

	    } catch (Exception ex) {
	        MENSAJEERROR("ERROR AL ELIMINAR: " + ex.getMessage());
	    }
	}
	//METODO QUE LIMPIA 
	void LIMPIAR(){
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
