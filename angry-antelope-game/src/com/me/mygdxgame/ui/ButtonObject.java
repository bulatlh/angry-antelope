package com.me.mygdxgame.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class ButtonObject {

	public enum ButtonState{
		PRESSED,NOT_PRESSED
	}
	private Vector2 position = new Vector2();
	private int width;
	private int height;
	private ButtonState state = ButtonState.NOT_PRESSED;
	
	public ButtonObject(Texture t, int x, int y){
		height = t.getHeight();
		width = t.getWidth();
		position.x = x;
		position.y = y;
	}
	
	public ButtonObject(int x, int y, int height, int width){
		this.height= height;
		this.width = width;
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
		if(x > position.x  && x < position.x + width){
			if(position.y < y && position.y - height > y){
				return true;
			}
		}
		return false;
	}

	public boolean isClicked(int x, int y){
		
		if ( x >= position.x  && x < position.x + width){
				return true;
		}
		return false;
	}
	
	
	public void render() {
		
	}
	
}
