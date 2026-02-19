package clases;

public class Cita {
	// variables privadas
	private int numCita, codMedico, codPaciente, codConsultorio, estado;
	private String fecha, hora, motivo;
	
	// variables estaticas privadas
	private static int indice = 0;
	
	// variables estaticas publicas
	public static String[] estados = new String[] {
		"Pendiente",
		"Atendida",
		"Cancelada"
	};
	
	// constructor
	public Cita(int numCita, int codPaciente, int codMedico, int codConsultorio, int estado, String fecha, String hora, String motivo) {
		this.numCita = numCita;
		this.codPaciente = codPaciente;
		this.codMedico = codMedico;
		this.codConsultorio = codConsultorio;
		this.estado = estado;
		this.fecha = fecha;
		this.hora = hora;
		this.motivo = motivo;
	}

	// getters & setters
	public int getNumCita() {
		return numCita;
	}
	public void setNumCita(int numCita) {
		this.numCita = numCita;
	}
	public int getCodPaciente() {
		return codPaciente;
	}
	public void setCodPaciente(int codPaciente) {
		this.codPaciente = codPaciente;
	}
	public int getCodMedico() {
		return codMedico;
	}
	public void setCodMedico(int codMedico) {
		this.codMedico = codMedico;
	}
	public int getCodConsultorio() {
		return codConsultorio;
	}
	public void setCodConsultorio(int codConsultorio) {
		this.codConsultorio = codConsultorio;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
	// metodos estaticos publicos
	public static int generarNumCita() {
		return indice + 1;
	}
}
