package gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JOptionPane;

import arreglo.ArregloMedico;
import clases.Cita;
import clases.Medico;

public class MantenimientoMedico extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton btnNuevo;
	private JButton btnEditar;
	private JButton btnEliminar;
	private JButton btnBuscarNombre;
	private JScrollPane scp;
	private JButton btnBuscarCodigo;
	private JLabel lblBuscar;
	private JTable tblTabla;
	private JPanel pnlOpciones;

	private DefaultTableModel modelo;
	private ArregloMedico arr = Principal.getArrMedicos();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDialog.setDefaultLookAndFeelDecorated(true);
			MantenimientoMedico dialog = new MantenimientoMedico();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MantenimientoMedico() {
		setResizable(false);
		setTitle("Mantenimiento - Médico");
		setBounds(100, 100, 608, 387);
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
		
		btnBuscarNombre = new JButton("Nombre");
		btnBuscarNombre.addActionListener(this);
		btnBuscarNombre.setBounds(493, 11, 89, 23);
		getContentPane().add(btnBuscarNombre);
		
		scp = new JScrollPane();
		scp.setBounds(10, 45, 572, 233);
		getContentPane().add(scp);

		// MODELO NO EDITABLE
		modelo = new DefaultTableModel(
		    new Object[][] {},
		    new String[] { "Código", "Nombres", "Apellidos", "Especialidad", "CMP", "Estado" }
		) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false; // ninguna celda es editable
		    }
		};

		tblTabla = new JTable(modelo);
		tblTabla.setFillsViewportHeight(true);
		scp.setViewportView(tblTabla);
		
		btnBuscarCodigo = new JButton("Código");
		btnBuscarCodigo.addActionListener(this);
		btnBuscarCodigo.setBounds(394, 11, 89, 23);
		getContentPane().add(btnBuscarCodigo);
		
		lblBuscar = new JLabel("Buscar por:");
		lblBuscar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBuscar.setBounds(260, 15, 124, 14);
		getContentPane().add(lblBuscar);

		// CARGAR MÉDICOS REGISTRADOS
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
		if (e.getSource() == btnBuscarNombre) {
			actionPerformedBtnBuscarNombre(e);
		}
		if (e.getSource() == btnNuevo) {
			actionPerformedBtnNuevo(e);
		}
	}

	// ============= NUEVO =============
	protected void actionPerformedBtnNuevo(ActionEvent e) {
		FormularioMedico ventana = new FormularioMedico();
		ventana.setLocationRelativeTo(this);
		ventana.setModal(true);
		ventana.setVisible(true);
		arr.adicionar(ventana.getMedico());

		// Al cerrar la ventana, recargamos la tabla (asumiendo que el formulario guarda en arr)
		load();
	}

	// ============= BUSCAR POR NOMBRE =============
	protected void actionPerformedBtnBuscarNombre(ActionEvent e) {
		// inicializar el JDialog en modo modal y espera a que se oculte
		FormularioBuscarNombre ventana = new FormularioBuscarNombre();
		ventana.setLocationRelativeTo(this);
		ventana.setModal(true);
		ventana.setVisible(true);
		
		if (ventana.getEmpezarBusqueda()) {
			String nombre = ventana.leerNombre();
			System.out.println("Iniciar busqueda con nombre: " + nombre);
			load(nombre);
		}
		
		ventana.dispose();
	}

	// ============= BUSCAR POR CÓDIGO =============
	protected void actionPerformedBtnBuscarCodigo(ActionEvent e) {
		FormularioBuscarCodigo ventana = new FormularioBuscarCodigo();
		ventana.setLocationRelativeTo(this);
		ventana.setModal(true);
		ventana.setVisible(true);
		
		if (ventana.getEmpezarBusqueda()) {
			String codigo = ventana.leerCodigo();
			System.out.println("Iniciar busqueda con codigo: " + codigo);
			try {
				int cod = Integer.parseInt(codigo);
				load(cod);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "El código debe ser numérico.", getTitle(),
						JOptionPane.WARNING_MESSAGE);
			}
		}
		
		ventana.dispose();
	}

	// ============= ELIMINAR (por ahora vacío) =============
	protected void actionPerformedBtnEliminar(ActionEvent e) {
		Medico medico = getMedico();
		if (medico == null) {
			JOptionPane.showMessageDialog(this, "Seleccione un médico", getTitle(), JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		int confirm = JOptionPane.showConfirmDialog(this,
				"¿Desea eliminar al medico " + medico.getNombres() + " " + medico.getApellidos() + "?", getTitle(),
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		if (confirm == 1) return;

		ArrayList<Cita> citasFuturas = Principal.getArrCitas().buscarFuturasPorMedico(medico.getCodMedico());

		if (citasFuturas.size() > 0) {
			String msg = "El medico no puede ser eliminado porque tiene " + citasFuturas.size();
			if (citasFuturas.size() == 1)
				msg += " cita futura";
			else
				msg += " citas futuras";

			JOptionPane.showMessageDialog(this, msg, "Error de validación", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		ArrayList<Cita> citas = Principal.getArrCitas().buscarPorMedico(medico.getCodMedico());
		if (citas.size() > 0) {
			for (Cita c : citas) {
				Principal.getArrCitas().eliminar(c);
			}
			JOptionPane.showMessageDialog(this, "El médico tenía citas pasadas, por lo que se eliminaron", "Anuncio", JOptionPane.INFORMATION_MESSAGE);
		}
		
		arr.eliminar(medico);
		deleteRow(tblTabla.getSelectedRow());
	}

	// ============= EDITAR =============
	protected void actionPerformedBtnEditar(ActionEvent e) {

	    int fila = tblTabla.getSelectedRow();

	    if (fila == -1) {
	        JOptionPane.showMessageDialog(this, "Seleccione un médico", getTitle(),
	                JOptionPane.INFORMATION_MESSAGE);
	        return;
	    }

	    int codigo = Integer.parseInt(tblTabla.getValueAt(fila, 0).toString());

	    FormularioMedico ventana = new FormularioMedico("editar");

	    ventana.setCodigoMedico(codigo);   // ← usa tu método
		ventana.llenarFormulario(arr.buscarCodMedico(codigo)); // opcional: llenar todo el formulario

	    ventana.setLocationRelativeTo(this);
	    ventana.setModal(true);
	    ventana.setVisible(true);

	    load(); // recargar tabla
	}
	
	private void load() {
		modelo.setRowCount(0);
		for (int i = 0; i < arr.tamano(); i++) {
			Medico m = arr.obtener(i);
			Object[] row = {
				m.getCodMedico(),             // ajusta si se llama distinto
				m.getNombres(),
				m.getApellidos(),
				m.getEspecialidad(),
				m.getCmp(),                   // ajusta si se llama distinto
				Medico.estados[m.getEstado()] // si tienes un arreglo de estados como en Paciente
			};
			modelo.addRow(row);
		}
	}

	// ============= CARGAR POR CÓDIGO =============
	private void load(int codigo) {
		modelo.setRowCount(0);
		boolean encontrado = false;

		for (int i = 0; i < arr.tamano(); i++) {
			Medico m = arr.obtener(i);
			if (m.getCodMedico() == codigo) {  // ajusta si tu getter es otro
				Object[] row = {
					m.getCodMedico(),
					m.getNombres(),
					m.getApellidos(),
					m.getEspecialidad(),
					m.getCmp(),
					Medico.estados[m.getEstado()]
				};
				modelo.addRow(row);
				encontrado = true;
				break;
			}
		}

		if (!encontrado) {
			JOptionPane.showMessageDialog(this, "No se encontró el código ingresado.", getTitle(),
					JOptionPane.INFORMATION_MESSAGE);
			load(); // opcional: volver a mostrar todos
		}
	}

	// ============= CARGAR POR NOMBRE =============
	private void load(String nombre) {
		modelo.setRowCount(0);
		boolean encontrado = false;

		for (int i = 0; i < arr.tamano(); i++) {
			Medico m = arr.obtener(i);

			// puedes usar equalsIgnoreCase, startsWith, contains, etc.
			if (m.getNombres().equalsIgnoreCase(nombre)) {
				Object[] row = {
					m.getCodMedico(),
					m.getNombres(),
					m.getApellidos(),
					m.getEspecialidad(),
					m.getCmp(),
					Medico.estados[m.getEstado()]
				};
				modelo.addRow(row);
				encontrado = true;
			}
		}

		if (!encontrado) {
			JOptionPane.showMessageDialog(this, "No se encontró el nombre ingresado.", getTitle(),
					JOptionPane.INFORMATION_MESSAGE);
			load(); // opcional: volver a mostrar todos
		}
	}

	private void deleteRow(int row) {
		if (row != -1) {
			modelo.removeRow(row);
		}
	}

	private Medico getMedico() {
		int indice = tblTabla.getSelectedRow();
		if (indice != -1) {
			return arr.obtener(indice);
		}
		return null;
	}
}