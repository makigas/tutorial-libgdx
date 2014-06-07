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
package es.makigas.gdxtutorial.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import es.makigas.gdxtutorial.HolaGdxGame;
import es.makigas.gdxtutorial.Pantalla;
import es.makigas.gdxtutorial.inputhandler.Coche;

public class PantallaCamera extends Pantalla {

	private Coche coche;
	
	private ShapeRenderer shaper;
	
	private OrthographicCamera cam;
		
	public PantallaCamera(HolaGdxGame game) {
		super(game);
	}
	
	@Override
	public void show() {
		coche = new Coche();
		coche.setPosition(100, 100);
		
		// Creo un shape renderer.
		shaper = new ShapeRenderer();
		
		// Creo una cámara.
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		cam = new OrthographicCamera(width, height);
	}
	
	@Override
	public void render(float delta) {
		// Limpio la pantalla
		Gdx.gl.glClearColor(0.4f, 0.5f, 0.6f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		cam.update();
		
		// Muestro un origen de coordenadas.
		shaper.setProjectionMatrix(cam.combined);
		shaper.begin(ShapeType.Line);
		shaper.line(-1000, 0, 1000, 0);
		shaper.line(0, -1000, 0, 1000);
		for (int i = -1000; i <= 1000; i += 50) {
			shaper.line(-10, i, 10, i);
			shaper.line(i, -10, i, 10);
		}
		shaper.end();
		
		// Renderizo un coche.
		game.batch.setProjectionMatrix(cam.combined);
		game.batch.begin();
		coche.draw(game.batch);
		game.batch.end();
		
		// Muevo la cámara si estoy pulsando las teclas apropiadas.
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			cam.translate(0, 10);
		} else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			cam.translate(0, -10);
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			cam.translate(-10, 0);
		} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			cam.translate(10, 0);
		}
		
		// También puedo hacer zoom.
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			cam.zoom += 0.01;
		} else if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
			cam.zoom -= 0.01;
		}
	}
	
	@Override
	public void resize(int width, int height) {
		// Cuando cambio el tamaño de la cámara la redimensiono.
		cam.setToOrtho(false, width, height);
	}
}
