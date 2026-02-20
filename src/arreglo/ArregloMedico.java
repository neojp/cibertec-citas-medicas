package arreglo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;

import clases.Medico;

public class ArregloMedico {
	// variables privadas
	ArrayList<Medico> arr;
	private String file = "src/main/resources/data/medicos.txt";
	
	// constructor
	public ArregloMedico() {
		arr = new ArrayList<Medico>();
		cargar();
	}
	
	// operaciones públicas básicas
	public void adicionar(Medico x) {
		arr.add(x);
		grabar();
	}
	
	public int tamano() {
		return arr.size();
	}
	
	public Medico obtener(int i) {
		return arr.get(i);
	}
	
	public void eliminar(Medico x) {
		arr.remove(x);
		grabar();
	}
	
	// buscar
	public Medico buscarCodMedico(int codMedico) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getCodMedico() == codMedico)
				return obtener(i);

		return null;
	}
	
	public ArrayList<Medico> buscarEstado(int estado) {
		ArrayList<Medico> aux = new ArrayList<Medico>();

		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getEstado() == estado)
				aux.add(obtener(i));

		if (aux.size() > 0)
			return aux;

		return null;
	}
	
	public ArrayList<Medico> buscarNombres(String nombres) {
		ArrayList<Medico> aux = new ArrayList<Medico>();

		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getNombres().equalsIgnoreCase(nombres))
				aux.add(obtener(i));

		if (aux.size() > 0)
			return aux;

		return null;
	}
	
	public ArrayList<Medico> buscarApellidos(String apellidos) {
		ArrayList<Medico> aux = new ArrayList<Medico>();

		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getApellidos().equalsIgnoreCase(apellidos))
				aux.add(obtener(i));

		if (aux.size() > 0)
			return aux;

		return null;
	}
	
	public ArrayList<Medico> buscarEspecialidad(String especialidad) {
		ArrayList<Medico> aux = new ArrayList<Medico>();

		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getEspecialidad().equalsIgnoreCase(especialidad))
				aux.add(obtener(i));

		if (aux.size() > 0)
			return aux;

		return null;
	}

	public Medico buscarCmp(String cmp) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getCmp().equalsIgnoreCase(cmp))
				return obtener(i);
		
		return null;
	}
	
	// ordenar por código
	public void ordenarPorCodigo() {
		arr.sort(Comparator.comparingInt(Medico::getCodMedico));
	}
	
	// ordenar alfabéticamente por nombre completo sin importar mayúsculas
	public void ordenarPorNombreCompleto() {
		arr.sort(Comparator.comparing(Medico::getNombreCompleto, String.CASE_INSENSITIVE_ORDER));
	}
	
	// 	archivos de texto
	private void cargar() {
		try {
			BufferedReader br;
			String linea;
			String[] s;
			
			// campos
			int codMedico, estado;
			String nombres, apellidos, especialidad, cmp;
			
			br = new BufferedReader(new FileReader(file));
			while ((linea = br.readLine()) != null) {
				s = linea.split(";", -1); // partir a traves de punto y coma, permitir cadenas vacías
				codMedico = Integer.parseInt(s[0].trim());
				nombres = s[1].trim();
				apellidos = s[2].trim();
				especialidad = s[3].trim();
				cmp = s[4].trim();
				estado = Integer.parseInt(s[5].trim());
				arr.add(new Medico(codMedico, nombres, apellidos, especialidad, cmp, estado));
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
			Medico x;
			pw = new PrintWriter(new FileWriter(file));
			for (int i=0; i<tamano(); i++) {
				x = obtener(i);
				linea = x.getCodMedico() + ";" +
						x.getNombres() + ";" +
						x.getApellidos() + ";" +
						x.getEspecialidad() + ";" +
						x.getCmp() + ";" +
						x.getEstado();
				pw.println(linea);
			}
			pw.close();
		}
		catch (Exception e) {
			System.out.println("Error al grabar: " + e.getMessage());
		}
	}
}
