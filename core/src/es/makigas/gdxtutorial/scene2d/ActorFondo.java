package es.makigas.gdxtutorial.scene2d;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.makigas.gdxtutorial.ShapeRenderer;
import es.makigas.gdxtutorial.ShapeRenderer.ShapeType;

public class ActorFondo extends Actor {

	private ShapeRenderer shaper;
	
	public ActorFondo() {
		shaper = new ShapeRenderer();
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.end();
		
		shaper.setProjectionMatrix(batch.getProjectionMatrix());
		shaper.setTransformMatrix(batch.getTransformMatrix());
		shaper.begin(ShapeType.Filled);
		shaper.rect(getX(), getY(), getWidth(),
				getHeight(), getOriginX(), getOriginY(), getRotation(),
				Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW);
		shaper.end();
		
		batch.begin();
	}
	
}
