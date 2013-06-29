package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Zombie {
	private enum State{
		IDLE,STALK, ATTACK
	}
	float rotation;
	
	State state = State.STALK;
	Vector2 	position = new Vector2();
	float   	acceleration;
	Rectangle 	bounds = new Rectangle();
	Sprite sprite;
	static Texture zombieTexture = new Texture(Gdx.files.internal("images/ambulance/zombie.png"));
	
	
	public Zombie(Vector2 position) {
		this.rotation = 0;
		this.acceleration = 0;
		this.position = position;
		this.bounds.height = Constants.ZOMBIE_HEIGHT;
		this.bounds.width = Constants.ZOMBIE_WIDTH;
		
		Sprite temp = new Sprite(zombieTexture);
		temp.setSize(bounds.width, bounds.height);
		sprite = temp;
	}
	
	public void rotateCW(com.me.mygdxgame.WorldRenderer renderer, boolean reverse){
		int mult = -1;
		if (reverse){
			mult = 1;
		}
		this.rotating(mult*Constants.ROTATE_SPEED,renderer);
	}
	
	public void rotateCCW(com.me.mygdxgame.WorldRenderer renderer, boolean reverse){
		int mult = 1;
		if (reverse){
			mult = -1;
		}		
		this.rotating(mult*Constants.ROTATE_SPEED,renderer);
	}
	
	public void rotating(float val,WorldRenderer renderer){
		rotation += val;
		renderer.rotating(val);
	}
	
	public float getRotation(){
		return rotation;
	}
	
	public Vector2 getPosition(){
		return position;
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
	
	public float getAcceleration(){
		return acceleration;
	}
	
	public void update(float delta){
		float tempRotation = (float) Math.toRadians(rotation + Constants.DEGREE_OFFSET);
//			velocity.y += (float) (Math.sin(tempRotation)*acceleration*delta);
//			velocity.x += (float) (Math.cos(tempRotation)*acceleration*delta);
		
//			if(Constants.MAX_VELOCITY < Math.sqrt(velocity.x * velocity.x + velocity.y * velocity.y)){
//				velocity.x = (float) (Constants.MAX_VELOCITY * Math.cos(tempRotation));
//				velocity.y = (float) (Constants.MAX_VELOCITY * Math.sin(tempRotation));
//			}
//			position.add(velocity.cpy().mul(delta));
		position.add(new Vector2((float) Math.cos(tempRotation)*acceleration*delta,
				(float) (Math.sin(tempRotation)*acceleration*delta)));
	}

	public void follow(float x, float y) {
		float ydiff = (float) (Math.random()*Constants.HALF_CAR_HEIGHT+ y - position.y);
		float xdiff = (float) (Math.random()*Constants.HALF_CAR_HEIGHT + x - position.x);
		
		float degrees = (float) Math.atan((ydiff)/(xdiff));
		
		if (xdiff > 0){
			degrees = degrees+135;
		}		
		if(state == State.IDLE){
			if(Constants.ZOMBIE_TRACKING_RADIUS > Math.sqrt((position.x-x)*(position.x-x) + (position.y-y)*(position.y-y))){
				state = State.STALK;				
			}
			acceleration = Constants.MIN_ZOMBIE_ACCELERATION;
		}
		else if(state == State.STALK){
			if(Constants.ZOMBIE_TRACKING_RADIUS < Math.sqrt((position.x-x)*(position.x-x) + (position.y-y)*(position.y-y))){
				state = State.IDLE;
				rotation = (float) (Math.random()*180);
			}
			else{
				if(Constants.ZOMBIE_ATTACKING_RADIUS > Math.sqrt((position.x-x)*(position.x-x) + (position.y-y)*(position.y-y))){
					state = State.ATTACK;
					acceleration = -100;
					//Call Attack here
					Car.health --;
				}
			}
		}
		else if(state == State.ATTACK){
			state = State.STALK;
		}
		
		if(state == State.ATTACK || state == State.STALK){
			this.rotation = (float) Math.toDegrees(degrees)+90;
		}
		if(state == State.STALK){
			acceleration();
		}
		if(state == State.IDLE){
			acceleration = Constants.MIN_ZOMBIE_ACCELERATION;
		}
		else{
			acceleration();
		}
		
	}
	
	public void acceleration() {
		float tempAccel = (float) (Constants.ZOMBIE_ACCELERATION+Math.random()*2);
		float tempMax = Constants.ZOMBIE_MAX_ACCELERATION;
		
		acceleration += tempAccel;
		if(Math.abs(acceleration) > tempMax){
			acceleration = tempMax;
		}
	}
	
	public void draw(SpriteBatch spriteBatch) {
		sprite.setPosition(getPosition().x, getPosition().y);
		sprite.setOrigin(bounds.width/2,bounds.height/2);
		sprite.setRotation(rotation);
		sprite.draw(spriteBatch);
	}
}
