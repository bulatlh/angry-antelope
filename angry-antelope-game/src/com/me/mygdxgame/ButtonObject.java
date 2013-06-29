package com.me.mygdxgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class ButtonObject {

	public enum ButtonState{
		PRESSED,NOT_PRESSED
	}
	private Vector2 position = new Vector2();
	private int width;
	private int height;
	private Texture texture;
	private ButtonState state = ButtonState.NOT_PRESSED;
	
	public ButtonObject(Texture t, int x, int y){
		texture = t;
		height = t.getHeight();
		width = t.getWidth();
		position.x = x;
		position.y = y;
	}
	
	public void pressed(){
		if(state == ButtonState.NOT_PRESSED){
			state = ButtonState.PRESSED;
		}
		else if(state == ButtonState.PRESSED){
			state = ButtonState.NOT_PRESSED;
		}
	}
	
	public boolean clickedButton(int x, int y){
		if(position.x < x && position.x+width > x){
			if(position.y < y && position.y+height > y){
				return true;
			}
		}
		return false;
	}

	public void render() {
		
	}
	
}
