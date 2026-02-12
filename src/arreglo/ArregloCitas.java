package arreglo;

import java.util.ArrayList;

import clases.Cita;

public class ArregloCitas {
	// variables privadas
	ArrayList<Cita> arr;
	
	// constructor
	public ArregloCitas() {
		arr = new ArrayList<Cita>();
	}
	
	// operaciones públicas básicas
	public void adicionar(Cita x) {
		arr.add(x);
	}
	
	public int tamano() {
		return arr.size();
	}
	
	public Cita obtener(int i) {
		return arr.get(i);
	}
	
	// buscar
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
			if (obtener(i).getFecha() == fecha)
				return obtener(i);

		return null;
	}
	
	public Cita buscarHora(String hora) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getHora() == hora)
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
