package com.example.monkey;

import java.util.List;

import android.graphics.Color;

import com.exaple.interfases.Pantalla;
import com.exaple.interfases.Juego;
import com.exaple.interfases.Graficos;
import com.exaple.interfases.Input.TouchEvent;
import com.exaple.interfases.Pixmap;
import com.example.monkey.mundo;


public class Pantallajuego extends Pantalla {
	enum EstadoJuego {
        Preparado,
        Ejecutandose,
        Pausado,
        FinJuego
    }
    
    EstadoJuego estado = EstadoJuego.Preparado;
    mundo mundo;
    int antiguaPuntuacion = 0;
    String puntuacion = "0";
    
    public Pantallajuego(Juego juego) {
        super(juego);
        mundo = new mundo();
    }
    
    
    public void update(float deltaTime){
    	 List<TouchEvent> touchEvents = juego.getInput().getTouchEvents();
         juego.getInput().getKeyEvents();
         
         if(estado == EstadoJuego.Preparado)
             updateReady(touchEvents);
         if(estado == EstadoJuego.Ejecutandose)
             updateRunning(touchEvents, deltaTime);
         if(estado == EstadoJuego.Pausado)
             updatePaused(touchEvents);
         if(estado == EstadoJuego.FinJuego)        	
             updateGameOver(touchEvents);
    }
    private void updateReady(List<TouchEvent> touchEvents) {
        if(touchEvents.size() > 0)        	
            estado = EstadoJuego.Ejecutandose;
    }
    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) { 
    	MonoJuego monkey = mundo.monkey;
    	
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(event.x < 64 && event.y < 64) {
                    if(Configuraciones.sonidoHabilitado)
                        Assets.pulsar.play(1);
                    estado = EstadoJuego.Pausado;
                    return;
                }
            }
            if(event.type == TouchEvent.TOUCH_DOWN) {
                if(event.x < 64 && event.y > 416) {
                	Personaje pe = monkey.per.get(0);
                	if(pe.x< 1){
                       pe.x=0;
                	}else{
                		pe.x -=1;
                	}
                    
                }
                if(event.x > 256 && event.y > 416) {
                	Personaje pe = monkey.per.get(0);
                	if(pe.x > 8){
                        pe.x=9;
                 	}else{
                 		pe.x +=1;
                 	}
                }
            }
        }
        mundo.update(deltaTime);
        if(mundo.finalJuego) {        	
            if(Configuraciones.sonidoHabilitado)
                Assets.grito.play(1);               
            estado = EstadoJuego.FinJuego;
        }
        if(antiguaPuntuacion != mundo.puntuacion) {
            antiguaPuntuacion = mundo.puntuacion;
            puntuacion = "" + antiguaPuntuacion;
            if(Configuraciones.sonidoHabilitado){
                Assets.risa.play(1);
        }
}
    }
    private void updatePaused(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(event.x > 80 && event.x <= 240) {
                    if(event.y > 100 && event.y <= 148) {
                        if(Configuraciones.sonidoHabilitado)
                            Assets.pulsar.play(1);
                        estado = EstadoJuego.Ejecutandose;
                        return;
                    }                    
                    if(event.y > 148 && event.y < 196) {
                        if(Configuraciones.sonidoHabilitado)
                            Assets.pulsar.play(1);
                        juego.setScreen(new MainMenuScreen(juego));                        
                        return;
                    }
                }
            }
        }
    }
    private void updateGameOver(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(event.x >= 128 && event.x <= 192 &&
                   event.y >= 200 && event.y <= 264) {
                    if(Configuraciones.sonidoHabilitado)
                        Assets.pulsar.play(1);                        
                    juego.setScreen(new MainMenuScreen(juego));
                    return;
                }
            }
        }
    }
    
    @Override
    public void present(float deltaTime) {
        Graficos g = juego.getGraphics();
        
        g.drawPixmap(Assets.fondo, 0, 0);
        drawWorld(mundo);
        if(estado == EstadoJuego.Preparado) 
            drawReadyUI();
        if(estado == EstadoJuego.Ejecutandose)
            drawRunningUI();
        if(estado == EstadoJuego.Pausado)
            drawPausedUI();
        if(estado == EstadoJuego.FinJuego)
        	drawGameOverUI();
            
        
        drawText(g, puntuacion, g.getWidth() / 2 - puntuacion.length()*20 / 2, g.getHeight() - 42);                
    }
    
    
    
    private void drawWorld(mundo mundo) {
    	Graficos g = juego.getGraphics();
    	MonoJuego monkey = mundo.monkey;
    	Bombas bom = monkey.bom.get(0);
    	Bananas ban = monkey.ban.get(0);
    	
    	Pixmap stainPixmap = null;
    	int len = monkey.bom.size();
    	for(int i = 1; i < len; i++) {
    		Bombas bo = monkey.bom.get(i);	
    		int x = bo.x * 32;
            int y = bo.y * 32;
            g.drawPixmap(Assets.bomba, x, y);
    	}
    	
    	int len2 = monkey.ban.size();
    	for(int i = 1; i < len; i++) {
    		Bananas ba = monkey.ban.get(i);	
    		int x = ba.x * 32;
            int y = ba.y * 32;
            g.drawPixmap(Assets.banana, x, y);
    	}
    	Personaje pe = monkey.per.get(0);
    	int x=pe.x *32;
    	int y=pe.y *32;
    	
    	g.drawPixmap(Assets.mono, x, y);
    	
    }
    
    private void drawReadyUI() {
        Graficos g = juego.getGraphics();
        
        g.drawPixmap(Assets.preparado, 47, 100);
        g.drawLine(0, 416, 480, 416, Color.BLACK);
    }
    
    private void drawRunningUI() {
    	  Graficos g = juego.getGraphics();

          g.drawPixmap(Assets.botones, 0, 0, 64, 128, 64, 64);
          g.drawLine(0, 416, 480, 416, Color.BLACK);
          g.drawPixmap(Assets.botones, 0, 416, 64, 64, 64, 64);
          g.drawPixmap(Assets.botones, 256, 416, 0, 64, 64, 64);
    }
    
    private void drawPausedUI() {
        Graficos g = juego.getGraphics();
        
        g.drawPixmap(Assets.menupausa, 80, 100);
        g.drawLine(0, 416, 480, 416, Color.BLACK);
    }

    private void drawGameOverUI() {
    	 Graficos g = juego.getGraphics();
         
         g.drawPixmap(Assets.finjuego, 62, 100);
         g.drawPixmap(Assets.botones, 128, 200, 0, 128, 64, 64);
         g.drawLine(0, 416, 480, 416, Color.BLACK);
    }
    
    public void drawText(Graficos g, String line, int x, int y) {
        int len = line.length();
        for (int i = 0; i < len; i++) {
            char character = line.charAt(i);

            if (character == ' ') {
                x += 20;
                continue;
            }

            int srcX = 0;
            int srcWidth = 0;
            if (character == '.') {
                srcX = 200;
                srcWidth = 10;
            } else {
                srcX = (character - '0') * 20;
                srcWidth = 20;
            }

            g.drawPixmap(Assets.numeros, x, y, srcX, 0, srcWidth, 32);
            x += srcWidth;
        }
    }
    
    
    @Override
    public void pause() {
        if(estado == EstadoJuego.Ejecutandose)
            estado = EstadoJuego.Pausado;
        
        if(mundo.finalJuego) {        	
            Configuraciones.addScore(mundo.puntuacion);
            Configuraciones.save(juego.getFileIO());           
        }
    }

    @Override
    public void resume() {
        
    }

    @Override
    public void dispose() {
    	
    }
    
}