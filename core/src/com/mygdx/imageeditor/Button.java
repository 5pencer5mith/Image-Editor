package com.mygdx.imageeditor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Button extends Rectangle2D{
	
	private static int _buttonCount;

	private int _buttonNumber;
	
	public Button(Vector2 scale, Vector2 position, Color recColor) {
		super(scale, position, recColor);
		
		InputManager.Instance.Buttons.add(this);
		_buttonCount += 1;
		_buttonNumber = _buttonCount;
	}
	
	public void onPressed() {
		System.out.println("Button " + _buttonNumber + " pressed");
	}
}