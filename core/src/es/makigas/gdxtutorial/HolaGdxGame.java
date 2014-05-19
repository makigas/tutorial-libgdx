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

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;

/**
 * Punto de entrada del juego. Esta clase es invocada por el lanzador del
 * juego cuando iniciamos nuestro juego en cualquier plataforma. Extiende
 * a ApplicationAdapter, que es una clase que tiene métodos que son llamados
 * automáticamente por el propio lanzador cuando considera oportuno (por
 * ejemplo, se ejecuta un método al crear el juego, etc.)
 * 
 * @author danirod
 */
public class HolaGdxGame extends ApplicationAdapter {

	/**
	 * El SpriteBatch es un objeto que usamos para enviar a la tarjeta
	 * gráfica múltiples texturas a la vez. De este modo, hacemos un uso
	 * más eficiente de los recursos porque mandar instrucciones de dibujado
	 * es un proceso un poco lento, y cuantas más imágenes mandemos, mejor.
	 */
	private SpriteBatch batch;
	
	/**
	 * Sirve para almacenar una textura, que es una imagen que reside en la
	 * tarjeta gráfica y que se puede mostrar en el juego para dibujar cosas.
	 */
	private Texture coche;
	
	/**
	 * Un Sprite sirve para guardar lo mismo que guardaríamos en un TextureRegion
	 * pero también para guardar más datos de la imagen, como su posición, su
	 * tamaño... así no tenemos que indicarlo cada vez que queramos dibujarla :D
	 */
	private Sprite miCoche;
	
	/** Velocidad que lleva el coche. */
	private float velocidad = 0;

	/** Aceleración que lleva el coche. */
	private float aceleracion = 0;
	
	/** Dónde debe desplazarse ahora el coche. */
	private float objetivoX = 0;
	
	/** ¿El coche debe moverse porque hemos hecho clic? */
	private boolean debeMoverse = false;

	/**
	 * El método create() es invocado cuando se crea el juego al abrir el programa
	 * o tocar el icono en nuestro teléfono móvil. Deberíamos usar este método
	 * para cargar las cosas...
	 */
	@Override
	public void create () {
		// Creamos algunas cosas sencillas...
		batch = new SpriteBatch();
				
		// Para cargar una textura indicamos su ubicación en la carpeta assets/
		coche = new Texture("coche.png");
		
		// Con un Sprite podemos hacer más cosas que con un TextureRegion...
		miCoche = new Sprite(coche, 0, 0, 215, 83);
		miCoche.setPosition(50, 50); // le damos una posición en la pantalla
	}

	/**
	 * El método render() se invoca cuando el juego vaya a mostrar un fotograma.
	 * Se pueden mostrar varias decenas o cientos de fotogramas por segundo así
	 * que mantén este método simple para que tarde lo mínimo posible y hacer
	 * que el juego vaya más fluido.
	 */
	@Override
	public void render () {
		renderizarJuego();
		procesarEntrada();
	}

	/**
	 * Este método es llamado cuando el juego se cierre y con este método podemos
	 * liberar recursos que no estemos utilizando. Debemos liberar manualmente
	 * todo aquello que no pueda liberar el recolector de basura de Java,
	 * por ejemplo, las texturas.
	 */
	@Override
	public void dispose() {
		// Liberar una textura es tan fácil como llamar a dispose().
		// ¡OJO! Cuando liberamos una textura los datos desaparecen de la memoria
		// así que no deberíamos intentar dibujarla otra vez. Las consecuencias
		// pueden ser impredecibles.
		coche.dispose();
	}
	
	/** Se encarga de renderizar el juego como vimos en el episodio 3. */

	private void renderizarJuego() {
		// Con glClearColor y glClear podemos limpiar la pantalla y cambiarla por
		// un fondo del color que le digamos. El color se lo decimos con los tres
		// primeros parámetros, que son valores flotantes de tipo RGB entre 0 y 1.
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Empezamos a mostrar cosas usando nuestro batch.
		batch.begin();
			
			// Para mostrar un Sprite la cosa es muy sencilla :)
			miCoche.draw(batch);
		batch.end();
	}
	
	/** Se encarga de procesar la entrada como vimos em el episodio 4. */

	private void procesarEntrada() {
		entradaTeclado();
		entradaRaton();
		actualizarPosicionCoche();
	}
	
	/** Procesa entrada por teclado. */

	private void entradaTeclado() {
		// Si se está moviendo porque hemos hecho clic no se puede interactuar.
		if (debeMoverse)
			return;
		
		boolean izda = Gdx.input.isKeyPressed(Input.Keys.A)
				|| Gdx.input.isKeyPressed(Input.Keys.LEFT);
		boolean dcha = Gdx.input.isKeyPressed(Input.Keys.D)
				|| Gdx.input.isKeyPressed(Input.Keys.RIGHT);
		boolean puedeMoverse = (izda != dcha);
		if (puedeMoverse && dcha)
			acelerar();
		else if (puedeMoverse && izda)
			marchaAtras();
		else
			frenarCoche();
	}
	
	/** Procesa entrada por ratón. */
	private void entradaRaton() {
		// Determinamos si hay que iniciar un movimiento.
		if (Gdx.input.isTouched()) {
			debeMoverse = true;
			objetivoX = Gdx.input.getX();
			// Si tenemos que ir a la derecha, que lleguemos con el capó.
			if (objetivoX > miCoche.getX())
				objetivoX -= miCoche.getWidth();
		}
		
		if (debeMoverse) {
			float distRestante = objetivoX - miCoche.getX();
			if (distRestante > 0)
				acelerar();
			else
				marchaAtras();
			debeMoverse = Math.abs(distRestante) > 10f;
		} else {
			frenarCoche();
		}
	}
	
	/** Intenta acelerar el coche si no tiene aceleración máxima. */

	/** Acelera el coche si no está a tope ya. */
	private void acelerar() {
		if (aceleracion <= 240f) aceleracion += 60f;
	}
	
	/** Lleva el coche marcha atrás. */

	/** El coche avanza hacia atrás. */
	private void marchaAtras() {
		if (aceleracion >= -240f) aceleracion -= 40f;
	}
	
	/** Se asegura que el coche esté quieto o lo frena si no lo está. */

	/** Se asegura de que el coche esté quieto o lo frena si no lo está. */
	private void frenarCoche() {
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
	

	/**
	 * Actualizamos la posición del coche usando las fórmulas del MRUA.
	 * v = v0 + at, x = x0 + vt + 0.5at² (revisen su libro de física...)
	 */
	private void actualizarPosicionCoche() {
		float posicion = miCoche.getX();
		float tiempo = Gdx.graphics.getDeltaTime();
		velocidad += aceleracion * tiempo;
		posicion += velocidad * tiempo + 0.5 * aceleracion * (tiempo * tiempo);
		miCoche.setX(posicion);
	}
}
