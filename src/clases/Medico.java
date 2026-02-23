package clases;

import arreglo.ArregloMedico;

public class Medico {

	// VARIABLES PRIVADAS
	private int codMedico;
	private int estado;
	private String nombres;
	private String apellidos;
	private String especialidad;
	private String cmp;
	private static int indice = 0;

	// CONSTRUCTOR VACÍO
		// constructor
	public Medico() {
		this(generarCodMedico(), "", "", "", "", 0);
	}

	// 3. CONSTRUCTOR PARA NUEVOS REGISTROS
	public Medico(String nombres, String apellidos, String especialidad, String cmp, int estado) {
		ArregloMedico arrMed = new ArregloMedico();
		this.codMedico = generarCodigoCorrelativo(arrMed);
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.especialidad = especialidad;
		this.cmp = cmp;
		this.estado = estado;
	}

	// CONSTRUCTOR PARA NUEVOS REGISTROS
	public Medico(int codMedico, String nombres, String apellidos,
	              String especialidad, String cmp, int estado) {

		this.codMedico = codMedico;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.especialidad = especialidad;
		this.cmp = cmp;
		this.estado = estado;
	}

	// MÉTODO ESTÁTICO PARA GENERAR CÓDIGO ÚNICO 
	public static int generarCodigoCorrelativo(ArregloMedico objArreglo) {
		if (objArreglo.tamano() == 0) {
			return 501; 
		} else {
			return objArreglo.obtener(objArreglo.tamano() - 1).getCodMedico() + 1;
		}
	}



	// =============================
	// GETTERS Y SETTERS
	// =============================

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

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		Medico.indice = indice;
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

	// =============================
	// MÉTODOS AUXILIARES
	// =============================
// validar que el CMP sea único y no exista en el sistema
	public static boolean validarCmpUnico(String cmp) {
		ArregloMedico arr = new ArregloMedico();
		System.out.println("validar cmp: " + cmp);
		System.out.println(arr.buscarPorCmp(cmp));
		return arr.buscarPorCmp(cmp) == null;
	}

			public int getEspecialidadIndex() {
		for (int i = 0; i < Medico.especialidades.length; i++)
			if (Medico.especialidades[i].equalsIgnoreCase(especialidad))
				return i;

		return -1;
	}
	
	public String getNombreCompleto() {
		if (apellidos == null || apellidos.isEmpty())
			return nombres;
		return nombres + " " + apellidos;
	}

	public String aLinea() {
		return codMedico + ";" +
		       nombres + ";" +
		       apellidos + ";" +
		       especialidad + ";" +
		       cmp + ";" +
		       estado;
	}

	// =============================
	// DATOS PARA COMBOBOX
	// =============================

	public static final String[] especialidades = {
		"ANESTESIOLOGÍA", "CARDIOLOGÍA", "CIRUGÍA GENERAL",
		"CIRUGÍA PLÁSTICA", "DERMATOLOGÍA", "ENDOCRINOLOGÍA",
		"GASTROENTEROLOGÍA", "GINECOLOGÍA Y OBSTETRICIA",
		"MEDICINA FAMILIAR", "MEDICINA FÍSICA Y REHABILITACIÓN",
		"MEDICINA GENERAL", "MEDICINA INTERNA",
		"NEFROLOGÍA", "NEUMOLOGÍA", "NEUROLOGÍA",
		"OFTALMOLOGÍA", "ONCOLOGÍA", "OTORRINOLARINGOLOGÍA",
		"PEDIATRÍA", "PSIQUIATRÍA", "RADIOLOGÍA",
		"TRAUMATOLOGÍA", "UROLOGÍA"
	};

	public static final String[] estados = { "INACTIVO", "ACTIVO" };

}