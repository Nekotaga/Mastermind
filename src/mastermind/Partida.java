package mastermind;

import static utilidades.Teclado.limpiarTeclado;

/**
 * Esta clase almacena los usuarios que desarrollan la partida declarada por ConfigurarPartida y su resultado.
 * 
 * @author Nekotaga
 * @version 1.3
 * @since 1.0
 * @see Usuario
 * @see ConfigurarPartida
 * @see Resultado
 */
public class Partida implements DibujablePartida {
	
	//Variables
	/**
	 * Almacena la dificultad determinada por ConfigurarPartida.
	 * @see ConfigurarPartida
	 * @see Dificultad
	 */
	private Dificultad dificultad;
	/**
	 * Almacena los datos del Usuario tipo Jugador.
	 * @see Usuario
	 * @see Jugador
	 */
	private Jugador jugador;
	/**
	 * Almacena los datos del primer Usuario tipo Maquina.
	 * @see Usuario
	 * @see Maquina
	 */
	private Maquina maquina1;
	/**
	 * Almacena los datos del segundo Usuario tipo Maquina.
	 * @see Usuario
	 * @see Maquina
	 */
	private Maquina maquina2;
	/**
	 * Almacena la cadena correspondiente al resultado de la partida.
	 * @see Resultado 
	 */
	private String resultadoPartida;
	
	//Constructor
	/**
	 * Construye un objeto Partida a partir de la dificultad.
	 * @param dificultad	La dificultad de la partida.
	 * @see Dificultad
	 */
	Partida(Dificultad dificultad){
		this.dificultad=dificultad;
	}
	
