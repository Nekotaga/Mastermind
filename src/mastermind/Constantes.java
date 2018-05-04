package mastermind;

/**
 * Esta clase almacena variables finales para otras clases como Tablero, Partida o ComfigurarPartida. No tiene cometido por sí misma.
 * 
 * @author Nekotaga
 * @version 1.0
 * @since 1.0
 * @see Tablero
 * @see Partida
 * @see ConfigurarPartida
 */
public final class Constantes {
	/**
	 * La altura de los colores a mostrar por el Tablero.
	 */
	static final byte ALTURA=3;
	/**
	 * La cadena que indica el valor nulo en las combinaciones de fichas blancas y negras del Tablero.
	 */
	static final String VACIO="🞅";
	/**
	 * La cadena que indica el valor no nulo en las combinaciones de fichas blancas y negras del Tablero.
	 */
	static final String CIRCULO="⬤";
	/**
	 * El reseteo de colores en formato ANSI para poner el color por defecto en los textos y fondos de Tablero, Partida y ConfigurarPartida.
	 */
	static final String RESET="\u001B[0m";
	
}
