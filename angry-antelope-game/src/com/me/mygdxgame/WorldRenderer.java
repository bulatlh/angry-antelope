package com.me.mygdxgame;


import com.me.mygdxgame.Car;
import com.me.mygdxgame.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

public class WorldRenderer {

	private World world;
	private OrthographicCamera cam;

	/** for debug rendering **/

	
	Box2DDebugRenderer box2drenderer;
	
	com.badlogic.gdx.physics.box2d.World box2dworld;
	
	Body player;
	
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
		player = car.createPlayer(box2dworld);
		player.setTransform(10.0f, 4.0f, 0);
		player.setFixedRotation(true);		
		
	}


	public void render() {
		Car car = world.getCar();
		
		//this.cam.position.set(car.getCenterPosition().x, car.getCenterPosition().y, 0);
				
		this.cam.update();
	    spriteBatch.setProjectionMatrix(this.cam.combined);
		spriteBatch.begin();
		car.drawCar(spriteBatch);
		drawTargets();
		drawZombies();
		drawInterface();
		spriteBatch.end();

		Matrix4 debugMatrix=new Matrix4(cam.combined);
		 
		debugMatrix.scale(1, 1, 1f);
		
		box2dworld.step(Gdx.graphics.getDeltaTime(), 4, 4);
		
		car.move(Gdx.graphics.getDeltaTime());
		car.rotating(car.getRotationSpeed());
		rotating(car.getRotationSpeed());
		
		box2drenderer.render(box2dworld, debugMatrix);		
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
