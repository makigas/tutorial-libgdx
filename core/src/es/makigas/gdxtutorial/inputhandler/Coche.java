package es.makigas.gdxtutorial.inputhandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Sprite del coche con información de velocidad y aceleración.
 * 
 * @author danirod
 */
public class Coche extends Sprite {
	
	/** Velocidad que lleva el coche. */
	public float velocidad = 0;

	/** Aceleración que lleva el coche. */
	public float aceleracion = 0;
	
	public Coche() {
		super(new Texture("coche.png"), 0, 0, 215, 83);
	}
	
	/** Actualiza la posición y la velocidad del coche. */
	public void update() {
		float posicion = getX();
		float tiempo = Gdx.graphics.getDeltaTime();
		velocidad += aceleracion * tiempo;
		posicion += velocidad * tiempo + 0.5 * aceleracion * (tiempo * tiempo);
		setX(posicion);
	}
	
	/** Acelera el coche si no está a tope ya. */
	public void acelerar() {
		if (aceleracion <= 240f) aceleracion += 60f;
	}

	/** El coche avanza hacia atrás. */
	public void marchaAtras() {
		if (aceleracion >= -240f) aceleracion -= 40f;
	}
	
	/** Se asegura de que el coche esté quieto o lo frena si no lo está. */
	public void frenarCoche() {
		// Como no se puede mover, la aceleración es 0 y las fuerzas
		// de rozamiento hacen que el coche se vaya frenando progresivamente.
		aceleracion = 0;
		// Como trabajamos con flotantes, tenemos que establecer un límite
		// de seguridad ya que los cálculos nunca tendrán buena precisión.
		if (Math.abs(velocidad) > 0.5f)
			velocidad *= 0.95f;
		else
			velocidad = 0;
	}
}
