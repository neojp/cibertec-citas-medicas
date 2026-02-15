package gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import arreglo.ArregloConsultorio;
import clases.Consultorio;
import clases.Libreria;

import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class MantenimientoConsultorio extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton btnNuevo;
	private JButton btnEditar;
	private JButton btnEliminar;
	private JButton btnBuscarCMP;
	private JScrollPane scp;
	private JButton btnBuscarCodigo;
	private JLabel lblBuscar;
	private JTable tblTabla;
	private DefaultTableModel modelo;
	private ArregloConsultorio arr = new ArregloConsultorio();
	private JPanel pnlOpciones;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MantenimientoConsultorio dialog = new MantenimientoConsultorio();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MantenimientoConsultorio() {
		setResizable(false);
		setTitle("Mantenimiento - Consultorio");
		setBounds(100, 100, 608, 387);
		getContentPane().setLayout(null);
		
		btnNuevo = new JButton("Agregar Nuevo");
		btnNuevo.addActionListener(this);
		btnNuevo.setBounds(10, 11, 138, 23);
		getContentPane().add(btnNuevo);
		
		pnlOpciones = new JPanel();
		pnlOpciones.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Opciones de filas seleccionadas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlOpciones.setBounds(10, 289, 572, 48);
		getContentPane().add(pnlOpciones);
		pnlOpciones.setLayout(null);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(10, 16, 89, 23);
		pnlOpciones.add(btnEditar);
		btnEditar.addActionListener(this);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(109, 16, 89, 23);
		pnlOpciones.add(btnEliminar);
		btnEliminar.addActionListener(this);
		
		btnBuscarCMP = new JButton("CMP");
		btnBuscarCMP.addActionListener(this);
		btnBuscarCMP.setBounds(493, 11, 89, 23);
		getContentPane().add(btnBuscarCMP);
		
		scp = new JScrollPane();
		scp.setBounds(10, 45, 572, 233);
		getContentPane().add(scp);
		
		modelo = Libreria.crearModelo(new String[] {
			"Código",
			"Nombre",
			"Piso",
			"Ubicación",
			"Capacidad",
			"Estado"
		});
		tblTabla = Libreria.crearTabla();
		tblTabla.setModel(modelo);
		scp.setViewportView(tblTabla);
		
		btnBuscarCodigo = new JButton("Código");
		btnBuscarCodigo.addActionListener(this);
		btnBuscarCodigo.setBounds(394, 11, 89, 23);
		getContentPane().add(btnBuscarCodigo);
		
		lblBuscar = new JLabel("Buscar por:");
		lblBuscar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBuscar.setBounds(304, 15, 79, 14);
		getContentPane().add(lblBuscar);
		
		listar();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(e);
		}
		if (e.getSource() == btnEditar) {
			actionPerformedBtnEditar(e);
		}
		if (e.getSource() == btnBuscarCodigo) {
			actionPerformedBtnBuscarCodigo(e);
		}
		if (e.getSource() == btnBuscarCMP) {
			actionPerformedBtnBuscarCMP(e);
		}
		if (e.getSource() == btnNuevo) {
			actionPerformedBtnNuevo(e);
		}
	}
	protected void actionPerformedBtnNuevo(ActionEvent e) {
		FormularioConsultorio ventana = new FormularioConsultorio();
		ventana.setLocationRelativeTo(this);
		ventana.setModal(true);
		ventana.setVisible(true);
	}
	protected void actionPerformedBtnBuscarCMP(ActionEvent e) {
		// inicializar el JDialog en modo modal y espera a que se oculte
		FormularioBuscarCMP ventana = new FormularioBuscarCMP();
		ventana.setLocationRelativeTo(this);
		ventana.setModal(true);
		ventana.setVisible(true);
		
		if (ventana.getEmpezarBusqueda()) {
			// este codigo espera a que la ventana se oculte
			// se obtiene el CMP del JTextField en la ventana
			String cmp = ventana.leerCMP();
			System.out.println("Iniciar busqueda con CMP: " + cmp);
		}
		
		// y ahora se cierra la ventana
		ventana.dispose();
	}
	protected void actionPerformedBtnBuscarCodigo(ActionEvent e) {
		// inicializar el JDialog en modo modal y espera a que se oculte
		FormularioBuscarCodigo ventana = new FormularioBuscarCodigo();
		ventana.setLocationRelativeTo(this);
		ventana.setModal(true);
		ventana.setVisible(true);
		
		if (ventana.getEmpezarBusqueda()) {
			// este codigo espera a que la ventana se oculte
			// se obtiene el codigo del JTextField en la ventana
			String codigo = ventana.leerCodigo();
			System.out.println("Iniciar busqueda con codigo: " + codigo);
		}
		
		// y ahora se cierra la ventana
		ventana.dispose();
	}
	protected void actionPerformedBtnEliminar(ActionEvent e) {
	}
	protected void actionPerformedBtnEditar(ActionEvent e) {
		// mostrar formulario si una fila esta seleccionada
		int indice = tblTabla.getSelectedRow();
		if (indice != -1) {
			// obtener consultorio por codigo
			int codigo = (int) modelo.getValueAt(indice, 0);
			Consultorio consultorio = arr.buscarCodConsultorio(codigo);
			
			// TODO: llenar formulario con estos datos
			
			// abrir formulario
			FormularioConsultorio ventana = new FormularioConsultorio("editar");
			ventana.setLocationRelativeTo(this);
			ventana.setModal(true);
			ventana.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(this, "Seleccione una fila");
		}
	}
	
	// actualizar tabla con contenido
	void listar() {
		System.out.println("listar " + arr.tamano() + " consultorios");
		modelo.setRowCount(0);
		for (int i = 0; i < arr.tamano(); i++) {
			Object[] fila = {
				arr.obtener(i).getCodConsultorio(),
				arr.obtener(i).getNombre(),
				arr.obtener(i).getPiso(),
				arr.obtener(i).getUbicacion(),
				arr.obtener(i).getCapacidad(),
				Consultorio.estados[arr.obtener(i).getEstado()]
			};
			modelo.addRow(fila);
		}
	}
}
