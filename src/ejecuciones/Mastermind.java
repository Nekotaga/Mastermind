package ejecuciones;

import mastermind.ConfigurarPartida;
import static utilidades.Teclado.cerrarTeclado;

public class Mastermind {

	public static void main(String[] args) {
		
		ConfigurarPartida configuracion = new ConfigurarPartida();
		boolean finalizarJuego;
		
		//configuracion.mostrarRotulo();
		do {
			finalizarJuego=configuracion.mostrarMenu();
		}while(!finalizarJuego);
		cerrarTeclado();
	}
}
