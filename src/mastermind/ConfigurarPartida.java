package mastermind;

import static utilidades.Teclado.leerEntre;

/**
 * Esta clase almacena la configuración para la partida que se vaya a realizar al igual que su dificultad.
 * 
 * @author Nekotaga
 * @version 1.2
 * @since 1.0
 * @see Partida
 * @see Dificultad
 * @see DibujableConfiguracion
 */
public class ConfigurarPartida implements DibujableConfiguracion{
	
	//Variables
	/**
	 * Almacena la dificultad de la partida a generar.
	 * @see Dificultad
	 */
	private Dificultad dificultad;
	/**
	 * Almacena la partida generada.
	 * @see Partida
	 */
	private Partida partida;
	
	//Métodos
	/**
	 * Muestra las reglas del juego.
	 */
	private void verReglas() {
		System.out.println("\n---------------------------------------------------------------------------------------------------------------"
				+ "\nREGLAS DEL JUEGO\n\n"
				+ "El MasterMind es un juego de lógica que consta de 2 jugadores.\n"
				+ "Hay 2 roles que se pueden tomar en este juego, el que coloca las combinación o el que la adivina.\n"
				+ "Según el modo de dificultad seleccionado habrá un número distinto de fichas por combinación, colores,\n"
				+ "jugadores que adivinan/colocan y además dictará si los colores pueden ser repetidos o no en las\n"
				+ "combinaciones a adivinar.\n"
				+ "Al comenzar la partida, el jugador que coloca las combinaciones colocará una combinación, la cual el\n"
				+ "oponente tendrá que adivinar en el número de intentos dado. Para hacer más fácil esta tarea, el jugador\n"
				+ "que coloca la combinación a adivinar, al final de cada ronda colocará una serie de fichas más pequeñas en\n"
				+ "blanco y negro en vez de en color las cuales significan lo siguiente:\n"
				+ "- Ficha negra: Una de las fichas colocadas aparece en la combinación a adivinar y además está bien colocada.\n"
				+ "- Ficha blanca: Una de las fichas colocadas aparece en la combinación a adivinar pero no está bien colocada.\n"
				+ "- Ficha vacía: Una de las fichas colocadas no aparece en la combinación a adivinar.\n"
				+ "Nota: Ninguna ficha negra o blanca indica nada sobre la posición de las fichas de color, ahí es donde el\n"
				+ "jugador que deberá aplicar la lógica para adivinar la combinación del oponente.\n"
				+ "---------------------------------------------------------------------------------------------------------------\n");
	}
	
