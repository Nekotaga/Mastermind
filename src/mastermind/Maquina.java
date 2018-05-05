package mastermind;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.TreeMap;

/**
 * Esta clase almacena una máquina que es hija de Usuariol, al igual que la IA de esta máquina.
 * 
 * @author Nekotaga
 * @version 1.3
 * @since 1.0
 * @see Usuario
 * @see Jugador
 * @see Ronda
 * @see Combinacion
 */
public class Maquina extends Usuario{
	
	//Variables
	/**
	 * Almacena un número que indica la fase de la IA por la que va la máquina.
	 * Forma parte de la IA.
	 */
	private byte fase;
	/**
	 * Almacena un número que cuenta las rondas que lleva jugando la máquina.
	 * Forma parte de la IA.
	 */
	private byte contadorRondas;
	/**
	 * Almacena un número que cuenta los colores que ha colocado la máquina.
	 * Forma parte de la IA.
	 */
	private byte contadorColores;
	/**
	 * Almacena un número que cuenta los colores que la máquina ha colocado ya.
	 * Forma parte de la IA.
	 * @see Color
	 */
	private byte coloresRellenos;
	/**
	 * Almacena el número de cambios en la combinación propuesta dentro de la {@link #fase} 2.
	 * Forma parte de la IA.
	 * @see Color
	 */
	private byte numCambiosFase2;
	/**
	 * Indica si está comprobando un color de la primera mitad de la combinacion o de la segunda.
	 * Forma parte de la IA.
	 * @see Combinacion
	 */
	private boolean primeraMitad;
	/**
	 * Almacena una combinación a la que se le van colocando los {@link #coloresCorrectos} según aparezcan.
	 * Forma parte de la IA.
	 * @see Combinacion
	 * @see Color
	 */
	private Combinacion combinacionCorrecta;
	/**
	 * Almacena una colección con los {@link #coloresCorrectos} junto a sus posiciones relativas con respecto a la combinacion a adivinar.
	 * Forma parte de la IA.
	 * @see Combinacion
	 * @see Color
	 */
	private TreeMap<Byte,Color> mapaColoresFacil;
	/**
	 * Almacena una colección con los {@link #coloresCorrectos} junto a sus posiciones relativas con respecto a la combinacion a adivinar.
	 * Forma parte de la IA.
	 * @see Combinacion
	 * @see Color
	 */
	private TreeMap<Color,Boolean[]> mapaColores;
	/**
	 * Almacena una colección con los colores que puede que sean {@link #coloresCorrectos} en ocasiones en las que sea necesarios almacenarlos aparte.
	 * Forma parte de la IA.
	 * @see Combinacion
	 * @see Color
	 */
	private ArrayList<Color> coloresPosibles;
	/**
	 * Almacena una colección con los colores que definitivamente aparecen en la combinación a adivinar.
	 * Forma parte de la IA.
	 * @see Combinacion
	 * @see Color
	 */
	private ArrayList<Color> coloresCorrectos;
	/**
	 * Almacena una colección con los colores que definitivamente no aparecen en la combinación a adivinar.
	 * Forma parte de la IA.
	 * @see Combinacion
	 * @see Color
	 */
	private ArrayList<Color> coloresIncorrectos;
	/**
	 * Almacena una colección con las combinaciones de colores que sea necesario guardar.
	 * Forma parte de la IA.
	 * @see Combinacion
	 * @see Color
	 */
	private ArrayList<Combinacion> combinacionesGuardadas;
	
	//Constructor
	/**
	 * Construye un nuevo objeto Maquina hijo de Usuario e inicializa sus variables.
	 * @param tiene 		Indica si la máquina va a colocar o no una combinacion original.
	 * @param dificultad	La dificultad de la partida.
	 * @see Usuario
	 * @see Dificultad
	 */
	Maquina(boolean tiene,Dificultad dificultad){
		super(tiene,dificultad);
		crearMaquina();
		if (tiene)
			combinacionOriginal=colocarCombinacionRandom();
	}
	
