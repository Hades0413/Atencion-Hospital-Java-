package clases;

public class Atencion {

	//	Atributos privados
	private int codigoAtencion, codigoPaciente, estado;
	private String fechaAtencion, horaAtencion;
	private double totalPagar;
	//	Constructor
	public Atencion(int codigoAtencion, int codigoPaciente, String fechaAtencion,
			        String horaAtencion, double totalPagar, int estado) {
		this.codigoAtencion = codigoAtencion;
		this.codigoPaciente = codigoPaciente;
		this.fechaAtencion = fechaAtencion;
		this.horaAtencion = horaAtencion;
		this.totalPagar = totalPagar;
		this.estado = estado;
	}
	//  Métodos de acceso público: set/get
	public void setCodigoAtencion(int codigoAtencion) {
		this.codigoAtencion = codigoAtencion;
	}
	public void setCodigoPaciente(int codigoPaciente) {
		this.codigoPaciente = codigoPaciente;
	}
	public void setFechaAtencion(String fechaConsulta) {
		this.fechaAtencion = fechaConsulta;
	}
	public void setHoraAtencion(String horaAtencion) {
		this.horaAtencion = horaAtencion;
	}
	public void setTotalPagar(double totalPagar) {
		this.totalPagar = totalPagar;
	}
	public void setEstado(int estado) {
		this.estado=estado;
	}
	public int getCodigoAtencion() {
		return codigoAtencion;
	}
	public int getCodigoPaciente() {
		return codigoPaciente;
	}
	public String getFechaAtencion() {
		return fechaAtencion;
	}
	public String getHoraAtencion() {
		return horaAtencion;
	}
	public double getTotalPagar() {
		return totalPagar;
	}
	public int getEstado() {
		return estado;
	}
	
}