package com.me.mygdxgame;


import com.me.mygdxgame.Car;
import com.me.mygdxgame.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class WorldRenderer {

	private static final float CAMERA_WIDTH = 10f;
	private static final float CAMERA_HEIGHT = 7f;

	private World world;
	private OrthographicCamera cam;

	/** for debug rendering **/
	ShapeRenderer debugRenderer = new ShapeRenderer();

	/** Textures **/
	private Texture carTexture;

	private SpriteBatch spriteBatch;
	private boolean debug = false;
	private int width;
	private int height;
	private float ppuX;	// pixels per unit on the X axis
	private float ppuY;	// pixels per unit on the Y axis
	public void setSize (int w, int h) {
		this.width = w;
		this.height = h;
		ppuX = (float)width / CAMERA_WIDTH;
		ppuY = (float)height / CAMERA_HEIGHT;
	}

	public WorldRenderer(World world, boolean debug) {
		this.world = world;
		this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
		this.cam.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0);
		this.cam.update();
		this.debug = debug;
		spriteBatch = new SpriteBatch();
		loadTextures();
	}

	private void loadTextures() {
		carTexture = new  Texture(Gdx.files.internal("car.png"));
	}

	public void render() {
		
//		GL10 gl = Gdx.graphics.getGL10();

//		System.out.println(gl);
		
	    //gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

	    //this.cam.update();
	    //this.cam.apply(gl);
	    
	    //Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	    
	    
	    //spriteBatch.setProjectionMatrix(this.cam.combined);
		//spriteBatch.begin();
		//drawCar();
//        this.cam.rotate(-.1f);
//		this.cam.translate(1, 1, 1);
//		this.cam.update();
		//spriteBatch.end();
		//if (debug){
		if (true){
			drawDebug();
		}
	}
	

	private void drawCar() {
		Car car = world.getCar();
		spriteBatch.draw(carTexture, car.getPosition().x * ppuX, car.getPosition().y * ppuY, Car.SIZE * ppuX, Car.SIZE * ppuY);
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
