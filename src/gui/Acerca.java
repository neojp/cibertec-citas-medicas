package gui;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Acerca extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel lblAutores;
	private JLabel lblBraulio;
	private JLabel lblDonnie;
	private JLabel lblJoan;
	private JButton btnCerrar;
	private JSeparator separator;
	private JLabel lblMicroprocesadores;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Acerca dialog = new Acerca();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Acerca() {
		setTitle("Acerca del Sistema");
		setBounds(100, 100, 450, 254);
		getContentPane().setLayout(null);
		{
			lblMicroprocesadores = new JLabel("Sistema de Reserva de Citas Médicas");
			lblMicroprocesadores.setHorizontalAlignment(SwingConstants.CENTER);
			lblMicroprocesadores.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblMicroprocesadores.setBounds(10, 11, 418, 46);
			getContentPane().add(lblMicroprocesadores);
		}

		lblAutores = new JLabel("Autores");
		lblAutores.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutores.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAutores.setBounds(180, 68, 79, 14);
		getContentPane().add(lblAutores);

		lblBraulio = new JLabel("Braulio Saloma");
		lblBraulio.setHorizontalAlignment(SwingConstants.CENTER);
		lblBraulio.setBounds(168, 93, 102, 14);
		getContentPane().add(lblBraulio);

		lblDonnie = new JLabel("Joan Piedra");
		lblDonnie.setHorizontalAlignment(SwingConstants.CENTER);
		lblDonnie.setBounds(168, 118, 102, 14);
		getContentPane().add(lblDonnie);

		lblJoan = new JLabel("Rosa Truyenque");
		lblJoan.setHorizontalAlignment(SwingConstants.CENTER);
		lblJoan.setBounds(168, 143, 102, 14);
		getContentPane().add(lblJoan);

		btnCerrar = new JButton("Cerrar");
		btnCerrar.setBackground(new Color(128, 64, 64));
		btnCerrar.setForeground(new Color(255, 255, 255));
		btnCerrar.addActionListener(this);
		btnCerrar.setBounds(178, 174, 89, 23);
		getContentPane().add(btnCerrar);

		separator = new JSeparator();
		separator.setBounds(10, 54, 418, 2);
		getContentPane().add(separator);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCerrar) {
			actionPerformedBtnCerrar(e);
		}
	}

	protected void actionPerformedBtnCerrar(ActionEvent e) {
		dispose();
	}
}
