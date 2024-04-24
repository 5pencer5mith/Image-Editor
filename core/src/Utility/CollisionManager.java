package Utility;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.imageeditor.Rectangle2D;

public class CollisionManager {
	
	public static CollisionManager Instance;
	
	public CollisionManager() { Instance = this; }
	
	public IHoverable getHovered(Vector2 coordinates) {
		Rectangle2D rec;
		
		for (int i = 0; i < InputManager.Instance.ihover.size; i++) {
			rec = (Rectangle2D) InputManager.Instance.ihover.get(i);
			if(coordinates.x > rec.Position.x && coordinates.x < rec.Position.x + rec.Scale.x) {
                if(coordinates.y > rec.Position.y && coordinates.y < rec.Position.y + rec.Scale.y) {
                    return (IHoverable) rec;
                }
            }
		}
		return null;
	}
	
	public IClickable getClicked(Vector2 coordinates) {
		Rectangle2D rec;
		
		for (int i = 0; i < InputManager.Instance.iclick.size; i++) {
			rec = (Rectangle2D) InputManager.Instance.iclick.get(i);
			if(coordinates.x > rec.Position.x && coordinates.x < rec.Position.x + rec.Scale.x) {
				if(coordinates.y > rec.Position.y && coordinates.y < rec.Position.y + rec.Scale.y) {
					return (IClickable) rec;
				}
			}
		}
		
		return null;

	}

}
