package com.the69th.fm;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.the69th.screen.SplashScreen;

public class MainGame extends Game {
	Animation anim;
	boolean doneLoading = false;
	float stateTime = 0f;
	TextureAtlas loader;
	
	public SpriteBatch batch;
	
	@Override
	public void create () {
		loader = new TextureAtlas("img/load.pack");
		TextureRegion[] frames = new TextureRegion[loader.getRegions().size];
		for (int i = 0; i < frames.length; i++)
			frames[i] = loader.findRegion("tmp-" + i);
		anim = new Animation(.05f, frames);
		batch = new SpriteBatch();
	}

	@Override
	public void render () {
		super.render();
		if (!Data.assets.update()) {
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			stateTime += Gdx.graphics.getDeltaTime();
			batch.begin();
			batch.draw(anim.getKeyFrame(stateTime, true), Gdx.graphics.getWidth() / 4f, Gdx.graphics.getHeight() / 2f - Gdx.graphics.getWidth() / 4f, 
					Gdx.graphics.getWidth() / 2f, Gdx.graphics.getWidth() / 2f);
			batch.end();
		} else if (!doneLoading) {
			doneLoading = true;
			setScreen(new SplashScreen(this));
		}
		
	}
	
	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
	}
}
