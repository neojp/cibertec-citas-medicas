package clases;

public class Medico {
	// variables privadas
	private int  codMedico, estado;
	private String nombres, apellidos, especialidad, cmp;
	
	// variables estaticas privadas
	private static int contador = 1;
	
	// 3. CONSTRUCTOR PARA NUEVOS REGISTROS
	public Medico(String nombres, String apellidos, String especialidad, String cmp, int estado) {
		this.codMedico = generarCorrelativo();
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
		
		//METODO QUE GENERA EL FORMATO M000001
		private String generarCorrelativo() {
			return String.format("M%05d", contador++);
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
	
	// getters & setters
	public int getCodMedico() {
		return codMedico;
	}
	public void setCodMedico(String codMedico) {
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
	
}
