package com.example.monkey;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.monkey.Bananas;
import com.example.monkey.Bombas;
import com.example.monkey.Personaje;

public class MonoJuego {
	
	static final int MUNDO_ANCHO = 10;
    static final int MUNDO_ALTO = 13;
	Random random = new Random();
	    public List<Bombas> bom = new ArrayList<Bombas>();
	    public List<Bananas> ban = new ArrayList<Bananas>();
	    public List<Personaje> per = new ArrayList<Personaje>();
	    public int direccion;  
	    public int tip;
	    public MonoJuego(){
	    	bom.add(new Bombas(1, -1));
	        bom.add(new Bombas(2, -2));
	        bom.add(new Bombas(3, -3));
	        bom.add(new Bombas(4, -4));
	        bom.add(new Bombas(4, -5));
	        bom.add(new Bombas(5, -8));
	        bom.add(new Bombas(6, -9));
	        
	        ban.add(new Bananas(5, -2));
	        ban.add(new Bananas(6, -4));
	        ban.add(new Bananas(7, -2));
	        ban.add(new Bananas(8, -8));
	        ban.add(new Bananas(8, -4));
	        ban.add(new Bananas(8, -7));
	        ban.add(new Bananas(2, -8));
	        
	        per.add(new Personaje(1,12));
	    }
	    public void avance() {
	    	
	    	int len1 = bom.size();
		        for(int i =0; i<len1; i++) {
		        	Bombas bo = bom.get(i);	
			    	bo.y +=1;
			    	if(bo.y==13){
			    		aleatoriobom(bo);
			    	}
			    	
	        }
		    int len2 = ban.size();
		        for(int i =0; i<len2; i++) {
		        	Bananas ba = ban.get(i);
		             ba.y +=1;
		         if(ba.y==13){
		        	 aleatorioban(ba);
		         }
		        
		    }
		        
	    }
	    
	    public void aleatoriobom(Bombas bom){
	    	bom.x=random.nextInt(MUNDO_ANCHO);
	    	bom.y=-1;
	    }
	    public void aleatorioban(Bananas ban){
	    	ban.x=random.nextInt(MUNDO_ANCHO);
	    	ban.y=-1;
	    }
	    public int comprobarChoque(){
	    	Personaje pe = per.get(0);
	    	
	    	int len1 = bom.size();
		        for(int i =0; i<len1; i++) {
			    	Bombas bo = bom.get(i);
		        	if(bo.x == pe.x && bo.y == pe.y){
		        		aleatoriobom(bo);
		        		tip=1;
		        	}
	        }
		    int len2 = ban.size();
		        for(int i =0; i<len2; i++) {
		        	Bananas ba = ban.get(i);
		        	if(ba.x == pe.x && ba.y == pe.y){
		        		aleatorioban(ba);
		        	tip=3;
		        	}
		    }
		        return tip;
	    }
}
