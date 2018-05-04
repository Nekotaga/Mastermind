package mastermind;

import static utilidades.Teclado.*;

/**
 * Esta clase almacena un jugador que es hijo de Usuario.
 * 
 * @author Nekotaga
 * @version 1.2
 * @since 1.0
 * @see Usuario
 * @see Maquina
 * @see Ronda
 * @see Combinacion
 */
public class Jugador extends Usuario{

	//Constructor
	/**
	 * Construye un nuevo objeto Jugador hijo de Usuario.
	 * @param tiene 		Indica si el jugador va a colocar o no una combinacion original.
	 * @param dificultad	La dificultad de la partida.
	 * @see Usuario
	 * @see Dificultad
	 */
	Jugador(boolean tiene,Dificultad dificultad){
		super(tiene,dificultad);
		if (tiene)
			combinacionOriginal=colocarCombinacion();
	}
	
	//Métodos Heredados
	/**
	 * Coloca una combinación bien para que la adivine el usuario contrario o para intentar adivinar la suya.
	 * @see Combinacion
	 * @see Usuario
	 */
	protected Combinacion colocarCombinacion() {
		String split;
		boolean error;
		boolean esValida;
		String combinacionSplit[];
		Combinacion combinacion = new Combinacion(new Ficha[dificultad.getNumCasillas()]);
		
		System.out.print("Introduzca una combinación con abreviaturas de los colores disponibles separada por espacios\nColores disponibles: ");	//---- Se pide la introducción de una
		for (byte i=0;i<dificultad.getColores().length;i++)																								// cadena de colores al usuario.
			if (i==dificultad.getColores().length-1)																									// El bucle sirve para mostrar
				System.out.printf("%s%s%s\n",dificultad.getColores()[i].getColor(),dificultad.getColores()[i],Constantes.RESET);						// los colores posibles. Si el
			else																																		// color mostrado no es el último,
				System.out.printf("%s%s%s | ",dificultad.getColores()[i].getColor(),dificultad.getColores()[i],Constantes.RESET);						// se muestra una barra.
		do {
			error=false;
			try {
				split=leerString().toLowerCase();		// El usuario introduce los datos
				combinacionSplit=split.split(" ");		// Se separan los colores por espacios
				if (combinacionSplit.length<dificultad.getNumCasillas()||combinacionSplit.length>dificultad.getNumCasillas())																											//---- Se comprueba si se han introducido las abreviaturas
					throw new Exception(String.format("%sNo ha introducido las abreviaturas necesarias\nDebe introducir %d abreviaturas separadas por espacios%s",Color.ROJO.getColor(),dificultad.getNumCasillas(),Constantes.RESET));		// necesarias. En caso negativo se lanza la excepción.
				for (byte i=0;i<dificultad.getNumCasillas();i++) {										//---- Se comprueba si las
					esValida=false;																			// abreviaturas introducidas
					for (byte j=0;j<dificultad.getColores().length&&!esValida;j++) {						// corresponden con colores
						if (combinacionSplit[i].equals(dificultad.getColores()[j].getAbreviatura())) {		// existentes que a su vez
							combinacion.getCombinacion()[i]=new Ficha(dificultad.getColores()[j]);			// estén disponibles.
							esValida=true;																	//
						}																					//
					}																						// ---------------------------------------------------------------------------------------------------------------------------------------------¬¬
					if (!esValida)																																																							// Si no corresponden
						throw new Exception(String.format("%sNo ha escrito correctamente las %s abreviaturas\nPuede encontrarlas entre paréntesis junto a los colores disponibles%s",Color.ROJO.getColor(),dificultad.getNumCasillas(),Constantes.RESET));	// se lanza la excepción.
				}
				if (!dificultad.isRepeticionColores()&&tiene&&combinacionOriginal==null)	//---- Se comprueba en el caso de que la dificultad no permita repetición y la
					for (byte i=0;i<combinacionSplit.length;i++)								// combinación introducida va a ser la original si hay abreviaturas repetidas.
						for (byte j=0;j<combinacionSplit.length;j++)							// -----------------------------------------------------------------------------------------------------------------¬¬
							if (i!=j&&combinacionSplit[i].equals(combinacionSplit[j]))																																// Si están repetidas
								throw new Exception(String.format("%sHa introducido abreviaturas repetidas\nNo puede introducir colores repetidos en esta dificultad%s",Color.ROJO.getColor(),Constantes.RESET));	// se lanza la excepción.
			}catch(Exception e){
				error=true;
				System.out.println(e.getMessage());
			}
		}while(error);
		System.out.println();
		return combinacion;
	}
	/**
	 * Comprueba que la combinacion del usuario contrario sea correcta.
	 * @param ronda		La ronda de la que se quiere comprobar la combinacion.
	 * @see Ronda
	 * @see Usuario
	 */
	protected void comprobarCombinacion(Ronda ronda) {
		boolean error;
		byte numero=0;
		byte contador;
		byte contadorNegras=0;
		byte contadorBlancas=0;
		String mensajeError=String.format("%sDebe introducir un valor entre 0 y %d: %s",Color.ROJO.getColor(),dificultad.getNumCasillas(),Constantes.RESET);
		
		if (!ronda.getCombinacionPropuesta().equals(combinacionOriginal)) {	// Solo lo comprobamos si el resultado no es ccorrecto, porque si lo es, se muestra y ya esta
			for (byte i=0;i<ronda.getResultadoByN().getCombinacion().length&&ronda.getResultadoByN().getCombinacion()[i]!=null;i++) {									//---- Se asigna el número de fichas
				if (Color.NEGRO==ronda.getResultadoByN().getCombinacion()[i].getColorFicha()&&ronda.getResultadoByN().getCombinacion()[i].getColorFicha()!=null)			// negras y blancas de la comprobación
					contadorNegras++;																																		// realizada en la construcción de
				else if (Color.BLANCO==ronda.getResultadoByN().getCombinacion()[i].getColorFicha()&&ronda.getResultadoByN().getCombinacion()[i].getColorFicha()!=null)		// la ronda para que el usuario
					contadorBlancas++;																																		// no pueda engañar a la máquina.
			}
			for (byte i=0;i<2;i++) {
				if (i==0) {
					System.out.print("Introduzca la cantidad de fichas del mismo color que su combinación que estén situadas en la misma posición (fichas negras): ");	//---- Se pide la introducción de casillas negras
					contador=contadorNegras;																																// y se marca el contador como el contador de negras
				}else {
					System.out.print("Introduzca la cantidad de fichas del mismo color que su combinación que no estén situadas en la misma posición (fichas blancas): ");	//---- Se pide la introducción de casillas blancas
					contador=contadorBlancas;																																// y se marca el contador como el contador de blancas
				}
				do {																								//---- Se pide la introducción de un número de fichas 
					error=false;																						// del color correspondiente y se comprueba
					try {																								// si es o no igual al de la comprobación.
						numero=leerEntre((byte)0,dificultad.getNumCasillas(),leerEntre.AMBOS_INCLUIDOS,mensajeError);	//
						if (numero!=contador)																			// ---------------------------------------------------------------------------------¬¬
							throw new Exception(String.format("%sSe ha equivocado en la introducción, introduzca correctamente la cantidad de fichas: %s",Color.ROJO.getColor(),Constantes.RESET));			// En caso contrario
					}catch(Exception e){																																									// se lanza una excepción
						error=true;																																											// y se repite la petición
						System.out.print(e.getMessage());																																					// de fichas del color
					}																																														// correspondiente.
				}while(error);																																												//
			}
			System.out.println();
		}
	}
	
}
