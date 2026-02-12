package arreglo;

import java.util.ArrayList;

import clases.Paciente;

public class ArregloPaciente {
	// variables privadas
	ArrayList<Paciente> arr;
	
	// constructor
	public ArregloPaciente() {
		arr = new ArrayList<Paciente>();
	}
	
	// operaciones públicas básicas
	public void adicionar(Paciente x) {
		arr.add(x);
	}
	
	public int tamano() {
		return arr.size();
	}
	
	public Paciente obtener(int i) {
		return arr.get(i);
	}
	
	// buscar
	public Paciente buscarCodPaciente(int codPaciente) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getCodPaciente() == codPaciente)
				return obtener(i);

		return null;
	}
	
	public Paciente buscarEdad(int edad) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getEdad() == edad)
				return obtener(i);
		
		return null;
	}

	public Paciente buscarEstado(int estado) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getEstado() == estado)
				return obtener(i);

		return null;
	}
	
	public Paciente buscarNombres(String nombres) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getNombres() == nombres)
				return obtener(i);

		return null;
	}
	
	public Paciente buscarApellidos(String apellidos) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getApellidos() == apellidos)
				return obtener(i);

		return null;
	}
	
	public Paciente buscarDni(String dni) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getDni() == dni)
				return obtener(i);
		
		return null;
	}
	
	public Paciente buscarCelular(String celular) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getCelular() == celular)
				return obtener(i);
		
		return null;
	}
	
	public Paciente buscarCorreo(String correo) {
		for (int i = 0; i < tamano(); i++)
			if (obtener(i).getCorreo() == correo)
				return obtener(i);
		
		return null;
	}
}
