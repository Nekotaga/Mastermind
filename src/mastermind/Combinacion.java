package mastermind;

import java.util.Arrays;

/**
 * Esta clase almacena una combinacion formada por un array de fichas, la cual formará parte de una ronda y/o de un usuario.
 * 
 * @author Nekotaga
 * @version 1.2
 * @since 1.0
 * @see Ficha
 * @see Ronda
 * @see Usuario
 */
public class Combinacion {

	//Variables
	/**
	 * Almacena un array de fichas.
	 * Puede devolverse mediante {@link #getCombinacion()}.
	 * @see Ficha
	 */
	private Ficha combinacion[];
	
	//Constructor
	/**
	 * Construye un nuevo objeto Combinacion que contiene un array de fichas.
	 * @param combinacion	El array de fichas.
	 * @see Ficha
	 */
	Combinacion(Ficha combinacion[]) {
		this.combinacion=combinacion;
	}
	
	//Getters
	/**
	 * Devuelve el valor de la variable {@link #combinacion}.
	 * @return	El array de fichas.
	 * @see Ficha
	 */
	protected Ficha[] getCombinacion() {
		return combinacion;
	}
	
	//Métodos Heredados
	/**
	 * Compara dos objetos Combinacion.
	 * @param obj El objeto a comparar (es necesario hacer casting a Combinacion).
	 * @return true si son iguales o false si no lo son.
	 */
	public boolean equals(Object obj) {
		boolean resultado=false;
		if (obj instanceof Combinacion&&Arrays.deepEquals(combinacion,((Combinacion) obj).combinacion)) {
			resultado=true;
		}
		return resultado;
	}
}
