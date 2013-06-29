package com.me.mygdxgame;


import com.me.mygdxgame.Car;
import com.me.mygdxgame.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class WorldRenderer {

	private World world;
	private OrthographicCamera cam;

	/** For area location **/
	private long startTime;
	private long currentTime;
	private boolean inArea = false;
	private final int DURATION = 5; 
	
	/** for debug rendering **/
	ShapeRenderer debugRenderer = new ShapeRenderer();

	/** Textures **/
	private Texture carTexture;
	private Sprite carSprite;
	private SpriteBatch spriteBatch;
	private boolean debug = false;
	private int width;
	private int height;
	private float ppuX;	// pixels per unit on the X axis
	private float ppuY;	// pixels per unit on the Y axis
	public void setSize (int w, int h) {
		this.width = w;
		this.height = h;
		ppuX = (float)width / Constants.CAMERA_WIDTH;
		ppuY = (float)height / Constants.CAMERA_HEIGHT;
	}

	public WorldRenderer(World world, boolean debug) {
		this.world = world;
		this.cam = new OrthographicCamera(Constants.CAMERA_WIDTH, Constants.CAMERA_HEIGHT);
		this.cam.position.set(Constants.CAMERA_WIDTH / 2f, Constants.CAMERA_HEIGHT / 2f, 0);
		this.cam.update();
		this.debug = debug;
		spriteBatch = new SpriteBatch();
		loadTextures();
	}

	private void loadTextures() {
		carTexture = new  Texture(Gdx.files.internal("images/ambulance/ambulance-1.png"));
		carSprite = new Sprite(carTexture);
		
		Car car = world.getCar();
		Rectangle rect = car.getBounds();
		carSprite.setSize(rect.width, rect.height);
	}

	public void render() {
		Car car = world.getCar();
		
		//this.cam.position.set(car.getCenterPosition().x, car.getCenterPosition().y, 0);
				
		this.cam.update();
	    spriteBatch.setProjectionMatrix(this.cam.combined);
		spriteBatch.begin();
		drawCar();
		drawInterface();
		spriteBatch.end();
//		if (true){
		if(debug){
			drawDebug();
		}
		
		// check if in area
		if (carInArea(car)) {
			currentTime = System.currentTimeMillis();
			
			// we were previously in the area
			if (inArea) {
				// check if done
				int differenceTime = (int) ((currentTime - startTime) / 1000);
				
				// calculate the time remaining in area
				int remainingTime = DURATION - differenceTime;
				
				if (remainingTime <= 0) {
					System.out.println("DONE");
					inArea = false;
				} else {
					System.out.println("TIME LEFT " + remainingTime + " SECONDS");
				}
				
			} else {
				System.out.println("WELCOME TO LOADING ZONE");
				// set boolean to true
				inArea = true;
				// make start = time
				startTime = System.currentTimeMillis();
			}
		} else {
			System.out.println(car.position);
			startTime = System.currentTimeMillis();
		}
	}
	
	private boolean carInArea(Car car) {
		float x = car.position.x;
		float y = car.position.y;
		
		if (x < 500 && x > 300) {
			if ( y < 500 && y > 300) {
				return true;
			}
		}
		
		return false;
	}

	private void drawInterface() {
		
	}

	public void rotateCW(){
		int mult = -1;
		if (world.getCar().getAcceleration() < 0){
			mult = 1;
		}		
		this.rotating(mult*Constants.ROTATE_SPEED);
	}
	
	public void rotateCCW(){
		int mult = 1;
		if (world.getCar().getAcceleration() < 0){
			mult = -1;
		}		
		this.rotating(mult*Constants.ROTATE_SPEED);
	}
	
	public void rotating(float val){
		//this.cam.rotate(-1*val);
		this.cam.update();
	}
	
	private void drawCar() {
		Car car = world.getCar();
		
		carSprite.setPosition(car.getPosition().x, car.getPosition().y);
		carSprite.setOrigin(carSprite.getWidth()/2,carSprite.getHeight()/2);
		carSprite.setRotation(car.getRotation());
		carSprite.draw(spriteBatch);
	}

	private void drawDebug() {
		// render blocks
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Rectangle);
		// render Bob
		Car car = world.getCar();
		Rectangle rect = car.getBounds();
		float x1 = car.getPosition().x + rect.x;
		float y1 = car.getPosition().y + rect.y;
		debugRenderer.setColor(new Color(0, 1, 0, 1));
		debugRenderer.rect(x1, y1, rect.width, rect.height);
		debugRenderer.end();
	}
}
