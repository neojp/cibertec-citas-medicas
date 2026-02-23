package gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import arreglo.ArregloCita;
import clases.Cita;
import clases.Consultorio;
import clases.Medico;
import clases.Paciente;
import libreria.Libreria;

import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class RegistroCitas extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton btnNuevo;
	private JButton btnEditar;
	private JButton btnCancelar;
	private JScrollPane scp;
	private JTable tblTabla;
	private DefaultTableModel modelo;
	private ArregloCita arr = Principal.getArrCitas();
	private JPanel pnlOpciones;
	private JButton btnEliminar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistroCitas dialog = new RegistroCitas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistroCitas() {
		setTitle("Registro de Citas");
		setBounds(100, 100, 820, 387);
		getContentPane().setLayout(null);
		
		btnNuevo = new JButton("Agregar Nueva");
		btnNuevo.addActionListener(this);
		btnNuevo.setBounds(10, 11, 138, 23);
		getContentPane().add(btnNuevo);
		
		pnlOpciones = new JPanel();
		pnlOpciones.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Opciones de filas seleccionadas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlOpciones.setBounds(10, 289, 784, 48);
		getContentPane().add(pnlOpciones);
		pnlOpciones.setLayout(null);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(10, 16, 89, 23);
		pnlOpciones.add(btnEditar);
		btnEditar.addActionListener(this);
		
		btnCancelar = new JButton("Cancelar Cita");
		btnCancelar.setBounds(208, 16, 115, 23);
		pnlOpciones.add(btnCancelar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(109, 16, 89, 23);
		pnlOpciones.add(btnEliminar);
		btnCancelar.addActionListener(this);
		
		scp = new JScrollPane();
		scp.setBounds(10, 45, 784, 233);
		getContentPane().add(scp);
		
		modelo = Libreria.crearModeloTabla(new String[] {
				"N\u00FAmero", "Paciente", "M\u00E9dico", "Consultorio", "Fecha", "Hora", "Estado", "Motivo"
		});
		tblTabla = Libreria.crearTabla();
		tblTabla.setModel(modelo);
		scp.setViewportView(tblTabla);
		
		listar();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(e);
		}
		if (e.getSource() == btnCancelar) {
			actionPerformedBtnCancelar(e);
		}
		if (e.getSource() == btnEditar) {
			actionPerformedBtnEditar(e);
		}
		if (e.getSource() == btnNuevo) {
			actionPerformedBtnNuevo(e);
		}
	}
	
	// abre el formulario de agregar
	protected void actionPerformedBtnNuevo(ActionEvent e) {
		// abrir formulario en modo agregar
		FormularioCita ventana = new FormularioCita();
		
		// posicionar ventana y esperar a que se cierre
		ventana.setLocationRelativeTo(this);
		ventana.setModal(true);
		ventana.setVisible(true);
		
		// la ventana fue cerrada, revisar si se presionó el botón de aceptar
		if (ventana.getSuccess()) {
			// agregar a la lista y grabar al archivo de texto
			arr.adicionar(ventana.getCita());
			
			// actualizar tabla
			listar();
			
			// cerrar ventana
			ventana.dispose();
		}
	}
	
	// abre el formulario de editar con datos de la cita seleccionada 
	protected void actionPerformedBtnEditar(ActionEvent e) {
		// obtener cita de la fila seleccionada en la tabla
		Cita cita = obtenerCita();
		if (cita != null) {
			// abrir formulario en modo editar
			FormularioCita ventana = new FormularioCita("editar");

			// llenar formulario con datos de la cita
			ventana.llenarFormulario(cita);
			
			// posicionar ventana y esperar a que se cierre
			ventana.setLocationRelativeTo(this);
			ventana.setModal(true);
			ventana.setVisible(true);
			
			// la ventana fue cerrada, revisar si se presionó el botón de aceptar
			if (ventana.getSuccess()) {
				// grabar al archivo de texto
				arr.grabar();
				
				// actualizar tabla
				listar();
				
				// cerrar ventana
				ventana.dispose();
			}
		} else {
			JOptionPane.showMessageDialog(this, "Seleccione una fila", "Anuncio", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	// cancela la cita seleccionada cambiando su estado a 2
	protected void actionPerformedBtnCancelar(ActionEvent e) {
		// obtener la cita de la fila seleccionada en la tabla
		Cita cita = obtenerCita();
		if (cita != null) {
			// mostrar un dialogo de confirmación antes de eliminarla
			int confirmar = JOptionPane.showConfirmDialog(
				this, 
				"¿Está seguro que quiere cancelar esta cita?", 
				"Confirmar Cancelación", 
				JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE
			);

			if (confirmar == 0) {
				// cancelar la cita, cambiando su estado a 2
				cita.setEstado(2);
				
				// grabar al archivo de texto
				arr.grabar();
				
				// actualizar la tabla
				listar();
			}
		} else {
			JOptionPane.showMessageDialog(this, "Seleccione una fila", "Anuncio", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	// actualizar tabla con contenido
	void listar() {
		// limpiar tabla
		modelo.setRowCount(0);

		// iterar el arreglo y llenar la tabla con datos del consultorio
		for (int i = 0; i < arr.tamano(); i++) {
			// crear fila con datos de la cita
			Object[] fila = {
				arr.obtener(i).getNumCita(),
				pacienteNombre(arr.obtener(i)),
				medicoNombre(arr.obtener(i)),
				consultorioNombre(arr.obtener(i)),
				arr.obtener(i).getFecha(),
				arr.obtener(i).getHora(),				
				Cita.estados[arr.obtener(i).getEstado()], // mostrar el label del estado
				arr.obtener(i).getMotivo()
			};
			
			// agregar fila
			modelo.addRow(fila);
		}
	}

	private String pacienteNombre(Cita cita) {
		String pacienteNombre = "";
		Paciente paciente = Principal.getArrPacientes().buscarCodPaciente(cita.getCodPaciente());
		if (paciente != null)
			pacienteNombre = paciente.getNombreCompleto();
		return pacienteNombre;
	}
	private String medicoNombre(Cita cita) {
		String nombre = "";
		Medico medico = Principal.getArrMedicos().buscarCodMedico(cita.getCodMedico());
		if (medico != null)
			nombre = medico.getNombreCompleto();
		return nombre;
	}
	private String consultorioNombre(Cita cita) {
		String nombre = "";
		Consultorio consultorio = Principal.getArrConsultorios().buscarCodConsultorio(cita.getCodConsultorio());
		if (consultorio != null)
			nombre = consultorio.getNombre();
		return nombre;
	}
	
	// obtiene el consultorio seleccionado de la tabla por su índice
	private Cita obtenerCita() {
		// obtener el índice de la fila seleccionada
		int indice = tblTabla.getSelectedRow();
		if (indice != -1) {
			// obtener consultorio por indice
			return arr.obtener(indice);
		}
		return null;
	}
	protected void actionPerformedBtnEliminar(ActionEvent e) {
		// obtener cita de la fila seleccionada en la tabla
		Cita cita = obtenerCita();
		if (cita != null) {
			// mostrar un dialogo de confirmación antes de eliminarla
			int confirmar = JOptionPane.showConfirmDialog(
				this, 
				"¿Está seguro que quiere eliminar esta cita?", 
				"Confirmar Eliminación", 
				JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE
			);

			if (confirmar == 0) {
				// eliminar la cita
				arr.eliminar(cita);
				
				// grabar al archivo de texto
				arr.grabar();
				
				// actualizar la tabla
				listar();
			}
		} else {
			JOptionPane.showMessageDialog(this, "Seleccione una fila", "Anuncio", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