	//Métodos
	/**
	 * Inicializa la máquina dando valores a sus variables.
	 */
	private void crearMaquina() {
		fase = 1;
		contadorRondas = 0;
		coloresRellenos = 0;
		contadorColores = 0;
		numCambiosFase2 = 0;
		primeraMitad = true;
		combinacionCorrecta = new Combinacion(new Ficha[dificultad.getNumCasillas()]);
		mapaColores = new TreeMap<Color,Boolean[]>();
		mapaColoresFacil = new TreeMap<Byte,Color>();
		coloresPosibles = new ArrayList<Color>();
		coloresCorrectos = new ArrayList<Color>();
		coloresIncorrectos = new ArrayList<Color>();
		combinacionesGuardadas = new ArrayList<Combinacion>();
	}
	/**
	 * Crea una combinación random.
	 * @return Una combinación random.
	 * @see Combinacion
	 */
	private Combinacion colocarCombinacionRandom() {
		byte numero;
		boolean colorActual;
		boolean colorRepetido;
		boolean almacenarColores[]=new boolean[dificultad.getNumColores()];
		Combinacion combinacion=new Combinacion(new Ficha[dificultad.getNumCasillas()]);
		Random rnd=new Random();
		for (byte i=0;i<dificultad.getNumCasillas();i++) {		// Recorremos la combinación
			if (!dificultad.isRepeticionColores()) {
				do {
					colorRepetido=false;									//---- Comprobamos que el
					numero=(byte)rnd.nextInt(dificultad.getColores().length);	// color no salga repetido
					colorActual=almacenarColores[numero];						// si no se permite la
					if (colorActual)											// repetición de colores.
						colorRepetido=true;										//
					else														//
						almacenarColores[numero]=true;							//
				}while (colorRepetido);											// ---------¬¬
				combinacion.getCombinacion()[i]=new Ficha(dificultad.getColores()[numero]);	// Creamos la ficha
			}else 	// Si la repetición de colores se permite simplemente creamos la ficha
				combinacion.getCombinacion()[i]=new Ficha(dificultad.getColores()[rnd.nextInt(dificultad.getColores().length)]);
		}
		return combinacion;
	}
	/**
	 * Cuenta la suma de fichas blancas y negras de la combinación resultante de comparar una combinación propuesta con la original.
	 * Forma parte de la IA.
	 * @param combinacionByN	La combinación de fichas blancas y negras.
	 * @return					La suma de fichas blancas y negras.
	 * @see Combinacion
	 * @see Ficha
	 */
	private byte contarFichasByN(Combinacion combinacionByN) {
		byte contadorFichas=0;
		for (Ficha f:combinacionByN.getCombinacion())
			if (f!=null)	// Mientras que no se encuentre una ficha null se aumenta el contador
				contadorFichas++;
		return contadorFichas;
	}
	/**
	 * Averigua los {@link #coloresCorrectos} de una combinacion.
	 * Forma parte de la IA.
	 * @param fichasNoVacias	El resultado de {@link #contarFichasByN(Combinacion)} de la combinación de la primera ronda.
	 * @param ronda				La ronda de la que averiguar los colores correctos.
	 * @see Color
	 * @see Ronda
	 * @see Combinacion
	 * @see #contarFichasByN(Combinacion)
	 */
	private void comprobarColores(byte fichasNoVacias,Ronda ronda) {
		byte inicioBucle=0;
		byte finBucle=0;
		byte numFichasByN=0;
		byte coloresRellenosActuales=0;
		Color colorIzq;
		Color colorDer;
		Color ultimoPropuesto=combinacionPropuesta.getCombinacion()[dificultad.getNumCasillas()-1].getColorFicha();
		Color penultimoDisponible=dificultad.getColores()[dificultad.getNumColores()-2];
		Combinacion combinacionByN;
		LinkedList<Ronda> rondas = tablero.getRondas();
		ArrayList<Color> coloresCorrIncor1 = new ArrayList<Color>();
		ArrayList<Color> coloresCorrIncor2 = new ArrayList<Color>();
		Boolean arrayPosicionColores[]=new Boolean[dificultad.getNumCasillas()];
		if (dificultad==Dificultad.FACIL_COMPROBAR) {
			combinacionByN = rondas.getLast().getResultadoByN();
			colorIzq=ronda.getCombinacionPropuesta().getCombinacion()[0].getColorFicha();
			colorDer=ronda.getCombinacionPropuesta().getCombinacion()[dificultad.getNumCasillas()-1].getColorFicha();
			if (fichasNoVacias==0||fichasNoVacias==1&&!primeraMitad||fichasNoVacias==2&&!primeraMitad||fichasNoVacias==3&&!primeraMitad) {	// Desde aqui...
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
			}															// ...hasta aquí se inicializan las variables según el resultado de la ronda inicial
//=========================================================================================================================================================
			if (fichasNoVacias==0||fichasNoVacias==4) {		// Si la primera combinación da 0 o 4, se sabrán los colores correctos en la primera ronda
				for (byte i=inicioBucle;i<finBucle;i++)
					coloresCorrectos.add(dificultad.getColores()[i]);
				for (Color color:dificultad.getColores())
					if (!coloresCorrectos.contains(color))
						coloresIncorrectos.add(color);
			//-----------------------------------------------------------------------------------------------------------------------------------------
			}else if (fichasNoVacias==1||fichasNoVacias==3) {		// Si la primera combinación da 1 o 3, se sabrán los colores correctos en la quinta ronda, dos rondas por mitad
				if (coloresPosibles.size()==2) {		// Si hay 2 colores apuntados como posibles, comprueba si el que está colocado de ellos es correcto o incorrecto
					if(contarFichasByN(combinacionByN)==numFichasByN) {
						coloresCorrIncor1.add(coloresPosibles.get(1));
						coloresCorrIncor2.add(coloresPosibles.get(0));
					}else {
						coloresCorrIncor1.add(coloresPosibles.get(0));
						coloresCorrIncor2.add(coloresPosibles.get(1));
					}
				}else if(contarFichasByN(combinacionByN)==numFichasByN) { 	// Si no los hay pero la combinación tiene las mismas fichas blancas y negras que el número base, se apuntan en correctos o incorrectos según el caso
					coloresCorrIncor2.add(colorDer);
					if (!coloresCorrIncor2.contains(colorIzq)) {
						coloresCorrIncor2.add(colorIzq);
					}
					if (combinacionesGuardadas.size()==0) {		// Si no hay combinaciones guardadas y el tipo de color actual (correcto o incorrecto) no contiene al color...
						for (byte i=inicioBucle;i<finBucle;i++) {
							if (!coloresCorrIncor2.contains(dificultad.getColores()[i]))
								coloresPosibles.add(dificultad.getColores()[i]);	// ...se apuntan en colores posibles los que falten de la mitad comprobada
						}
					}else if (combinacionesGuardadas.size()==1) {	// Si hay una combinación guardada, se añaden los colores al tipo de color actual (correcto o incorrecto)
						coloresCorrIncor1.add(combinacionesGuardadas.get(0).getCombinacion()[dificultad.getNumCasillas()-1].getColorFicha());
						for (byte i=inicioBucle;i<finBucle;i++) {
							if (!coloresCorrIncor1.contains(dificultad.getColores()[i])&&!coloresCorrIncor2.contains(dificultad.getColores()[i]))
								coloresCorrIncor2.add(dificultad.getColores()[i]);		// Se añaden a los colores al tipo de color actual (correcto o incorrecto) los que falten de la mitad comprobada
						}
					}
				}else {		// Si no es ninguno de los casos anteriores...
					combinacionesGuardadas.add(combinacionPropuesta);	// ...se añade la combinación propuesta a las guardadas...
					if (combinacionesGuardadas.size()==2) {		// ...y en el caso de que ya haya 2, ya se añaden todos los colores al tipo de color actual (correcto o incorrecto)
						coloresCorrIncor1.add(dificultad.getColores()[0+inicioBucle]);
						coloresCorrIncor2.add(dificultad.getColores()[1+inicioBucle]);
						coloresCorrIncor2.add(dificultad.getColores()[2+inicioBucle]);
						coloresCorrIncor2.add(dificultad.getColores()[3+inicioBucle]);
					}
				}
				if (primeraMitad&&coloresCorrIncor1.size()+coloresCorrIncor2.size()==4) {	// Si se completa la primera mitad, se resetea todo y comienza la comprobación de la segunda
					combinacionesGuardadas.clear();
					coloresPosibles.clear();
					primeraMitad=false;
					contadorColores=0;
				}
			//-----------------------------------------------------------------------------------------------------------------------------------------
			}else if (fichasNoVacias==2) {		// Si la primera combinación da 2, se sabrán los colores correctos entre la trecera y la septima ronda
				if (contadorRondas>0) {
					if (contarFichasByN(combinacionByN)==0) {	// Si la combinación son 2 colores o ninguno, ya sabrá los correctos y los incorrectos de esa mitad
						coloresCorrIncor1=coloresIncorrectos;
						coloresCorrIncor2=coloresCorrectos;
					}else if (contarFichasByN(combinacionByN)==2) {
						coloresCorrIncor1=coloresCorrectos;
						coloresCorrIncor2=coloresIncorrectos;
					}
					if (contarFichasByN(combinacionByN)==0||contarFichasByN(combinacionByN)==2) {	//---- En el caso de que alguna de
						coloresCorrIncor1.add(colorIzq);												// las 2 propiedades anteriores
						coloresCorrIncor1.add(colorDer);												// se activase, se apuntan los colores
						for (byte i=inicioBucle;i<finBucle;i++) {										// correctos e incorrectos de esa mitad.
							if (!coloresCorrIncor1.contains(dificultad.getColores()[i]))				//
								coloresCorrIncor2.add(dificultad.getColores()[i]);						//
						}																				//
						if (primeraMitad) {			// Tras hacerlo, si se está comprobando la primera mitad, se resetea el contador de colores y se pasa a la segunda
							primeraMitad=false;
							contadorColores=0;
						}
					}
				}
			}
		}else {
			coloresRellenosActuales=coloresRellenos;	// Guarda los colores rellenos de este punto
			coloresRellenos=contarFichasByN(ronda.getResultadoByN());	// Actualiza los colores rellenos
			if (coloresRellenos==coloresRellenosActuales)	// Los compara con antes para ver si el último color era correcto o no
				coloresIncorrectos.add(ultimoPropuesto);
			else {
				coloresCorrectos.add(ultimoPropuesto);
			}
			coloresPosibles.remove(ultimoPropuesto);	// Eliminamos ese color de los posibles
			for (byte i=coloresRellenosActuales;i<coloresRellenos;i++)
				combinacionCorrecta.getCombinacion()[i]=new Ficha(coloresCorrectos.get(coloresCorrectos.size()-1));	// Creamos poco a poco la combinación correcta
			if (dificultad.isRepeticionColores()&&coloresCorrectos.size()+coloresIncorrectos.size()==dificultad.getNumColores()-1&&ultimoPropuesto==penultimoDisponible) {	// Solo para el dificil
				for (byte i=coloresRellenos;i<dificultad.getNumCasillas();i++)
					combinacionCorrecta.getCombinacion()[i]=new Ficha(dificultad.getColores()[dificultad.getNumColores()-1]);
				for (byte i=contarFichasByN(ronda.getResultadoByN());i<dificultad.getNumCasillas();i++) {
					if (i==dificultad.getNumCasillas()-1)
						coloresCorrectos.add(dificultad.getColores()[dificultad.getNumColores()-1]);
					coloresRellenos++;
				}
			}else if (!dificultad.isRepeticionColores()&&coloresPosibles.size()+coloresCorrectos.size()==dificultad.getNumCasillas()) {	// Solo para el medio
				coloresRellenosActuales=coloresRellenos;
				for (Color color:coloresPosibles) {
					coloresCorrectos.add(color);
					coloresRellenos++;
				}
				for (byte i=coloresRellenosActuales;i<dificultad.getNumCasillas();i++)
					combinacionCorrecta.getCombinacion()[i]=new Ficha(coloresCorrectos.get(i));
			}
			if (coloresRellenos==dificultad.getNumCasillas()) {	// Si ya se ha completado, pasamos a la segunda fase
				for (byte i=0;i<dificultad.getNumCasillas();i++)
					arrayPosicionColores[i]=true;
				for (Ficha fco:combinacionPropuesta.getCombinacion())
					mapaColores.put(fco.getColorFicha(),arrayPosicionColores);
				fase++;
			}
		}
	}
	/**
	 * Comprueba las posiciones correctas de los {@link #coloresCorrectos} tras haberlos averiguado.
	 * Forma parte de la IA.
	 * @param primeraParte	Indica qué parte de la función va a realizarse.
	 * @see Color
	 * @see Combinacion
	 * @see #comprobarColores(byte, Ronda)
	 */
	private void comprobarPosiciones(boolean primeraParte) {
		byte contadorIzq;	// Posición en la que se meterá la siguiente ficha de la mitad izquierda
		byte contadorDer;	// Posición en la que se meterá la siguiente ficha de la mitad derecha
		boolean apareceIzq;
		boolean yaAsignado;
		Color coloresIzq[] = {mapaColoresFacil.get((byte)0),mapaColoresFacil.get((byte)1)};
		Color coloresDer[] = {mapaColoresFacil.get((byte)2),mapaColoresFacil.get((byte)3)};
		Ronda ronda;
		Combinacion combinacionByN;
		for (byte contador=0;contador<2;contador++) {	// Lo hacemos 2 veces para que compruebe tanto segun el primer color como el segundo y no repita una ronda
			contadorIzq=0;
			contadorDer=(byte)(dificultad.getNumCasillas()/2);
			for (Color color:coloresCorrectos) {	// Se recorre el array de colores correctos
				if (primeraParte) {		// Si esta en la primera parte
					yaAsignado=false;		// Se resetea la variable
					for (byte i=1;i<tablero.getRondas().size()&&!yaAsignado;i++) {		// Se recorren las rondas
						ronda=tablero.getRondas().get(i);		// Se coge cada ronda
						for (byte j=0;j<ronda.getCombinacionPropuesta().getCombinacion().length&&!yaAsignado;j++) {		// Mientras aún el color no esté asignado a ninguna mitad, se recorre la ronda
							if (ronda.getCombinacionPropuesta().getCombinacion()[j].getColorFicha()==color) {		// Se comprueba si el color se encuentra en alguna ronda
								combinacionByN=ronda.getResultadoByN();
								if (j<dificultad.getNumCasillas()/2)	//---- Se mira si aparece
									apareceIzq=true;						// en la izquierda
								else										// o en la derecha
									apareceIzq=false;						// de la ronda
								if (contarFichasByN(combinacionByN)==1||combinacionByN.getCombinacion()[0].equals(combinacionByN.getCombinacion()[1])) {	// Cubre 2 del mismo color y 1 con nulo
									if (combinacionByN.getCombinacion()[0].getColorFicha()==Color.NEGRO&&apareceIzq||combinacionByN.getCombinacion()[0].getColorFicha()==Color.BLANCO&&!apareceIzq) {	//---- Si el color es negro
										mapaColoresFacil.put(contadorIzq,color);																																	// y aparece en la
										coloresIzq[contadorIzq]=color;																																		// izquierda y viceversa
										contadorIzq++;																																						// (blanco derecha).
									}else {																//---- Si el color es negro
										mapaColoresFacil.put(contadorDer,color);									// y aparece en la
										coloresDer[contadorDer-(dificultad.getNumCasillas()/2)]=color;		// derecha y viceversa
										contadorDer++;														// (blanco izquierda).
									}
									yaAsignado=true;
								}else if (contarFichasByN(combinacionByN)==2) {		// Cubre 2 de distinto color si 1 ha aparecido antes
									for (Color c:coloresIzq) {	// Si el compañeo está en la izquierda
										if (c==ronda.getCombinacionPropuesta().getCombinacion()[0].getColorFicha()){
											mapaColoresFacil.put(contadorIzq,color);
											coloresIzq[contadorIzq]=color;
											contadorIzq++;
											yaAsignado=true;
										}
									}										
									if (!yaAsignado){	// Si no se ha asignado aún se comprueba esto
										for (Color c:coloresDer) {	// Si el compañero está en la derecha
											if (c==ronda.getCombinacionPropuesta().getCombinacion()[(dificultad.getNumCasillas()/2)-1].getColorFicha()){
												mapaColoresFacil.put(contadorDer,color);
												coloresDer[contadorDer-(dificultad.getNumCasillas()/2)]=color;
												contadorDer++;
												yaAsignado=true;
											}
										}
									}
								}
							}
						}
					}
				}else {
					if (!mapaColoresFacil.containsValue(color)) {	// Si aún no se ha asignado a ninguna posición se comprueba de qué posición es siempre que sea posible y si no se consiguen rellenar todas las posiciones, se hace otra ronda
						if (mapaColoresFacil.containsKey((byte)0)&&mapaColoresFacil.containsKey((byte)1)) {
							if (mapaColoresFacil.containsKey((byte)2))
								mapaColoresFacil.put((byte)3,color);
							else
								mapaColoresFacil.put((byte)2,color);
						}else if (mapaColoresFacil.containsKey((byte)2)&&mapaColoresFacil.containsKey((byte)3)) {
							if (mapaColoresFacil.containsKey((byte)0))
								mapaColoresFacil.put((byte)1,color);
							else
								mapaColoresFacil.put((byte)0,color);
						}
					}
				}
			}
		}
	}
	
