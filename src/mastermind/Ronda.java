package mastermind;

/**
 * Esta clase almacena tres combinaciones, de las cuales 2, le llegan desde el Tablero
 * 
 * @author Nekotaga
 * @version 1.0
 * @since 1.0
 * @see Combinacion
 * @see Tablero
 */
public class Ronda {

	//Variables
	/**
	 * Almacena el número de ronda por el que va.
	 * Puede devolverse mediante {@link #getContadorRonda()}.
	 */
	private int contadorRonda;
	/**
	 * Almacena el número de fichas negras generado por {@link #contarByN()}.
	 */
	private byte contadorNegras;
	/**
	 * Almacena el número de fichas blancas generado por {@link #contarByN()}.
	 */
	private byte contadorBlancas;
	/**
	 * Almacena la combinación de fichas blancas y negras generada por {@link #crearByN()}.
	 * Puede devolverse mediante {@link #getResultadoByN()}.
	 * @see Combinacion
	 */
	private Combinacion resultadoByN;
	/**
	 * Almacena la combinación de fichas de color a adivinar pasada por el constructor #{@link Ronda}.
	 * @see Combinacion
	 */
	private Combinacion combinacionOriginal;
	/**
	 * Almacena la combinación de fichas de color propuesta pasada por el constructor #{@link Ronda}.
	 * Puede devolverse mediante {@link #getCombinacionPropuesta()}.
	 * @see Combinacion
	 */
	private Combinacion combinacionPropuesta;
	
	//Constructor
	/**
	 * Construye un nuevo objeto Ronda formado por 3 combinaciones.
	 * @param contadorRonda			El número de ronda.
	 * @param combinacionOriginal	La combinación a adivinar.
	 * @param combinacionPropuesta	La combinación propuesta.
	 * @see Combinacion
	 */
	Ronda(int contadorRonda,Combinacion combinacionOriginal,Combinacion combinacionPropuesta){
		this.contadorRonda=contadorRonda;
		this.combinacionOriginal=combinacionOriginal;
		this.combinacionPropuesta=combinacionPropuesta;
		crearByN();
	}

	//Getters
	/**
	 * Devuelve el valor de la variable {@link #contadorRonda}.
	 * @return	El número de ronda.
	 */
	protected int getContadorRonda() {
		return contadorRonda;
	}
	/** 
	 * Devuelve el valor de la variable {@link #resultadoByN}.
	 * @return	La combinacion de fichas blancas y negras.
	 * @see Combinacion
	 */
	protected Combinacion getResultadoByN() {
		return resultadoByN;
	}
	/** 
	 * Devuelve el valor de la variable {@link #combinacionPropuesta}.
	 * @return	La combinacion propuesta.
	 * @see Combinacion
	 */
	protected Combinacion getCombinacionPropuesta() {
		return combinacionPropuesta;
	}

	//Métodos
	/**
	 * Crea una combinacion de fichas blancas y negras a partir de {@link #contadorBlancas} y {@link #contadorNegras}.
	 * @see Combinacion
	 * @see Ficha
	 * @see #contarByN()
	 */
	private void crearByN() {
		Ficha fichasByN[] = new Ficha[combinacionOriginal.getCombinacion().length];
		byte contador=0;
		contarByN();	// Contamos las casillas blancas y negras
		for (byte negras=0;negras<contadorNegras;negras++) {		//---- Se almacenan las casillas
			fichasByN[contador]=new Ficha(Color.NEGRO);					// negras en el array.
			contador++;													//
		}
		for (byte blancas=0;blancas<contadorBlancas;blancas++) {	//---- Se almacenan las casillas
			fichasByN[contador]=new Ficha(Color.BLANCO);				// blancas en el array.
			contador++;													//
		}
		resultadoByN = new Combinacion(fichasByN);
	}
	/**
	 * Cuenta las fichas blancas y negras que hay entre la {@link #combinacionOriginal} y la {@link #combinacionPropuesta}.
	 * @see Combinacion
	 */
	private void contarByN() {
		boolean aparece;
		boolean comprobadaOriginal[] = new boolean[combinacionOriginal.getCombinacion().length];
		for (byte i=0;i<comprobadaOriginal.length;i++)	//---- Esto sirve para que las casillas
			comprobadaOriginal[i]=false;					// comprobadas no se vuelvan a comprobar.
		for (byte i=0;i<combinacionPropuesta.getCombinacion().length;i++) {															//---- Compara las casillas de la
			aparece=false;																												// combinación propuesta por
			for (byte j=0;j<combinacionOriginal.getCombinacion().length&&!aparece;j++) {												// las casillas una a una de
				if (combinacionPropuesta.getCombinacion()[i].equals(combinacionOriginal.getCombinacion()[j])&&!comprobadaOriginal[j]) {	// la combinación original.
					if (combinacionPropuesta.getCombinacion()[i].equals(combinacionOriginal.getCombinacion()[i])) {						// Al hacer esto se meten las
						contadorNegras++;																								// casillas comprobadas de la
						comprobadaOriginal[i]=true;																						// combinación original en un
					}else if (combinacionPropuesta.getCombinacion()[j].equals(combinacionOriginal.getCombinacion()[j])) {				// array para marcarlas y que
						contadorNegras++;																								// no se comparen 2 veces. La
						comprobadaOriginal[j]=true;																						// ccombinación propuesta no
					}else {																												// lo necesita porque se
						contadorBlancas++;																								// recorre de uno en uno. 
						comprobadaOriginal[j]=true;																						// Si el color de ficha de
					}																													// las 2 casillas es la misma,
					aparece=true;																										// según la posición de ambas
				}																														// se marcará como ficha negra
			}																															// o ficha blanca.
		}
	}
}
