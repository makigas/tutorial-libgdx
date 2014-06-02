/*
 * Este código pertenece al Tutorial de libGDX del canal de Makigas
 * Copyright (C) 2014 makigas -- http://youtube.com/makigas93
 * Copyright (C) 2014 Dani Rodríguez <danirod@outlook.com>
 * Todos los derechos reservados
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. Neither the name of copyright holders nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * ''AS IS'' AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL COPYRIGHT HOLDERS OR CONTRIBUTORS
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package es.makigas.gdxtutorial;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Pantalla de la animación. Usada para el episodio de animaciones.
 * 
 * @author danirod
 */
public class PantallaAnimation extends Pantalla {
	
	/** Atlas que guarda los recursos. */
	private TextureAtlas atlas;
	
	/** Textura del dude. */
	private TextureRegion dudeRegion;
	
	/** Fotogramas de la animación. */
	private TextureRegion[] dudeFrames;
	
	/** Estructura de datos de la animación. */
	private Animation dudeAnimation;
	
	/** Contador de tiempo. Usado para la animación. */
	private float duracion = 0;
	
	/** Ancho real del dude. */
	private static final int ANCHO = 252;
	
	/** Alto real del dude. */
	private static final int ALTO = 49;

	public PantallaAnimation(HolaGdxGame game) {
		super(game);
	}
	
	@Override
	public void show() {
		// Si quisiese cargar mi dude usando Texture haría ésto:
		// dude = new Texture("dudewalking.png");
		// dudeRegion = new TextureRegion(dude, ANCHO, ALTO);
		
		// Cargo mi dude usando un TextureAtlas. Ojo que está un poco
		// mal hecho. En realidad no debería haber metido en el atlas
		// el dude que ya tiene espacio, porque la clase TextureAtlas
		// ya devuelve regiones.
		// TODO: Corregir este detalle.
		atlas = new TextureAtlas("atlas.atlas");
		TextureRegion region = atlas.findRegion("dudewalking");
		dudeRegion = new TextureRegion(region, 0, 0, ANCHO, ALTO);
		
		// Construyo la animación.
		// (1) Divido la región en un array bidimensional de regiones.
		// (2) Convierto el array de un array 2D [][] a un array 1D [].
		// (3) Construyo la animación a partir de mi array 2D.
		TextureRegion[][] temp = dudeRegion.split(ANCHO / 9, ALTO); // (1)
		dudeFrames = new TextureRegion[temp.length * temp[0].length]; // (2)
		int indice = 0;
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				dudeFrames[indice++] = temp[i][j];
			}
		}
		dudeAnimation = new Animation(0.1f, dudeFrames); // (3)
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		
		// Obtengo el fotograma. Notar cómo primero incremento la variable
		// duracion. Cuando obtengo mi fotograma, lo hago diciendole a 
		// Animation cuántos segundos han pasado desde que empezó la animación.
		// Él hace sus cálculos por su cuenta y devuelve el fotograma que
		// debería ir ahora.
		duracion += delta;
		TextureRegion frame = dudeAnimation.getKeyFrame(duracion, true);
		
		game.batch.begin();
			game.batch.draw(frame, 100, 100);
		game.batch.end();
	}
}
