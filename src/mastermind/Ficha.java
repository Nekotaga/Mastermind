package mastermind;

/**
 * Esta clase almacena un color que formará parte de una combinación.
 * @author Nekotaga
 * @version 1.0
 * @since 1.0
 * @see Color
 * @see Combinacion
 */
public class Ficha {

	//Variables
	/**
	 * Almacena el color de la ficha.
	 * Puede devolverse mediante {@link #getColorFicha()}.
	 * @see Color
	 */
	private Color colorFicha;
	
	//Constructor
	/**
	 * Construye un nuevo objeto Ficha formado por un Color.
	 * @param colorFicha	El color de la ficha.
	 * @see Color
	 */
	Ficha(Color colorFicha){
		this.colorFicha=colorFicha;
	}
	
	//Getters
	/**
	 * Devuelve el valor de la variable {@link #colorFicha}.
	 * @return El color de la ficha.
	 */
	protected Color getColorFicha() {
		return colorFicha;
	}

	//Métodos Heredados
	/**
	 * Compara dos objetos Ficha.
	 * @param obj El objeto a comparar (es necesario hacer casting a Combinacion).
	 * @return true si son iguales o false si no lo son.
	 */
	public boolean equals(Object obj) {
		boolean resultado=false;
		if (obj instanceof Ficha&&colorFicha.equals(((Ficha) obj).colorFicha)){
			resultado=true;
		}
		return resultado;
	}
}
