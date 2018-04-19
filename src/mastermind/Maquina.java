package mastermind;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.TreeMap;

public class Maquina extends Usuario{
	
	//Variables
	/*private byte fase=1;
	private byte contadorRondas=0;
	private byte contadorColores=0;
	private boolean primeraMitad=true;
	private TreeMap<Color,IA> mapaColores = new TreeMap<Color,IA>();
	private ArrayList<Color> coloresCorrectos = new ArrayList<Color>();
	private ArrayList<Color> coloresIncorrectos = new ArrayList<Color>();
	private ArrayList<Combinacion> combinacionesGuardadas = new ArrayList<Combinacion>();*/
	
	//Constructor
	Maquina(boolean tiene,Dificultad dificultad){
		super(dificultad);
		if (tiene)
			combinacionOriginal=colocarCombinacion();
	}
	
	//Métodos
	private Combinacion colocarCombinacionRandom() {
		byte numero;
		boolean colorActual;
		boolean colorRepetido;
		boolean almacenarColores[]=new boolean[dificultad.getNumColores()];
		Combinacion combinacion=new Combinacion(TipoCombinacion.COLOR,new Ficha[dificultad.getNumCasillas()]);
		Random rnd=new Random();
		
		for (byte i=0;i<dificultad.getNumCasillas();i++) {
			if (!dificultad.isRepeticionColores()) {
				do {
					colorRepetido=false;
					numero=(byte)rnd.nextInt(dificultad.getColores().length);
					colorActual=almacenarColores[numero];
					if (colorActual)
						colorRepetido=true;
					else
						almacenarColores[numero]=true;
				}while (colorRepetido);
				combinacion.getCombinacion()[i]=new Ficha(dificultad.getColores()[numero]);
			}else 
				combinacion.getCombinacion()[i]=new Ficha(dificultad.getColores()[rnd.nextInt(dificultad.getColores().length)]);
		}
		return combinacion;
	}
	
