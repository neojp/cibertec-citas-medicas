package arreglo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import clases.Paciente;

public class ArregloPaciente {
	// variables privadas
	private ArrayList<Paciente> arr;
	private String file = "src/main/resources/data/pacientes.txt";
	
	// constructor
	public ArregloPaciente() {
		arr = new ArrayList<Paciente>();
		cargar();
	}
	
	// operaciones públicas básicas
	public void adicionar(Paciente x) {
		arr.add(x);
		grabar();
	}
	
	public int tamano() {
		return arr.size();
	}
	
	public Paciente obtener(int i) {
		return arr.get(i);
	}
	
	public void eliminar(Paciente x) {
		arr.remove(x);
		grabar();
	}
	
	// buscar
	public Paciente buscarCodPaciente(int codPaciente) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getCodPaciente() == codPaciente)
				return obtener(i);

		return null;
	}
	
	public Paciente buscarEdad(int edad) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getEdad() == edad)
				return obtener(i);
		
		return null;
	}

	public Paciente buscarEstado(int estado) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getEstado() == estado)
				return obtener(i);

		return null;
	}
	
	public Paciente buscarNombres(String nombres) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getNombres().equalsIgnoreCase(nombres))
				return obtener(i);

		return null;
	}
	
	public Paciente buscarApellidos(String apellidos) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getApellidos().equalsIgnoreCase(apellidos))
				return obtener(i);

		return null;
	}
	
	public Paciente buscarDni(String dni) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getDni().equalsIgnoreCase(dni))
				return obtener(i);
		
		return null;
	}
	
	public Paciente buscarCelular(String celular) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getCelular().equalsIgnoreCase(celular))
				return obtener(i);
		
		return null;
	}
	
	public Paciente buscarCorreo(String correo) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getCorreo().equalsIgnoreCase(correo))
				return obtener(i);
		
		return null;
	}
	
	// archivos de texto
	private void cargar() {
		try {
			BufferedReader br;
			String linea;
			String[] s;
			
			// campos
			int codPaciente, edad, estado;
			String nombres, apellidos, dni, celular, correo;
			
			br = new BufferedReader(new FileReader(file));
			while ((linea = br.readLine()) != null) {
				s = linea.split(";", -1); // partir a traves de punto y coma, permitir cadenas vacías
				codPaciente = Integer.parseInt(s[0].trim());
				edad = Integer.parseInt(s[1].trim());
				estado = Integer.parseInt(s[2].trim());
				nombres = s[3].trim();
				apellidos = s[4].trim();
				dni = s[5].trim();
				celular = s[6].trim();
				correo = s[7].trim();
				arr.add(new Paciente(codPaciente, edad, estado, nombres, apellidos, dni, celular, correo));
			}
			br.close();
		}
		catch (Exception e) {
			System.out.println("Error al cargar: " + e.getMessage());
		}
	}
	
	private void grabar() {
		try {
			PrintWriter pw;
			String linea;
			Paciente x;
			pw = new PrintWriter(new FileWriter(file));
			for (int i=0; i<tamano(); i++) {
				x = obtener(i);
				linea = x.getCodPaciente() + ";" +
						x.getEdad() + ";" +
						x.getEstado() + ";" +
						x.getNombres() + ";" +
						x.getApellidos() + ";" +
						x.getDni() + ";" +
						x.getCelular() + ";" +
						x.getCorreo();
				pw.println(linea);
			}
			pw.close();
		}
		catch (Exception e) {
			System.out.println("Error al grabar: " + e.getMessage());
		}
	}
}
