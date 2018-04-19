package utilidades;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Teclado {
	
	public enum leerEntre{
		AMBOS_EXCLUIDOS,MAYOR_EXCLUIDO,MENOR_EXCLUIDO,AMBOS_INCLUIDOS
	}
	
	private static Scanner teclado = new Scanner (System.in);
	
	public static void cerrarTeclado() {
		teclado.close();
	}
	public static void limpiarTeclado() {
		teclado.nextLine();
	}
	public static String leerString() {
		String cadena;
		cadena=teclado.nextLine();		
		return cadena;	
	}
	public static boolean elegirEntre(String mensaje1,String mensaje2,String mensaje3) {
		byte b=0;
		boolean booleano,valorAdecuado;
		
		System.out.printf("%s\n 1.- %s\n 2.- %s\n",mensaje1,mensaje2,mensaje3);
		do {
			valorAdecuado=true;
			try {
				b=teclado.nextByte();
			}catch (InputMismatchException e) {
				valorAdecuado=false;
			}finally {
				limpiarTeclado();
				if (b!=1&&b!=2||!valorAdecuado) {
					valorAdecuado=false;
					System.out.println("Por Favor, escriba 1 ó 2");
				}
			}
		}while (!valorAdecuado);
		if (b==1)
			booleano=true;
		else
			booleano=false;
		
		return booleano;
	}
	
	//Número entre 2 valores
	public static byte leerEntre(byte numMenor,byte numMayor,leerEntre valor,String mensajeError) {
		if (numMayor<=numMenor||valor==leerEntre.AMBOS_EXCLUIDOS&&numMayor==numMenor+1)
			throw new IllegalArgumentException("ERROR: El número mayor es menor o igual al menor");
		
		byte b=0;
		boolean valorAdecuado;
			
		do {
			valorAdecuado=true;
			try {
				b=teclado.nextByte();
			}catch (InputMismatchException e) {
				valorAdecuado=false;
			}finally {
				limpiarTeclado();
				switch (valor) {
					case AMBOS_EXCLUIDOS:
						if (b<=numMenor||b>=numMayor||!valorAdecuado) {
							valorAdecuado=false;
							System.out.print(mensajeError);
						}
						break;
					case MAYOR_EXCLUIDO:
						if (b<numMenor||b>=numMayor||!valorAdecuado) {
							valorAdecuado=false;
							System.out.print(mensajeError);
						}
						break;
					case MENOR_EXCLUIDO:
						if (b<=numMenor||b>numMayor||!valorAdecuado) {
							valorAdecuado=false;
							System.out.print(mensajeError);
						}
						break;
					case AMBOS_INCLUIDOS:
						if (b<numMenor||b>numMayor||!valorAdecuado) {
							valorAdecuado=false;
							System.out.print(mensajeError);
						}
						break;
				}
			}
		}while (!valorAdecuado);
		
		return b;
	}
	
}
//*/