package es.makigas.gdxtutorial.inputhandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.input.GestureDetector.GestureListener;

import es.makigas.gdxtutorial.HolaGdxGame;
import es.makigas.gdxtutorial.Pantalla;

public class PantallaAuto extends Pantalla {
	
	public PantallaAuto(HolaGdxGame game) {
		super(game);
	}

	private Coche coche;
	private ControladorVirtual controlador;
	private InputMultiplexer multiplexor;
	private InputProcessor teclado, raton;
	
	@Override
	public void show() {
		coche = new Coche();
		coche.setPosition(50, 50);
		
		// Creo los controladores.
		controlador = new ControladorVirtual();
		teclado = new EntradaCocheTeclado(controlador);
		raton = new EntradaCocheRaton(controlador);
		
		// Para poder usar ambos a la vez creo multiplexores.
		multiplexor = new InputMultiplexer();
		multiplexor.addProcessor(raton);
		multiplexor.addProcessor(teclado);
		
		// setInputProcessor = poner un controlador o un multiplexor.
		Gdx.input.setInputProcessor(multiplexor);
	}
	
	@Override
	public void render(float delta) {
		// render
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
			coche.draw(game.batch);
		game.batch.end();
		
		// update
		if (controlador.obedeceRaton) {
			// Si aún debo obedecer al ratón, continúo avanzando.
			float restante = controlador.objetivoX - coche.getX();
			if (restante > 0) {
				coche.acelerar();
			} else {
				coche.marchaAtras();
			}
			// Si aún estoy lejos de mi objetivo sigo obedeciendo al ratón.
			controlador.obedeceRaton = (Math.abs(restante) > 10f);
		} else if (controlador.moverDerecha) {
			coche.acelerar();
		} else if (controlador.moverIzquierda) {
			coche.marchaAtras();
		} else {
			coche.frenarCoche();
		}
		coche.update();
	}
}
