package codigo;

public class ReferenciaFicha {

	//Variables
	private ReferenciaColor colorFicha;
	
	//Constructor
	public ReferenciaFicha(ReferenciaColor colorFicha){
		this.colorFicha=colorFicha;
	}
	
	//Getters
	protected ReferenciaColor getColorFicha() {
		return colorFicha;
	}

	//Métodos Heredados
	public boolean equals(Object obj) {
		boolean resultado=false;
		if (obj instanceof ReferenciaFicha&&colorFicha.equals(((ReferenciaFicha) obj).colorFicha)){
			resultado=true;
		}
		return resultado;
	}
}
