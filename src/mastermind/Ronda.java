package mastermind;

public class Ronda {

	//Variables
	private int contadorRonda;
	private byte contadorNegras;
	private byte contadorBlancas;
	private Combinacion resultadoByN;
	private Combinacion combinacionOriginal;
	private Combinacion combinacionPropuesta;
	
	//Constructor
	Ronda(int contadorRonda,Combinacion combinacionOriginal,Combinacion combinacionPropuesta){
		this.contadorRonda=contadorRonda;
		this.combinacionOriginal=combinacionOriginal;
		this.combinacionPropuesta=combinacionPropuesta;
		crearByN();
	}

	//Getters
	protected int getContadorRonda() {
		return contadorRonda;
	}
	protected Combinacion getResultadoByN() {
		return resultadoByN;
	}
	protected Combinacion getCombinacionPropuesta() {
		return combinacionPropuesta;
	}

	//Métodos
	private void crearByN() {
		Ficha fichasByN[] = new Ficha[combinacionOriginal.getCombinacion().length];
		byte contador=0;
		contarByN();
		for (byte negras=0;negras<contadorNegras;negras++) {
			fichasByN[contador]=new Ficha(Color.NEGRO);
			contador++;
		}
		for (byte blancas=0;blancas<contadorBlancas;blancas++) {
			fichasByN[contador]=new Ficha(Color.BLANCO);
			contador++;
		}
		resultadoByN = new Combinacion(TipoCombinacion.BLANCO_NEGRO,fichasByN);
	}
	private void contarByN() {
		boolean aparece;
		boolean comprobadaOriginal[] = new boolean[combinacionOriginal.getCombinacion().length];
		for (byte i=0;i<comprobadaOriginal.length;i++)
			comprobadaOriginal[i]=false;
		for (byte i=0;i<combinacionPropuesta.getCombinacion().length;i++) {
			aparece=false;
			for (byte j=0;j<combinacionOriginal.getCombinacion().length&&!aparece;j++) {
				if (combinacionPropuesta.getCombinacion()[i].equals(combinacionOriginal.getCombinacion()[j])&&!comprobadaOriginal[j]) {
					if (combinacionPropuesta.getCombinacion()[i].equals(combinacionOriginal.getCombinacion()[i])) {
						contadorNegras++;
						comprobadaOriginal[i]=true;
					}else if (combinacionPropuesta.getCombinacion()[j].equals(combinacionOriginal.getCombinacion()[j])) {
						contadorNegras++;
						comprobadaOriginal[j]=true;
					}else {
						contadorBlancas++;
						comprobadaOriginal[j]=true;
					}
					aparece=true;
				}
			}
		}
	}
}
