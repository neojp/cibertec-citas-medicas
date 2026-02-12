package clases;

public class Medico {
	// variables privadas
	private int codMedico, estado;
	private String nombres, apellidos, especialidad, cmp;
	
	// variables estaticas privadas
	private static int indice = 500;
	
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
	public Medico(int codMedico, int estado, String nombres, String apellidos, String especialidad, String cmp) {
		this.codMedico = codMedico;
		this.estado = estado;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.especialidad = especialidad;
		this.cmp = cmp;
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
	
	// metodos estaticos publicos
	public static int generarCodMedico() {
		return indice + 1;
	}
}
