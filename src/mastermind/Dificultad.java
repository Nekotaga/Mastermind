package mastermind;

public enum Dificultad {
	
	//Enum
	FACIL_ADIVINAR((byte)1,(byte)4,(byte)10,false),
	FACIL_COMPROBAR((byte)1,(byte)4,(byte)10,false),
	MEDIO((byte)2,(byte)5,(byte)15,false),
	DIFICIL((byte)2,(byte)8,(byte)0,true);
	
	//Variables
	private byte numColores;
	private byte numJugadores;
	private byte numCasillas;
	private byte numIntentos;
	private boolean repeticionColores;
	private Color colores[];
	
	//Constructor
	Dificultad(byte numJugadores,byte numCasillas,byte numIntentos,boolean repeticionColores){
		this.numJugadores=numJugadores;
		this.numCasillas=numCasillas;
		this.numIntentos=numIntentos;
		this.repeticionColores=repeticionColores;
		asociarColores();
	}
	
	//Getters
	protected byte getNumJugadores() {
		return numJugadores;
	}
	protected byte getNumCasillas() {
		return numCasillas;
	}
	protected byte getNumColores() {
		return numColores;
	}
	protected byte getNumIntentos() {
		return numIntentos;
	}
	protected boolean isRepeticionColores() {
		return repeticionColores;
	}
	protected Color[] getColores() {
		return colores;
	}
	
	//Métodos
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
