package codigo;

public class PruebaRonda {

	//Variables
	private int contadorRonda;
	public byte contadorNegras;
	public byte contadorBlancas;
	public ReferenciaCombinacion resultadoByN;
	public ReferenciaCombinacion combinacionOriginal;
	public ReferenciaCombinacion combinacionPropuesta;
	
	//Constructor
	public PruebaRonda(int contadorRonda,ReferenciaCombinacion combinacionOriginal,ReferenciaCombinacion combinacionPropuesta){
		this.contadorRonda=contadorRonda;
		this.combinacionOriginal=combinacionOriginal;
		this.combinacionPropuesta=combinacionPropuesta;
		crearByN();
	}

	//Getters
	protected int getContadorRonda() {
		return contadorRonda;
	}
	protected ReferenciaCombinacion getResultadoByN() {
		return resultadoByN;
	}
	protected ReferenciaCombinacion getCombinacionPropuesta() {
		return combinacionPropuesta;
	}

	//Métodos
	private void crearByN() {
		ReferenciaFicha fichasByN[] = new ReferenciaFicha[combinacionOriginal.getCombinacion().length];
		byte contador=0;
		contarByN();	// Contamos las casillas blancas y negras
		for (byte negras=0;negras<contadorNegras;negras++) {		//---- Se almacenan las casillas
			fichasByN[contador]=new ReferenciaFicha(ReferenciaColor.NEGRO);					// negras en el array.
			contador++;													//
		}
		for (byte blancas=0;blancas<contadorBlancas;blancas++) {	//---- Se almacenan las casillas
			fichasByN[contador]=new ReferenciaFicha(ReferenciaColor.BLANCO);				// blancas en el array.
			contador++;													//
		}
		resultadoByN = new ReferenciaCombinacion(fichasByN);
	}
	private void contarByN() {
		boolean aparece;
		boolean comprobadaOriginal[] = new boolean[combinacionOriginal.getCombinacion().length];
		if (combinacionOriginal.getCombinacion().length!=combinacionPropuesta.getCombinacion().length)
			throw new IllegalArgumentException();
		for (byte i=0;i<comprobadaOriginal.length;i++)	//---- Esto sirve para que las casillas
			comprobadaOriginal[i]=false;					// comprobadas no se vuelvan a comprobar.
		for (byte i=0;i<combinacionPropuesta.getCombinacion().length;i++) {																//---- Compara las casillas de la
			aparece=false;																													// combinación propuesta por
			for (byte j=0;j<combinacionOriginal.getCombinacion().length&&!aparece;j++) {													// las casillas una a una de
				if (combinacionPropuesta.getCombinacion()[i].equals(combinacionOriginal.getCombinacion()[j])&&!comprobadaOriginal[j]) {		// la combinación original.
					if (combinacionPropuesta.getCombinacion()[i].equals(combinacionOriginal.getCombinacion()[i])&&!comprobadaOriginal[i]) {	// Al hacer esto se meten las
						contadorNegras++;																									// casillas comprobadas de la
						comprobadaOriginal[i]=true;																							// combinación original en un
					}else if (combinacionPropuesta.getCombinacion()[j].equals(combinacionOriginal.getCombinacion()[j])) {					// array para marcarlas y que
						contadorNegras++;																									// no se comparen 2 veces. La
						comprobadaOriginal[j]=true;																							// ccombinación propuesta no
					}else {																													// lo necesita porque se
						contadorBlancas++;																									// recorre de uno en uno. 
						comprobadaOriginal[j]=true;																							// Si el color de ficha de
					}																														// las 2 casillas es la misma,
					aparece=true;																											// según la posición de ambas
				}																															// se marcará como ficha negra
			}																																// o ficha blanca.
		}
	}
}
