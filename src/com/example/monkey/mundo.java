package com.example.monkey;

public class mundo {
	static final int INCREMENTO_PUNTUACION = 10;
    static final float TICK_INICIAL = 0.5f;
    static final float TICK_DECREMENTO = 0.1f;
    
    public MonoJuego monkey;
    public boolean finalJuego = false;
    public int puntuacion = 0;
    public Pantallajuego pan;
    
    float tiempoTick = 0;
    static float tick = TICK_INICIAL;
    
    public mundo(){
    	monkey = new MonoJuego();
    }
    
    public void update(float deltaTime){
    	if (finalJuego)
    		
    		return;
    	tiempoTick += deltaTime;
    	
    	 while (tiempoTick > tick) {
         	tiempoTick -= tick;
             monkey.avance();
             if (monkey.comprobarChoque()==1) 
             	finalJuego = true; 
                return;
             }
    	 if(monkey.comprobarChoque()==3){
    		 puntuacion =puntuacion + INCREMENTO_PUNTUACION;
    		 monkey.tip=0;
    		 
    	 }
    	  if (puntuacion % 100 == 1 || puntuacion % 100 == 2 || puntuacion % 100 == 3 || puntuacion % 100 == 4 || puntuacion % 100 == 5 ) {
             tick -= TICK_DECREMENTO;
             monkey.ban.add(new Bananas(3, -2));
             monkey.ban.add(new Bananas(2, -2));
             monkey.bom.add(new Bombas(7, -3));
             monkey.bom.add(new Bombas(8, -3));
             
         }
    }


    }
    
