package utilidades;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Esta clase se encarga de aportar métodos para la introducción de datos por teclado.
 * 
 * @author Nekotaga
 * @version 1.0
 * @since 1.0
 */
public class Teclado {
	
	//Enum
	public enum leerEntre{
		AMBOS_EXCLUIDOS,MAYOR_EXCLUIDO,MENOR_EXCLUIDO,AMBOS_INCLUIDOS
	}
	
	//Variables
	/**
	 * Esta es la variable que se encargará de la interacción con el usuario.
	 */
	private static Scanner teclado = new Scanner (System.in);
	
	//Métodos
	/**
	 * Cierra el teclado para que no se pueda escribir más.
	 */
	public static void cerrarTeclado() {
		teclado.close();
	}
	/**
	 * Limpia el buffer u obliga a pulsar la tecla Enter para continuar.
	 */
	public static void limpiarTeclado() {
		teclado.nextLine();
	}
	/**
	 * Devuelve una cadena que debe introducir el usuario por teclado.
	 * @return La cadena.
	 */
	public static String leerString() {
		String cadena;
		cadena=teclado.nextLine();		
		return cadena;	
	}
	/**
	 * Devuelve true o false según si se elige la primera o la segunda respuesta a partir de la petición dada al usuario.
	 * @param mensaje1	La petición al usuario.
	 * @param mensaje2	La respuesta 1 (true).
	 * @param mensaje3	La respuesta 2 (false).
	 * @return	true si el usuario ha elegido la primera respuesta o false si ha elegido la segunda.
	 */
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
	/**
	 * Devuelve un número introducido por el usuario por teclado entre 2 números límite.
	 * @param numMenor		El límite inferior.
	 * @param numMayor		El límite superior.
	 * @param valor			Si se incluyen o no los límites.
	 * @param mensajeError	El mensaje de error que se dará al usuario en caso de que introducizca un número fuera de los límites.
	 * @return	El número.
	 */
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