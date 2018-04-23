package mastermind;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.TreeMap;

public class Maquina extends Usuario{
	
	//Variables
	private byte fase=1;
	private byte contadorRondas=0;
	private byte contadorColores=0;
	private boolean primeraMitad=true;
	private TreeMap<Color,IA> mapaColores = new TreeMap<Color,IA>();
	private ArrayList<Color> coloresPosibles = new ArrayList<Color>();
	private ArrayList<Color> coloresCorrectos = new ArrayList<Color>();
	private ArrayList<Color> coloresIncorrectos = new ArrayList<Color>();
	private ArrayList<Combinacion> combinacionesGuardadas = new ArrayList<Combinacion>();
	
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
	private byte contarFichasByN(LinkedList<Ronda> rondas) {
		byte contadorFichas=0;
		Combinacion resultadoByN = rondas.getLast().getResultadoByN();
		for (Ficha f:resultadoByN.getCombinacion())
			if (f!=null)
				contadorFichas++;
		return contadorFichas;
	}
	private void comprobarCombinaciones(byte fichasNoVacias,Ronda ronda) {
		byte inicioBucle=0;
		byte finBucle=0;
		byte numFichasByN=0;
		Color colorIzq=ronda.getCombinacionPropuesta().getCombinacion()[0].getColorFicha();
		Color colorDer=ronda.getCombinacionPropuesta().getCombinacion()[dificultad.getNumCasillas()-1].getColorFicha();
		Combinacion combinacionByN = ronda.getResultadoByN();
		ArrayList<Color> coloresCorrIncor1 = new ArrayList<Color>();
		ArrayList<Color> coloresCorrIncor2 = new ArrayList<Color>();
		LinkedList<Ronda> rondas = tablero.getRondas();
		switch (dificultad) {
			case FACIL_ADIVINAR:
				System.out.println(Color.ROJO.getColor()+"Este método no se puede usar de esta forma"+Constantes.RESET);
				break;
			case FACIL_COMPROBAR:
				if (fichasNoVacias==0||fichasNoVacias==1&&!primeraMitad||fichasNoVacias==2&&!primeraMitad||fichasNoVacias==3&&!primeraMitad) {
					inicioBucle=(byte)(dificultad.getNumColores()/2);
					finBucle=dificultad.getNumColores();
					if (fichasNoVacias==3) {
						coloresCorrIncor1=coloresCorrectos;
						coloresCorrIncor2=coloresIncorrectos;
						numFichasByN=0;
					}else {
						coloresCorrIncor1=coloresIncorrectos;
						coloresCorrIncor2=coloresCorrectos;
						numFichasByN=2;
					}
				}else if (fichasNoVacias==1&&primeraMitad||fichasNoVacias==2&&primeraMitad||fichasNoVacias==3&&primeraMitad||fichasNoVacias==4) {
					inicioBucle=0;
					finBucle=(byte)(dificultad.getNumColores()/2);
					if (fichasNoVacias==3) {
						coloresCorrIncor1=coloresIncorrectos;
						coloresCorrIncor2=coloresCorrectos;
						numFichasByN=2;
					}else {
						coloresCorrIncor1=coloresCorrectos;
						coloresCorrIncor2=coloresIncorrectos;
						numFichasByN=0;
					}
				}
//=========================================================================================================================================================
				if (fichasNoVacias==0||fichasNoVacias==4) {
					for (byte i=inicioBucle;i<finBucle;i++)
						coloresCorrectos.add(dificultad.getColores()[i]);
				//-----------------------------------------------------------------------------------------------------------------------------------------
				}else if (fichasNoVacias==1||fichasNoVacias==3) {
					if (coloresPosibles.size()==2) {
						if(contarFichasByN(rondas)==numFichasByN) {
							coloresCorrIncor1.add(coloresPosibles.get(1));
							coloresCorrIncor2.add(coloresPosibles.get(0));
						}else {
							coloresCorrIncor1.add(coloresPosibles.get(0));
							coloresCorrIncor2.add(coloresPosibles.get(1));
						}
					}else {
						if(contarFichasByN(rondas)==numFichasByN) {
							coloresCorrIncor2.add(colorDer);
							if (!coloresCorrIncor2.contains(colorIzq)) {
								coloresCorrIncor2.add(colorIzq);
							}
							if (combinacionesGuardadas.size()==0) {
								for (byte i=inicioBucle;i<finBucle;i++) {
									if (!coloresCorrIncor2.contains(dificultad.getColores()[i]))
										coloresPosibles.add(dificultad.getColores()[i]);
								}
							}else if (combinacionesGuardadas.size()==1) {
								coloresCorrIncor1.add(combinacionesGuardadas.get(0).getCombinacion()[dificultad.getNumCasillas()-1].getColorFicha());
								for (byte i=inicioBucle;i<finBucle;i++) {
									if (!coloresCorrIncor1.contains(dificultad.getColores()[i])&&!coloresCorrIncor2.contains(dificultad.getColores()[i]))
										coloresCorrIncor2.add(dificultad.getColores()[i]);
								}
							}
						}else {
							combinacionesGuardadas.add(combinacionPropuesta);
							if (combinacionesGuardadas.size()==2) {
								coloresCorrIncor1.add(dificultad.getColores()[0+inicioBucle]);
								coloresCorrIncor2.add(dificultad.getColores()[1+inicioBucle]);
								coloresCorrIncor2.add(dificultad.getColores()[2+inicioBucle]);
								coloresCorrIncor2.add(dificultad.getColores()[3+inicioBucle]);
							}
						}
					}
					if (primeraMitad&&coloresCorrIncor1.size()+coloresCorrIncor2.size()==4) {
						combinacionesGuardadas.clear();
						coloresPosibles.clear();
						primeraMitad=false;
						contadorColores=0;
					}
				//-----------------------------------------------------------------------------------------------------------------------------------------
				}else if (fichasNoVacias==2) {
					if (contadorRondas>0) {
						if (contarFichasByN(rondas)==0) {
							coloresCorrIncor1=coloresIncorrectos;
							coloresCorrIncor2=coloresCorrectos;
						}else if (contarFichasByN(rondas)==2) {
							coloresCorrIncor1=coloresCorrectos;
							coloresCorrIncor2=coloresIncorrectos;
						}
						if (contarFichasByN(rondas)==0||contarFichasByN(rondas)==2) {
							coloresCorrIncor1.add(colorIzq);
							coloresCorrIncor1.add(colorDer);
							for (byte i=inicioBucle;i<finBucle;i++) {
								if (!coloresCorrIncor1.contains(dificultad.getColores()[i]))
									coloresCorrIncor2.add(dificultad.getColores()[i]);
							}
							if (primeraMitad) {
								primeraMitad=false;
								contadorColores=0;
							}
						}
					}
				}
				break;
			case MEDIO:
				break;
			case DIFICIL:
				break;
				
		}
	}
	
