package arreglo;

import java.util.ArrayList;

import clases.Medico;

public class ArregloMedico {
	// variables privadas
	ArrayList<Medico> arr;
	
	// constructor
	public ArregloMedico() {
		arr = new ArrayList<Medico>();
	}
	
	// operaciones públicas básicas
	public void adicionar(Medico x) {
		arr.add(x);
	}
	
	public int tamano() {
		return arr.size();
	}
	
	public Medico obtener(int i) {
		return arr.get(i);
	}
	
	/* buscar
	public Medico buscarCodMedico(int codMedico) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getCodMedico() == codMedico)
				return obtener(i);

		return null;
	}*/
	
	public Medico buscarEstado(int estado) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getEstado() == estado)
				return obtener(i);

		return null;
	}
	
	public Medico buscarNombres(String nombres) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getNombres().equalsIgnoreCase(nombres))
				return obtener(i);

		return null;
	}
	
	public Medico buscarApellidos(String apellidos) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getApellidos().equalsIgnoreCase(apellidos))
				return obtener(i);

		return null;
	}
	
	public Medico buscarEspecialidad(String especialidad) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getEspecialidad().equalsIgnoreCase(especialidad))
				return obtener(i);
		
		return null;
	}
	
	public Medico buscarCmp(String cmp) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getCmp() == cmp)
				return obtener(i);
		
		return null;
	}
}
