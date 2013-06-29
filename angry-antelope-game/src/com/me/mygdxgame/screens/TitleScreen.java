package com.me.mygdxgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.me.mygdxgame.Constants;
import com.me.mygdxgame.GameScreen;
import com.me.mygdxgame.controller.GameState;

public class TitleScreen implements Screen, InputProcessor {

	Texture titleTexture = new Texture(Gdx.files.internal("images/workingTitleScreen.png"));
	
	TitleScreenRenderer renderer = new TitleScreenRenderer();
	SpriteBatch batch = new SpriteBatch();
	
	Vector2 startGamePosBotLeft = new Vector2(67,757);
	Vector2 startGamePosTopRight = new Vector2(411,617);
	
	public TitleScreen(){
		Texture.setEnforcePotImages(false);
	}
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT); 
		batch.begin();
	    batch.draw(titleTexture, 0, 0);
	    batch.end();
//		renderer.render();
//	    Constants.theGreatCreater.setScreen(new GameScreen());
//	    System.out.println("OH HI THERE");
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

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
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		System.out.println("OMG A KEYCODE"+keycode);
		Constants.theGreatCreater.setScreen(new GameScreen());
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		System.out.println("OMG IM TOUCHING THIS "+screenX+screenY);
		if(screenX < startGamePosTopRight.x && screenX > startGamePosBotLeft.x){
			if(screenY < startGamePosTopRight.y && screenY > startGamePosBotLeft.y){
				//go to Game Screen
				Constants.theGreatCreater.setScreen(new GameScreen());
			}
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		System.out.println("OMG IM TOUCHING THIS "+screenX+screenY);
		if(screenX < startGamePosTopRight.x && screenX > startGamePosBotLeft.x){
			if(screenY < startGamePosTopRight.y && screenY > startGamePosBotLeft.y){
				//go to Game Screen
				Constants.theGreatCreater.setScreen(new GameScreen());
			}
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
