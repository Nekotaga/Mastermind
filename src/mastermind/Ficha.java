package mastermind;

public class Ficha {

	//Variables
	private Color colorFicha;
	
	//Constructor
	Ficha(Color colorFicha){
		this.colorFicha=colorFicha;
	}
	
	//Getters
	protected Color getColorFicha() {
		return colorFicha;
	}

	//Métodos Heredados
	public boolean equals(Object obj) {
		boolean resultado=false;
		if (obj instanceof Ficha&&colorFicha.equals(((Ficha) obj).colorFicha)){
			resultado=true;
		}
		return resultado;
	}
}
