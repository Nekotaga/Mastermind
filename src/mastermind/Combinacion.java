package mastermind;

import java.util.Arrays;

public class Combinacion {

	//Variables
	private TipoCombinacion tipo;
	private Ficha combinacion[];
	
	//Constructor
	Combinacion(TipoCombinacion tipo, Ficha combinacion[]) {
		this.tipo=tipo;
		this.combinacion=combinacion;
	}
	
	//Getters
	protected Ficha[] getCombinacion() {
		return combinacion;
	}
	
	//Métodos Heredados
	public boolean equals(Object obj) {
		boolean resultado=false;
		if (obj instanceof Combinacion&&tipo.equals(((Combinacion) obj).tipo)&&Arrays.deepEquals(combinacion,((Combinacion) obj).combinacion)) {
			resultado=true;
		}
		return resultado;
	}
}
