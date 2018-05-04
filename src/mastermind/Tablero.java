package mastermind;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Esta clase almacena el las rondas de los usuarios y las muestra.
 * 
 * @author Nekotaga
 * @version 1.2
 * @since 1.0
 * @see Ronda
 * @see Combinacion
 * @see DibujableTablero
 */
public class Tablero implements DibujableTablero{
	
	//Variables
	/**
	 * Almacena el máximo de rondas que puede haber a partir de la dificultad.
	 * @see Ronda
	 * @see Dificultad
	 */
	private byte maxRondas;
	/**
	 * Almacena la combinación a adivinar.
	 * Puede devolverse mediante {@link #getCombinacionOriginal()}.
	 * Puede modificarse mediante {@link #setCombinacionOriginal(Combinacion)}.
	 * @see Combinacion
	 */
	private Combinacion combinacionOriginal;
	/**
	 * Almacena las rondas de la partida.
	 * Puede devolverse mediante {@link #getRondas()}.
	 * @see Ronda
	 * @see Partida
	 */
	private LinkedList<Ronda> rondas;
	/**
	 * Almacena la dificultad de la partida.
	 * @see Partida
	 * @see Dificultad
	 */
	private Dificultad dificultad;
	
	//Constructor
	/**
	 * Construye un objeto Tablero a partir de la dificultad.
	 * @param dificultad	La dificultad de la partida.
	 * @see Dificultad
	 * @see Partida
	 */
	Tablero(Dificultad dificultad){
		this.dificultad=dificultad;
		maxRondas=dificultad.getNumIntentos();
		crearTablero();
	}
	
	//Getters y Setters
	/**
	 * Devuelve el valor de la variable {@link #rondas}.
	 * @return Las rondas jugadas.
	 * @see Ronda
	 */
	protected LinkedList<Ronda> getRondas(){
		return rondas;
	}
	/**
	 * Devuelve el valor de la variable {@link #combinacionOriginal}.
	 * @return La combinación a adivinar.
	 * @see Combinacion
	 */
	protected Combinacion getCombinacionOriginal() {
		return combinacionOriginal;
	}
	/**
	 * Modifica el valor de la variable {@link #combinacionOriginal}.
	 * @param combinacionOriginal	La combinacion a adivinar.
	 * @see Combinacion
	 */
	protected void setCombinacionOriginal(Combinacion combinacionOriginal) {
		this.combinacionOriginal=combinacionOriginal;
	}
	
	//Métodos
	/**
	 * Inicializa el tablero inicializando su variable {@link #rondas}.
	 * @see Ronda
	 */
	private void crearTablero() {
		rondas = new LinkedList<Ronda>();
	}
	/**
	 * Este método contiene la fórmula para generar el separador de las combinaciones que ponen ambos jugadores.
	 * @see Combinacion
	 */
	private void verSeparador() {
		for (byte i=0;i<(dificultad.getNumCasillas()+2)*3+(3*(dificultad.getNumCasillas())/2)+1;i++) {
			if (i%2==0)
				System.out.printf("%s  %s",Color.BLANCO.getColorFondo(),Constantes.RESET);
			else
				System.out.printf("%s  %s",Color.NEGRO.getColorFondo(),Constantes.RESET);
		}
		System.out.println("\n");
	}
	/**
	 * Este método muestra la combinación propuesta de una forma u otra según la ronda y los roles de los jugadores.
	 * @param ronda La ronda de la que se va a mostrar la combinación propuesta.
	 * @param ajeno Si el tablero se ve desde una perspectiva de tercera persona o no.
	 * @see Combinacion
	 * @see Ronda
	 */
	private void verCombinacionPropuesta(Ronda ronda,boolean ajeno) {
		Color color=Color.NEGRO;
		Ficha cPropuesta[];
		Ficha cByN[];
		for (byte i=0;i<Constantes.ALTURA;i++) {
			cPropuesta=ronda.getCombinacionPropuesta().getCombinacion();
			cByN=ronda.getResultadoByN().getCombinacion();
			if (i==1)	// Se colocan los números de ronda
				System.out.printf("%s    %02d%s    ",Color.BLANCO.getColor(),(ronda.getContadorRonda()+1),Constantes.RESET);
			else
				System.out.print("          ");
			for (Ficha f:cPropuesta)	// Se colocan los colores de la combinacion propuesta
				System.out.printf("%s      %s  ",f.getColorFicha().getColorFondo(),Constantes.RESET);
			System.out.print(" ");
			for (byte j=0;j<(cByN.length)/2;j++) {	// Se colocan los puntos de las fichas blancas y negras
				if (ajeno&&ronda.getContadorRonda()==rondas.getLast().getContadorRonda()&&i!=1&&!rondas.getLast().getCombinacionPropuesta().equals(combinacionOriginal)) {
					System.out.printf("%s%s%s   ",Color.BLANCO.getColor(),Constantes.VACIO,Constantes.RESET);
					if ((cByN.length)%2!=0&&j==(cByN.length/2)-1&&i==2)
						System.out.printf("%s%s%s   ",Color.BLANCO.getColor(),Constantes.VACIO,Constantes.RESET);
				}else if (i==0) {
					if ((cByN.length)%2!=0&&cByN[j+(cByN.length/2)+1]!=null) {
						color=cByN[j+(cByN.length/2)+1].getColorFicha();
						System.out.printf("%s%s%s   ",color.getColor(),Constantes.CIRCULO,Constantes.RESET);
					}else if ((cByN.length)%2==0&&cByN[j+(cByN.length/2)]!=null) {
						color=cByN[j+(cByN.length/2)].getColorFicha();
						System.out.printf("%s%s%s   ",color.getColor(),Constantes.CIRCULO,Constantes.RESET);
					}else
						System.out.printf("%s%s%s   ",Color.BLANCO.getColor(),Constantes.VACIO,Constantes.RESET);
				}else if (i==2) {
					if (cByN[j]!=null) {
						color=cByN[j].getColorFicha();
						System.out.printf("%s%s%s   ",color.getColor(),Constantes.CIRCULO,Constantes.RESET);
					}else
						System.out.printf("%s%s%s   ",Color.BLANCO.getColor(),Constantes.VACIO,Constantes.RESET);
					if ((cByN.length)%2!=0&&j==(cByN.length/2)-1) {
						if (cByN[j+1]!=null) {
							color=cByN[j+1].getColorFicha();
							System.out.printf("%s%s%s   ",color.getColor(),Constantes.CIRCULO,Constantes.RESET);
						}else
							System.out.printf("%s%s%s   ",Color.BLANCO.getColor(),Constantes.VACIO,Constantes.RESET);
					}
				}else
					System.out.print("   ");
			}
			System.out.println();
		}
		System.out.println();
	}
	/**
	 * Este método muestra la combinación original de una forma u otra según los roles de los usuarios.
	 * @param propio Si el tablero se ve desde una perspectiva de primera persona.
	 * @see Combinacion
	 * @see Usuario
	 */
	private void verCombinacionOriginal(boolean propio) {
		Color color=Color.NEGRO;
		Ficha cOriginal[]=combinacionOriginal.getCombinacion();
		for (byte i=0;i<Constantes.ALTURA;i++) {	// Con esto se genera la línea de la combinación original
			if (i==1)
				System.out.printf("%s    %02d%s    ",Color.BLANCO.getColor(),maxRondas,Constantes.RESET);	// El número máximo de rondas
			else
				System.out.print("          ");		// Los espacios por encima y por debajo de ese número máximo de rondas
			for (Ficha fco:cOriginal) {
				if (!propio||combinacionOriginal.equals(rondas.getLast().getCombinacionPropuesta())||rondas.getLast().getContadorRonda()==maxRondas-1)
					color=fco.getColorFicha();		// Se cambia el negro por el de la ficha de la combinación original si no estás viendo el tablero en primera persona, si se ha acertado o se ha llegado a la ronda máxima
				System.out.printf("%s      %s  ",color.getColorFondo(),Constantes.RESET);	// Se muestra el color
			}
			System.out.println();
		}
		System.out.println();
	}
	/**
	 * Agrega una ronda a la colección rondas.
	 * @param ronda La ronda agregada.
	 * @see Ronda
	 */
	protected void agregarRonda(Ronda ronda) {
		rondas.add(ronda);
	}
	
