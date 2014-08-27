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

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import es.makigas.gdxtutorial.camera.PantallaCamera;
import es.makigas.gdxtutorial.inputhandler.PantallaAuto;
import es.makigas.gdxtutorial.scene2d.PantallaScene;
import es.makigas.gdxtutorial.ui.PantallaUI;

/**
 * Clase principal. Es un Game, están diseñados para soportar varias
 * pantallas y disponen de un método (setScreen) para cambiar entre una
 * pantalla y otra cómodamente.
 * 
 * @author danirod
 */
public class HolaGdxGame extends Game {
	
	/**
	 * SpriteBatch. Es un poco absurdo crear uno para cada pantalla, lo
	 * dejamos aquí y que las pantallas accedan a éste. Un poco sucio por
	 * mi parte dejarlo public pero paso de crear un getter en este momento
	 * (aunque sería lo suyo, por ejemplo para evitar que se pueda modificar)
	 */
	public SpriteBatch batch;
	
	public Pantalla coche;
	public Pantalla animacion;
	public Pantalla auto;
	public Pantalla camera;
	public Pantalla scene;
    public Pantalla ui;

	@Override
	public void create() {
		// Creamos algunas cosas sencillas...
		batch = new SpriteBatch();
		
		// Creamos las pantallas
		coche = new PantallaCoche(this);
		animacion = new PantallaAnimation(this);
		auto = new PantallaAuto(this);
		camera = new PantallaCamera(this);
		scene = new PantallaScene(this);
        ui = new PantallaUI(this);
		
		// Hoy nos interesa esta pantalla.
		setScreen(ui);
	}
}
