package libreria;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class Libreria {
	// redondear números enteros a 2 decimales
	public static String redondear(double num) {
		return String.format("%.2f", num);
	}
	
	// crear tablas con opciones predeterminadas que no permitan seleccionar más de 1 fila, seleccionar columnas o editar celdas 
	public static JTable crearTabla() {
		JTable tblTabla = new JTable();

		// incrementar la altura de la tabla
		tblTabla.setFillsViewportHeight(true);
		
		// no permitir que se muestren celdas individuales como si estuvieran seleccionadas
		tblTabla.setFocusable(false);
		
		// no permitir que se seleccionen columnas 
		tblTabla.setColumnSelectionAllowed(false);
		
		// solo se puede seleccionar una fila a la vez
		tblTabla.setRowSelectionAllowed(true);
		tblTabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		return tblTabla;
	}

	// crear modelo para tablas sin que las celdas puedan ser seleccionadas
	public static DefaultTableModel crearModeloTabla(String[] columnas) {
		return new DefaultTableModel(
			// crear modelo sin datos
			new Object[][] {
			},
			// asignar columnas del parámetro
			columnas
		) {
			// no permitir que las celdas sean editables
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	}
	
	public static Date strToDate(String str) {
		try {
			return new SimpleDateFormat("dd/MM/yyyy").parse(str);
		} catch (ParseException e) {
			System.out.println(e);
		}
		return null;
	}
	
	public static Date strToTime(String str) {
		try {
			return new SimpleDateFormat("HH:mm").parse(str);
		} catch (ParseException e) {
			System.out.println(e);
		}
		return null;
	}

	public static Date strToDate(String str) {
		try {
			return new SimpleDateFormat("dd/MM/yyyy").parse(str);
		} catch (ParseException e) {
			System.out.println(e);
		}
		return null;
	}
}
