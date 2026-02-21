package gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import arreglo.ArregloCitas;
import arreglo.ArregloPaciente;
import clases.Paciente;

import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class MantenimientoPaciente extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton btnNuevo;
	private JButton btnEditar;
	private JButton btnEliminar;
	private JButton btnBuscarDNI;
	private JScrollPane scp;
	private JButton btnBuscarCodigo;
	private JLabel lblBuscar;
	private JTable tblTabla;
	private DefaultTableModel modelo;
	private ArregloPaciente arr = Principal.getArrPacientes();
	private JPanel pnlOpciones;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDialog.setDefaultLookAndFeelDecorated(true);
			MantenimientoPaciente dialog = new MantenimientoPaciente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MantenimientoPaciente() {
		setResizable(false);
		setTitle("Mantenimiento - Paciente");
		setBounds(100, 100, 608, 386);
		getContentPane().setLayout(null);

		btnNuevo = new JButton("Agregar Nuevo");
		btnNuevo.addActionListener(this);
		btnNuevo.setBounds(10, 11, 138, 23);
		getContentPane().add(btnNuevo);

		pnlOpciones = new JPanel();
		pnlOpciones.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Opciones de filas seleccionadas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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

		btnBuscarDNI = new JButton("DNI");
		btnBuscarDNI.addActionListener(this);
		btnBuscarDNI.setBounds(493, 11, 89, 23);
		getContentPane().add(btnBuscarDNI);

		scp = new JScrollPane();
		scp.setBounds(10, 45, 572, 233);
		getContentPane().add(scp);

		modelo = new DefaultTableModel(new Object[][] {},
				new String[] { "Código", "Nombres", "Apellidos", "DNI", "Edad", "Celular", "Correo", "Estado" });
		tblTabla = new JTable();
		tblTabla.setModel(modelo);
		tblTabla.setFillsViewportHeight(true);
		scp.setViewportView(tblTabla);

		btnBuscarCodigo = new JButton("Código");
		btnBuscarCodigo.addActionListener(this);
		btnBuscarCodigo.setBounds(394, 11, 89, 23);
		getContentPane().add(btnBuscarCodigo);

		lblBuscar = new JLabel("Buscar por:");
		lblBuscar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBuscar.setBounds(321, 15, 69, 14);
		getContentPane().add(lblBuscar);

		load();
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
		if (e.getSource() == btnBuscarDNI) {
			actionPerformedBtnBuscarDNI(e);
		}
		if (e.getSource() == btnNuevo) {
			actionPerformedBtnNuevo(e);
		}
	}

	protected void actionPerformedBtnNuevo(ActionEvent e) {
		FormularioPaciente ventana = new FormularioPaciente();
		ventana.setLocationRelativeTo(this);
		ventana.setModal(true);
		ventana.setVisible(true);

		if (ventana.getSuccess()) {
			System.out.println(ventana.getPaciente().toString());
			Paciente paciente = ventana.getPaciente();
			arr.adicionar(paciente);
			addRow(paciente);
			
		}
	}

	protected void actionPerformedBtnBuscarDNI(ActionEvent e) {
		// inicializar el JDialog en modo modal y espera a que se oculte
		FormularioBuscarDNI ventana = new FormularioBuscarDNI();
		ventana.setLocationRelativeTo(this);
		ventana.setModal(true);
		ventana.setVisible(true);

		if (ventana.getEmpezarBusqueda()) {
			// este codigo espera a que la ventana se oculte
			// se obtiene el DNI del JTextField en la ventana
			String dni = ventana.leerDNI();
			System.out.println("Iniciar busqueda con CMP: " + dni);
			load(dni);
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
			load(Integer.parseInt(codigo));
		}

		// y ahora se cierra la ventana
		ventana.dispose();
	}

	protected void actionPerformedBtnEditar(ActionEvent e) {
		Paciente paciente = getPaciente();
		if (paciente == null) {
			JOptionPane.showMessageDialog(this, "Seleccione un paciente", getTitle(), JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		FormularioPaciente ventana = new FormularioPaciente("editar");

		ventana.fillWithData(paciente);
		ventana.setLocationRelativeTo(this);
		ventana.setModal(true);
		ventana.setVisible(true);

		if (ventana.getSuccess()) {
			arr.grabar();
			updateRow(tblTabla.getSelectedRow());
		}
	}

	protected void actionPerformedBtnEliminar(ActionEvent e) {
		Paciente paciente = getPaciente();
		if (paciente == null) {
			JOptionPane.showMessageDialog(this, "Seleccione un paciente", getTitle(), JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		int confirm = JOptionPane.showConfirmDialog(this,
				"¿Desea eliminar al paciente " + paciente.getNombres() + " " + paciente.getApellidos() + "?", getTitle(),
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		if (confirm == 1) return;

		int nFutureDates = Principal.getArrCitas().buscarFuturasPorPaciente(paciente.getCodPaciente());

		if (nFutureDates > 0) {
			String msg = "El consultorio no puede ser eliminado porque tiene " + nFutureDates;
			if (nFutureDates == 1)
				msg += " cita futura";
			else
				msg += " citas futuras";

			JOptionPane.showMessageDialog(this, msg, "Error de validación", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		List<Integer> numCitas = Principal.getArrCitas().getNumCitasByPaciente(paciente.getCodPaciente());
		if (numCitas.size() > 0) {
			ArregloCitas citas = Principal.getArrCitas();
			for (int i = 0; i < numCitas.size(); i++) {
				citas.deleteByPk(numCitas.get(i));
			}
		}
		
		arr.eliminar(paciente);
		deleteRow(tblTabla.getSelectedRow());
	}

	private void load() {
		modelo.setRowCount(0);
		for (int i = 0; i < arr.tamano(); i++) {
			Paciente item = arr.obtener(i);
			Object[] row = { item.getCodPaciente(), item.getNombres(), item.getApellidos(), item.getDni(),
					item.getEdad(), item.getCelular(), item.getCorreo(), Paciente.estados[item.getEstado()] };
			modelo.addRow(row);
		}
	}
	
	private void load(int codigo) {
		modelo.setRowCount(0);
		for (int i = 0; i < arr.tamano(); i++) {
			Paciente item = arr.obtener(i);
			
			if(item.getCodPaciente() == codigo) {
				addRow(item);
				break;
			}
		}
	}
	
	private void load(String dni) {
		modelo.setRowCount(0);
		for (int i = 0; i < arr.tamano(); i++) {
			Paciente item = arr.obtener(i);
			
			if(item.getDni().equals(dni)) {
				addRow(item);
				break;
			}
		}
	}
	
	private void addRow(Paciente paciente) {
		Object[] row =  new Object[] {
				paciente.getCodPaciente(),
				paciente.getNombres(),
				paciente.getApellidos(),
				paciente.getDni(),
				paciente.getEdad(),
				paciente.getCelular(),
				paciente.getCorreo(),
				Paciente.estados[paciente.getEstado()]
		};
		modelo.addRow(row);
	}

	private void updateRow(int row) {
		Object[] item = arr.obtener(row).getToRow();

		deleteRow(row);
		modelo.insertRow(row, item);
	}

	private void deleteRow(int row) {
		modelo.removeRow(row);
	}

	private Paciente getPaciente() {
		int indice = tblTabla.getSelectedRow();
		if (indice != -1) {
			return arr.obtener(indice);
		}
		return null;
	}
}
