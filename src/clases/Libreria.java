package clases;

import javax.swing.JDialog;
import javax.swing.JFrame;

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
	private static String redondear(double num) {
		return String.format("%.2f", num);
	}
}
