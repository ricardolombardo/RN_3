package Modelo;

import java.util.ArrayList;

public class Capa {
	
	private ArrayList<Neurona> neuronas;
	
	public Capa(int cantidad) {
		neuronas=new ArrayList<Neurona>();
		for(int i=0;i<cantidad;i++) {
			Neurona neurona=new Neurona();
			this.neuronas.add(neurona);
		}
	}
	
	public void borrarEntradas() {
		for(Neurona n:neuronas) {
			n.borrarEntradas();
		}
	}

	public ArrayList<Neurona> getNeuronas() {
		return neuronas;
	}

	public void setNeuronas(ArrayList<Neurona> neuronas) {
		this.neuronas = neuronas;
	}

}
