package com.mygdx.imageeditor;

import java.io.IOException;
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
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class ImageEditor extends ApplicationAdapter {
	public static ImageEditor Instance;
	public Vector2 ScreenSize;
	public Array<Rectangle2D> Rectangles = new Array<Rectangle2D>();
	private EditWindow _editWindow;
	private SpriteBatch batch;
	
	@Override
	public void create () {
		Util.testIntToSignedBytes();
		Instance = this;
		batch = new SpriteBatch();
		ScreenSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		InputManager inputManager = new InputManager();
		Gdx.input.setInputProcessor(inputManager);
		
		new ImageInputOutput();
		
		Vector2 editWindowSize = new Vector2(500, ScreenSize.y - 40);
		_editWindow = new EditWindow(editWindowSize, new Vector2(ScreenSize.x - editWindowSize.x, 0));
		
		Button butt = new Button(new Vector2(50, 50), Vector2.Zero, Color.CORAL);
		
		CollisionManager collisionManager = new CollisionManager();
		
	}

	@Override
	public void render () {
		ScreenUtils.clear(0f, 0f, 0f, 1);
		batch.begin();
		
		Rectangle2D rec;
		for (int i = 0; i < Rectangles.size; i++) {
			rec = Rectangles.get(i);
			batch.draw(rec.RecTexture, rec.Position.x, rec.Position.y, rec.Scale.x, rec.Scale.y);
		}
		
		batch.draw(_editWindow.DoodleTexture, _editWindow.Position.x, _editWindow.Position.y, _editWindow.Scale.x, _editWindow.Scale.y);
		
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
	
	public void filesImported (String[] filePaths) {
		Pixmap map = ImageInputOutput.Instance.loadImage(filePaths[0]);
		if (map == null) return;
		
		_editWindow.RecTexture = new Texture(map);
	}
}
