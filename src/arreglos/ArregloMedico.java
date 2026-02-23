package arreglos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import clases.Medico;

public class ArregloMedico {
	
	private ArrayList<Medico> medicos;
	private String nombreArchivo = "medicos.txt";
	
	public ArregloMedico() {
		medicos = new ArrayList<Medico>();
		cargarArchivo(); // INICIA AUTOMATICAMENTE AL INICIAR
	}
	
	public void adicionar(Medico m) {
		medicos.add(m);
		grabarArchivo(); // GUARDA LOS DATOS EN EL DISCO 
	}
	
	public void eliminar(Medico m) {
		medicos.remove(m);
		grabarArchivo(); // ACTUALIZA TRAS LA ELIMINACION
	}
	public Medico buscar(int codMedico) {
		for (Medico m : medicos) {
			if (m.getCodMedico() == codMedico)
				return m;
		}
		return null;
	}
		
	public int tamano() {
		return medicos.size();
	}
	
	public Medico obtener(int i) {
		return medicos.get(i);
	}
	
	public void actualizar() {
		grabarArchivo(); // Guarda los cambios realizados en los objetos del ArrayList
	}
	
	// GENERAR PROXIMO CODIGO ENPEZANDO EN 501
	public int proximoCodigo() {
	    // SI NO HAY NINGUN REGISTRO SE EMPIESA CON 501
	    if (tamano() == 0) {
	        return 501;
	    }
	    // SE BUSCA EL CODIGO MAXIMO 
	    int codigoMaximo = 0;
	    for (int i = 0; i < tamano(); i++) {
	        if (obtener(i).getCodMedico() > codigoMaximo) {
	            codigoMaximo = obtener(i).getCodMedico();
	        }
	    }
	    
	    // MUESTRA EL CODIG MAXIMO Y AGREGA 1
	    return codigoMaximo + 1;
	}
	//VALIDA QUE EL CMP SEA UNICO
	public Medico buscarPorCmp(String cmp) {
		for (Medico m : medicos) {
			if (m.getCmp().equalsIgnoreCase(cmp))
				return m;
		}
		return null;
	}
	
	// METODO PARA GUARDAR DATOS EN ARCHIVO DE TEXTO EN MEDICOS.TXT
	public void grabarArchivo() {
			try {
				PrintWriter pw = new PrintWriter(new FileWriter(nombreArchivo));
				for (int i = 0; i < tamano(); i++) {
					Medico m = obtener(i);
					pw.println(m.getCodMedico() + ";" + 
							   m.getNombres() + ";" + 
							   m.getApellidos() + ";" + 
							   m.getEspecialidad() + ";" + 
							   m.getCmp() + ";" + 
							   m.getEstado());
				}
				// CIERRA EL FLUJO DE ESCRITURA PARA LIBERAR RECURSOS
				pw.close();
			} catch (Exception e) {
				// CAPTURA POSIBLES ERRORES DE ESCRITURA O CREACIÓN DEL ARCHIVO
				System.out.println("ERROR AL GRABAR ARCHIVO: " + e.getMessage());
			}
		}
		
		//METODO PARA CARGAR DATOS DEL ARCHIVO AL ARREGLO 
		private void cargarArchivo() {
			try {
				// CREA UN OBJETO BufferedReader PARA LEER EL ARCHIVO LÍNEA POR LÍNEA
				BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
				String linea;
				// LEE CADA LÍNEA DEL ARCHIVO HASTA QUE NO EXISTA MÁS CONTENIDO
				while ((linea = br.readLine()) != null) {
					// SEPARA LOS DATOS UTILIZANDO EL CARÁCTER ';'
					String[] d = linea.split(";");
					
					// CONVIERTE LOS DATOS DEL TEXTO A SUS TIPOS CORRESPONDIENTES
					int cod = Integer.parseInt(d[0]);
					String nom = d[1];
					String ape = d[2];
					String esp = d[3];
					String cmp = d[4];
					int est = Integer.parseInt(d[5]);
					
					// CREA UN NUEVO OBJETO MÉDICO CON LOS DATOS LEÍDOS
					medicos.add(new Medico(cod, nom, ape, esp, cmp, est));
				}
				
				// CIERRA EL FLUJO DE LECTURA PARA LIBERAR RECURSOS
				br.close();
			} catch (Exception e) {
				System.out.println("ARCHIVO NUEVO O NO ENCONTRADO. SE CREARÁ AUTOMÁTICAMENTE AL GRABAR.");
			}
		}
	
	
	
	
	
	
	
	
	

}
