package mastermind;

/**
 * Este enum almacena las distintas dificultades para la configuración de la partida igual que los datos de cada una de ellas.
 * 
 * @author Nekotaga
 * @version 1.0
 * @since 1.0
 * @see Color
 * @see ConfigurarPartida
 * 
 */
public enum Dificultad {
	
	//Enum
	FACIL_ADIVINAR((byte)1,(byte)4,(byte)10,false),
	FACIL_COMPROBAR((byte)1,(byte)4,(byte)10,false),
	MEDIO((byte)2,(byte)5,(byte)15,false),
	DIFICIL((byte)2,(byte)8,(byte)0,true);
	
	//Variables
	/**
	 * Almacena el número de colores de la dificultad. 
	 * Puede devolverse mediante {@link #getNumColores()}.
	 */
	private byte numColores;
	/**
	 * Almacena el número de jugadores de la dificultad. 
	 * Puede devolverse mediante {@link #getNumJugadores()}.
	 */
	private byte numJugadores;
	/**
	 * Almacena el número de casillas de la dificultad. 
	 * Puede devolverse mediante {@link #getNumCasillas()}.
	 */
	private byte numCasillas;
	/**
	 * Almacena el número de rondas máximas de la dificultad. 
	 * Puede devolverse mediante {@link #getNumIntentos()}.
	 */
	private byte numIntentos;
	/**
	 * Indica si se pueden o no repetir colores en esa dificultad. 
	 * Puede devolverse mediante {@link #isRepeticionColores()}.
	 */
	private boolean repeticionColores;
	/**
	 * Almacena un array con los colores de la dificultad. 
	 * Puede devolverse mediante {@link #getColores()}.
	 * @see Color
	 */
	private Color colores[];
	
	//Constructor
	/**
	 * Construye un nuevo objeto Dificultad que contiene los números de jugadores, casillas y rondas máximas, los colores asociados a la dificultad y si estos se pueden o no repetir.
	 * @param numJugadores		El número de jugadores.
	 * @param numCasillas		El número de casillas.
	 * @param numIntentos		El múmero de rondas máximas.
	 * @param repeticionColores Si se repiten o no los colores.
	 */
	Dificultad(byte numJugadores,byte numCasillas,byte numIntentos,boolean repeticionColores){
		this.numJugadores=numJugadores;
		this.numCasillas=numCasillas;
		this.numIntentos=numIntentos;
		this.repeticionColores=repeticionColores;
		asociarColores();
	}
	
	//Getters
	/**
	 * Devuelve el valor de la variable {@link #numJugadores}.
	 * @return	El número de jugadores.
	 */
	protected byte getNumJugadores() {
		return numJugadores;
	}
	/**
	 * Devuelve el valor de la variable {@link #numCasillas}.
	 * @return El número de casillas.
	 */
	protected byte getNumCasillas() {
		return numCasillas;
	}
	/**
	 * Devuelve el valor de la variable {@link #getNumColores()}.
	 * @return El número de colores.
	 */
	protected byte getNumColores() {
		return numColores;
	}
	/**
	 * Devuelve el valor de la variable {@link #numIntentos}.
	 * @return El número de rondas máximas.
	 */
	protected byte getNumIntentos() {
		return numIntentos;
	}
	/**
	 * Devuelve el valor de la variable {@link #repeticionColores}.
	 * @return Si se repìten o no los colores.
	 */
	protected boolean isRepeticionColores() {
		return repeticionColores;
	}
	/**
	 * Devuelve el valor de la variable {@link #colores}.
	 * @return El array de colores.
	 * @see Color
	 */
	protected Color[] getColores() {
		return colores;
	}
	
