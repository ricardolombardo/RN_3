package controladores;

import Modelo.RedNeuronal;

public class Principal {
	
	public static void main(String[] args) {
		
		
		//Generacion de entradas
		
		/*
		double[][] entradas=new double[10][10];
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				entradas[i][j]=(j+i)%10;
				System.out.print(entradas[i][j]+" ");
			}
			System.out.println();
		}
		*/
		int neuronasDeEntrada=50;
		double[][] entradas=new double[15][neuronasDeEntrada];
		
		double[] a={0,0,0,0,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,1,1,0,1,1,0,0,0,0,1,1,0,0,1,1,0,0,0,0,0,0,0,0,1,1,0,0,0,0};
		double[] b= {0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,1,1,0,1,1,0,0,0,0,1,1,0,0,1,1,0,0,0,0,0,0,0,0,1,1,0,0,0};
		double[] c= {0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,1,1,0,1,1,0,0,0,0,1,1,0,0,1,1,0,0,0,0,0,0,0,0,1,1,0,0};
		double[] d= {0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,1,1,0,1,1,0,0,0,0,1,1,0,0,1,1,0,0,0,0,0,0,0,0,1,1,0};
		double[] e= {0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,1,1,0,1,1,0,0,0,0,1,1,0,0,1,1,0,0,0,0,0,0,0,0,1,1};
		double[] f= {0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,1,1,0,1,1,0,0,0,0,1,1,0,0,1,1,0,0,0,0,0,0,0,0,1,1};
		double[] g= {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,1,1,0,1,1};
		double[] h= {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,1,1};
		double[] i1= {0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,1,1,0,1,1,0,0,0,0,1,1,0,0,1,1,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,1,1};
		double[] j= {0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,1,1,0,1,1,0,0,0,0,1,1,0,0,1,1,0,0,0,0,0,0,0,0,1,1};
		double[] k= {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,1,1,0,1,1,0,0,0,0,1,1,0,0,1,1};
		double[] l= {0,0,1,1,1,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,1,1,0,0,0,0};
		double[] m= {0,0,0,1,1,1,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,1,1,0,0,0};
		double[] n= {0,0,0,0,1,1,1,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,1,1,0,0};
		double[] o= {0,0,0,0,0,1,1,1,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,1,1,0};
		
		entradas[0]=a;
		entradas[1]=b;
		entradas[2]=c;
		entradas[3]=d;
		entradas[4]=e;
		entradas[5]=f;
		entradas[6]=g;
		entradas[7]=h;
		entradas[8]=i1;
		entradas[9]=j;
		entradas[10]=k;
		entradas[11]=l;
		entradas[12]=m;
		entradas[13]=n;
		entradas[14]=o;
		
		//Generacion de salidas
		
		int neuronasDeSalida=3;
		
		/*
		double[][] salidas = new double[3][10];
		double[] n1= {0,1,0,1,1,1,0,1,0,0};
		double[] n2= {1,1,1,0,0,1,0,1,0,1};
		double[] n3= {0,1,0,0,1,0,0,1,1,0};
		*/
		
		
		double[][] salidas = new double[neuronasDeSalida][15];
		double[] n1= {1,1,1,1,1,1,1,1,1,1,1,0,0,0,0};
		double[] n2= {1,1,1,1,1,1,1,1,1,1,1,0,0,0,0};
		double[] n3= {1,1,1,1,1,1,1,1,1,1,1,0,0,0,0};
		
		salidas[0]=n1;
		salidas[1]=n2;
		salidas[2]=n3;
		
		RedNeuronal rn=new RedNeuronal(neuronasDeEntrada,10,neuronasDeSalida,true);
		rn.setDataSetEntrada(entradas);
		rn.setDataSetSalida(salidas);
		rn.entrenar();
		double porcentaje=rn.reconocer();
		System.out.println("El porcentaje de pertenencia es del "+porcentaje*100+" %");
		
		//double[] testEntradas= {9.0, 0.0, 1.0, 2.0,3.0,4.0,5.0,6.0,7.0,8.0};
		double[] testEntradas= {0,0,0,0,0,1,1,1,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,1,1,0};
		int[] salida=rn.procesarBinario(testEntradas);
		
		for(int i=0;i<salida.length;i++) {
				System.out.print(salida[i]+" ");
		}System.out.println();
		
		double[] testEntradas2= {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,1,1,0,1,1,0,0,0,0,1,1,0,0,1,1};
		salida=rn.procesarBinario(testEntradas2);
		
		for(int i=0;i<salida.length;i++) {
				System.out.print(salida[i]+" ");
		}	
		
	}

}