	//Métodos Heredados
	protected Combinacion colocarCombinacion() {
		Combinacion combinacion = new Combinacion(TipoCombinacion.COLOR,new Ficha[dificultad.getNumCasillas()]);
		/*if (dificultad.equals(Dificultad.FACIL_COMPROBAR)) {
			if (contadorColores>2&&tablero.getRondas().get(0).getResultadoByN().getCombinacion()[1]==null) {		// Out of bonds del caso 2
				if (primeraMitad) {
					coloresCorrectos.add(dificultad.getColores()[0]);
					coloresIncorrectos.add(dificultad.getColores()[1]);
					coloresIncorrectos.add(dificultad.getColores()[2]);
					coloresIncorrectos.add(dificultad.getColores()[3]);
					primeraMitad=false;
				}else {
					coloresIncorrectos.add(dificultad.getColores()[4]);
					coloresCorrectos.add(dificultad.getColores()[5]);
					coloresCorrectos.add(dificultad.getColores()[6]);
					coloresCorrectos.add(dificultad.getColores()[7]);
				}
			}else if (contadorColores>2&&tablero.getRondas().get(0).getResultadoByN().getCombinacion()[3]==null) {	// Out of bonds del caso 4
				if (primeraMitad) {
					coloresIncorrectos.add(dificultad.getColores()[0]);
					coloresCorrectos.add(dificultad.getColores()[1]);
					coloresCorrectos.add(dificultad.getColores()[2]);
					coloresCorrectos.add(dificultad.getColores()[3]);
					primeraMitad=false;
				}else {
					coloresCorrectos.add(dificultad.getColores()[4]);
					coloresIncorrectos.add(dificultad.getColores()[5]);
					coloresIncorrectos.add(dificultad.getColores()[6]);
					coloresIncorrectos.add(dificultad.getColores()[7]);
				}
			}
			switch (fase) {
				case 1:
					if (contadorRondas==0) {
						for (byte i=0;i<dificultad.getNumCasillas();i++) {
							combinacion.getCombinacion()[i]=new Ficha(dificultad.getColores()[i]);
						}
					}else if (primeraMitad) {
						contadorColores++;
						for (byte i=0;i<dificultad.getNumCasillas();i++) {
							if (i<dificultad.getNumCasillas()/2)
								combinacion.getCombinacion()[i]=new Ficha(dificultad.getColores()[0]);
							else
								combinacion.getCombinacion()[i]=new Ficha(dificultad.getColores()[contadorColores]);
						}
					}else if (!primeraMitad) {
						contadorColores++;
						for (byte i=0;i<dificultad.getNumCasillas();i++) {
							if (i<dificultad.getNumCasillas()/2)
								combinacion.getCombinacion()[i]=new Ficha(dificultad.getColores()[(dificultad.getNumColores()/2)]);
							else
								combinacion.getCombinacion()[i]=new Ficha(dificultad.getColores()[(dificultad.getNumColores()/2)+contadorColores]);
						}
					}
					break;
				case 2:
					for (byte i=0;i<dificultad.getNumCasillas();i++) {
						combinacion.getCombinacion()[i]=new Ficha(coloresCorrectos.get(i));
					}
					break;
			}
		}else*/
			combinacion=colocarCombinacionRandom();
		return combinacion;
	}
	protected void comprobarCombinacion(Ronda ronda) {
		Color colorIzq=ronda.getCombinacionPropuesta().getCombinacion()[0].getColorFicha();
		Color colorDer=ronda.getCombinacionPropuesta().getCombinacion()[dificultad.getNumCasillas()-1].getColorFicha();
		LinkedList<Ronda> rondas = tablero.getRondas();
		Combinacion resultadoByN = rondas.getLast().getResultadoByN();
		/*if (fase==1) {
				
			//Caso 1
			if (rondas.get(0).getResultadoByN().getCombinacion()[0]==null) {
				coloresCorrectos.add(dificultad.getColores()[4]);
				coloresCorrectos.add(dificultad.getColores()[5]);
				coloresCorrectos.add(dificultad.getColores()[6]);
				coloresCorrectos.add(dificultad.getColores()[7]);
			
			//Caso 2
			}else if (rondas.get(0).getResultadoByN().getCombinacion()[1]==null) {
				if (primeraMitad&&resultadoByN.getCombinacion()[0]==null) {
					coloresIncorrectos.add(colorIzq);
					coloresIncorrectos.add(colorDer);
					primeraMitad=false;
					contadorColores=0;
				}else if (primeraMitad) {
					combinacionesGuardadas.add(combinacionPropuesta);
					if (combinacionesGuardadas.size()==2) {
						coloresCorrectos.add(dificultad.getColores()[0]);
						coloresIncorrectos.add(dificultad.getColores()[1]);
						coloresIncorrectos.add(dificultad.getColores()[2]);
						coloresIncorrectos.add(dificultad.getColores()[3]);
						primeraMitad=false;
						contadorColores=0;
					}
				}else if (coloresIncorrectos.size()==2&&coloresCorrectos.size()<3) {
					if (resultadoByN.getCombinacion()[1]!=null&&resultadoByN.getCombinacion()[2]==null) {
						if (!coloresCorrectos.contains(colorIzq))
							coloresCorrectos.add(colorIzq);
						if (!coloresCorrectos.contains(colorDer))
							coloresCorrectos.add(colorDer);
					}
					if (coloresCorrectos.size()==3) {
						for (byte i=(byte)(dificultad.getNumColores()/2);i<dificultad.getNumColores();i++) {
							if (!coloresCorrectos.contains(dificultad.getColores()[i]))
								coloresIncorrectos.add(dificultad.getColores()[i]);
						}
					}
				}else if (coloresCorrectos.size()==3) {
					if (resultadoByN.getCombinacion()[3]!=null) {
						coloresCorrectos.add(colorDer);
						for (byte i=(byte)(dificultad.getNumColores()/2);i<dificultad.getNumColores();i++) {
							if (!coloresIncorrectos.contains(dificultad.getColores()[i])&&!coloresCorrectos.contains(dificultad.getColores()[i]))
								coloresIncorrectos.add(dificultad.getColores()[i]);
						}
					}else {
						coloresIncorrectos.add(colorDer);
						for (byte i=(byte)(dificultad.getNumColores()/2);i<dificultad.getNumColores();i++) {
							if (!coloresIncorrectos.contains(dificultad.getColores()[i])&&!coloresCorrectos.contains(dificultad.getColores()[i]))
								coloresCorrectos.add(dificultad.getColores()[i]);
						}
					}
				}
			
			//Caso 3
			}else if (rondas.get(0).getResultadoByN().getCombinacion()[2]==null) {
				if (primeraMitad&&contadorRondas>0) {
					if (resultadoByN.getCombinacion()[0]==null) {
						coloresIncorrectos.add(colorIzq);
						coloresIncorrectos.add(colorDer);
						for (byte i=0;i<dificultad.getNumColores()/2;i++) {
							if (!coloresIncorrectos.contains(dificultad.getColores()[i]))
								coloresCorrectos.add(dificultad.getColores()[i]);
						}
						primeraMitad=false;
						contadorColores=0;
					}else if (resultadoByN.getCombinacion()[1]!=null&&resultadoByN.getCombinacion()[2]==null) {
						coloresCorrectos.add(colorIzq);
						coloresCorrectos.add(colorDer);
						for (byte i=0;i<dificultad.getNumColores()/2;i++) {
							if (!coloresCorrectos.contains(dificultad.getColores()[i]))
								coloresIncorrectos.add(dificultad.getColores()[i]);
						}
						primeraMitad=false;
						contadorColores=0;
					}
				}else if (!primeraMitad) {
					if (resultadoByN.getCombinacion()[0]==null) {
						coloresIncorrectos.add(colorIzq);
						coloresIncorrectos.add(colorDer);
						for (byte i=(byte)(dificultad.getNumColores()/2);i<dificultad.getNumColores();i++) {
							if (!coloresIncorrectos.contains(dificultad.getColores()[i]))
								coloresCorrectos.add(dificultad.getColores()[i]);
						}
					}else if (resultadoByN.getCombinacion()[1]!=null&&resultadoByN.getCombinacion()[2]==null) {
						coloresCorrectos.add(colorIzq);
						coloresCorrectos.add(colorDer);
						for (byte i=(byte)(dificultad.getNumColores()/2);i<dificultad.getNumColores();i++) {
							if (!coloresCorrectos.contains(dificultad.getColores()[i]))
								coloresIncorrectos.add(dificultad.getColores()[i]);
						}
					}
				}
			
			//Caso 4
			}else if (rondas.get(0).getResultadoByN().getCombinacion()[3]==null) {
				if (primeraMitad&&resultadoByN.getCombinacion()[1]!=null&&resultadoByN.getCombinacion()[2]==null) {
					coloresCorrectos.add(colorIzq);
					coloresCorrectos.add(colorDer);
					primeraMitad=false;
					contadorColores=0;
				}else if (coloresCorrectos.size()==2&&coloresIncorrectos.size()<3) {
					if (resultadoByN.getCombinacion()[0]==null) {
						if (!coloresIncorrectos.contains(colorIzq))
							coloresIncorrectos.add(colorIzq);
						if (!coloresIncorrectos.contains(colorDer))
							coloresIncorrectos.add(colorDer);
					}
					if (coloresIncorrectos.size()==3) {
						for (byte i=(byte)(dificultad.getNumColores()/2);i<dificultad.getNumColores();i++) {
							if (!coloresIncorrectos.contains(dificultad.getColores()[i]))
								coloresCorrectos.add(dificultad.getColores()[i]);
						}
					}
				}else if (coloresIncorrectos.size()==3) {
					if (resultadoByN.getCombinacion()[3]!=null) {
						coloresCorrectos.add(colorDer);
						for (byte i=(byte)(dificultad.getNumColores()/2);i<dificultad.getNumColores();i++) {
							if (!coloresIncorrectos.contains(dificultad.getColores()[i])&&!coloresCorrectos.contains(dificultad.getColores()[i]))
								coloresIncorrectos.add(dificultad.getColores()[i]);
						}
					}else {
						coloresIncorrectos.add(colorDer);
						for (byte i=(byte)(dificultad.getNumColores()/2);i<dificultad.getNumColores();i++) {
							if (!coloresIncorrectos.contains(dificultad.getColores()[i])&&!coloresCorrectos.contains(dificultad.getColores()[i]))
								coloresCorrectos.add(dificultad.getColores()[i]);
						}
					}
				}
			
			//Caso 5
			}else {
				coloresCorrectos.add(dificultad.getColores()[0]);
				coloresCorrectos.add(dificultad.getColores()[1]);
				coloresCorrectos.add(dificultad.getColores()[2]);
				coloresCorrectos.add(dificultad.getColores()[3]);
			}
			
		if (coloresCorrectos.size()==dificultad.getNumCasillas())
			fase++;
	}else if (fase==2) {
			//az ce am ro
			//am ce li ro
		}
		contadorRondas++;*/
	}
}
