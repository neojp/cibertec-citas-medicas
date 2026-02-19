package clases;

import arreglo.ArregloMedico;
import arreglo.ArregloMedico;

public class Medico {
	// variables privadas
	private int  codMedico, estado;
	private String nombres, apellidos, especialidad, cmp;
	
	// variables estaticas privadas
	private static int indice = 0;
	
	// variables estaticas publicas
	public static String[] especialidades = new String[] {
		"Anestesiología",
		"Cardiología",
		"Cirugía General",
		"Cirugía Plástica",
		"Dermatología",
		"Endocrinología",
		"Gastroenterología",
		"Ginecología y Obstetricia",
		"Medicina Familiar",
		"Medicina Física y Rehabilitación",
		"Medicina General",
		"Medicina Interna",
		"Nefrología",
		"Neumología",
		"Neurología",
		"Oftalmología",
		"Oncología",
		"Otorrinolaringología",
		"Pediatría",
		"Psiquiatría",
		"Radiología",
		"Traumatología",
		"Urología"
	};
	
	public static String[] estados = new String[] {
		"Inactivo",
		"Activo"
	};
	
	// constructor
	public Medico() {
		this(generarCodMedico(), "", "", "", "", 0);
	}
	public Medico(int codMedico, String nombres, String apellidos, String especialidad, String cmp, int estado) {
		this.codMedico = codMedico;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.especialidad = especialidad;
		this.cmp = cmp;
		this.estado = estado;
	}
	
	// getters & setters
	public int getCodMedico() {
		return codMedico;
	}
	public void setCodMedico(int codMedico) {
		this.codMedico = codMedico;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	public String getCmp() {
		return cmp;
	}
	public void setCmp(String cmp) {
		this.cmp = cmp;
	}
	public int getEspecialidadIndex() {
		for (int i = 0; i < Medico.especialidades.length; i++)
			if (Medico.especialidades[i].equalsIgnoreCase(especialidad))
				return i;

		return -1;
	}

	// métodos estaticos públicos
	// generar código de médico en base al último código disponible
	public static int generarCodMedico() {
		// siempre obtener la última versión del arreglo
		// ordenar por código de forma ascendente
		// y obtener el último código disponible para incrementar por 1
		ArregloMedico arr = new ArregloMedico();
		if (arr.tamano() > 0) {
			arr.ordenarPorCodigo();
			indice = arr.obtener(arr.tamano() - 1).getCodMedico();
		}

		// incrementar el indice por 1
		indice++;

		return indice;
	}
	
	// validar que el CMP sea único y no exista en el sistema
	public static boolean validarCmpUnico(String cmp) {
		ArregloMedico arr = new ArregloMedico();
		System.out.println("validar cmp: " + cmp);
		System.out.println(arr.buscarCmp(cmp));
		return arr.buscarCmp(cmp) == null;
	}
}
