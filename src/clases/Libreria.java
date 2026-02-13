package clases;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Libreria {
	// centra la ventana relativa al padre, la hace modal y la hace visible
	public static <D extends JDialog, F extends JFrame> void setModalWindow(D ventana, F ventanaPadre) {
		ventana.setLocationRelativeTo(ventanaPadre);
		ventana.setModal(true);
		ventana.setVisible(true);
	}
	public static <D extends JDialog> void setModalWindow(D ventana, D ventanaPadre) {
		ventana.setLocationRelativeTo(ventanaPadre);
		ventana.setModal(true);
		ventana.setVisible(true);
	}
	
	// redondear números enteros a 2 decimales
	public static String redondear(double num) {
		return String.format("%.2f", num);
	}
	
	// redondear números enteros a 2 decimales
	public static String getLabelEstado(int estado) {
		if (estado == 0) return "Inactivo";
		else return "Activo";
	}
	
	// tablas
	public static JTable crearTabla() {
		JTable tblTabla = new JTable();
		tblTabla.setFocusable(false);
		tblTabla.setFillsViewportHeight(true);
		tblTabla.setColumnSelectionAllowed(false);
		tblTabla.setRowSelectionAllowed(true);
		return tblTabla;
	}

	// modelo para tablas
	public static DefaultTableModel crearModelo(String[] columnas) {
		return new DefaultTableModel(
			// datos
			new Object[][] {
			},
			// columnas
			columnas
		) {
			// no dejar que las celdas sean editables
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	}
}