	//Métodos Heredados
	protected Combinacion colocarCombinacion() {
		byte posInicial;
		byte colorEscogido;
		Combinacion combinacion = new Combinacion(TipoCombinacion.COLOR,new Ficha[dificultad.getNumCasillas()]);
		if (primeraMitad) {
			posInicial=0;
			colorEscogido=0;
		}else {
			posInicial=(byte)(dificultad.getNumColores()/2);
			colorEscogido=4;
		}
		if (dificultad.equals(Dificultad.FACIL_COMPROBAR)) {
			switch (fase) {
				case 1:
					if (contadorRondas==0) {
						for (byte i=0;i<dificultad.getNumCasillas();i++) {
							combinacion.getCombinacion()[i]=new Ficha(dificultad.getColores()[i]);
						}
					}else if (coloresPosibles.size()==2) {
						for (byte i=0;i<dificultad.getNumCasillas();i++) {
							if (i<dificultad.getNumCasillas()/2)
								combinacion.getCombinacion()[i]=new Ficha(dificultad.getColores()[colorEscogido]);
							else
								combinacion.getCombinacion()[i]=new Ficha(coloresPosibles.get(0));
						}
					}else {
						contadorColores++;
						for (byte i=0;i<dificultad.getNumCasillas();i++) {
							if (i<dificultad.getNumCasillas()/2)
								combinacion.getCombinacion()[i]=new Ficha(dificultad.getColores()[posInicial]);
							else
								combinacion.getCombinacion()[i]=new Ficha(dificultad.getColores()[posInicial+contadorColores]);
						}
					}
					break;
				case 2:
					for (byte i=0;i<dificultad.getNumCasillas();i++) {
						combinacion.getCombinacion()[i]=new Ficha(coloresCorrectos.get(i));
					}
					break;
			}
		}else
			combinacion=colocarCombinacionRandom();
		return combinacion;
	}
	protected void comprobarCombinacion(Ronda ronda) {
		if (fase==1) {
			if (tablero.getRondas().getFirst().getResultadoByN().getCombinacion()[0]==null)
				comprobarCombinaciones((byte)0,ronda);
			else if (tablero.getRondas().getFirst().getResultadoByN().getCombinacion()[1]==null&&contadorRondas!=0) 
				comprobarCombinaciones((byte)1,ronda);
			else if (tablero.getRondas().getFirst().getResultadoByN().getCombinacion()[2]==null&&contadorRondas!=0) 
				comprobarCombinaciones((byte)2,ronda);
			else if (tablero.getRondas().getFirst().getResultadoByN().getCombinacion()[3]==null&&contadorRondas!=0)
				comprobarCombinaciones((byte)3,ronda);
			else if (tablero.getRondas().getFirst().getResultadoByN().getCombinacion()[3]!=null)
				comprobarCombinaciones((byte)4,ronda);
			if (coloresCorrectos.size()==dificultad.getNumCasillas()) {
				combinacionesGuardadas.clear();
				contadorColores=0;
				fase++;
			}
		}else if (fase==2) {
			
		}
		contadorRondas++;
	}
}
