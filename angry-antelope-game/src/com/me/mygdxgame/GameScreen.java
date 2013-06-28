package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;

public class GameScreen implements Screen, InputProcessor {

	private World world;
	private WorldRenderer renderer;
	private WorldController controller;
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		if (Gdx.app.getType().equals(ApplicationType.Android)){
			controller.accelerometerControlX();
			controller.accelerometerControlY();
		}
		
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		controller.update(delta);
		renderer.render();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		renderer.setSize(width, height);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		world = new World();
		renderer = new WorldRenderer(world, false);
		controller = new WorldController(world, renderer);
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		if (keycode == Keys.A){
			controller.leftPressed();
		}
		if (keycode == Keys.S){
			controller.downPressed();
		}
		if (keycode == Keys.D){
			controller.rightPressed();
		}
		if (keycode == Keys.W){
			controller.upPressed();
		}		
		
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		
		if (keycode == Keys.A){
			controller.leftReleased();
		}
		if (keycode == Keys.S){
			controller.downReleased();
		}
		if (keycode == Keys.D){
			controller.rightReleased();
		}
		if (keycode == Keys.W){
			controller.upReleased();
		}
		
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		if (Gdx.app.getType().equals(ApplicationType.Android)){
			if(Gdx.input.getAccelerometerX() >4){
				controller.leftPressed();
			}
			if(Gdx.input.getAccelerometerX() <-4){
				controller.rightPressed();
			}
		}
		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		if (!Gdx.app.getType().equals(ApplicationType.Android))
			return false;
		if(Gdx.input.getAccelerometerY() >4){
			controller.leftReleased();
		}
//		if (x > width / 2 && y > height / 2) {
		if(Gdx.input.getAccelerometerY() <-4){
			controller.rightReleased();
		}
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
