package clases;

public class Consultorio {
	// variables privadas
	private int codConsultorio, piso, capacidad, estado;
	private String nombre, ubicacion;

	// variables estaticas privadas
	private static int indice = 300;
	
	// variables estaticas publicas
	public static String[] estados = new String[] {
		"Inactivo",
		"Activo"
	};

	// constructor
	public Consultorio(int codConsultorio, int piso, int capacidad, int estado, String nombre, String ubicacion) {
		this.codConsultorio = codConsultorio;
		this.piso = piso;
		this.capacidad = capacidad;
		this.estado = estado;
		this.nombre = nombre;
		this.ubicacion = ubicacion;
	}

	// getters & setters
	public int getCodConsultorio() {
		return codConsultorio;
	}
	public void setCodConsultorio(int codConsultorio) {
		this.codConsultorio = codConsultorio;
	}
	public int getPiso() {
		return piso;
	}
	public void setPiso(int piso) {
		this.piso = piso;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	// metodos estaticos publicos
	public static int generarCodConsultorio() {
		return indice + 1;
	}
	
	public static boolean validarPiso(int piso) {
		return piso >= 0;
	}
}
