package Modelo;

import java.util.ArrayList;

public class RedNeuronal {
	
	private double[][] dataSetEntrada;
	private double[][] dataSetSalida;
	private Capa entrada,oculta,salida;
	private Conexion[][] conexionesEntradaOculta,conexionesOcultaSalida;
	private ValoresSalida[] valores;
	private int exitos=0;
	private MatrizPesos m1,m2;
	private boolean salidaBinaria;
	private double[] promediosSalida;

	public RedNeuronal(int cantEntrada,int cantOculta,int cantSalida, boolean tipoSalida) {
		entrada=new Capa(cantEntrada);
		oculta=new Capa(cantOculta);
		salida=new Capa(cantSalida);
		this.inicializarValoresSalida();
		this.generarConexiones();
		this.salidaBinaria=tipoSalida;
		this.promediosSalida=new double[cantSalida];
	}
	
	public RedNeuronal(int cantEntrada,int cantOculta,int cantSalida, boolean tipoSalida,double[] pesosM1,double[] pesosM2,double[] promediosSalida) {
		entrada=new Capa(cantEntrada);
		oculta=new Capa(cantOculta);
		salida=new Capa(cantSalida);
		this.inicializarValoresSalida();
		this.generarConexionesConPesos(pesosM1,pesosM2);
		this.actualizarM1M2();
		this.salidaBinaria=tipoSalida;
		this.promediosSalida=promediosSalida;
	}
	
	public void inicializarValoresSalida() {
		valores=new ValoresSalida[salida.getNeuronas().size()];
		for(int i=0;i<valores.length;i++) {
			valores[i]=new ValoresSalida();
		}
	}
	
	public void generarConexiones() {
		//Creo la matriz de pesos
		MatrizPesos pesosEntradaOculta=new MatrizPesos(entrada.getNeuronas().size(),oculta.getNeuronas().size());
		
		//Creo las conexiones
		conexionesEntradaOculta= new Conexion[entrada.getNeuronas().size()][oculta.getNeuronas().size()];
		for(int i=0;i<conexionesEntradaOculta.length;i++) {
			for(int j=0;j<conexionesEntradaOculta[i].length;j++) {
				conexionesEntradaOculta[i][j]=new Conexion();
				conexionesEntradaOculta[i][j].setEntrada(entrada.getNeuronas().get(i));
				conexionesEntradaOculta[i][j].setSalida(oculta.getNeuronas().get(j));
				conexionesEntradaOculta[i][j].setPeso(pesosEntradaOculta.getPeso(i, j));
			}
		}
		
		//Creo la matriz de pesos
		MatrizPesos pesosOcultaSalida=new MatrizPesos(oculta.getNeuronas().size(),salida.getNeuronas().size());
				
		//Creo las conexiones
		conexionesOcultaSalida= new Conexion[oculta.getNeuronas().size()][salida.getNeuronas().size()];
		for(int i=0;i<conexionesOcultaSalida.length;i++) {
			for(int j=0;j<conexionesOcultaSalida[i].length;j++) {
				conexionesOcultaSalida[i][j]=new Conexion();
				conexionesOcultaSalida[i][j].setEntrada(oculta.getNeuronas().get(i));
				conexionesOcultaSalida[i][j].setSalida(salida.getNeuronas().get(j));
				conexionesOcultaSalida[i][j].setPeso(pesosOcultaSalida.getPeso(i, j));
			}
		}
	}
	
