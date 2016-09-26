package com.example.monkey;

import com.exaple.interfases.Juego;
import com.exaple.interfases.Graficos;
import com.exaple.interfases.Pantalla;
import com.exaple.interfases.Graficos.PixmapFormat;

public class LoadingScreen extends Pantalla {
	 public LoadingScreen(Juego juego) {
	        super(juego);
	    }

	@Override
	public void update(float deltaTime) {
		 Graficos g = juego.getGraphics();
	        Assets.fondo = g.newPixmap("fondo.png", PixmapFormat.RGB565);
	        Assets.banana = g.newPixmap("banana3.png", PixmapFormat.RGB565);
	        Assets.bomba = g.newPixmap("bomba.png", PixmapFormat.RGB565);
	        Assets.logo = g.newPixmap("logo.png", PixmapFormat.ARGB4444);
	        Assets.menuprincipal = g.newPixmap("menuprincipal.png", PixmapFormat.ARGB4444);
	        Assets.botones = g.newPixmap("botones.png", PixmapFormat.ARGB4444);
	        Assets.ayuda1 = g.newPixmap("ayuda1.png", PixmapFormat.ARGB4444);
	        Assets.ayuda2 = g.newPixmap("ayuda2.png", PixmapFormat.ARGB4444);
	        Assets.numeros = g.newPixmap("numeros.png", PixmapFormat.ARGB4444);
	        Assets.preparado = g.newPixmap("preparado.png", PixmapFormat.ARGB4444);
	        Assets.menupausa = g.newPixmap("menupausa.png", PixmapFormat.ARGB4444);
	        Assets.finjuego = g.newPixmap("finjuego.png", PixmapFormat.ARGB4444);
	        Assets.mono = g.newPixmap("mono1.png", PixmapFormat.ARGB4444);
	        Assets.explosion = juego.getAudio().nuevoSonido("explosion.ogg");
	        Assets.grito = juego.getAudio().nuevoSonido("grito.ogg");
	        Assets.risa = juego.getAudio().nuevoSonido("risa.ogg");
	        Assets.pulsar = juego.getAudio().nuevoSonido("pulsar.ogg");
	        Assets.explocion = g.newPixmap("explosion.png", PixmapFormat.ARGB4444);
	        
	        
	        
	        Configuraciones.cargar(juego.getFileIO());       
	        juego.setScreen(new MainMenuScreen(juego));
	}

	@Override
	public void present(float deltaTime) {
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
