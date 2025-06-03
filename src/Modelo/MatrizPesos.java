package Modelo;

public class MatrizPesos {
	
	private double[][] pesos;

	public MatrizPesos(int entrada,int salida) {
		pesos=new double[entrada][salida];
		this.generarPesos();
	}
	
	public MatrizPesos(int entrada,int salida,double[] pesosNuevos) {
		int cont=0;
		pesos=new double[entrada][salida];
		for(int i=0;i<pesos.length;i++) {
			for(int j=0;j<pesos[i].length;j++) {
				pesos[i][j]=pesosNuevos[cont];
				//System.out.println(cont+"-"+pesosNuevos[cont]);
				cont++;
			}
		}
	}
	
	public void generarPesos() {
		for(int i=0;i<pesos.length;i++) {
			for(int j=0;j<pesos[i].length;j++) {
				double peso=Math.random()*10;
				double pesoSigno;
				if(Math.random()>0.5){pesoSigno=1;
				}else{pesoSigno=-1;}	
				pesos[i][j]=peso*pesoSigno;
			}
		}
	}
	
	public double getPeso(int i,int j) {
		return pesos[i][j];
	}

	public double[][] getPesos() {
		return pesos;
	}

	public void setPesos(double[][] pesos) {
		this.pesos = pesos;
	}
	

}
