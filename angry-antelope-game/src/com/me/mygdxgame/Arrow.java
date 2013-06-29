package com.me.mygdxgame;


import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Arrow {

	Vector2 targetPos = new Vector2();
	Vector2 arrowPosition = new Vector2();
	Rectangle bounds = new Rectangle(); 
	Sprite greenSprite; 
	Sprite redSprite;
	Sprite currentSprite; 
	boolean gone = false;
	
	boolean isClose = false;
	
	private long startTime;  
	
	private static Texture ArrowTexture = new Texture(Gdx.files.internal("images/ambulance/arrow.png"));
	private static Texture RedArrow = new Texture(Gdx.files.internal("images/ambulance/arrow_red.png"));
	
	float rotation;  


	Random rand = new Random();

	public Arrow(){
		bounds.height = Constants.ARROW_HEIGHT;
		bounds.width = Constants.ARROW_WIDTH;
		greenSprite = new Sprite(Arrow.ArrowTexture);
		greenSprite.setSize(bounds.width, bounds.height);
		redSprite = new Sprite(Arrow.RedArrow);
		greenSprite.setSize(bounds.width, bounds.height);
		
		currentSprite = greenSprite;
		
		targetPos = new Vector2(700, 500);
		arrowPosition = new Vector2(targetPos);
	}

	public void initTargetPosition(float x, float y) {
		targetPos = new Vector2(x, y);
	}

	public boolean update(float car_x, float car_y){
		if (gone)
			return !gone;
		float x_len = targetPos.x - car_x; 
		float y_len = targetPos.y - car_y;
		float c_len = (float)Math.sqrt( Math.pow(x_len, 2) + Math.pow(y_len, 2));
		float x_coor = 0; 
		float y_coor = 0;
		boolean pickedUp = false;
		if (c_len > Constants.CAR_HEIGHT){
			x_coor = car_x + 
					Constants.CAR_HEIGHT * x_len / c_len;
			y_coor = car_y +  
					Constants.CAR_HEIGHT * y_len / c_len;
			isClose = false;
			if (currentSprite != greenSprite)
				currentSprite = greenSprite;
		}else {
			if (isClose){
				// if i was close
				long currentTime = System.currentTimeMillis();
				int left = Constants.DURATION - (int)((currentTime - startTime) / 1000);
				if ( left <= 1 ){
					currentSprite = redSprite;
				}
				if ( left < 0 ){
					gone = true;
					pickedUp = true;
//					isClose = false;
					System.out.println("Aaaand... Gone!");
				}else {
					System.out.println("Time left " + left  );
				}
				
			}else {
//				I was not close 
				startTime = System.currentTimeMillis(); 
			}
			
			isClose = true;
			x_coor = targetPos.x;
			y_coor = targetPos.y;
		}
		arrowPosition.set(x_coor, y_coor);
		
		return pickedUp;
	}

	public void draw(SpriteBatch spriteBatch){
		if ( !gone ){
			currentSprite.setPosition(arrowPosition.x, arrowPosition.y);
			currentSprite.setOrigin(currentSprite.getWidth()/2, currentSprite.getHeight()/2);
			currentSprite.setRotation(0);
			currentSprite.draw(spriteBatch);
		}
	}

	public Rectangle getBounds(){
		return bounds;
	}

	public void setRotation(float rotation){
		this.rotation = rotation;
	}

	public Vector2 getArrowPosition(){
		return arrowPosition;
	}

}
