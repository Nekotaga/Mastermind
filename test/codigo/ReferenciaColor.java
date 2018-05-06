package codigo;

public enum ReferenciaColor {
	
	//Enum
	AMARILLO("\u001B[33;1m","\u001B[103m"),
	AZUL("\u001B[34m","\u001B[44m"),
	BURDEOS("\u001B[31m","\u001B[41m"),
	CELESTE("\u001B[36;1m","\u001B[106m"),
	GRIS("\u001B[30;1m","\u001B[100m"),
	LIMA("\u001B[32;1m","\u001B[102m"),
	MORADO("\u001B[35;1m","\u001B[105m"),
	ROJO("\u001B[31;1m","\u001B[101m"),
	TEAL("\u001B[36m","\u001B[46m"),
	VERDE("\u001B[32m","\u001B[42m"),
	BLANCO("\u001B[37;1m","\u001B[107m"),
	NEGRO("\u001B[30m","\u001B[40m");
	
	//Variables
	private String color;
	private String colorFondo;
	private String abreviatura;

	//Constructor
	ReferenciaColor(String color,String colorFondo){
		this.color=color;
		this.colorFondo=colorFondo;
		abreviatura=super.toString().substring(0,2).toLowerCase();
	}
	
	//Getters
	protected String getColor() {
		return color;
	}
	protected String getColorFondo() {
		return colorFondo;
	}
	protected String getAbreviatura(){
		return abreviatura;
	}
	
	//Métodos Heredados
	public String toString(){
		return String.format("%s (%s)",super.toString().charAt(0)+super.toString().substring(1).toLowerCase(),abreviatura);
	}
}
