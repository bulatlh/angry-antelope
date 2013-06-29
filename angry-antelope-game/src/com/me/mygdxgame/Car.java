package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Car {

	public enum State {
		IDLE, WALKING
	}
	public static int health = Constants.PLAYER_HEALTH;
	float rotation;
	float rotationSpeed;

	static Vector2 	position = new Vector2();
	float   	acceleration;
	Vector2 	velocity = new Vector2();
	Rectangle 	bounds = new Rectangle();
	State		state = State.IDLE;

	Body player;
	Fixture playerPhysicsFixture;
	
	
	private Texture carTexture;
	private Sprite carSprite;	
	
	public Car(Vector2 position) {
		this.rotation = 0;
		this.rotationSpeed = 0;
		this.acceleration = 0;
		this.position = position;
		this.bounds.height = Constants.CAR_HEIGHT;
		this.bounds.width = Constants.CAR_WIDTH;
		
		carTexture = new  Texture(Gdx.files.internal("images/ambulance/ambulance-1.png"));
		carSprite = new Sprite(carTexture);
		carSprite.setSize(bounds.width, bounds.height);
	}
	
	public boolean nearby(Zombie z){
		float xdiff = getCenterPosition().x - z.getCenterPosition().x;
		float ydiff = getCenterPosition().y - z.getCenterPosition().y;
		
		if (Math.sqrt(xdiff*xdiff + ydiff*ydiff) < Constants.HIT_RADIUS){
			return true;
		}
		return false;
		
	}
	
	public void draw(SpriteBatch spriteBatch) {		
		carSprite.setPosition(position.x, position.y);
		carSprite.setOrigin(bounds.width/2, bounds.height/2);
		carSprite.setRotation(rotation);
		carSprite.draw(spriteBatch);
	}

	public Vector2 getCenterPosition() {
		return new Vector2(position.x+Constants.CAR_WIDTH/2,position.y+Constants.CAR_HEIGHT/2);
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
	
	public static Vector2 getPosition(){
		return position;
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
	
	public Vector2 getVelocity(){
		return velocity;
	}
	
	public float getAcceleration(){
		return acceleration;
	}
	
	public void setState(State newState){
		this.state = newState;
	}
	
	public void update(float delta){
		
		
		float tempRotation = (float) Math.toRadians(rotation + Constants.DEGREE_OFFSET);
//		velocity.y += (float) (Math.sin(tempRotation)*acceleration*delta);
//		velocity.x += (float) (Math.cos(tempRotation)*acceleration*delta);
		
//		if(Constants.MAX_VELOCITY < Math.sqrt(velocity.x * velocity.x + velocity.y * velocity.y)){
//			velocity.x = (float) (Constants.MAX_VELOCITY * Math.cos(tempRotation));
//			velocity.y = (float) (Constants.MAX_VELOCITY * Math.sin(tempRotation));
//		}
//		position.add(velocity.cpy().mul(delta));
		position.add(new Vector2((float) Math.cos(tempRotation)*acceleration*delta,
				(float) (Math.sin(tempRotation)*acceleration*delta)));
	}

	public void acceleration(int i) {
		float tempAccel = Constants.CAR_ACCELERATION;
		float tempMax = Constants.CAR_MAX_ACCELERATION;
		if (acceleration < 0){
			tempAccel *= Constants.CAR_BACK_ACCELERATION_SCALE;	
			tempMax *= Constants.CAR_BACK_ACCELERATION_SCALE;
		}
		acceleration += i*tempAccel;
		if(Math.abs(acceleration) > tempMax){
			acceleration = i*tempMax;
		}
	}
	
	public void applyFriction() {
		acceleration *= Constants.ACCELERATION_LOSS;
		if (Math.abs(acceleration) <= Constants.ACCELERATION_MIN){
			acceleration = 0;
		}
	}
}
