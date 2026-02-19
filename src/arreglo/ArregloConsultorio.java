package arreglo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;

import clases.Consultorio;

public class ArregloConsultorio {
	// variables privadas
	private ArrayList<Consultorio> arr;
	private String file = "src/main/resources/data/consultorios.txt";
	
	// constructor
	public ArregloConsultorio() {
		arr = new ArrayList<Consultorio>();
		cargar();
	}
	
	// operaciones públicas básicas
	public void adicionar(Consultorio x) {
		arr.add(x);
		grabar();
	}
	
	public int tamano() {
		return arr.size();
	}
	
	public Consultorio obtener(int i) {
		return arr.get(i);
	}
	
	public void eliminar(Consultorio x) {
		arr.remove(x);
		grabar();
	}
	
	// buscar
	public Consultorio buscarCodConsultorio(int codConsultorio) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getCodConsultorio() == codConsultorio)
				return obtener(i);

		return null;
	}
	
	public Consultorio buscarPiso(int piso) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getPiso() == piso)
				return obtener(i);
		
		return null;
	}

	public Consultorio buscarCapacidad(int capacidad) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getCapacidad() == capacidad)
				return obtener(i);

		return null;
	}
	
	public Consultorio buscarEstado(int estado) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getEstado() == estado)
				return obtener(i);

		return null;
	}
	
	public Consultorio buscarNombre(String nombre) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getNombre().equalsIgnoreCase(nombre))
				return obtener(i);

		return null;
	}
	
	public Consultorio buscarUbicacion(String ubicacion) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getUbicacion().equalsIgnoreCase(ubicacion))
				return obtener(i);

		return null;
	}
	
	// ordenar
	public void ordenarPorCodigo() {
		arr.sort(Comparator.comparingInt(Consultorio::getCodConsultorio));
	}
	
	// archivos de texto
	private void cargar() {
		try {
			BufferedReader br;
			String linea;
			String[] s;
			
			// campos
			int codConsultorio, piso, capacidad, estado;
			String nombre, ubicacion;
			
			br = new BufferedReader(new FileReader(file));
			while ((linea = br.readLine()) != null) {
				s = linea.split(";", -1); // partir a traves de punto y coma, permitir cadenas vacías
				codConsultorio = Integer.parseInt(s[0].trim());
				piso = Integer.parseInt(s[1].trim());
				capacidad = Integer.parseInt(s[2].trim());
				estado = Integer.parseInt(s[3].trim());
				nombre = s[4].trim();
				ubicacion = s[5].trim();
				arr.add(new Consultorio(codConsultorio, piso, capacidad, estado, nombre, ubicacion));
			}
			br.close();
		}
		catch (Exception e) {
			System.out.println("Error al cargar: " + e.getMessage());
		}
	}
	
	public void grabar() {
		try {
			PrintWriter pw;
			String linea;
			Consultorio x;
			pw = new PrintWriter(new FileWriter(file));
			for (int i=0; i<tamano(); i++) {
				x = obtener(i);
				linea = x.getCodConsultorio() + ";" +
						x.getPiso() + ";" +
						x.getCapacidad() + ";" +
						x.getEstado() + ";" +
						x.getNombre() + ";" +
						x.getUbicacion();
				pw.println(linea);
			}
			pw.close();
		}
		catch (Exception e) {
			System.out.println("Error al grabar: " + e.getMessage());
		}
	}
}
