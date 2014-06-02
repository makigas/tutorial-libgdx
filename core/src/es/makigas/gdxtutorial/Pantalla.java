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

import com.badlogic.gdx.Screen;

/**
 * Pantalla abstracta. En la mayoría de los casos nos interesa construir una
 * pantalla abstracta no implementable pero que disponga de características
 * comunes que queremos que las distintas pantallas de nuestro juego hereden
 * (OOP, tíos).
 * 
 * @author danirod
 */
public abstract class Pantalla implements Screen {
	
	/**
	 * Juego al que pertenece la pantalla. Es muy interesante conectar una
	 * pantalla con el juego sea de la forma que sea ya que de este modo
	 * el juego puede acceder a cosas como el contexto (el SpriteBatch que
	 * he dejado ahí) o simplemente llamar a setScreen.
	 */
	protected HolaGdxGame game;
	
	public Pantalla(HolaGdxGame game) {
		this.game = game;
	}
	
	// Para no tener que implementar tantos métodos por nuestra cuenta,
	// dejo los métodos vacíos. Si una pantalla quiere usar un método,
	// lo extiende. Pero si no quiere usarlo, no tiene que extenderlo.

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
