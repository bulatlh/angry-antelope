package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

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
	
	Vector2 	position = new Vector2();
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
	
	public Body createPlayer(com.badlogic.gdx.physics.box2d.World box2dworld) {
		BodyDef def = new BodyDef();
		def.type = BodyType.DynamicBody;
		player = box2dworld.createBody(def);
 
		player.setTransform(bounds.width/2, bounds.height/2, 0);
		
		PolygonShape poly = new PolygonShape();		
		poly.setAsBox(bounds.width/2, bounds.height/2, new Vector2(getCenterPosition().x + Constants.CAR_BOX_SHIFT_X, getCenterPosition().y + Constants.CAR_BOX_SHIFT_Y), (float) 0);
		
		
		playerPhysicsFixture = player.createFixture(poly, 10);
		poly.dispose();				
  
		
		return player;
	}
	
	public void drawCar(SpriteBatch spriteBatch) {		
		carSprite.setPosition(position.x, position.y);
		carSprite.setOrigin(bounds.width/2, bounds.height/2);
		carSprite.setRotation((float) Math.toDegrees(player.getAngle()));
		carSprite.draw(spriteBatch);
	}
	
	public void rotateCW(com.me.mygdxgame.WorldRenderer renderer, boolean reverse){
		int mult = -1;
		if (reverse){
			mult = 1;
		}	
		player.setAngularVelocity(mult*Constants.ROTATE_SPEED);
		rotationSpeed = mult*Constants.ROTATE_SPEED;
	}
	
	public void rotateCCW(com.me.mygdxgame.WorldRenderer renderer, boolean reverse){
		int mult = 1;
		if (reverse){
			mult = -1;
		}		
		player.setAngularVelocity(mult*Constants.ROTATE_SPEED);
		rotationSpeed = mult*Constants.ROTATE_SPEED;
	}
	
	public void rotating(float val){
		rotation += val;
	}
	
	public void clearAngularVelocity(){
		player.setAngularVelocity(0);
		rotationSpeed = 0;
		
	}
	
	public Sprite getSprite(){
		return carSprite;
	}
	
	public float getRotationSpeed(){
		return rotationSpeed;
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
		
		float tempRotation = (float) Math.toRadians(rotation + Constants.DEGREE_OFFSET);
		
		player.setLinearVelocity((float) Math.cos(tempRotation)*acceleration, (float) Math.sin(tempRotation)*acceleration);		
	}
	
	public void move(float val){
		position.add(player.getLinearVelocity().x*val, player.getLinearVelocity().y*val);
	}
	
	
	public void applyFriction() {
		acceleration *= Constants.ACCELERATION_LOSS;
		if (Math.abs(acceleration) <= Constants.ACCELERATION_MIN){
			acceleration = 0;
		}
		float tempRotation = (float) Math.toRadians(rotation + Constants.DEGREE_OFFSET);		
		player.setLinearVelocity((float) Math.cos(tempRotation)*acceleration, (float) Math.sin(tempRotation)*acceleration);		
	}
}
