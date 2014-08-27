package es.makigas.gdxtutorial.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.badlogic.gdx.scenes.scene2d.ui.Tree.Node;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import es.makigas.gdxtutorial.HolaGdxGame;
import es.makigas.gdxtutorial.Pantalla;

public class PantallaUI extends Pantalla {
	
	private Stage _stage;
			
	public PantallaUI(HolaGdxGame game) {
		super(game);
	}
	
	@Override
	public void show() {
		Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		
		// He incluido el ejemplo de los botones animados porque consideré
		// que sería el más divertido para todos. Realmente no hay código
		// en esta sesión, porque son sólo distintos snippets de código.
		
		final Button btnNewGame, btnLoadGame, btnSettings, btnQuit;
		btnNewGame = new TextButton("New Game", skin);
		btnLoadGame = new TextButton("Load Game", skin);
		btnSettings = new TextButton("Settings", skin);
		btnQuit = new TextButton("Quit", skin);
		
		// Posicionamos los elementos en una tabla.
		final int w = 300, h = 50, sep = 20;
		Table tblLayout = new Table();
		tblLayout.add(btnNewGame).width(w).height(h).space(sep).row();
		tblLayout.add(btnLoadGame).width(w).height(h).space(sep).row();
		tblLayout.add(btnSettings).width(w).height(h).space(sep).row();
		tblLayout.add(btnQuit).width(w).height(h).space(sep).row();
		
		// Al hacer clic en el botón nuevo juego, los botones se van.
		btnNewGame.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				// Píxeles verticales que se desplazarán los botones. Movemos
				// el alto de la pantalla porque es matemáticamente imposible
				// que se sigan viendo los botones tras la animación.
				final int desplY = Gdx.graphics.getHeight();
				
				// Segundos de delay entre el movimiento de cada botón.
				final float espera = 0.1f;
				
				// Cuanto tiempo dura la animación del botón volando.
				final float duracion = 0.5f;
				
				btnNewGame.addAction(sequence(delay(0 * espera),
						parallel(moveBy(0, desplY, duracion), fadeOut(duracion))));
				btnLoadGame.addAction(sequence(delay(1 * espera),
						parallel(moveBy(0, desplY, duracion), fadeOut(duracion))));
				btnSettings.addAction(sequence(delay(2 * espera),
						parallel(moveBy(0, desplY, duracion), fadeOut(duracion))));
				btnQuit.addAction(sequence(delay(3 * espera),
						parallel(moveBy(0, desplY, duracion), fadeOut(duracion))));
			}
			
		});
		
		_stage = new Stage();
		tblLayout.setFillParent(true);
		_stage.addActor(tblLayout);
		Gdx.input.setInputProcessor(_stage);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		_stage.act();
		_stage.draw();
	}
	
	@Override
	public void resize(int width, int height) {
		_stage.getViewport().update(width, height);
	}
}