	//Métodos
	/**
	 * Asocia los colores correspondientes a la dificultad.
	 * Esta asociación ocurre en el constructor de la dificultad.
	 * @see Color
	 */
	private void asociarColores() {
		if (this.name().equals("DIFICIL")) {
			Color colores[]={Color.ROJO,Color.BURDEOS,Color.AMARILLO,Color.VERDE,Color.LIMA,Color.CELESTE,Color.TEAL,Color.AZUL,Color.MORADO,Color.GRIS};
			this.colores=colores;
		}else {
			Color colores[]={Color.ROJO,Color.BURDEOS,Color.AMARILLO,Color.VERDE,Color.LIMA,Color.CELESTE,Color.TEAL,Color.AZUL};
			this.colores=colores;
		}
		numColores=(byte)colores.length;
	}
	/**
	 * Devuelve el nombre de la dificultad.
	 * @return El nombre de la dificultad con la primera letra en mayúscula, las demás en minúscula y las tildes donde corresponden.
	 */
	protected String nombre() {
		String nombre="Ninguna";
		if (this==Dificultad.FACIL_ADIVINAR)
			nombre=String.format("%s",super.toString().charAt(0)+"á"+super.toString().substring(2,5).toLowerCase()+" (Adivinar combinación)");
		else if (this==Dificultad.FACIL_COMPROBAR)
			nombre=String.format("%s",super.toString().charAt(0)+"á"+super.toString().substring(2,5).toLowerCase()+" (Comprobar combinaciones)");
		else if (this==Dificultad.MEDIO)
			nombre=String.format("%s",super.toString().charAt(0)+super.toString().substring(1).toLowerCase());
		else if (this==Dificultad.DIFICIL)
			nombre=String.format("%s",super.toString().charAt(0)+super.toString().substring(1,3).toLowerCase()+"í"+super.toString().substring(4).toLowerCase());
		return nombre;
	}
	
	//Métodos Heredados
	/**
	 * Devuelve la descripcion de la dificultad.
	 * @return	El nombre de la dificultad junto a una descripción compuesta por los jugadores, el número de casillas, el máximo de rondas, los colores disponibles y si se puede o no repetir colores.
	 */
	public String toString() {
		String descripcion="";
		if (this==Dificultad.FACIL_ADIVINAR)
			descripcion=String.format("%s\n - Jugadores: %d (jugador)\n - Casillas: %d\n - Rondas máximas:%d\n - Colores: %d\n - Repetición de colores: %s\n",Dificultad.FACIL_ADIVINAR.nombre(),Dificultad.FACIL_ADIVINAR.getNumJugadores(),Dificultad.FACIL_ADIVINAR.getNumCasillas(),Dificultad.FACIL_ADIVINAR.getNumIntentos(),Dificultad.FACIL_ADIVINAR.getNumColores(),Dificultad.FACIL_ADIVINAR.isRepeticionColores()?"Sí":"No");
		else if (this==Dificultad.FACIL_COMPROBAR)
			descripcion=String.format("%s\n - Jugadores: %d (máquina)\n - Casillas: %d\n - Rondas máximas:%d\n - Colores: %d\n - Repetición de colores: %s\n",Dificultad.FACIL_COMPROBAR.nombre(),Dificultad.FACIL_COMPROBAR.getNumJugadores(),Dificultad.FACIL_COMPROBAR.getNumCasillas(),Dificultad.FACIL_COMPROBAR.getNumIntentos(),Dificultad.FACIL_COMPROBAR.getNumColores(),Dificultad.FACIL_COMPROBAR.isRepeticionColores()?"Sí":"No");
		else if (this==Dificultad.MEDIO)
			descripcion=String.format("%s\n - Jugadores: %d (jugador y máquina)\n - Casillas: %d\n - Rondas máximas:%d\n - Colores: %d\n - Repetición de colores: %s\n",Dificultad.MEDIO.nombre(),Dificultad.MEDIO.getNumJugadores(),Dificultad.MEDIO.getNumCasillas(),Dificultad.MEDIO.getNumIntentos(),Dificultad.MEDIO.getNumColores(),Dificultad.MEDIO.isRepeticionColores()?"Sí":"No");
		else if (this==Dificultad.DIFICIL)
			descripcion=String.format("%s\n - Jugadores: %d (máquina y máquina)\n - Casillas: %d\n - Rondas máximas:∞\n - Colores: %d\n - Repetición de colores: %s\n",Dificultad.DIFICIL.nombre(),Dificultad.DIFICIL.getNumJugadores(),Dificultad.DIFICIL.getNumCasillas(),Dificultad.DIFICIL.getNumColores(),Dificultad.DIFICIL.isRepeticionColores()?"Sí":"No");
		return descripcion;
	}
}
