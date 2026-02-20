package arreglo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

import clases.Cita;
import clases.Consultorio;

public class ArregloCitas {
	// variables privadas
	ArrayList<Cita> arr;
	private String file = "src/main/resources/data/citas.txt";
	
	// constructor
	public ArregloCitas() {
		arr = new ArrayList<Cita>();
		cargar();
	}
	
	// operaciones públicas básicas
	public void adicionar(Cita x) {
		arr.add(x);
		grabar();
	}
	
	public int tamano() {
		return arr.size();
	}
	
	public Cita obtener(int i) {
		return arr.get(i);
	}
	
	public void eliminar(Cita x) {
		arr.remove(x);
		grabar();
	}
	
	// buscar
	public Cita buscarNumCita(int numCita) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getNumCita() == numCita)
				return obtener(i);

		return null;
	}
	
	public ArrayList<Cita> buscarCodPaciente(int codPaciente) {
		ArrayList<Cita> aux = new ArrayList<Cita>();

		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getCodPaciente() == codPaciente)
				aux.add(obtener(i));

		if (aux.size() > 0)
			return aux;

		return null;
	}
	
	public ArrayList<Cita> buscarCodMedico(int codMedico) {
		ArrayList<Cita> aux = new ArrayList<Cita>();

		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getCodMedico() == codMedico)
				aux.add(obtener(i));

		if (aux.size() > 0)
			return aux;

		return null;
	}

	public ArrayList<Cita> buscarCodConsultorio(int codConsultorio) {
		ArrayList<Cita> aux = new ArrayList<Cita>();

		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getCodConsultorio() == codConsultorio)
				aux.add(obtener(i));

		if (aux.size() > 0)
			return aux;

		return null;
	}
	
	public ArrayList<Cita> buscarEstado(int estado) {
		ArrayList<Cita> aux = new ArrayList<Cita>();

		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getEstado() == estado)
				aux.add(obtener(i));

		if (aux.size() > 0)
			return aux;

		return null;
	}
	
	public ArrayList<Cita> buscarFecha(String fecha) {
		ArrayList<Cita> aux = new ArrayList<Cita>();

		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getFecha().equalsIgnoreCase(fecha))
				aux.add(obtener(i));

		if (aux.size() > 0)
			return aux;

		return null;
	}
	
	public ArrayList<Cita> buscarHora(String hora) {
		ArrayList<Cita> aux = new ArrayList<Cita>();

		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getHora().equalsIgnoreCase(hora))
				aux.add(obtener(i));

		if (aux.size() > 0)
			return aux;

		return null;
	}
	
	public ArrayList<Cita> buscarMotivo(String motivo) {
		ArrayList<Cita> aux = new ArrayList<Cita>();

		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getMotivo().equalsIgnoreCase(motivo))
				aux.add(obtener(i));

		if (aux.size() > 0)
			return aux;

		return null;
	}
	
	public ArrayList<Cita> buscarFuturasPorConsultorio(int codConsultorio) {
		ArrayList<Cita> aux = new ArrayList<Cita>();

		for (int i = 0; i < tamano(); i++) {
			// filtrar por código de consultorio y estado pendiente
			if (obtener(i).getCodConsultorio() == codConsultorio && obtener(i).getEstado() == 0) {
				// generar un objeto de fecha con los datos de la cita
				DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
				String fechaTexto = obtener(i).getFecha() + " " + obtener(i).getHora();
				LocalDateTime fechaIngresada = LocalDateTime.parse(fechaTexto, formato);
				
				// comparar con la fecha actual
				LocalDateTime ahora = LocalDateTime.now();
				if (fechaIngresada.isAfter(ahora)) {
					aux.add(obtener(i));
				}
			}
		}

		if (aux.size() > 0)
			return aux;

		return null;
	}
	
	public ArrayList<Cita> buscarFuturasPorMedico(int codMedico) {
		ArrayList<Cita> aux = new ArrayList<Cita>();

		for (int i = 0; i < tamano(); i++) {
			// filtrar por código de medico y estado pendiente
			if (obtener(i).getCodMedico() == codMedico && obtener(i).getEstado() == 0) {
				// generar un objeto de fecha con los datos de la cita
				DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
				String fechaTexto = obtener(i).getFecha() + " " + obtener(i).getHora();
				LocalDateTime fechaIngresada = LocalDateTime.parse(fechaTexto, formato);
				
				// comparar con la fecha actual
				LocalDateTime ahora = LocalDateTime.now();
				if (fechaIngresada.isAfter(ahora)) {
					aux.add(obtener(i));
				}
			}
		}

		if (aux.size() > 0)
			return aux;

		return null;
	}
	
	public ArrayList<Cita> buscarFuturasPorPaciente(int codPaciente) {
		ArrayList<Cita> aux = new ArrayList<Cita>();

		for (int i = 0; i < tamano(); i++) {
			// filtrar por código de paciente y estado pendiente
			if (obtener(i).getCodPaciente() == codPaciente && obtener(i).getEstado() == 0) {
				// generar un objeto de fecha con los datos de la cita
				DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
				String fechaTexto = obtener(i).getFecha() + " " + obtener(i).getHora();
				LocalDateTime fechaIngresada = LocalDateTime.parse(fechaTexto, formato);
				
				// comparar con la fecha actual
				LocalDateTime ahora = LocalDateTime.now();
				if (fechaIngresada.isAfter(ahora)) {
					aux.add(obtener(i));
				}
			}
		}

		if (aux.size() > 0)
			return aux;

		return null;
	}
	
	// ordenar por código
	public void ordenarPorNumero() {
		arr.sort(Comparator.comparingInt(Cita::getNumCita));
	}
	
	// archivos de texto
	private void cargar() {
		try {
			BufferedReader br;
			String linea;
			String[] s;

			// campos
			int numCita, codPaciente, codMedico, codConsultorio, estado;
			String fecha, hora, motivo;

			br = new BufferedReader(new FileReader(file));
			while ((linea = br.readLine()) != null) {
				s = linea.split(";", -1); // partir a traves de punto y coma, permitir cadenas vacías
				numCita = Integer.parseInt(s[0].trim());
				codPaciente = Integer.parseInt(s[1].trim());
				codMedico = Integer.parseInt(s[2].trim());
				codConsultorio = Integer.parseInt(s[3].trim());
				estado = Integer.parseInt(s[4].trim());
				fecha = s[5].trim();
				hora = s[6].trim();
				motivo = s[7].trim();
				arr.add(new Cita(numCita, codPaciente, codMedico, codConsultorio, estado, fecha, hora, motivo));
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
			Cita x;
			pw = new PrintWriter(new FileWriter(file));
			for (int i=0; i<tamano(); i++) {
				x = obtener(i);
				linea = x.getNumCita() + ";" +
						x.getCodPaciente() + ";" +
						x.getCodMedico() + ";" +
						x.getCodConsultorio() + ";" +
						x.getEstado() + ";" +
						x.getFecha() + ";" +
						x.getHora() + ";";
						x.getMotivo();
				pw.println(linea);
			}
			pw.close();
		}
		catch (Exception e) {
			System.out.println("Error al grabar: " + e.getMessage());
		}
	}
}
