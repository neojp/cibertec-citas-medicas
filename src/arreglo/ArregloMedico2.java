package arreglo;

import java.io.*;
import java.util.*;
import clases.Medico;

public class ArregloMedico2 {
	private ArrayList<Medico> arr;
	private String ruta = "medicos.txt"; // El archivo se creará en la carpeta del proyecto

	public ArregloMedico2() {
		arr = new ArrayList<Medico>();
		cargar(); // CARGA LOS DATOS AUTOMÁTICAMENTE AL INICIAR
	}

	
// METODO GUARDA  LOS REGISTROS DEL ARREGLO EN EL ARCHIVO DE TEXTO
	private void guardar() {
		try (PrintWriter pw = new PrintWriter(new FileWriter(ruta))) {
			for (int i = 0; i < tamano(); i++) {
				pw.println(obtener(i).aLinea());
			}
		} catch (Exception e) {
			System.out.println("Error al guardar: " + e.getMessage());
		}
	}

	private void cargar() {
		// INTENTO ABRIR Y LEER EL ARCHIVO DE DATOS
		try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
			String linea;
			
			// VARIABLE PARA GUARDAR EL MAYOR CORRELATIVO ENCONTRADO EN EL ARCHIVO 
			int maxCorrelativo = 0;
			// LEER EL ARCHIVO LINEA POR LINEA HASTA EL FINAL
			while ((linea = br.readLine()) != null) {
				// SEPARAR LOS DATOS USANDO ; 
				String[] d = linea.split(";", -1);  // partir a traves de punto y coma, permitir cadenas vacías
				// CREA EL OBJETO MEDICO USANDO LOS DATOS LEIDOS DEL ARCHIVO
				Medico m = new Medico(d[0], d[1], d[2], d[3], d[4], Integer.parseInt(d[5]));
				arr.add(m);
				
				// EXTRAER EL NUMERO DEL CODIGO
				int numActual = Integer.parseInt(d[0].substring(1));
				if (numActual > maxCorrelativo) maxCorrelativo = numActual;
			}
			// ACTUALIZAR EL CONTADOR ESTATICO PARA EVITAR CODIGOS REPETIDOS
			Medico.setContador(maxCorrelativo + 1);
			
		} catch (FileNotFoundException e) {
			// SI EL ARCHIVO NO EXISTE NO SE HACE NADA SE CREAR UNO NUEVO
		} catch (Exception e) {
			System.out.println("Error al cargar: " + e.getMessage());
		}
	}

	

	public void adicionar(Medico x) {
		arr.add(x);
		guardar(); // CADA VEZ QUE AGREGAMOS, SE ACTUALIZA EL ARCHIVO
	}

	public int tamano() {
		return arr.size();
	}

	public Medico obtener(int i) {
		return arr.get(i);
	}

	//  MÉTODOS DE BÚSQUEDA 

	public Medico buscarCodMedico(String codMedico) {
		for (Medico x : arr) {
			if (x.getCodMedico().equalsIgnoreCase(codMedico))
				return x;
		}
		return null;
	}

	public Medico buscarCmp(String cmp) {
		for (Medico x : arr) {
			if (x.getCmp().equals(cmp))
				return x;
		}
		return null;
	}
}