	//Métodos Heredados
	/**
	 * Coloca una combinación bien para que la adivine el usuario contrario o para intentar adivinar la suya.
	 * Forma parte de la IA.
	 * @see Combinacion
	 * @see Usuario
	 */
	protected Combinacion colocarCombinacion() {
		byte posInicial;
		byte colorEscogido;
		Combinacion combinacion = new Combinacion(new Ficha[dificultad.getNumCasillas()]);
		if (dificultad.equals(Dificultad.FACIL_COMPROBAR)) {
			if (primeraMitad) {
				posInicial=0;
				colorEscogido=0;
			}else {
				posInicial=(byte)(dificultad.getNumColores()/2);
				colorEscogido=4;
			}
			switch (fase) {
				case 1:
					for (byte i=0;i<dificultad.getNumCasillas();i++) {
						if (contadorRondas==0) {																			//---- Primera Combinacion
							combinacion.getCombinacion()[i]=new Ficha(dificultad.getColores()[i]);								//
						}else if (coloresCorrectos.size()==dificultad.getNumCasillas()&&mapaColoresFacil.size()==0){		//---- Si ya se saben los
							if (i<dificultad.getNumCasillas()/2)																// colores correctos pero
								combinacion.getCombinacion()[i]=new Ficha(coloresCorrectos.get(0));								// no se sabe la posición
							else																								// relativa de ninguno
								combinacion.getCombinacion()[i]=new Ficha(coloresIncorrectos.get(0));							//
						}else if (coloresCorrectos.size()==dificultad.getNumCasillas()){									//---- Si ya se saben los
							if (i==0)																							// colores correctos y
								contadorColores++;																				// se sabe la posición
							if (i<dificultad.getNumCasillas()/2) {																// relativa de al menos 1
								if (mapaColoresFacil.containsKey((byte)0))														//
									combinacion.getCombinacion()[i]=new Ficha(mapaColoresFacil.get((byte)0));					//
								else																							//
									combinacion.getCombinacion()[i]=new Ficha(mapaColoresFacil.get((byte)2));					//
							}else {																								//
								while (mapaColoresFacil.containsValue(coloresCorrectos.get(contadorColores)))					//
									contadorColores++;																			//
									combinacion.getCombinacion()[i]=new Ficha(coloresCorrectos.get(contadorColores));			//
							}																									//
						}else if (coloresPosibles.size()==2) {																//---- Aún no se saben todos
							if (i<dificultad.getNumCasillas()/2)																// los colores correctos
								combinacion.getCombinacion()[i]=new Ficha(dificultad.getColores()[colorEscogido]);				// pero si se sabe cuales
							else																								// son 2 de los posibles
								combinacion.getCombinacion()[i]=new Ficha(coloresPosibles.get(0));								//
						}else {																								//---- Aún no se saben todos
							if (i==0)																							// los colores correctos
								contadorColores++;																				// y tampoco hay solo 2
							if (i<dificultad.getNumCasillas()/2)																// colores posibles
								combinacion.getCombinacion()[i]=new Ficha(dificultad.getColores()[posInicial]);					//
							else																								//
								combinacion.getCombinacion()[i]=new Ficha(dificultad.getColores()[posInicial+contadorColores]);	//
						}
					}
					break;
				case 2:
					Ficha fichasMixtas[] = {new Ficha(Color.NEGRO),new Ficha(Color.NEGRO),new Ficha(Color.BLANCO),new Ficha(Color.BLANCO)};
					Ficha fichasBlancas[] = {new Ficha(Color.BLANCO),new Ficha(Color.BLANCO),new Ficha(Color.BLANCO),new Ficha(Color.BLANCO)};
					if (numCambiosFase2==0) {	// Colocamos combinaciones para ordenar los colores en sus mitades, solo se necesitan 3 combinaciones máximo para adivinar la combinación llegados a este punto
						for (byte i=0;i<mapaColoresFacil.size();i++)
							combinacion.getCombinacion()[i]=new Ficha(mapaColoresFacil.get(i));
					}else if (numCambiosFase2==1&&tablero.getRondas().getLast().getResultadoByN().equals(new Combinacion(fichasMixtas))) {
						combinacion.getCombinacion()[0]=new Ficha(mapaColoresFacil.get((byte)1));
						combinacion.getCombinacion()[1]=new Ficha(mapaColoresFacil.get((byte)0));
						combinacion.getCombinacion()[2]=new Ficha(mapaColoresFacil.get((byte)2));
						combinacion.getCombinacion()[3]=new Ficha(mapaColoresFacil.get((byte)3));
					}else if (numCambiosFase2==1&&tablero.getRondas().getLast().getResultadoByN().equals(new Combinacion(fichasBlancas))) {
						combinacion.getCombinacion()[0]=new Ficha(mapaColoresFacil.get((byte)1));
						combinacion.getCombinacion()[1]=new Ficha(mapaColoresFacil.get((byte)0));
						combinacion.getCombinacion()[2]=new Ficha(mapaColoresFacil.get((byte)3));
						combinacion.getCombinacion()[3]=new Ficha(mapaColoresFacil.get((byte)2));
					}else if (numCambiosFase2==2) {
						combinacion.getCombinacion()[0]=new Ficha(mapaColoresFacil.get((byte)0));
						combinacion.getCombinacion()[1]=new Ficha(mapaColoresFacil.get((byte)1));
						combinacion.getCombinacion()[2]=new Ficha(mapaColoresFacil.get((byte)3));
						combinacion.getCombinacion()[3]=new Ficha(mapaColoresFacil.get((byte)2));
					}
					break;
			}
		}else
			if (fase==1){		// Se averiguan los colores
				for (byte i=0;i<coloresRellenos;i++)
					combinacion.getCombinacion()[i]=combinacionPropuesta.getCombinacion()[i];
				for (byte i=coloresRellenos;i<dificultad.getNumCasillas();i++)
					combinacion.getCombinacion()[i]=new Ficha(dificultad.getColores()[contadorColores]);
				contadorColores++;
			}else {			// Se averiguan las posiciones
				combinacion=combinacionCorrecta;
			}
		return combinacion;
	}
	/**
	 * Compara su combinacion propuesta con la del usuario contrincante para comprobar las fichas que tiene correctas y sus posiciones.
	 * Forma parte de la IA.
	 * @param ronda		La ronda de la que se quiere comprobar la combinacion.
	 * @see Ronda
	 * @see Usuario
	 */
	protected void comprobarCombinacion(Ronda ronda) {
		boolean posicionesComprobadas=false;
		Combinacion combinacionByN_inicial;
		if (dificultad.equals(Dificultad.FACIL_COMPROBAR)) {
			combinacionByN_inicial=tablero.getRondas().getFirst().getResultadoByN();
			if (fase==1) {
				if (coloresCorrectos.size()!=dificultad.getNumCasillas()) {		// Comprueba la cantidad de fichas de la primera combinación de la ronda y según ella entra en unaa ejecución u otra
					if (contarFichasByN(combinacionByN_inicial)==0)
						comprobarColores((byte)0,ronda);
					else if (contarFichasByN(combinacionByN_inicial)==1&&contadorRondas!=0) 
						comprobarColores((byte)1,ronda);
					else if (contarFichasByN(combinacionByN_inicial)==2&&contadorRondas!=0) 
						comprobarColores((byte)2,ronda);
					else if (contarFichasByN(combinacionByN_inicial)==3&&contadorRondas!=0)
						comprobarColores((byte)3,ronda);
					else if (contarFichasByN(combinacionByN_inicial)==4)
						comprobarColores((byte)4,ronda);
				}
				if (coloresCorrectos.size()==dificultad.getNumCasillas()) {		// Aquí entra solo cuando ya sabe los colores que son, para así comprobar sus posiciones
					combinacionesGuardadas.clear();			// Borra las guardadas de los posibles, ya que no nos hacen ya falta
					contadorColores=0;		// El contador de colores lo resetea también
					if (!posicionesComprobadas) {		// Comprueba la primera parte de las posiciones
						comprobarPosiciones(!posicionesComprobadas);
						posicionesComprobadas=true;
					}
					comprobarPosiciones(!posicionesComprobadas);		// Comprueba la segunda parte de las posiciones
					if (mapaColoresFacil.size()==dificultad.getNumCasillas())		// Si ya sabe las mitades en las que van todas las fichas correctas, pasa a la segunda fase
						fase++;
				}
			}else
				numCambiosFase2++;		// Contador que aumenta en cada ronda
		}else {
			if (fase==1) {
				if (contadorRondas==0)
					for (Color color:dificultad.getColores())
						coloresPosibles.add(color);		// Se añaden todos los colores a posibles
				comprobarColores((byte)0,ronda);	// Se comprueban los colores
			}else {
				
			}
		}
		contadorRondas++;		// Aumentamos el contador de rondas
	}
}
