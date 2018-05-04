package mastermind;

/**
 * Esta interfaz es la que implementa Tablero para mostrarse.
 * 
 * @author Nekotaga
 * @version 1.2
 * @since 1.0
 * @see Tablero
 */
public interface DibujableTablero {
	
	/**
	 * Muestra el tablero desde la perspectiva del usuario.
	 * @see Usuario
	 * @see Tablero
	 */
	public void mostrarTableroPropio();
	/**
	 * Muestra el tablero desde la perspectiva del oponente.
	 * @see Usuario
	 * @see Tablero
	 */
	public void mostrarTableroAjeno();
	/**
	 * Muestra el tablero cuando la dificultad es difícil.
	 * @param original Si se muestra o no la combinaicon original.
	 * @param jug1 Si el tablero es del primer o segundo jugador.
	 * @see Dificultad
	 * @see Tablero
	 */
	public void mostrarTableroDificil(boolean original,boolean jug1);
	/**
	 * Muestra solo la combinación propuesta del usuario de la última ronda y la combinación original.
	 * @deprecated
	 * @see Usuario
	 * @see Ronda
	 * @see Combinacion
	 * @see Tablero
	 */
	public void mostrarTableroFinal();
}
