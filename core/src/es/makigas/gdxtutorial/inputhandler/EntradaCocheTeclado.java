package es.makigas.gdxtutorial.inputhandler;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

/**
 * Controlador de entrada basado en teclado. Al pulsar algunas teclas válidas
 * se altera el estado del coche.
 * 
 * @author danirod
 */
public class EntradaCocheTeclado extends InputAdapter {
	
	private ControladorVirtual controlador;
	
	public EntradaCocheTeclado(ControladorVirtual controlador) {
		this.controlador = controlador;
	}
	
	/** keyDown = al empezar a pulsar una tecla */
	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Input.Keys.LEFT:
		case Input.Keys.A:
			if (!controlador.moverDerecha)
				controlador.moverIzquierda = true;
			return true;
		case Input.Keys.RIGHT:
		case Input.Keys.D:
			if (!controlador.moverIzquierda)
				controlador.moverDerecha = true;
			return true;
		default:
			return false;
		}
	}
	
	/** keyUp = al dejar de pulsar una tecla */
	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
		case Input.Keys.LEFT:
		case Input.Keys.A:
			controlador.moverIzquierda = false;
			return true;
		case Input.Keys.RIGHT:
		case Input.Keys.D:
			controlador.moverDerecha = false;
			return true;
		default:
			return false;
		}
	}
	
}
