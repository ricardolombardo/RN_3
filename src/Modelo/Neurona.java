package Modelo;

import java.util.ArrayList;

public class Neurona {
	
	private double e,p,salida;
	private ArrayList<Double> entradas=new ArrayList<>();
	
	public Neurona(){
		e=Math.E;
		p=1;
	}

	
	public ArrayList<Double> getEntradas() {
		return entradas;
	}


	public void setEntradas(ArrayList<Double> entradas) {
		this.entradas = entradas;
	}


	public double getE() {
		return e;
	}


	public void setE(double e) {
		this.e = e;
	}


	public double getSalida() {
		return salida;
	}


	public void setSalida(double salida) {
		this.salida = salida;
	}


	public double funcion(double a){
		return(1/(1+Math.pow(e, -a/p)));
	}
	
	public double sumarEntradas(){
		double total=0;
		for(double valor:entradas){
			total=total+valor;
		}
		return total;
	}
	
	public void borrarEntradas() {
		this.entradas.clear();
	}
	
	public void propagar(){
		salida= this.funcion(this.sumarEntradas());
	}

}
