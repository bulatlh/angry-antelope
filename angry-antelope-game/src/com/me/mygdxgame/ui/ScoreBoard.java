package com.me.mygdxgame.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.me.mygdxgame.Car;
import com.me.mygdxgame.Constants;

public class ScoreBoard {
	String name = null;
	private BitmapFont font;
	private float textWidth;
	private float textHeight;
	private Vector2 v; 
	private int score; 
	
	public ScoreBoard(String name) {
		this.name = name;
		score = 0;
		font = new BitmapFont();
//		font.
		textWidth = 700; 
//				font.getBounds(name).width;
		textHeight = 400; 
//				font.getBounds(name).height;
		
		v = Car.getPosition();
		
		v.x -= 100;
		v.y -= 100;
	}
	
	public void update(int score){
		this.score = score;  
	}
	
	public void draw(SpriteBatch spriteBatch){
		font.draw(spriteBatch, 
				name + ": " + Constants.SCORE, 
				v.x, v.y); 
	}
	
	
}