	//Métodos
	/**
	 * Desarrolla una partida de dificultad fácil.
	 * @see Dificultad
	 */
	private void partidaFacil() {
		Ronda ronda;
		boolean resultado=false;
		if (dificultad==Dificultad.FACIL_ADIVINAR) {
			jugador = new Jugador(false,dificultad);
			maquina1 = new Maquina(true,dificultad);
			jugador.getTablero().setCombinacionOriginal(maquina1.getCombinacionOriginal());			// El jugador pone la combinación original en el tablero de la máquina
		}else {
			jugador = new Jugador(true,dificultad);
			maquina1 = new Maquina(false,dificultad);
			maquina1.getTablero().setCombinacionOriginal(jugador.getCombinacionOriginal());			// La máquina pone la combinación original en el tablero del jugador
		}
		for (byte contadorRondas=0;contadorRondas<dificultad.getNumIntentos()&&!resultado;contadorRondas++) {
			if (dificultad==Dificultad.FACIL_ADIVINAR) {
				jugador.setCombinacionPropuesta(jugador.colocarCombinacion());						// El jugador coloca una combinación para adivinar la combinación de la máquina
				ronda = new Ronda(contadorRondas,maquina1.getCombinacionOriginal(),jugador.getCombinacionPropuesta());	// Se crea la ronda del jugador a partir de la combinación original y la propuesta en este turno por el jugador
				jugador.getTablero().agregarRonda(ronda);											// Agregamos la ronda a la coleción de rondas del tablero del jugador
				if (maquina1.getCombinacionOriginal().equals(jugador.getCombinacionPropuesta()))	// Comprobamos si la combinación dada por el jugador es igual a la original
					resultado=true;
				jugador.getTablero().mostrarTableroPropio();										// Mostramos el tablero del jugador desde la perspectiva del jugador
			}else {
				maquina1.setCombinacionPropuesta(maquina1.colocarCombinacion());					// La máquina coloca una combinación para adivinar la combinación del jugador
				ronda = new Ronda(contadorRondas,jugador.getCombinacionOriginal(),maquina1.getCombinacionPropuesta());	// Se crea la ronda de la máquina a partir de la combinación original y la propuesta en este turno por la máquina
				maquina1.getTablero().agregarRonda(ronda);											// Agregamos la ronda a la coleción de rondas del tablero de la máquina
				maquina1.comprobarCombinacion(ronda);
				if (jugador.getCombinacionOriginal().equals(maquina1.getCombinacionPropuesta()))	// Comprobamos si la combinación dada por la máquina es igual a la original
					resultado=true;
				maquina1.getTablero().mostrarTableroAjeno();										// Mostramos el tablero de la máquina desde la perspectiva del jugador
				jugador.comprobarCombinacion(ronda);												// El jugador comprueba si la combinación de la máquina es correcta
			}
			
		}
		if (resultado&&dificultad==Dificultad.FACIL_ADIVINAR||!resultado&&dificultad==Dificultad.FACIL_COMPROBAR)
			resultadoPartida=Resultado.VICTORIA.getMensaje();
		else
			resultadoPartida=Resultado.DERROTA.getMensaje();
	}
	/**
	 * Desarrolla una partida de dificultad media.
	 * @see Dificultad
	 */
	private void partidaMedio() {
		Ronda rondaJug;
		Ronda rondaMaq;
		boolean resultadoJug=false;
		boolean resultadoMaq=false;
		System.out.println("Coloque la combinación que tendrá que averiguar la máquina");
		jugador = new Jugador(true,dificultad);
		maquina1 = new Maquina(true,dificultad);
		jugador.getTablero().setCombinacionOriginal(maquina1.getCombinacionOriginal());			// El jugador pone la combinación original en el tablero de la máquina
		maquina1.getTablero().setCombinacionOriginal(jugador.getCombinacionOriginal());			// La máquina pone la combinación original en el tablero del jugador
		for (byte contadorRondas=0;contadorRondas<dificultad.getNumIntentos()&&!resultadoJug&&!resultadoMaq;contadorRondas++) {
			//Jugador
			System.out.println("Coloque una combinación para intentar averiguar la de la máquina");
			jugador.setCombinacionPropuesta(jugador.colocarCombinacion());						// El jugador coloca una combinación para adivinar la combinación de la máquina
			rondaJug = new Ronda(contadorRondas,maquina1.getCombinacionOriginal(),jugador.getCombinacionPropuesta());	// Se crea la ronda del jugador a partir de la combinación original y la propuesta en este turno por el jugador
			jugador.getTablero().agregarRonda(rondaJug);										// Agregamos la ronda a la colección de rondas del tablero del jugador
			if (maquina1.getCombinacionOriginal().equals(jugador.getCombinacionPropuesta()))	// Comprobamos si la combinación dada por el jugador es igual a la original
				resultadoJug=true;
			System.out.println("                  "+Color.AZUL.getColorFondo()+Color.BLANCO.getColor()+" TABLERO DEL JUGADOR "+Constantes.RESET);
			jugador.getTablero().mostrarTableroPropio();										// Mostramos el tablero del jugador desde la perspectiva del jugador
			limpiarTeclado();
			//Máquina
			maquina1.setCombinacionPropuesta(maquina1.colocarCombinacion());					// La máquina coloca una combinación para adivinar la combinación del jugador
			rondaMaq = new Ronda(contadorRondas,jugador.getCombinacionOriginal(),maquina1.getCombinacionPropuesta());	// Se crea la ronda de la máquina a partir de la combinación original y la propuesta en este turno por la máquina
			maquina1.comprobarCombinacion(rondaMaq);											// La máquina hace comprobaciones con respecto a su combinacion
			maquina1.getTablero().agregarRonda(rondaMaq);										// Agregamos la ronda a la colección de rondas del tablero de la máquina
			if (jugador.getCombinacionOriginal().equals(maquina1.getCombinacionPropuesta()))	// Comprobamos si la combinación dada por la máquina es igual a la original
				resultadoMaq=true;
			System.out.println("                  "+Color.AZUL.getColorFondo()+Color.BLANCO.getColor()+"TABLERO DE LA MAQUINA"+Constantes.RESET);
			maquina1.getTablero().mostrarTableroAjeno();										// Mostramos el tablero de la máquina desde la perspectiva del jugador
			jugador.comprobarCombinacion(rondaMaq);												// El jugador comprueba si la combinación de la máquina es correcta
		}
		if (resultadoJug&&resultadoMaq)
			resultadoPartida=Resultado.EMPATE.getMensaje();
		else if (resultadoJug)
			resultadoPartida=Resultado.VICTORIA.getMensaje();
		else if (resultadoMaq)
			resultadoPartida=Resultado.DERROTA.getMensaje();
	}
	/**
	 * Desarrolla una partida de dificultad difícil.
	 * @see Dificultad
	 */
	private void partidaDificil() {
		Ronda rondaMaq1;
		Ronda rondaMaq2;
		int contadorRondas=0;
		boolean resultadoMaq1=false;
		boolean resultadoMaq2=false;
		maquina1 = new Maquina(true,dificultad);
		maquina2 = new Maquina(true,dificultad);
		maquina1.getTablero().setCombinacionOriginal(maquina2.getCombinacionOriginal());		// La máquina 2 pone la combinación original en el tablero de la máquina 1
		maquina2.getTablero().setCombinacionOriginal(maquina1.getCombinacionOriginal());		// La máquina 1 pone la combinación original en el tablero de la máquina 2
		do{
			//Maquina 1
			maquina1.setCombinacionPropuesta(maquina1.colocarCombinacion());					// La máquina 1 coloca una combinación para adivinar la combinación de la máquina 2
			rondaMaq1 = new Ronda(contadorRondas,maquina2.getCombinacionOriginal(),maquina1.getCombinacionPropuesta());	// Se crea la ronda de la máquina 1 a partir de la combinación original y la propuesta en este turno por la máquina 2
			maquina1.comprobarCombinacion(rondaMaq1);											// La máquina 1 hace comprobaciones con respecto a su combinacion
			maquina1.getTablero().agregarRonda(rondaMaq1);										// Agregamos la ronda a la colección de rondas del tablero de la máquina 1
			if (maquina2.getCombinacionOriginal().equals(maquina1.getCombinacionPropuesta()))	// Comprobamos si la combinación dada por la máquina 1 es igual a la original
				resultadoMaq1=true;
			//Máquina 2
			maquina2.setCombinacionPropuesta(maquina2.colocarCombinacion());					// La máquina 2 coloca una combinación para adivinar la combinación de la máquina 1
			rondaMaq2 = new Ronda(contadorRondas,maquina1.getCombinacionOriginal(),maquina2.getCombinacionPropuesta());	// Se crea la ronda de la máquina 2 a partir de la combinación original y la propuesta en este turno por la máquina 1
			maquina2.comprobarCombinacion(rondaMaq2);											// La máquina 2 hace comprobaciones con respecto a su combinacion
			maquina2.getTablero().agregarRonda(rondaMaq2);										// Agregamos la ronda a la colección de rondas del tablero de la máquina 2
			if (maquina1.getCombinacionOriginal().equals(maquina2.getCombinacionPropuesta()))	// Comprobamos si la combinación dada por la máquina 2 es igual a la original
				resultadoMaq2=true;
			//Pasamos de ronda
			try {
				Thread.sleep(750);		// Milisegundos que tarda en pasar de ronda
			}catch (InterruptedException e){
				System.out.println();
			}
			if (contadorRondas%10==0||contadorRondas==0) {
				maquina1.getTablero().mostrarTableroDificil(true,true);		// Mostramos la combinacion original cada 10 turnos
				maquina2.getTablero().mostrarTableroDificil(true,false);		// Mostramos la combinacion original cada 10 turnos
			}
			maquina1.getTablero().mostrarTableroDificil(false,true);		// Mostramos la última combinación de la máquina 1 que se ha realizado en la partida
			maquina2.getTablero().mostrarTableroDificil(false,false);		// Mostramos la última combinación de la máquina 2 que se ha realizado en la partida
			contadorRondas++;		// Aumentamos el contador
		}while(!resultadoMaq1&&!resultadoMaq2);
		if (resultadoMaq1&&resultadoMaq2)
			resultadoPartida=Resultado.EMPATE.getMensaje();
		else if (resultadoMaq1)
			resultadoPartida=Resultado.VICTORIA_MAQ.getMensaje()+" 1"+Constantes.RESET;
		else if (resultadoMaq2)
			resultadoPartida=Resultado.VICTORIA_MAQ.getMensaje()+" 2"+Constantes.RESET;
	}
	
	//Métodos de Interfaz
	/**
	 * Muestra la partida en curso.
	 * @see DibujablePartida
	 */
	public void mostrarPartida(){
		System.out.printf("Datos de la partida:\n - Dificultad: %s",dificultad.toString());
		System.out.println(" - Colores permitidos:\n");
		for(byte i=0;i<dificultad.getNumColores();i++)
			System.out.printf("%s  > %-15s%s%23s\n",dificultad.getColores()[i].getColor(),dificultad.getColores()[i].toString(),dificultad.getColores()[i].getColorFondo(),Constantes.RESET);
		System.out.println();
		switch (dificultad) {
			case FACIL_ADIVINAR:
				partidaFacil();
				break;
			case FACIL_COMPROBAR:
				partidaFacil();
				break;
			case MEDIO:
				partidaMedio();
				break;
			case DIFICIL:
				partidaDificil();
				break;
		}
		System.out.printf("%s\n\n",resultadoPartida);
	}
}
