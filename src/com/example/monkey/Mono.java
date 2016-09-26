package com.example.monkey;


import com.exaple.interfases.Pantalla;
import com.exaple.java.AndroidJuego;


public class Mono extends AndroidJuego {
	 @Override
	    public Pantalla getStartScreen() {
	    	return new LoadingScreen(this); 
	    }
}