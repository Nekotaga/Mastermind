package codigo;

import java.util.Arrays;

public class ReferenciaCombinacion {

	//Variables
	private ReferenciaFicha combinacion[];
	
	//Constructor
	public ReferenciaCombinacion(ReferenciaFicha combinacion[]) {
		this.combinacion=combinacion;
	}
	
	//Getters
	protected ReferenciaFicha[] getCombinacion() {
		return combinacion;
	}
	
	//Métodos Heredados
	public boolean equals(Object obj) {
		boolean resultado=false;
		if (obj instanceof ReferenciaCombinacion&&Arrays.deepEquals(combinacion,((ReferenciaCombinacion) obj).combinacion)) {
			resultado=true;
		}
		return resultado;
	}
}
