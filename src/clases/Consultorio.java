package clases;

import arreglo.ArregloConsultorio;

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
	public Consultorio() {
		this(generarCodConsultorio(), 1, 1, 0, "", "");
	}
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
	// generar código de consultorio en base al último código disponible
	public static int generarCodConsultorio() {
		// siempre obtener la ultima version del arreglo
		// ordenar por código de forma ascendente
		// y obtener el último código disponible para incrementar por 1
		ArregloConsultorio arr = new ArregloConsultorio();
		if (arr.tamano() > 0) {
			arr.ordenarPorCodigo();
			indice = arr.obtener(arr.tamano() - 1).getCodConsultorio();
		}

		// incrementar el indice por 1
		indice++;

		return indice;
	}
	
	// validar que piso sea mayor a 0
	public static boolean validarPiso(int piso) {
		return piso > 0;
	}
}
