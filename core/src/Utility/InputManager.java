package Utility;


import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.buttons.Button;
import com.mygdx.imageeditor.ImageEditor;

public class InputManager implements InputProcessor {
	
	public Array<IClickable> iclick = new Array<IClickable>();
	
	public Array<IHoverable> ihover = new Array<IHoverable>();
	
	public Array<Button> Buttons = new Array<Button>();

	public static InputManager Instance;
	
	private IHoverable _hoveredButton;
	
	private IClickable _currentlyClicked;
	
	private boolean _controlPressed;
	
	public InputManager() {
		Instance = this;
	}
	
	public CollisionManager CollisionManager;

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		if (keycode == Keys.CONTROL_LEFT) System.out.println("You pressed Control!");
		if (_controlPressed && keycode == Keys.S) {
			if (ImageInputOutput.Instance.imageFolderLocation == null) return true;
			try {ImageInputOutput.Instance.saveImage(ImageInputOutput.Instance.imageFolderLocation + "/output.bmp");}
			catch (IOException e) {e.printStackTrace();}
		}
		if (keycode == Keys.CONTROL_LEFT) _controlPressed = true;
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		if (keycode == Keys.CONTROL_LEFT) _controlPressed = false;
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
		
		Vector2 worldPosition = new Vector2(screenX, ImageEditor.Instance.ScreenSize.y - screenY);
		IClickable collision = CollisionManager.Instance.getClicked(worldPosition);
		if (collision != null) collision.onClickDown(worldPosition);
		
		_currentlyClicked = collision;
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		
		if (_currentlyClicked != null) _currentlyClicked.onClickUp(new Vector2(screenX, ImageEditor.Instance.ScreenSize.y - screenY));
		
		return false;
	}

	@Override
	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		mouseMoved(screenX, screenY);
		if(_currentlyClicked != null) _currentlyClicked.onClickDragged(new Vector2(screenX, ImageEditor.Instance.ScreenSize.y - screenY));
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		IHoverable collision = CollisionManager.Instance.getHovered(new Vector2(screenX, ImageEditor.Instance.ScreenSize.y - screenY));
		if(collision != _hoveredButton && _hoveredButton != null) _hoveredButton.onHoverExit();
		if(collision != null) collision.onHovered();
		if (collision != _hoveredButton) _currentlyClicked = null;
		_hoveredButton = collision;
		
		return true;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		// TODO Auto-generated method stub
		return false;
	}

}
