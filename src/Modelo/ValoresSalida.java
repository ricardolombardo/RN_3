package Modelo;

import java.util.ArrayList;

public class ValoresSalida {
	
	private ArrayList<Double> valores;
	
	public ValoresSalida() {
		valores=new ArrayList<Double>();
	}
	
	public double getPromedio() {
		double suma=0;
		for(double v:valores) {
			suma=suma+v;
		}
		return suma/valores.size();
	}
	
	public ArrayList<Integer> getSalidasBinarizadas() {
		ArrayList<Integer> salidasBinarizadas=new ArrayList<Integer>();
		double promedio=this.getPromedio();
		for(double v:valores) {
			if(v>promedio) {
				salidasBinarizadas.add(1);
			}else {
				salidasBinarizadas.add(0);
			}
		}
		return salidasBinarizadas;
	}

	public ArrayList<Double> getValores() {
		return valores;
	}

	public void setValores(ArrayList<Double> valores) {
		this.valores = valores;
	}

}
