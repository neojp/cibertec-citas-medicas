package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CrearEditar extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblCodigo;
	private JLabel lblNombres;
	private JLabel lblNombre;
	private JLabel lblApellidos;
	private JLabel lblPiso;
	private JLabel lblEspecialidad;
	private JLabel lblUbicacion;
	private JLabel lblCapacidad;
	private JLabel lblCmp;
	private JLabel lblDni;
	private JLabel lblEdad;
	private JLabel lblCelular;
	private JLabel lblCorreo;
	private JLabel lblEstado;
	private JTextField txtCodigo;
	private JTextField txtNombres;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtPiso;
	private JTextField txtEspecialidad;
	private JTextField txtUbicacion;
	private JTextField txtCapacidad;
	private JTextField txtCmp;
	private JTextField txtDni;
	private JTextField txtEdad;
	private JTextField txtCelular;
	private JTextField txtCorreo;
	private JTextField txtEstado;
	private JButton btnCrear;
	private JButton btnCancelar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CrearEditar dialog = new CrearEditar("crear", "consultorio");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CrearEditar(String action, String type) {
		boolean esPaciente = type == "paciente";
		boolean esMedico = type == "medico";
		boolean esConsultorio = type == "consultorio";
		
		setTitle(action == "editar" ? "Editar" : "Crear");
		setBounds(100, 100, 450, 290);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		String codLabel = getCodLabel(type);
		
		lblCodigo = new JLabel(codLabel);
		lblCodigo.setBounds(12, 12, 125, 15);
		contentPanel.add(lblCodigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(155, 10, 283, 19);
		contentPanel.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		if (esPaciente || esMedico) {
			lblNombres = new JLabel("Nombres");
			lblNombres.setBounds(12, 39, 125, 15);
			contentPanel.add(lblNombres);
			
			lblApellidos = new JLabel("Apellidos");
			lblApellidos.setBounds(12, 66, 125, 15);
			contentPanel.add(lblApellidos);	
			
			txtNombres = new JTextField();
			txtNombres.setColumns(10);
			txtNombres.setBounds(155, 37, 283, 19);
			contentPanel.add(txtNombres);
			
			txtApellidos = new JTextField();
			txtApellidos.setColumns(10);
			txtApellidos.setBounds(155, 64, 283, 19);
			contentPanel.add(txtApellidos);
		}
		
		if (esPaciente) {
			lblDni = new JLabel("DNI");
			lblDni.setBounds(12, 93, 125, 15);
			contentPanel.add(lblDni);
			
			lblEdad = new JLabel("Edad");
			lblEdad.setBounds(12, 120, 125, 15);
			contentPanel.add(lblEdad);
			
			lblCelular = new JLabel("Celular");
			lblCelular.setBounds(12, 147, 125, 15);
			contentPanel.add(lblCelular);
				
			lblCorreo = new JLabel("Correo");
			lblCorreo.setBounds(12,174,125,15);
			contentPanel.add(lblCorreo);
			
			txtDni = new JTextField();
			txtDni.setColumns(10);
			txtDni.setBounds(155, 91, 283, 19);
			contentPanel.add(txtDni);
			
			txtEdad = new JTextField();
			txtEdad.setColumns(10);
			txtEdad.setBounds(155, 118, 283, 19);
			contentPanel.add(txtEdad);
			
			txtCelular = new JTextField();
			txtCelular.setColumns(10);
			txtCelular.setBounds(155, 145, 283, 19);
			contentPanel.add(txtCelular);
			
			txtCorreo = new JTextField();
			txtCorreo.setColumns(10);
			txtCorreo.setBounds(155, 172, 283, 19);
			contentPanel.add(txtCorreo);
		}
		
		if (esMedico) {
			lblEspecialidad = new JLabel("Especialidad");
			lblEspecialidad.setBounds(12, 93, 125, 15);
			contentPanel.add(lblEspecialidad);
			
			lblCmp = new JLabel("cmp");
			lblCmp.setBounds(12, 120, 125, 15);
			contentPanel.add(lblCmp);
			
			txtEspecialidad = new JTextField();
			txtEspecialidad.setColumns(10);
			txtEspecialidad.setBounds(155, 91, 283, 19);
			contentPanel.add(txtEspecialidad);
			
			txtCmp = new JTextField();
			txtCmp.setColumns(10);
			txtCmp.setBounds(155, 118, 283, 19);
			contentPanel.add(txtCmp);
		}
		
		if(esConsultorio) {
			lblNombre = new JLabel("Nombre");
			lblNombre.setBounds(12, 39, 125, 15);
			contentPanel.add(lblNombre);
			
			txtNombre = new JTextField();
			txtNombre.setColumns(10);
			txtNombre.setBounds(155, 37, 283, 19);
			contentPanel.add(txtNombre);
			
			lblPiso = new JLabel("Piso");
			lblPiso.setBounds(12, 66, 125, 15);
			contentPanel.add(lblPiso);	
			
			txtPiso = new JTextField();
			txtPiso.setColumns(10);
			txtPiso.setBounds(155, 64, 283, 19);
			contentPanel.add(txtPiso);
			
			lblUbicacion = new JLabel("Ubicación");
			lblUbicacion.setBounds(12, 93, 125, 15);
			contentPanel.add(lblUbicacion);
			
			txtUbicacion = new JTextField();
			txtUbicacion.setColumns(10);
			txtUbicacion.setBounds(155, 91, 283, 19);
			contentPanel.add(txtUbicacion);
			
			lblCapacidad = new JLabel("Capacidad");
			lblCapacidad.setBounds(12, 120, 125, 15);
			contentPanel.add(lblCapacidad);
			
			txtCapacidad = new JTextField();
			txtCapacidad.setColumns(10);
			txtCapacidad.setBounds(155, 118, 283, 19);
			contentPanel.add(txtCapacidad);
		}
		
		lblEstado = new JLabel("Estado");
		lblEstado.setBounds(12,201,125,15);
		contentPanel.add(lblEstado);
		
		txtEstado = new JTextField();
		txtEstado.setColumns(10);
		txtEstado.setBounds(155, 199, 283, 19);
		contentPanel.add(txtEstado);
		
		btnCrear = new JButton("CREAR");
		btnCrear.setBounds(12, 239, 198, 35);
		btnCrear.setBackground(new Color(0,64,128));
		btnCrear.setForeground(new Color(225,225,225));
		contentPanel.add(btnCrear);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(222, 239, 216, 35);
		btnCancelar.setBackground(new Color(128,64,64));
		btnCancelar.setForeground(new Color(225,225,225));
		contentPanel.add(btnCancelar);
		System.out.println("renderizando "+type);
		
	}
	
	private String getCodLabel(String type) {
		switch(type) {
			case "paciente": return "Código paciente";
			case "medico": return "Código medico";
			default: return "Código consultorio";
		}
	}
}
