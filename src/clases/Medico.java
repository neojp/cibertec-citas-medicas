package clases;

import arreglo.ArregloMedico;

public class Medico {
	// variables privadas
	private int  codMedico, estado;
	private String nombres, apellidos, especialidad, cmp;
	
	// variables estaticas privadas
	private static int contador = 1;
	private static int indice = 1;

	// constructor
	public Medico() {
		this(generarCodMedico(), "", "", "", "", 0);
	}

	// 3. CONSTRUCTOR PARA NUEVOS REGISTROS
	public Medico(String nombres, String apellidos, String especialidad, String cmp, int estado) {
		this.codMedico = generarCodigoCorrelativo();
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.especialidad = especialidad;
		this.cmp = cmp;
		this.estado = estado;
	}
	
	// 4. CONSTRUCTOR PARA CARGAR DESDE ARCHIVO (Recibe el código ya existente)
		public Medico(int codMedico, String nombres, String apellidos, String especialidad, String cmp, int estado) {
			this.codMedico = codMedico;
			this.nombres = nombres;
			this.apellidos = apellidos;
			this.especialidad = especialidad;
			this.cmp = cmp;
			this.estado = estado;
		}

		//METODOS 
		
    		// MÉTODO ESTÁTICO PARA GENERAR CÓDIGO ÚNICO 
		public static int generarCodigoCorrelativo(ArregloMedico objArreglo) {
        		if (objArreglo.tamano() == 0) {
        		    return 501; 
        		} else {
        		    return objArreglo.obtener(objArreglo.tamano() - 1).getCodMedico() + 1;
        		}
		}
		
		// Convierte el objeto a una línea de texto para el archivo .txt
		public String aLinea() {
			return codMedico + ";" + nombres + ";" + apellidos + ";" + especialidad + ";" + cmp + ";" + estado;
			
		}
		
		
		/* Devuelve el código que le tocaría al próximo medico 
		pero sin aumentar el contador*/
		public static String proximoCodigo() {
			return String.format("M%05d", contador);
		}
		//método cambia manualmente el valor del contador valida el ultimo nro del contador 
		public static void setContador(int nuevoValor) {
			contador = nuevoValor;
		}
			
	// variables estaticas publicas para el JCONBOBOX
		public static String[] especialidades = new String[] {
		        "ANESTESIOLOGÍA",
		        "CARDIOLOGÍA",
		        "CIRUGÍA GENERAL",
		        "CIRUGÍA PLÁSTICA",
		        "DERMATOLOGÍA",
		        "ENDOCRINOLOGÍA",
		        "GASTROENTEROLOGÍA",
		        "GINECOLOGÍA Y OBSTETRICIA",
		        "MEDICINA FAMILIAR",
		        "MEDICINA FÍSICA Y REHABILITACIÓN",
		        "MEDICINA GENERAL",
		        "MEDICINA INTERNA",
		        "NEFROLOGÍA",
		        "NEUMOLOGÍA",
		        "NEUROLOGÍA",
		        "OFTALMOLOGÍA",
		        "ONCOLOGÍA",
		        "OTORRINOLARINGOLOGÍA",
		        "PEDIATRÍA",
		        "PSIQUIATRÍA",
		        "RADIOLOGÍA",
		        "TRAUMATOLOGÍA",
		        "UROLOGÍA"
		};
		
		public static final String[] estados = {"INACTIVO", "ACTIVO"};


	
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
	
	public String getNombreCompleto() {
		String str = this.nombres;
		if (!this.apellidos.isEmpty())
			str += " " + this.apellidos;
		return str;
	}
	
}
