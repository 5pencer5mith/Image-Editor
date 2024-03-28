package com.mygdx.imageeditor;

import java.util.Random;

import javax.naming.ReferralException;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class ImageEditor extends ApplicationAdapter {
	SpriteBatch batch;
	Rectangle2D rectangle;
	private Vector2 _screenSize;
	@Override
	public void create () {
		batch = new SpriteBatch();
		_screenSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		rectangle = new Rectangle2D(new Vector2(200, 100), new Vector2(200, 100), new Vector2(5,5), Color.RED);
		
		
	}

	@Override
	public void render () {
		Random r = new Random();
		
		ScreenUtils.clear(.0f, 0f, 0f, 1);
		batch.begin();
		batch.draw(rectangle.RecTexture, rectangle.Position.x, rectangle.Position.y);
		if (rectangle.Position.x > _screenSize.x || rectangle.Position.x < _screenSize.x * -1) {
			rectangle.Velocity.x *= -1;
			rectangle.changeColor(new Color(r.nextFloat(), r.nextFloat(), r.nextFloat(), 1));
		}
		if (rectangle.Position.y > _screenSize.y || rectangle.Position.y < _screenSize.y * -1) {
			rectangle.Velocity.y *= -1;
			rectangle.changeColor(new Color(r.nextFloat(), r.nextFloat(), r.nextFloat(), 1));
		}
		rectangle.Position.add(rectangle.Velocity);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		rectangle.RecTexture.dispose();
	}
}
