package es.makigas.gdxtutorial.inputhandler;

/**
 * Controlador virtual del coche. Lo usamos para poder separar la lógica del
 * juego de la entrada real. Así, si cambia la entrada del juego, sólo hay
 * que cambiar cómo la entrada toca el controlador, no hay que cambiar cosas
 * físicas de nuestro juego.
 * 
 * @author danirod
 */
public class ControladorVirtual {
	
	/** El coche se debe mover a la izquierda. */
	public boolean moverIzquierda;
	
	/** El coche se debe mover a la derecha. */
	
	public boolean moverDerecha;
	
	/** El coche debe obedecer al ratón e ignorar el teclado. */
	public boolean obedeceRaton;
	
	/** Dónde dice el ratón que debe ir. */
	public int objetivoX;
	
}
