package es.makigas.gdxtutorial.scene2d;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

public class ActorCoche extends Actor implements Disposable {
	
	private Texture coche;
	private TextureRegion miCoche;
	
	public ActorCoche() {
		coche = new Texture("coche.png");
		miCoche = new TextureRegion(coche, 215, 83);
		setSize(215, 83);
	}
	
	@Override
	public void dispose() {
		coche.dispose();
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		Color col = getColor();
		batch.setColor(col.r, col.g, col.b, col.a * parentAlpha);
		batch.draw(miCoche, getX(), getY(),
				getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(),
				getRotation());
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
	}
	
}
