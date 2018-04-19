package mastermind;

public enum Resultado {
	
	//Enum
	VICTORIA(Color.LIMA.getColor()+"¡Felicidades, ha ganado la partida!"+Constantes.RESET),
	EMPATE(Color.AMARILLO.getColor()+"La partida a terminado en empate"+Constantes.RESET),
	DERROTA(Color.MORADO.getColor()+"Oh... la próxima vez será..."+Constantes.RESET),
	VICTORIA_MAQ(Color.LIMA.getColor()+"Ha ganado la máquina ");	//Añadir num de máquina y reset
	
	//Variables
	private String mensaje;
	

	//Constructor
	Resultado(String mensaje){
		this.mensaje=mensaje;
	}
	
	//Getters
	protected String getMensaje() {
		return mensaje;
	}
	
}
