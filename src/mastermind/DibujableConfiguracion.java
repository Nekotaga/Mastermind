package mastermind;

/**
 * Esta interfaz es la que implementa ConfigurarPartida para mostrarse.
 * 
 * @author Nekotaga
 * @version 1.0
 * @since 1.0
 * @see ConfigurarPartida
 */
public interface DibujableConfiguracion {
	
	/**
	 * Muestra el rótulo.
	 * @see ConfigurarPartida
	 */
	public void mostrarRotulo();
	/**
	 * Muestra el menu.
	 * @return Indica si salir o no del juego.
	 * @see ConfigurarPartida
	 */
	public boolean mostrarMenu();
	
}
