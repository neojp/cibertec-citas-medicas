package libreria;

import java.awt.event.KeyEvent;
import javax.swing.JTextField;

public class Validacion {

    // METODO  PARA VALIDAR SOLO NÚMEROS Y LÍMITE
    public static void soloNumeros(KeyEvent e, JTextField txt, int limite) {
        char c = e.getKeyChar();
        if (!Character.isDigit(c)) {
            e.consume();
        }
        if (txt.getText().length() >= limite) {
            e.consume();
        }
    }

    // MÉTODO ESTÁTICO PARA VALIDAR SOLO LETRAS Y LÍMITE
    public static void soloLetras(KeyEvent e, JTextField txt, int limite) {
        char c = e.getKeyChar();
        // PERMITE LETRAS Y EL ESPACIO
        if (!Character.isLetter(c) && c != KeyEvent.VK_SPACE) {
            e.consume();
        }
        if (txt.getText().length() >= limite) {
            e.consume();
        }
    }
}