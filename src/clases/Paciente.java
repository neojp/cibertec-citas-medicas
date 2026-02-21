package clases;

import arreglo.ArregloPaciente;

public class Paciente {
	// variables privadas
	private int codPaciente, edad, estado;
	private String nombres, apellidos, dni, celular, correo;
	
	// variables estaticas privadas
	private static int indice = 1000;
	
	// variables estaticas publicas
	public static String[] estados = new String[] {
		"Inactivo",
		"Activo"
	};

	// constructor
	public Paciente() {
		this(generarCodPaciente(), 0, 0, "", "", "", "", "");
	}
	
	public Paciente(int codPaciente, int edad, int estado, String nombres, String apellidos, String dni, String celular, String correo) {
		this.codPaciente = codPaciente;
		this.edad = edad;
		this.estado = estado;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.dni = dni;
		this.celular = celular;
		this.correo = correo;
	}
	
	// getters & setters
	public int getCodPaciente() {
		return codPaciente;
	}
	public void setCodPaciente(int codPaciente) {
		this.codPaciente = codPaciente;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
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
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	// metodos estaticos publicos
	// generar código de paciente en base al último código disponible
	public static int generarCodPaciente() {
		// siempre obtener la última versión del arreglo
		// ordenar por código de forma ascendente
		// y obtener el último código disponible para incrementar por 1
		ArregloPaciente arr = new ArregloPaciente();
		if (arr.tamano() > 0) {
			arr.ordenarPorCodigo();
			indice = arr.obtener(arr.tamano() - 1).getCodPaciente();
		}

		// incrementar el indice por 1
		indice++;

		return indice;
	}
	
	public static boolean validarEdad(int edad) {
		return edad >= 0;
	}
	
	// validar que el DNI sea único y no exista en el sistema
	public static boolean validarDniUnico(String dni) {
		ArregloPaciente arr = new ArregloPaciente();
		System.out.println("validar DNI: " + dni);
		System.out.println(arr.buscarDni(dni));
		return arr.buscarDni(dni) == null;
	}
	
	public Object[] getToRow() {
		return new Object[]{
				codPaciente,
				nombres,
				apellidos,
				dni,
				edad,
				celular,
				correo,
				estado
			};
	}
}
