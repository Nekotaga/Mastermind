package mastermind;

public abstract class Usuario {
	
	//Variables
	protected Tablero tablero;
	protected Combinacion combinacionOriginal;
	protected Combinacion combinacionPropuesta;
	protected Dificultad dificultad;
	
	//Constructor
	Usuario(Dificultad dificultad){
		this.dificultad=dificultad;
		tablero = new Tablero(dificultad);
	}
	
	//Getters
	protected Tablero getTablero() {
		return tablero;
	}
	protected Combinacion getCombinacionOriginal() {
		return combinacionOriginal;
	}
	protected Combinacion getCombinacionPropuesta() {
		return combinacionPropuesta;
	}protected void setCombinacionPropuesta(Combinacion combinacion) {
		combinacionPropuesta=combinacion;
	}
	
	//Métodos
	protected abstract Combinacion colocarCombinacion();
	protected abstract void comprobarCombinacion(Ronda ronda);
}
