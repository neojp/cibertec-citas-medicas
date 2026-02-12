package clases;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class Libreria {
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
}
