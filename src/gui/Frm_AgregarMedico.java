package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import clases.Medico;
import arreglo.ArregloMedico2;
import libreria.Validacion;

public class Frm_AgregarMedico extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtCodMedico;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtCmp;
	private JTable tblBDMedico;
	private JButton btnAgregar;
	private JButton btnCancelarRegistro;
	private JComboBox<String> cboEstado;
	private JComboBox<String> cboEspecialidad;
	private DefaultTableModel modelo;

	
	ArregloMedico2 objArreglo = new ArregloMedico2();
	private JLabel lblMensaje;

	public static void main(String[] args) {
		try {
			Frm_AgregarMedico dialog = new Frm_AgregarMedico();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Frm_AgregarMedico() {
		setTitle("< Registrar Medico >");
		setBounds(100, 100, 735, 410);
		getContentPane().setLayout(null);
		
		JLabel lblCodMedico = new JLabel("Código del médico :");
		lblCodMedico.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCodMedico.setBounds(21, 37, 135, 22);
		getContentPane().add(lblCodMedico);
		
		JLabel lblNombres = new JLabel("Nombres :");
		lblNombres.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNombres.setBounds(50, 69, 106, 22);
		getContentPane().add(lblNombres);
		
		JLabel lblApellidos = new JLabel("Apellidos :");
		lblApellidos.setHorizontalAlignment(SwingConstants.TRAILING);
		lblApellidos.setBounds(50, 102, 106, 22);
		getContentPane().add(lblApellidos);
		
		JLabel lblEspecialidad = new JLabel("Especialidad :");
		lblEspecialidad.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEspecialidad.setBounds(301, 37, 85, 22);
		getContentPane().add(lblEspecialidad);
		
		JLabel lblCmp = new JLabel("CMP :");
		lblCmp.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCmp.setBounds(301, 69, 106, 22);
		getContentPane().add(lblCmp);
		
		JLabel lblEstado = new JLabel("Estado :");
		lblEstado.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEstado.setBounds(301, 102, 106, 22);
		getContentPane().add(lblEstado);
		
		lblMensaje = new JLabel("");
		lblMensaje.setForeground(new Color(255, 0, 0)); // Color Rojo para errores
		lblMensaje.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensaje.setBounds(199, 147, 350, 36);
		getContentPane().add(lblMensaje);
		
		
		
		// --- CAMPOS DE TEXTO ---
		txtCodMedico = new JTextField();
		txtCodMedico.setEditable(false); // BLOQUEADO: Se autogenera
		txtCodMedico.setBounds(166, 39, 123, 18);
		getContentPane().add(txtCodMedico);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(166, 71, 130, 18);
		getContentPane().add(txtNombre);
		
		txtApellidos = new JTextField();
		txtApellidos.setBounds(166, 109, 130, 18);
		getContentPane().add(txtApellidos);
		
		txtCmp = new JTextField();
		txtCmp.setBounds(417, 71, 155, 18);
		getContentPane().add(txtCmp);
		
		// --- COMBO BOXES ---
		cboEstado = new JComboBox<String>();
		cboEstado.setModel(new DefaultComboBoxModel<>(new String[] {"Seleccione >>>", "INACTIVO", "ACTIVO"}));
		cboEstado.setBounds(417, 103, 155, 20);
		getContentPane().add(cboEstado);
		
		cboEspecialidad = new JComboBox<String>();
		cboEspecialidad.setBounds(400, 39, 172, 20);
		// LLENAR COMBOBOX  DESDE EL ARREGLO DE LA CLASE MEDICO
		cboEspecialidad.addItem("Seleccione >>>");
		for(String esp : Medico.especialidades) {
			cboEspecialidad.addItem(esp);
		}
		getContentPane().add(cboEspecialidad);
		

		btnAgregar = new JButton("AGREGAR");
		btnAgregar.setForeground(Color.WHITE);
		btnAgregar.setBackground(new Color(0, 64, 128));
		btnAgregar.addActionListener(this);
		btnAgregar.setBounds(582, 37, 114, 31);
		getContentPane().add(btnAgregar);
		
		btnCancelarRegistro = new JButton("CANCELAR");
		btnCancelarRegistro.setForeground(Color.WHITE);
		btnCancelarRegistro.setBackground(new Color(128, 64, 64));
		btnCancelarRegistro.addActionListener(this);
		btnCancelarRegistro.setBounds(582, 78, 116, 33);
		getContentPane().add(btnCancelarRegistro);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 194, 676, 156);
		getContentPane().add(scrollPane);
		
		tblBDMedico = new JTable();
		modelo = new DefaultTableModel(
			new Object[][] {},
			new String[] { "CODIGO", "NOMBRES", "APELLIDOS", "ESPECIALIDAD", "CMP", "ESTADO" }
		);
		tblBDMedico.setModel(modelo);
		scrollPane.setViewportView(tblBDMedico);

		// --- CARGA INICIAL ---
		txtCodMedico.setText(Medico.proximoCodigo());
		listar();
		txtNombre.requestFocus();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAgregar) {
			actionPerformedBtnAgregar(e);
		}
		if (e.getSource() == btnCancelarRegistro) {
			actionPerformedBtnCancelar(e);
		}
	}

	protected void actionPerformedBtnAgregar(ActionEvent e) {
		try {
			// OBTENGO DATOS INGRESADOS Y ELIMINO ESPACIOS EN BLANCO AL INICIO Y FINAL
			String nom = txtNombre.getText().trim().toUpperCase();
			String ape = txtApellidos.getText().trim().toUpperCase();
			String cmp = txtCmp.getText().trim();
			
			//OBTENGO VALORES SELECCIONADOS EN LOS COMBOBOX
			String esp = cboEspecialidad.getSelectedItem().toString();
			int est = cboEstado.getSelectedIndex();

			// VALIDO QUE NINGÚN CAMPO ESTE VACIO Y QUE SE HAYA SELECCIONADO UNA OPCION 
			if (nom.isEmpty() || ape.isEmpty() || cmp.isEmpty() || est == 0 || esp.contains("Seleccione")) {
				JOptionPane.showMessageDialog(this, "Debe completar todos los campos");
				txtNombre.requestFocus();
			//DETENGO LA EJECUCIÓN SI LA VALIDACIÓN FALLA
				return;
			}

			// CREAR OBJETO MEDICO Y AGREGARLO AL ARREGLO (SE GUARDA AUTOMATICAMENTE EN EL ARCHIVO)
			Medico m = new Medico(nom, ape, esp, cmp, est - 1); 
			objArreglo.adicionar(m);

			// ACTUAALIZO MI FORMULARIO 
			listar();
			limpiar();
			txtCodMedico.setText(Medico.proximoCodigo());
			JOptionPane.showMessageDialog(this, "Médico registrado correctamente.");
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
		}
	}
	
	protected void actionPerformedBtnCancelar(ActionEvent e) {
		limpiar();
	}

	private void listar() {
		
		modelo.setRowCount(0); // LIMPIA TODAS LAS FILAS ACTUALES DE LA TABLA ANTES DE VOLVER A CARGAR LOS DATOS
		
		// RECORRER TODOS LOS MEDICOS REGISTRADOS EN EL ARREGLO
		for (int i = 0; i < objArreglo.tamano(); i++) {
			
			// OBTENGO EL MEDICO SEGUN SU POSICION EN EL ARREGLO
			Medico m = objArreglo.obtener(i);
			
			// CREAR UN ARREGLO DE OBJETOS CON LOS DATOS QUE SE MOSTRARAN EN LA TABLA
			Object[] fila = {
				m.getCodMedico(),
				m.getNombres(),
				m.getApellidos(),
				m.getEspecialidad(),
				m.getCmp(),
				Medico.estados[m.getEstado()]
			};
			modelo.addRow(fila);
		}
	}
    // LIMPIA LOS CAJAS DE TEXTO Y LOS COMBOS 
	private void limpiar() {
		txtNombre.setText("");
		txtApellidos.setText("");
		txtCmp.setText("");
		lblMensaje.setText("");
		cboEspecialidad.setSelectedIndex(0);
		cboEstado.setSelectedIndex(0);
		txtNombre.requestFocus();
	}
	
	
}