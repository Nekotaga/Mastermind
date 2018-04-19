package mastermind;

import static utilidades.Teclado.leerEntre;

public class ConfigurarPartida implements DibujableConfiguracion{
	
	//Variables
	private Dificultad dificultad;
	private Partida partida;
	
	//Métodos de Interfaz
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
	public boolean mostrarMenu() {
		boolean salir=false;
		byte opcionMenu;
		byte opcionDificultad;
		do {
			System.out.println("Elija una de las siguientes opciones:");
			System.out.println(" 1.- Comenzar partida (debe seleccionar una dificultad previamente)");
			System.out.println(" 2.- Elegir dificultad");
			System.out.println(" 3.- Salir el juego");
			if (dificultad!=null)
				System.out.printf("%sDificultad seleccionada: %s%s\n\nOpcion: ",Color.AMARILLO.getColor(),dificultad.nombre(),Constantes.RESET);
			else
				System.out.printf("%sNinguna dificultad seleccionada%s\n\nOpcion: ",Color.AMARILLO.getColor(),Constantes.RESET);
			opcionMenu=leerEntre((byte)1,(byte)3,leerEntre.AMBOS_INCLUIDOS,Color.ROJO.getColor()+"Debe introducir 1, 2 o 3"+Constantes.RESET+"\nOpción: ");
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
					salir=true;
					System.out.println("Fin de la partida");
					break;
			}
		}while(!salir);
		return salir;
	}
}
