package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

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

	Body zombieBody;
	Fixture zombiePhysicsFixture;

	
	
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
	
	public Body createZombie(com.badlogic.gdx.physics.box2d.World box2dworld) {
		BodyDef def = new BodyDef();
		def.type = BodyType.DynamicBody;
		zombieBody = box2dworld.createBody(def);
 
		zombieBody.setTransform(bounds.width/2, bounds.height/2, 0);
		
		CircleShape poly = new CircleShape();		
		poly.setRadius(bounds.height/2);
		poly.setPosition(new Vector2(getCenterPosition().x, getCenterPosition().y));
		
		
		zombiePhysicsFixture = zombieBody.createFixture(poly, 10);
		poly.dispose();				
  
		
		return zombieBody;
	}	
	
	public void rotateCW(com.me.mygdxgame.WorldRenderer renderer, boolean reverse){
		int mult = -1;
		if (reverse){
			mult = 1;
		}
		zombieBody.setAngularVelocity(mult*Constants.ROTATE_SPEED);
	}
	
	public void rotateCCW(com.me.mygdxgame.WorldRenderer renderer, boolean reverse){
		int mult = 1;
		if (reverse){
			mult = -1;
		}	
		zombieBody.setAngularVelocity(mult*Constants.ROTATE_SPEED);		
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
	
	public float getAcceleration(){
		return acceleration;
	}
	
	public void update(float delta){
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
	
	public Vector2 getCenterPosition() {
		return new Vector2(position.x+Constants.ZOMBIE_WIDTH/2,position.y+Constants.CAR_HEIGHT/2);
	}	
	
	public void acceleration() {
		float tempAccel = (float) (Constants.ZOMBIE_ACCELERATION+Math.random()*2);
		float tempMax = Constants.ZOMBIE_MAX_ACCELERATION;
		
		acceleration += tempAccel;
		if(Math.abs(acceleration) > tempMax){
			acceleration = tempMax;
		}
		
		float tempRotation = (float) Math.toRadians(rotation + Constants.DEGREE_OFFSET);
		
		zombieBody.setLinearVelocity((float) Math.cos(tempRotation)*acceleration, (float) Math.sin(tempRotation)*acceleration);	
		
	}
	
	public void draw(SpriteBatch spriteBatch) {
		sprite.setPosition(zombieBody.getPosition().x + zombieBody.getLocalCenter().x - (bounds.width/2), zombieBody.getPosition().y + zombieBody.getLocalCenter().y  - (bounds.height/2));
		sprite.setOrigin(bounds.width/2,bounds.height/2);
		sprite.setRotation(rotation);
		sprite.draw(spriteBatch);
	}
}
