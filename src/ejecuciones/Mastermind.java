package ejecuciones;

import mastermind.ConfigurarPartida;
import static utilidades.Teclado.cerrarTeclado;

public class Mastermind {

	public static void main(String[] args) {
		
		ConfigurarPartida configuracion = new ConfigurarPartida();
		boolean finalizarJuego;
		
		//configuracion.mostrarRotulo();		// Muestra el rótulo del juego
		do {
			finalizarJuego=configuracion.mostrarMenu();		// Muestra el juego en sí
		}while(!finalizarJuego);
		cerrarTeclado();		// Cerramos el teclado para que no se pueda escribir más una vez el juego haya acabado
	}
}
