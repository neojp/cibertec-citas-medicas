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
	private JLabel lblJoan;
	private JLabel lblRosa;
	private JButton btnCerrar;
	private JLabel lblMicroprocesadores;
	private JLabel lblAntonio;

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
		setBounds(100, 100, 450, 301);
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
		
		lblAntonio = new JLabel("Antonio Javier Ceron Salcedo");
		lblAntonio.setHorizontalAlignment(SwingConstants.CENTER);
		lblAntonio.setBounds(121, 98, 192, 14);
		getContentPane().add(lblAntonio);

		lblBraulio = new JLabel("Braulio Saloma Calderon");
		lblBraulio.setHorizontalAlignment(SwingConstants.CENTER);
		lblBraulio.setBounds(121, 125, 192, 14);
		getContentPane().add(lblBraulio);

		lblJoan = new JLabel("Joan Piedra Lau");
		lblJoan.setHorizontalAlignment(SwingConstants.CENTER);
		lblJoan.setBounds(121, 150, 192, 14);
		getContentPane().add(lblJoan);

		lblRosa = new JLabel("Rosa Truyenque Tanaka");
		lblRosa.setHorizontalAlignment(SwingConstants.CENTER);
		lblRosa.setBounds(121, 175, 192, 14);
		getContentPane().add(lblRosa);

		btnCerrar = new JButton("Cerrar");
		btnCerrar.setBackground(new Color(128, 64, 64));
		btnCerrar.setForeground(new Color(255, 255, 255));
		btnCerrar.addActionListener(this);
		btnCerrar.setBounds(172, 206, 89, 23);
		getContentPane().add(btnCerrar);
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
