package com.me.mygdxgame.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.mygdxgame.Constants;
import com.me.mygdxgame.ui.ButtonObject;

public class RullezScreen implements Screen, InputProcessor {

	Texture titleTexture = new Texture(Gdx.files.internal("images/Rules.png"));
	
	TitleScreenRenderer renderer = new TitleScreenRenderer();
	SpriteBatch batch = new SpriteBatch();
	
	ArrayList<ButtonObject> buttons;
	
	
	public RullezScreen(){
		Texture.setEnforcePotImages(false);
		buttons = new ArrayList<ButtonObject>();
		ButtonObject button = new ButtonObject(68, 752, 137, 321);
		buttons.add(button);
		
		
	}
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT); 
		batch.begin();
	    batch.draw(titleTexture, 200, 150);
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
	
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//		
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		System.out.println("OMG IM TOUCHING THIS "+screenX + " - " +screenY);
		
		Constants.theGreatCreater.setScreen(new TitleScreen());
		
			
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