	//Interfaz
	/**
	 * Muestra el tablero desde la perspectiva del usuario.
	 * @see Usuario
	 * @see DibujableTablero
	 */
	public void mostrarTableroPropio() {
		verCombinacionOriginal(true);
		verSeparador();
		for (ListIterator<Ronda> it = rondas.listIterator(rondas.size());it.hasPrevious();)
			verCombinacionPropuesta(it.previous(),false);
	}
	/**
	 * Muestra el tablero desde la perspectiva del oponente.
	 * @see Usuario
	 * @see DibujableTablero
	 */
	public void mostrarTableroAjeno() {
		for (Ronda ronda:rondas)
			verCombinacionPropuesta(ronda,true);
		verSeparador();
		verCombinacionOriginal(false);
	}
	/**
	 * Muestra el tablero cuando la dificultad es difícil.
	 * @see Dificultad
	 * @see DibujableTablero
	 */
	public void mostrarTableroDificil(boolean original,boolean jug1){
		Ronda ronda=rondas.getLast();
		Ficha cOriginal[]=combinacionOriginal.getCombinacion();
		Ficha cPropuesta[]=ronda.getCombinacionPropuesta().getCombinacion();
		Ficha cByN[]=ronda.getResultadoByN().getCombinacion();
		if (original) {
			System.out.print("\n    ");
			for (Ficha fco:cOriginal)
				System.out.printf("%s  %s ",fco.getColorFicha().getColorFondo(),Constantes.RESET);	// Se colocan los colores de la combinacion propuesta
			System.out.println("\n");
		}else {
			if (jug1)
				System.out.printf("%s    %02d%s    ",Color.BLANCO.getColor(),(ronda.getContadorRonda()+1),Constantes.RESET);	// Se colocan los números de ronda
			else
				System.out.print("    ");
			for (Ficha fco:cPropuesta)
				System.out.printf("%s  %s ",fco.getColorFicha().getColorFondo(),Constantes.RESET);	// Se colocan los colores de la combinacion propuesta
			System.out.print(" ");
			for (Ficha fco:cByN) {	// Se colocan los puntos negros y blancos
				if (fco!=null)
					System.out.printf("%s%s%s   ",fco.getColorFicha().getColor(),Constantes.CIRCULO,Constantes.RESET);
				else
					System.out.printf("%s%s%s   ",Color.BLANCO.getColor(),Constantes.VACIO,Constantes.RESET);
			}
			if (jug1)
				System.out.print("  ||  ");
			else
				System.out.println();
		}
	}
	/**
	 * Muestra solo la combinación propuesta del usuario de la última ronda y la {@link #getCombinacionOriginal()}.
	 * @deprecated
	 * @see Usuario
	 * @see Ronda
	 * @see Combinacion
	 * @see DibujableTablero
	 */
	public void mostrarTableroFinal() {
		verCombinacionOriginal(false);
		verSeparador();
		verCombinacionPropuesta(rondas.getLast(),false);
	}
	
}
