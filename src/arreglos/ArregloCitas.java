package arreglos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import clases.Cita;

public class ArregloCitas {
	// variables privadas
	ArrayList<Cita> arr;
	
	// constructor
	public ArregloCitas() {
		arr = new ArrayList<Cita>();
		cargarArchivo();
	}
	
	
	public void adicionar(Cita x) {
		arr.add(x);
		grabarArchivo();
	}
	
	// METODO PARA ELIMINAR 
		public void eliminar(Cita x) {
			arr.remove(x);
			grabarArchivo();
		}
	//METODO PARA ACTUALIZAR 
		public void actualizarArchivo() {
	        grabarArchivo();
	    }
	
	
	
	
	public int tamano() {
		return arr.size();
	}
	
	public Cita obtener(int i) {
		return arr.get(i);
	}
	
	//GENERA EL PROXIMO NUMERO DE LA CITA VALIDANDO EL MAXIMO NRO 
	public int proximoCodigo() {
		if (tamano() == 0) {
			return 1001;
		}
		int max = 0;
		for (int i = 0; i < tamano(); i++) {
			if (obtener(i).getNumCita() > max) {
				max = obtener(i).getNumCita();
			}
		}
		return max + 1;
	}
	
	public void grabarArchivo() {
		try {
			// GUARDA LAS CITAS EN EL ARCHIVO CITAS.TXT 
			PrintWriter pw = new PrintWriter(new FileWriter("citas.txt"));
			for (int i = 0; i < tamano(); i++) {
				Cita c = obtener(i);
				pw.println(c.getNumCita() + ";" + 
						   c.getCodPaciente() + ";" + 
						   c.getCodMedico() + ";" + 
						   c.getCodConsultorio() + ";" + 
						   c.getEstado() + ";" + 
						   c.getFecha() + ";" + 
						   c.getHora() + ";" + 
						   c.getMotivo());
			}
			pw.close();
		} catch (Exception e) {
			System.out.println("ERROR AL REGISTRAR CITA:  " + e.getMessage());
		}
	}
	
	private void cargarArchivo() {
		try {
			File f = new File("citas.txt");
			if (f.exists()) {
				BufferedReader br = new BufferedReader(new FileReader("citas.txt"));
				String linea;
				while ((linea = br.readLine()) != null) {
					String[] s = linea.split(";");
					int num = Integer.parseInt(s[0]);
					int pac = Integer.parseInt(s[1]);
					int med = Integer.parseInt(s[2]);
					int con = Integer.parseInt(s[3]);
					int est = Integer.parseInt(s[4]);
					String fec = s[5];
					String hor = s[6];
					String mot = s[7];
					
					arr.add(new Cita(num, pac, med, con, est, fec, hor, mot));
				}
				br.close();
			}
		} catch (Exception e) {
			System.out.println("EROOR AL EJECUTAR EL ARCHIVO CITAS: " + e.getMessage());
		}
	}
	
	
	
	
	// MÉTODOS DE BÚSQUEDA 
		public Cita buscarNumCita(int numCita) {
			for (int i = 0; i < tamano(); i++)
				if (obtener(i).getNumCita() == numCita)
					return obtener(i);
			return null;
		}
	
	public Cita buscarCodPaciente(int codPaciente) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getCodPaciente() == codPaciente)
				return obtener(i);

		return null;
	}
	
	public Cita buscarCodMedico(int codMedico) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getCodMedico() == codMedico)
				return obtener(i);

		return null;
	}
	
	public Cita buscarCodConsultorio(int codConsultorio) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getCodConsultorio() == codConsultorio)
				return obtener(i);

		return null;
	}
	
	public Cita buscarEstado(int estado) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getEstado() == estado)
				return obtener(i);

		return null;
	}
	
	public Cita buscarFecha(String fecha) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getFecha().equals(fecha))
				return obtener(i);
		return null;
	}
	
	public Cita buscarHora(String hora) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getHora().equals(hora))
				return obtener(i);
		return null;
	}
	
	public Cita buscarMotivo(String motivo) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getMotivo() == motivo)
				return obtener(i);
		return null;
	}
}
