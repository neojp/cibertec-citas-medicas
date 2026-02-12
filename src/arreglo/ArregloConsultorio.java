package arreglo;

import java.util.ArrayList;

import clases.Consultorio;

public class ArregloConsultorio {
	// variables privadas
	ArrayList<Consultorio> arr;
	
	// constructor
	public ArregloConsultorio() {
		arr = new ArrayList<Consultorio>();
	}
	
	// operaciones públicas básicas
	public void adicionar(Consultorio x) {
		arr.add(x);
	}
	
	public int tamano() {
		return arr.size();
	}
	
	public Consultorio obtener(int i) {
		return arr.get(i);
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
			if (obtener(i).getNombre() == nombre)
				return obtener(i);

		return null;
	}
	
	public Consultorio buscarUbicacion(String ubicacion) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getUbicacion() == ubicacion)
				return obtener(i);

		return null;
	}
}
