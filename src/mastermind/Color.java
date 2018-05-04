package mastermind;

/**
 * Este enum almacena los colores posibles para las fichas y las dificultades al igual que los datos de cada uno de ellos.
 * Tambien se usa para dar color a los textos que salen por consola de los objetos Partida y ConfigurarPartida.
 * 
 * @author Nekotaga
 * @version 1.0
 * @since 1.0
 * @see Ficha
 * @see Dificultad
 * @see Partida
 * @see ConfigurarPartida
 */
public enum Color {
	
	//Enum
	AMARILLO("\u001B[33;1m","\u001B[103m"),
	AZUL("\u001B[34m","\u001B[44m"),
	BURDEOS("\u001B[31m","\u001B[41m"),
	CELESTE("\u001B[36;1m","\u001B[106m"),
	GRIS("\u001B[30;1m","\u001B[100m"),
	LIMA("\u001B[32;1m","\u001B[102m"),
	MORADO("\u001B[35;1m","\u001B[105m"),
	ROJO("\u001B[31;1m","\u001B[101m"),
	TEAL("\u001B[36m","\u001B[46m"),
	VERDE("\u001B[32m","\u001B[42m"),
	BLANCO("\u001B[37;1m","\u001B[107m"),
	NEGRO("\u001B[30m","\u001B[40m");
	
	//Variables
	/**
	 * Almacena una cadena con el valor ANSI del color del texto.
	 * Puede devolverse mediante {@link #getColor()}.
	 */
	private String color;
	/**
	 * Almacena una cadena con el valor ANSI del color del fondo.
	 * Puede devolverse mediante {@link #getColorFondo()}.
	 */
	private String colorFondo;
	/**
	 * Almacena una cadena con las dos primeras letras en minúscula del color correspondiente a partir del {@link #toString()} de object.
	 * Puede devolverse mediante {@link #getAbreviatura()}.
	 */
	private String abreviatura;

	//Constructor
	/**
	 * Construye un nuevo objeto Color que contiene el color en sus dos formatos (texto y fondo) y su abreviatura.
	 * @param color			El color ANSI en formato texto.
	 * @param colorFondo	El color ANSI en formato fondo.
	 */
	Color(String color,String colorFondo){
		this.color=color;
		this.colorFondo=colorFondo;
		abreviatura=super.toString().substring(0,2).toLowerCase();
	}
	
	//Getters
	/**
	 * Devuelve el valor de la variable {@link #color}.
	 * @return	El color ANSI en formato texto.
	 */
	protected String getColor() {
		return color;
	}
	/**
	 * Devuelve el valor de la variable {@link #colorFondo}.
	 * @return	El color ANSI en formato fondo.
	 */
	protected String getColorFondo() {
		return colorFondo;
	}
	/**
	 * Devuelve el valor de la variable {@link #abreviatura}.
	 * @return	La abreviatura del color.
	 */
	protected String getAbreviatura(){
		return abreviatura;
	}
	
	//Métodos Heredados
	/**
	 * Devuelve la descripcion del color.
	 * @return	El nombre del color ANSI con la primera letra en mayúscula y las demás en minúscula, seguido de su abreviatura entre paréntesis.
	 */
	public String toString(){
		return String.format("%s (%s)",super.toString().charAt(0)+super.toString().substring(1).toLowerCase(),abreviatura);
	}
}
