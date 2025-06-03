package Modelo;

public class Conexion {
	
	private Neurona entrada,salida;
	private double peso;
	
	public void propagar() {
		entrada.propagar();
		salida.getEntradas().add(entrada.getSalida()*peso);
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public Neurona getEntrada() {
		return entrada;
	}

	public void setEntrada(Neurona entrada) {
		this.entrada = entrada;
	}

	public Neurona getSalida() {
		return salida;
	}

	public void setSalida(Neurona salida) {
		this.salida = salida;
	}
	
	

}
