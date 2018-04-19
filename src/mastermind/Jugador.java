package mastermind;

import static utilidades.Teclado.*;

public class Jugador extends Usuario{
	
	boolean tiene;

	//Constructor
	Jugador(boolean tiene,Dificultad dificultad){
		super(dificultad);
		this.tiene=tiene;
		if (tiene)
			combinacionOriginal=colocarCombinacion();
	}
	
	//Métodos Heredados
	protected Combinacion colocarCombinacion() {
		String split;
		boolean error;
		boolean esValida;
		String combinacionSplit[];
		Combinacion combinacion = new Combinacion(TipoCombinacion.COLOR,new Ficha[dificultad.getNumCasillas()]);
		
		System.out.print("Introduzca una combinación con abreviaturas de los colores disponibles separada por espacios\nColores disponibles: ");
		for (byte i=0;i<dificultad.getColores().length;i++)
			if (i==dificultad.getColores().length-1)
				System.out.printf("%s%s%s\n",dificultad.getColores()[i].getColor(),dificultad.getColores()[i],Constantes.RESET);
			else
				System.out.printf("%s%s%s | ",dificultad.getColores()[i].getColor(),dificultad.getColores()[i],Constantes.RESET);
		do {
			error=false;
			try {
				split=leerString().toLowerCase();
				combinacionSplit=split.split(" ");
				if (combinacionSplit.length<dificultad.getNumCasillas()||combinacionSplit.length>dificultad.getNumCasillas())
					throw new Exception(String.format("%sNo ha introducido las abreviaturas necesarias\nDebe introducir %d abreviaturas separadas por espacios%s",Color.ROJO.getColor(),dificultad.getNumCasillas(),Constantes.RESET));
				for (byte i=0;i<dificultad.getNumCasillas();i++) {
					esValida=false;
					for (byte j=0;j<dificultad.getColores().length&&!esValida;j++) {
						if (combinacionSplit[i].equals(dificultad.getColores()[j].getAbreviatura())) {
							combinacion.getCombinacion()[i]=new Ficha(dificultad.getColores()[j]);
							esValida=true;
						}
					}
					if (!esValida)
						throw new Exception(String.format("%sNo ha escrito correctamente las %s abreviaturas\nPuede encontrarlas entre paréntesis junto a los colores disponibles%s",Color.ROJO.getColor(),dificultad.getNumCasillas(),Constantes.RESET));
				}
				if (!dificultad.isRepeticionColores()&&tiene&&combinacionOriginal==null)
					for (byte i=0;i<combinacionSplit.length;i++)
						for (byte j=0;j<combinacionSplit.length;j++)
							if (i!=j&&combinacionSplit[i].equals(combinacionSplit[j]))
								throw new Exception(String.format("%sHa introducido abreviaturas repetidas\nNo puede introducir colores repetidos en esta dificultad%s",Color.ROJO.getColor(),Constantes.RESET));
			}catch(Exception e){
				error=true;
				System.out.println(e.getMessage());
			}
		}while(error);
		return combinacion;
	}
	protected void comprobarCombinacion(Ronda ronda) {
		boolean error;
		byte numNegras;
		byte numBlancas;
		byte contadorNegras=0;
		byte contadorBlancas=0;
		String mensajeError=String.format("%sDebe introducir un valor entre 0 y %d: %s",Color.ROJO.getColor(),dificultad.getNumCasillas(),Constantes.RESET);
		
		if (!ronda.getCombinacionPropuesta().equals(combinacionOriginal)) {
			for (byte i=0;i<ronda.getResultadoByN().getCombinacion().length&&ronda.getResultadoByN().getCombinacion()[i]!=null;i++)
				if (Color.NEGRO==ronda.getResultadoByN().getCombinacion()[i].getColorFicha()&&ronda.getResultadoByN().getCombinacion()[i].getColorFicha()!=null)
					contadorNegras++;
				else if (Color.BLANCO==ronda.getResultadoByN().getCombinacion()[i].getColorFicha()&&ronda.getResultadoByN().getCombinacion()[i].getColorFicha()!=null)
					contadorBlancas++;
			System.out.print("Introduzca la cantidad de fichas del mismo color que su combinación que estén situadas en la misma posición: ");
			do {
				error=false;
				try {
					numNegras=leerEntre((byte)0,dificultad.getNumCasillas(),leerEntre.AMBOS_INCLUIDOS,mensajeError);
					if (numNegras!=contadorNegras)
						throw new Exception(String.format("%sSe ha equivocado en la introducción, introduzca correctamente la cantidad de casillas negras: %s",Color.ROJO.getColor(),Constantes.RESET));
				}catch(Exception e){
					error=true;
					System.out.print(e.getMessage());
				}
			}while(error);
			System.out.print("Introduzca la cantidad de fichas del mismo color que su combinación que no estén situadas en la misma posición: ");
			do {
				error=false;
				try {
					numBlancas=leerEntre((byte)0,dificultad.getNumCasillas(),leerEntre.AMBOS_INCLUIDOS,mensajeError);
					if (numBlancas!=contadorBlancas)
						throw new Exception(String.format("%sSe ha equivocado en la introducción, introduzca correctamente la cantidad de casillas blancas: %s",Color.ROJO.getColor(),Constantes.RESET));
				}catch(Exception e){
					error=true;
					System.out.print(e.getMessage());
				}
			}while(error);
			System.out.println();
		}
	}
	
}
