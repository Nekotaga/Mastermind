package mastermind;

/**
 * Esta clase almacena los mensajes a mostrar en la Partida al ganar, perder o empatar un juego.
 * 
 * @author Nekotaga
 * @version 1.0
 * @since 1.0
 * @see Partida
 */
public enum Resultado {
	
	//Enum
	VICTORIA(Color.LIMA.getColor()+"¡Felicidades, ha ganado la partida!"+Constantes.RESET),
	EMPATE(Color.AMARILLO.getColor()+"La partida ha terminado en empate"+Constantes.RESET),
	DERROTA(Color.MORADO.getColor()+"Oh... la próxima vez será..."+Constantes.RESET),
	VICTORIA_MAQ(Color.LIMA.getColor()+"Ha ganado la máquina ");	//Añadir num de máquina y reset
	
	//Variables
	/**
	 * Almacena una cadena con el mensaje a mostrar al ganar, perder o empatar.
	 * Puede devolverse mediante {@link #getMensaje()}.
	 */
	private String mensaje;
	
	//Constructor
	/**
	 * Construye un nuevo objeto Resultado con el mensaje a mostrar al ganar perder o empatar.
	 * @param mensaje	El mensaje con la información del resultado de la partida.
	 * @see Partida
	 */
	Resultado(String mensaje){
		this.mensaje=mensaje;
	}
	
	//Getters
	/**
	 * Devuelve el valor de la variable {@link #mensaje}.
	 * @return	El mensaje a devolver.
	 */
	protected String getMensaje() {
		return mensaje;
	}
	
}
