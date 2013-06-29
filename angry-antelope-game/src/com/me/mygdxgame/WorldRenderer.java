package com.me.mygdxgame;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

public class WorldRenderer {

	public static World world;
	private OrthographicCamera cam;

	/** for debug rendering **/

	 Box2DDebugRenderer box2drenderer;
	
	public static com.badlogic.gdx.physics.box2d.World box2dworld = new com.badlogic.gdx.physics.box2d.World(new Vector2(0, 0), true);

	
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
		ppuX = width / Constants.CAMERA_WIDTH;
		ppuY = height / Constants.CAMERA_HEIGHT;
	}

	public WorldRenderer(World world, boolean debug) {
		WorldRenderer.world = world;
		this.cam = new OrthographicCamera(Constants.CAMERA_WIDTH, Constants.CAMERA_HEIGHT);
		this.cam.position.set(Constants.CAMERA_WIDTH / 2f, Constants.CAMERA_HEIGHT / 2f, 0);
		this.cam.update();
		this.debug = debug;
		spriteBatch = new SpriteBatch();
		
		box2drenderer = new Box2DDebugRenderer();

		for(Zombie z:world.getZombies()){
			Body zombieBody = z.createZombie(box2dworld);
			zombieBody.setFixedRotation(true);	
			world.zombieBodies.add(zombieBody);
		}		
		
		
	}


	public void render() {
		Car car = world.getCar();
		
		this.cam.position.set(car.getCenterPosition().x, car.getCenterPosition().y, 0);
				
		this.cam.update();
	    spriteBatch.setProjectionMatrix(this.cam.combined);
		spriteBatch.begin();
		car.update(Gdx.graphics.getDeltaTime());

		drawZombies();
		drawTargets();
		
		car.draw(spriteBatch);		
		drawTargets();
		drawInterface();


		drawScore();

		car.draw(spriteBatch);
		
		spriteBatch.end();
		
		Matrix4 debugMatrix=new Matrix4(cam.combined);
		 
		debugMatrix.scale(1, 1, 1f);
		
		box2dworld.step(Gdx.graphics.getDeltaTime(), 4, 4);
		
		//box2drenderer.render(box2dworld, debugMatrix);
		
		box2dworld.step(Gdx.graphics.getDeltaTime(), 4, 4);		
		//box2drenderer.render(box2dworld, debugMatrix);
	}

	
	private void drawTargets() {
		TargetManager manager = world.getTargets();
		manager.drawTargets(spriteBatch);
	}

	private void drawZombies() {
		Car car = world.getCar();		
		// TODO Auto-generated method stub
		for(Zombie z:world.getZombies()){
			if (car.nearby(z) && z.isAlive){
				z.kill(spriteBatch);
			}
			z.draw(spriteBatch);
		}
		
	}

	private void drawInterface() {
		
	}
	
	private void drawScore(){
		world.scoreBoard.draw(spriteBatch);
	}
	
	public void rotating(float val){
		//this.cam.rotate(-1*val);
		this.cam.update();
	}
}
