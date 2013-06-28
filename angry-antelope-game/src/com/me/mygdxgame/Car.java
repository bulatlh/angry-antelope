package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Car {

	public enum State {
		IDLE, WALKING
	}
	float rotation;
	
	Vector2 	position = new Vector2();
	private float   	acceleration;
	Vector2 	velocity = new Vector2();
	Rectangle 	bounds = new Rectangle();
	State		state = State.IDLE;

	public Car(Vector2 position) {
		this.rotation = 0;
		this.acceleration = 0;
		this.position = position;
		this.bounds.height = Constants.CAR_HEIGHT;
		this.bounds.width = Constants.CAR_WIDTH;
	}
	
	public void rotateCW(){
		int mult = -1;
		if (acceleration < 0){
			mult = 1;
		}
		this.rotating(mult*Constants.ROTATE_SPEED);
	}
	
	public void rotateCCW(){
		int mult = 1;
		if (acceleration < 0){
			mult = -1;
		}		
		this.rotating(mult*Constants.ROTATE_SPEED);
	}
	
	public void rotating(float val){
		rotation += val;
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
		
		
		if (Gdx.app.getType().equals(ApplicationType.Android)){
			rotation -= Gdx.input.getAccelerometerY()+Constants.ROTATION_SCALAR;
			acceleration = (float) (Gdx.input.getAccelerometerX()*Constants.ACCELEROMETER_SCALAR);
		}
		
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

	public Vector2 getCenterPosition() {
		return new Vector2(position.x+Constants.CAR_WIDTH/2,position.y+Constants.CAR_HEIGHT/2);
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
