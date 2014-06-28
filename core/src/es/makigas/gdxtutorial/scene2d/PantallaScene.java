package es.makigas.gdxtutorial.scene2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.utils.viewport.*;

import es.makigas.gdxtutorial.HolaGdxGame;
import es.makigas.gdxtutorial.Pantalla;

public class PantallaScene extends Pantalla {
	
	private Stage escenario;
	private Actor coche, fondo;

	public PantallaScene(HolaGdxGame game) {
		super(game);
		escenario = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(escenario);
		
		fondo = new ActorFondo();
		fondo.setSize(escenario.getWidth(), escenario.getHeight());
		escenario.addActor(fondo);
		
		coche = new ActorCoche();
		coche.setPosition(-50, 100);
		escenario.addActor(coche);
		
		coche.addCaptureListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				coche.addAction(Actions.color(Color.GREEN, 1));
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				coche.addAction(Actions.color(Color.WHITE, 1));
			}
		});
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		escenario.draw();
		escenario.act();
		
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			ParallelAction par = new ParallelAction();
			par.addAction(Actions.moveTo(250, 250, 1));
			par.addAction(Actions.color(Color.BLUE, 1));
			coche.addAction(par);
		}
	}
	
	@Override
	public void resize(int width, int height) {
		escenario.getViewport().update(width, height);
		fondo.setSize(width, height);
	}
}
