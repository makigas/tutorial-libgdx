package es.makigas.gdxtutorial;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HolaGdxGame extends Game {

	public SpriteBatch batch;
	
	public Pantalla coche;
	public Pantalla animacion;

	@Override
	public void create() {
		// Creamos algunas cosas sencillas...
		batch = new SpriteBatch();
		
		coche = new PantallaCoche(this);
		animacion = new PantallaAnimation(this);
		setScreen(animacion);
	}

}
