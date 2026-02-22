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
	// CARGAR DESDE ARCHIVO DE TEXTO GUARDADO
	private void cargar() {
		// INTENTO ABRIR Y LEER EL ARCHIVO DE DATOS
		try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
			String linea;
			
			// VARIABLE PARA GUARDAR EL MAYOR CORRELATIVO ENCONTRADO EN EL ARCHIVO 
			int maxCorrelativo = 0;
			// LEER EL ARCHIVO LINEA POR LINEA HASTA EL FINAL
			while ((linea = br.readLine()) != null) {
				// SEPARAR LOS DATOS USANDO ; 
				String[] d = linea.split(";");
				// CREA EL OBJETO MEDICO USANDO LOS DATOS LEIDOS DEL ARCHIVO
				Medico m = new Medico(Integer.parseInt(d[0]), d[1], d[2], d[3], d[4], Integer.parseInt(d[5]));
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
	
	// AGREGAR REGISTRO
		public void adicionar(Medico m) {
			arr.add(m);
			guardar(); // CADA VEZ QUE AGREGAMOS, SE ACTUALIZA EL ARCHIVO
		}
	
	
	// MODIFICAR REGISTRO 
	public void actualizar(Medico mActualizado) {
	    for (int i = 0; i < tamano(); i++) {
	        if (obtener(i).getCodMedico() == mActualizado.getCodMedico()) {
	            arr.set(i, mActualizado); // REEMPLAZA LOS DATOS VIEJOS POR  EL NUEVO
	            guardar(); // ACTUALIZA  EL .TXT CON LA LISTA ACTUALIZADA
	            break;
	        }
	    }
	}
	
	 // ELIMINAR REGISTRO 
    public void eliminar(int codMedico) {

        for (int i = 0; i < arr.size(); i++) {

            if (arr.get(i).getCodMedico() == codMedico) {

                arr.remove(i);
                guardar();
                return;
            }
        }
    }

	


	public int tamano() {
		return arr.size();
	}

	public Medico obtener(int i) {
		return arr.get(i);
	}

	//  MÉTODOS DE BÚSQUEDA 

	public Medico buscarCodMedico(int codMedico) {
		for (Medico x : arr) {
			if (x.getCodMedico() == codMedico)
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