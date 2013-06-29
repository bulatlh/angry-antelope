package com.me.mygdxgame;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

public class WorldRenderer {

	private World world;
	private OrthographicCamera cam;

	/** for debug rendering **/

	
	Box2DDebugRenderer box2drenderer;
	
	com.badlogic.gdx.physics.box2d.World box2dworld;
		
	/** For area location **/
	private long startTime;
	private long currentTime;
	private boolean inArea = false;
	private final int DURATION = 5; 
	
	/** Textures **/
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
		
		box2drenderer = new Box2DDebugRenderer();

		box2dworld = new com.badlogic.gdx.physics.box2d.World(new Vector2(0, 0), true);		
		Car car = world.getCar();
		
		for(Zombie z:world.getZombies()){
			Body zombieBody = z.createZombie(box2dworld);
			zombieBody.setFixedRotation(true);	
			world.zombieBodies.add(zombieBody);
		}		
		
		
	}


	public void render() {
		Car car = world.getCar();
		
		//this.cam.position.set(car.getCenterPosition().x, car.getCenterPosition().y, 0);
				
		this.cam.update();
	    spriteBatch.setProjectionMatrix(this.cam.combined);
		spriteBatch.begin();
		car.draw(spriteBatch);
		drawTargets();
		drawZombies();
		drawInterface();
		spriteBatch.end();

		Matrix4 debugMatrix=new Matrix4(cam.combined);
		 
		debugMatrix.scale(1, 1, 1f);
		
		box2dworld.step(Gdx.graphics.getDeltaTime(), 4, 4);
		
		//box2drenderer.render(box2dworld, debugMatrix);
		

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
			//System.out.println(car.position);
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
	
	private void drawTargets() {
		TargetManager manager = world.getTargets();
		manager.drawTargets(spriteBatch);
		
	}

	private void drawZombies() {
		// TODO Auto-generated method stub
		for(Zombie z:world.getZombies()){
			z.draw(spriteBatch);
		}
		
	}

	private void drawInterface() {
		
	}
	
	public void rotating(float val){
		//this.cam.rotate(-1*val);
		this.cam.update();
	}
}