	public void generarConexionesConPesos(double[] pesosM1,double[] pesosM2) {
		MatrizPesos pesosEntradaOculta=new MatrizPesos(entrada.getNeuronas().size(),oculta.getNeuronas().size(),pesosM1);
		
		conexionesEntradaOculta= new Conexion[entrada.getNeuronas().size()][oculta.getNeuronas().size()];
		for(int i=0;i<conexionesEntradaOculta.length;i++) {
			for(int j=0;j<conexionesEntradaOculta[i].length;j++) {
				conexionesEntradaOculta[i][j]=new Conexion();
				conexionesEntradaOculta[i][j].setEntrada(entrada.getNeuronas().get(i));
				conexionesEntradaOculta[i][j].setSalida(oculta.getNeuronas().get(j));
				conexionesEntradaOculta[i][j].setPeso(pesosEntradaOculta.getPeso(i, j));
				
			}
		}
		
		MatrizPesos pesosOcultaSalida=new MatrizPesos(oculta.getNeuronas().size(),salida.getNeuronas().size(),pesosM2);
				
		conexionesOcultaSalida= new Conexion[oculta.getNeuronas().size()][salida.getNeuronas().size()];
		for(int i=0;i<conexionesOcultaSalida.length;i++) {
			for(int j=0;j<conexionesOcultaSalida[i].length;j++) {
				conexionesOcultaSalida[i][j]=new Conexion();
				conexionesOcultaSalida[i][j].setEntrada(oculta.getNeuronas().get(i));
				conexionesOcultaSalida[i][j].setSalida(salida.getNeuronas().get(j));
				conexionesOcultaSalida[i][j].setPeso(pesosOcultaSalida.getPeso(i, j));
			}
		}
	}
	
	
	public void entrenar() {
		int iteraciones = 10000;
		for(int n=0;n<iteraciones;n++) {
			for(int i=0;i<dataSetEntrada.length;i++) {
				borrarEntradas();
				this.setDatosEntrada(dataSetEntrada[i]);
				propagar();
			}
			if(this.salidaBinaria) {
				compararSalidaBinaria();
			}else {
				//DESARROLLAR LA FUNCION PARA UNA SALIDA NO BINARIA
				System.out.println("Aun no se encuentra soportada");
			}
			this.generarConexiones();
			this.inicializarValoresSalida();
		}
		System.out.println("ID de la mejor "+m1.getPeso(0, 0)+"*"+m2.getPeso(0, 0));
	}
	
	public void borrarEntradas() {
		entrada.borrarEntradas();
		oculta.borrarEntradas();
		salida.borrarEntradas();
	}
	
	public void setDatosEntrada(double[] datos) {
		for(int i=0;i<datos.length;i++) {
			entrada.getNeuronas().get(i).getEntradas().add(datos[i]);
		}
	}
	
	public void propagar() {
		//Propagacion capa entrada - capa oculta
		for(int i=0;i<conexionesEntradaOculta.length;i++) {
			for(int j=0;j<conexionesEntradaOculta[i].length;j++) {
				conexionesEntradaOculta[i][j].propagar();
			}	
		}
		//propagacion capa oculta - capa salida
		for(int i=0;i<conexionesOcultaSalida.length;i++) {
			for(int j=0;j<conexionesOcultaSalida[i].length;j++) {
				conexionesOcultaSalida[i][j].propagar();
			}	
		}
		
		//propagacion capa salida
		int nroNeurona=0;
		for(Neurona n:salida.getNeuronas()) {
			n.propagar();
			//Guarda todos los valores obtenidos para luego poder compararlos
			valores[nroNeurona].getValores().add(n.getSalida());
			nroNeurona++;
		}
		
	}
	
	public int compararSalidaBinaria() {
		int correctos=0;
		for(int i=0;i<valores.length;i++) {
			ArrayList<Integer> salidaBinarizada=valores[i].getSalidasBinarizadas();
			for(int j=0;j<salidaBinarizada.size();j++) {
				if(salidaBinarizada.get(j)==dataSetSalida[i][j]) {
					correctos++;
				}
			}
		}
		if(exitos<correctos) {
			exitos=correctos;
			this.actualizarM1M2();		
			for(int i=0;i<valores.length;i++) {
				promediosSalida[i]=valores[i].getPromedio();
			}
			System.out.println("Nuevos exitos "+correctos+" ID: "+m1.getPesos()[0][0]+"*"+m2.getPesos()[0][0]);
		}
		return correctos;
	}
	