	//Métodos de Interfaz
	/**
	 * Muestra el rótulo.
	 * @see DibujableConfiguracion
	 */
	public void mostrarRotulo() {
		System.out.printf("\n  %1$s  %11$s          %1$s  %11$s      %2$s      %11$s        %3$s            %11$s  %4$s              %11$s  %5$s              %11$s  %6$s            %11$s    %7$s  %11$s          %7$s  %11$s  %8$s              %11$s  %9$s  %11$s          %9$s  %11$s  %10$s            %11$s\n"+
		"  %1$s    %11$s      %1$s    %11$s    %2$s  %11$s      %2$s  %11$s    %3$s  %11$s                    %4$s  %11$s        %5$s  %11$s              %6$s  %11$s          %6$s  %11$s  %7$s    %11$s      %7$s    %11$s        %8$s  %11$s        %9$s    %11$s        %9$s  %11$s  %10$s  %11$s          %10$s  %11$s\n"+
		"  %1$s  %11$s  %1$s  %11$s  %1$s  %11$s  %1$s  %11$s  %2$s  %11$s          %2$s  %11$s  %3$s  %11$s                    %4$s  %11$s        %5$s  %11$s              %6$s  %11$s          %6$s  %11$s  %7$s  %11$s  %7$s  %11$s  %7$s  %11$s  %7$s  %11$s        %8$s  %11$s        %9$s  %11$s  %9$s  %11$s      %9$s  %11$s  %10$s  %11$s          %10$s  %11$s\n"+
		"  %1$s  %11$s    %1$s  %11$s    %1$s  %11$s  %2$s              %11$s    %3$s          %11$s          %4$s  %11$s        %5$s        %11$s        %6$s            %11$s    %7$s  %11$s    %7$s  %11$s    %7$s  %11$s        %8$s  %11$s        %9$s  %11$s    %9$s  %11$s    %9$s  %11$s  %10$s  %11$s          %10$s  %11$s\n"+
		"  %1$s  %11$s          %1$s  %11$s  %2$s  %11$s          %2$s  %11$s              %3$s  %11$s        %4$s  %11$s        %5$s  %11$s              %6$s  %11$s      %6$s  %11$s      %7$s  %11$s          %7$s  %11$s        %8$s  %11$s        %9$s  %11$s      %9$s  %11$s  %9$s  %11$s  %10$s  %11$s          %10$s  %11$s\n"+
		"  %1$s  %11$s          %1$s  %11$s  %2$s  %11$s          %2$s  %11$s              %3$s  %11$s        %4$s  %11$s        %5$s  %11$s              %6$s  %11$s        %6$s  %11$s    %7$s  %11$s          %7$s  %11$s        %8$s  %11$s        %9$s  %11$s        %9$s    %11$s  %10$s  %11$s          %10$s  %11$s\n"+
		"  %1$s  %11$s          %1$s  %11$s  %2$s  %11$s          %2$s  %11$s  %3$s            %11$s          %4$s  %11$s        %5$s              %11$s  %6$s  %11$s          %6$s  %11$s  %7$s  %11$s          %7$s  %11$s  %8$s              %11$s  %9$s  %11$s          %9$s  %11$s  %10$s            %11$s\n\n"+
		"  %10$s              %11$s  %4$s              %11$s  %9$s              %11$s  %7$s              %11$s  %3$s              %11$s  %1$s              %11$s  %2$s              %11$s  %6$s              %11$s  %5$s              %11$s  %8$s              %11$s  \n\n",
		Color.AMARILLO.getColorFondo(),
		Color.AZUL.getColorFondo(),
		Color.LIMA.getColorFondo(),
		Color.BURDEOS.getColorFondo(),
		Color.CELESTE.getColorFondo(),
		Color.VERDE.getColorFondo(),
		Color.BLANCO.getColorFondo(),
		Color.MORADO.getColorFondo(),
		Color.TEAL.getColorFondo(),
		Color.ROJO.getColorFondo(),
		//Ficha.GRIS.getColorFondo(),
		//Ficha.NEGRO.getColorFondo(),
		Constantes.RESET);
	
	}
	/**
	 * Muestra el menu.
	 * @return Indica si salir o no del juego.
	 * @see DibujableConfiguracion
	 */
	public boolean mostrarMenu() {
		boolean salir=false;
		byte opcionMenu;
		byte opcionDificultad;
		do {
			System.out.println("Elija una de las siguientes opciones:");
			System.out.println(" 1.- Comenzar partida (debe seleccionar una dificultad previamente)");
			System.out.println(" 2.- Elegir dificultad");
			System.out.println(" 3.- Reglas del juego");
			System.out.println(" 4.- Salir el juego");
			if (dificultad!=null)
				System.out.printf("%sDificultad seleccionada: %s%s\n\nOpcion: ",Color.AMARILLO.getColor(),dificultad.nombre(),Constantes.RESET);
			else
				System.out.printf("%sNinguna dificultad seleccionada%s\n\nOpcion: ",Color.AMARILLO.getColor(),Constantes.RESET);
			opcionMenu=leerEntre((byte)1,(byte)4,leerEntre.AMBOS_INCLUIDOS,Color.ROJO.getColor()+"Debe introducir 1, 2, 3 o 4"+Constantes.RESET+"\nOpción: ");
			switch (opcionMenu) {
				case 1:
					if (dificultad!=null) {
						partida = new Partida(dificultad);
						partida.mostrarPartida();
					}else
						System.out.println(Color.ROJO.getColor()+"Debe introducir una dificultad antes de poder comenzar la partida\n"+Constantes.RESET);
					break;
				case 2:
					System.out.println("Elija una de las siguientes dificultades:");
					System.out.printf("\n1. %s",Dificultad.FACIL_ADIVINAR.toString());
					System.out.printf("\n2. %s",Dificultad.FACIL_COMPROBAR.toString());
					System.out.printf("\n3. %s",Dificultad.MEDIO.toString());
					System.out.printf("\n4. %s",Dificultad.DIFICIL.toString());
					System.out.print("\nOpción: ");
					opcionDificultad=leerEntre((byte)1,(byte)4,leerEntre.AMBOS_INCLUIDOS,Color.ROJO.getColor()+"Debe introducir 1, 2, 3 o 4"+Constantes.RESET+"\nOpción: ");
					System.out.println();
					switch (opcionDificultad) {
						case 1:
							dificultad=Dificultad.FACIL_ADIVINAR;
							break;
						case 2:
							dificultad=Dificultad.FACIL_COMPROBAR;
							break;
						case 3:
							dificultad=Dificultad.MEDIO;
							break;
						case 4:
							dificultad=Dificultad.DIFICIL;
							break;
					}
					break;
				case 3:
					verReglas();
					break;
				case 4:
					salir=true;
					System.out.println("Fin de la partida");
					break;
			}
		}while(!salir);
		return salir;
	}
}
