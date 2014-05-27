package es.makigas.gdxtutorial;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PantallaAnimation extends Pantalla {
	
	private TextureAtlas atlas;
	private Texture dude;
	private TextureRegion dudeRegion;
	private TextureRegion[] dudeFrames;
	private Animation dudeAnimation;
	private float duracion = 0;
	
	private static final int ANCHO = 252;
	private static final int ALTO = 49;

	public PantallaAnimation(HolaGdxGame game) {
		super(game);
	}
	
	@Override
	public void show() {
		atlas = new TextureAtlas("atlas.atlas");
		TextureRegion region = atlas.findRegion("dudewalking");
		dudeRegion = new TextureRegion(region, 0, 0, ANCHO, ALTO);
		TextureRegion[][] temp = dudeRegion.split(ANCHO / 9, ALTO);
		dudeFrames = new TextureRegion[temp.length * temp[0].length];
		int indice = 0;
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				dudeFrames[indice++] = temp[i][j];
			}
		}
		dudeAnimation = new Animation(0.1f, dudeFrames);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		
		duracion += delta;
		TextureRegion frame = dudeAnimation.getKeyFrame(duracion, true);
		game.batch.begin();
			game.batch.draw(frame, 100, 100);
		game.batch.end();
	}
}
