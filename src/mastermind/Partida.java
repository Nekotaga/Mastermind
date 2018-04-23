package mastermind;

import static utilidades.Teclado.limpiarTeclado;

public class Partida implements DibujablePartida {
	
	//Variables
	private Dificultad dificultad;
	private Jugador jugador;
	private Maquina maquina1;
	private Maquina maquina2;
	private String resultadoPartida;
	
	//Constructor
	Partida(Dificultad dificultad){
		this.dificultad=dificultad;
	}
	
	//Métodos
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
			maquina1.getTablero().agregarRonda(rondaMaq);										// Agregamos la ronda a la colección de rondas del tablero de la máquina
			if (jugador.getCombinacionOriginal().equals(maquina1.getCombinacionPropuesta()))	// Comprobamos si la combinación dada por la máquina es igual a la original
				resultadoMaq=true;
			System.out.println("                  "+Color.AZUL.getColorFondo()+Color.BLANCO.getColor()+"TABLERO DE LA MÁQUINA"+Constantes.RESET);
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
			maquina1.getTablero().agregarRonda(rondaMaq1);										// Agregamos la ronda a la colección de rondas del tablero de la máquina 1
			if (maquina2.getCombinacionOriginal().equals(maquina1.getCombinacionPropuesta()))	// Comprobamos si la combinación dada por la máquina 1 es igual a la original
				resultadoMaq1=true;
			//Máquina 2
			maquina2.setCombinacionPropuesta(maquina2.colocarCombinacion());					// La máquina 2 coloca una combinación para adivinar la combinación de la máquina 1
			rondaMaq2 = new Ronda(contadorRondas,maquina1.getCombinacionOriginal(),maquina2.getCombinacionPropuesta());	// Se crea la ronda de la máquina 2 a partir de la combinación original y la propuesta en este turno por la máquina 1
			maquina2.getTablero().agregarRonda(rondaMaq2);										// Agregamos la ronda a la colección de rondas del tablero de la máquina 2
			if (maquina1.getCombinacionOriginal().equals(maquina2.getCombinacionPropuesta()))	// Comprobamos si la combinación dada por la máquina 2 es igual a la original
				resultadoMaq2=true;
			//Pasamos de ronda
			contadorRondas++;
			System.out.println(contadorRondas);
		}while(!resultadoMaq1&&!resultadoMaq2);
		System.out.println("                                 "+Color.AZUL.getColorFondo()+Color.BLANCO.getColor()+"TABLERO DE LA MÁQUINA 1"+Constantes.RESET);
		maquina1.getTablero().mostrarTableroFinal();											// Mostramos la última combinación de la máquina 1 que se ha realizado en la partida
		System.out.println("                                 "+Color.AZUL.getColorFondo()+Color.BLANCO.getColor()+"TABLERO DE LA MÁQUINA 2"+Constantes.RESET);
		maquina2.getTablero().mostrarTableroFinal();											// Mostramos la última combinación de la máquina 2 que se ha realizado en la partida
		if (resultadoMaq1&&resultadoMaq2)
			resultadoPartida=Resultado.EMPATE.getMensaje();
		else if (resultadoMaq1)
			resultadoPartida=Resultado.VICTORIA_MAQ.getMensaje()+" 1"+Constantes.RESET;
		else if (resultadoMaq2)
			resultadoPartida=Resultado.VICTORIA_MAQ.getMensaje()+" 2"+Constantes.RESET;
	}
	
	//Métodos de Interfaz
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
