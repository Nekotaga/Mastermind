package mastermind;
/**
 * Esta clase almacena los tableros y combinaciones que van a aparecer en la partida, además de ser la clase padre de Jugador y Maquina.
 * 
 * @author Nekotaga
 * @version 1.2
 * @since 1.0
 * @see Jugador
 * @see Maquina
 * @see Combinacion
 * @see Tablero
 * @see Partida
 */
public abstract class Usuario {
	
	//Variables
	/**
	 * Indica si el usuario tiene o no una {@link #combinacionOriginal}.
	 * @see Combinacion
	 */
	protected boolean tiene;
	/**
	 * Almacena el tablero del usuario.
	 * Puede devolverse mediante {@link #getTablero()}.
	 * @see Tablero
	 */
	protected Tablero tablero;
	/**
	 * Almacena la combinacion a adivinar por el usuario.
	 * Puede devolverse mediante {@link #combinacionOriginal}.
	 * @see Combinacion
	 */
	protected Combinacion combinacionOriginal;
	/**
	 * Almacena la combinacion propuesta por el usuario.
	 * Puede devolverse mediante {@link #getCombinacionPropuesta()}.
	 * Puede modificarse mediante {@link #setCombinacionPropuesta(Combinacion)}.
	 * @see Combinacion
	 */
	protected Combinacion combinacionPropuesta;
	/**
	 * Almacena la dificultad de la partida.
	 * @see Dificultad
	 * @see Partida
	 */
	protected Dificultad dificultad;
	
	//Constructor
	/**
	 * Construye un objeto Usuario que puede ser un Jugador o una Maquina.
	 * @param tiene Indica si el usuario tiene o no una {@link #combinacionOriginal}.
	 * @param dificultad La dificultad de la partida.
	 * @see Jugador
	 * @see Maquina
	 * @see Partida
	 */
	Usuario(boolean tiene,Dificultad dificultad){
		this.tiene=tiene;
		this.dificultad=dificultad;
		tablero = new Tablero(dificultad);
	}
	
	//Getters
	/**
	 * Devuelve el valor de la variable {@link #tablero}.
	 * @return El tablero.
	 * @see Tablero
	 */
	protected Tablero getTablero() {
		return tablero;
	}
	/**
	 * Devuelve el valor de la variable {@link #combinacionOriginal}.
	 * @return La combinacion a adivinar.
	 * @see Combinacion
	 */
	protected Combinacion getCombinacionOriginal() {
		return combinacionOriginal;
	}
	/**
	 * Devuelve el valor de la variable {@link #getCombinacionPropuesta()}.
	 * @return La combinacion propuesta.
	 * @see Combinacion
	 */
	protected Combinacion getCombinacionPropuesta() {
		return combinacionPropuesta;
	}
	/**
	 * Modifica el valor de la variable {@link #getCombinacionPropuesta()}.
	 * @param combinacionPropuesta La combinacion propuesta.
	 * @see Combinacion
	 */
	protected void setCombinacionPropuesta(Combinacion combinacionPropuesta) {
		this.combinacionPropuesta=combinacionPropuesta;
	}
	
	//Métodos
	/**
	 * Coloca una combinación.
	 * Heredado por Jugador y Maquina.
	 * @return La combinacion colocada.
	 * @see Jugador
	 * @see Maquina
	 * @see Combinacion
	 */
	protected abstract Combinacion colocarCombinacion();
	/**
	 * Comprueba una combinación.
	 * Heredado por Jugador y Maquina.
	 * @param ronda La ronda de la que comprobar la combinacion.
	 * @see Jugador
	 * @see Maquina
	 * @see Combinacion
	 * @see Ronda
	 */
	protected abstract void comprobarCombinacion(Ronda ronda);
}