	public void actualizarM1M2() {
		m1=new MatrizPesos(entrada.getNeuronas().size(),oculta.getNeuronas().size());
		m2=new MatrizPesos(oculta.getNeuronas().size(),salida.getNeuronas().size());
		for(int f=0;f<conexionesEntradaOculta.length;f++) {
			for(int c=0;c<conexionesEntradaOculta[f].length;c++) {
				m1.getPesos()[f][c]=conexionesEntradaOculta[f][c].getPeso();
			}
		}

		for(int f=0;f<conexionesOcultaSalida.length;f++) {
			for(int c=0;c<conexionesOcultaSalida[f].length;c++) {
				m2.getPesos()[f][c]=conexionesOcultaSalida[f][c].getPeso();
			}
		}
	}

	public double[][] getDataSetEntrada() {
		return dataSetEntrada;
	}

	public void setDataSetEntrada(double[][] dataSetEntrada) {
		this.dataSetEntrada = dataSetEntrada;
	}

	public double[][] getDataSetSalida() {
		return dataSetSalida;
	}

	public void setDataSetSalida(double[][] dataSetSalida) {
		this.dataSetSalida = dataSetSalida;
	}

	public ValoresSalida[] getValores() {
		return valores;
	}

	public void setValores(ValoresSalida[] valores) {
		this.valores = valores;
	}
	
	
	public double reconocer() {
		//Linea que le agregue por la experiencia del reconocimiento de imagenes
		this.inicializarValoresSalida();
	
		for(int i=0;i<conexionesEntradaOculta.length;i++) {
			for(int j=0;j<conexionesEntradaOculta[i].length;j++) {
				conexionesEntradaOculta[i][j].setPeso(m1.getPeso(i, j));
			}
		}
		
		for(int i=0;i<conexionesOcultaSalida.length;i++) {
			for(int j=0;j<conexionesOcultaSalida[i].length;j++) {
				conexionesOcultaSalida[i][j].setPeso(m2.getPeso(i, j));
			}
		}
		
		for(int i=0;i<dataSetEntrada.length;i++) {
			borrarEntradas();
			this.setDatosEntrada(dataSetEntrada[i]);
			propagar();
		}
		
		int correctos=0;
		for(int i=0;i<valores.length;i++) {
			ArrayList<Integer> salidaBinarizada=valores[i].getSalidasBinarizadas();
			for(int j=0;j<salidaBinarizada.size();j++) {
				if(salidaBinarizada.get(j)==dataSetSalida[i][j]) {
					correctos++;
				}
			}
		}
		
		System.out.println("Exitos: "+correctos+" "+m1.getPeso(0, 0)+"*"+m2.getPeso(0, 0));
		System.out.println("Total de salidas: "+dataSetSalida.length*dataSetSalida[0].length);
		double valor=correctos;
		return valor/(dataSetSalida.length*dataSetSalida[0].length);
		
	}
	
	public int[] procesarBinario(double[] datos) {
		this.inicializarValoresSalida();
		for(int i=0;i<conexionesEntradaOculta.length;i++) {
			for(int j=0;j<conexionesEntradaOculta[i].length;j++) {
				conexionesEntradaOculta[i][j].setPeso(m1.getPeso(i, j));
			}
		}
		
		for(int i=0;i<conexionesOcultaSalida.length;i++) {
			for(int j=0;j<conexionesOcultaSalida[i].length;j++) {
				conexionesOcultaSalida[i][j].setPeso(m2.getPeso(i, j));
			}
		}
		borrarEntradas();
		this.setDatosEntrada(datos);
		propagar();
		int []salida=new int[valores.length];
		
		for(int i=0;i<salida.length;i++) {
			if(valores[i].getValores().get(0)>this.promediosSalida[i]) {
				salida[i]=1;
			}else {
				salida[i]=0;
			}
		}
		return salida;
		
	}

	public MatrizPesos getM1() {
		return m1;
	}

	public void setM1(MatrizPesos m1) {
		this.m1 = m1;
	}

	public MatrizPesos getM2() {
		return m2;
	}

	public void setM2(MatrizPesos m2) {
		this.m2 = m2;
	}

	public double[] getPromediosSalida() {
		return promediosSalida;
	}

	public void setPromediosSalida(double[] promediosSalida) {
		this.promediosSalida = promediosSalida;
	}
	
}
