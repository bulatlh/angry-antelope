package com.me.mygdxgame.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.mygdxgame.Constants;
import com.me.mygdxgame.GameScreen;
import com.me.mygdxgame.ui.ButtonObject;

public class TitleScreen implements Screen, InputProcessor {

	Texture titleTexture = new Texture(Gdx.files.internal("images/workingTitleScreen.png"));
	
	TitleScreenRenderer renderer = new TitleScreenRenderer();
	SpriteBatch batch = new SpriteBatch();
	
	ArrayList<ButtonObject> buttons;
	
	
	public TitleScreen(){
		Texture.setEnforcePotImages(false);
		buttons = new ArrayList<ButtonObject>();
//		Texture txr = new Texture(Gdx.files.internal("images/button/start.png"));
		
		ButtonObject button = new ButtonObject(150, 50, 100, 200);
		buttons.add(button);
//		txr = new Texture(Gdx.files.internal("images/button/rules.png"));
		button = new ButtonObject(500, 50, 100, 200);
		buttons.add(button);
//		txr = new Texture(Gdx.files.internal("images/button/achievements.png"));
		button = new ButtonObject(800, 50, 100, 200);
		buttons.add(button);
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
//		System.out.println("OMG IM TOUCHING THIS "+screenX+screenY);
//		if(screenX < startGamePosTopRight.x && screenX > startGamePosBotLeft.x){
//			if(screenY < startGamePosTopRight.y && screenY > startGamePosBotLeft.y){
//				//go to Game Screen
//				Constants.theGreatCreater.setScreen(new GameScreen());
//			}
//		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		System.out.println("OMG IM TOUCHING THIS "+screenX+screenY);
		
		for(int i = 0; i < buttons.size(); ++i){
			if (buttons.get(i).isClicked(screenX, screenY)){
				System.out.println("clicked in");
				switch (i){
					case 0:  
//						Constants.theGreatCreater.setScreen(new GameScreen());
						break; 
					case 1: 
//						Constants.theGreatCreater.setScreen(new GameScreen());
						break;
					case 2: 
//						Constants.theGreatCreater.setScreen(new GameScreen());
						break;
					default: 
						break;
				}
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
