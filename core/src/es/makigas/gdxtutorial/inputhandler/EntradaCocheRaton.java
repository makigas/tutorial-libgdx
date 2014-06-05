package es.makigas.gdxtutorial.inputhandler;

import com.badlogic.gdx.InputAdapter;

/**
 * Controlador de entrada basado en ratón/touch. Al hacer clic en un punto
 * de la pantalla, el coche se desplaza hacia esa posición.
 * 
 * @author danirod
 */
public class EntradaCocheRaton extends InputAdapter {
	
	private ControladorVirtual controlador;
	
	public EntradaCocheRaton(ControladorVirtual controlador) {
		this.controlador = controlador;
	}
	
	/** touchUp = al dejar de pulsar. */
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		controlador.obedeceRaton = true;
		controlador.objetivoX = screenX;
		return true;
	}

}
