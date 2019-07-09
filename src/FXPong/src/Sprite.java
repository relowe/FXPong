/*
 * Copyright (C) 2019 Robert Lowe
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;


/**
 * This is a generic sprite.  It provides a mechanism to put a
 * 2d graphic image on the screen, and it provides basic AABB 
 * collision detection.
 * @author Robert lowe
 */
public class Sprite extends ImageView implements SolidObject {
    /**
     * Create an empty sprite.
     */
    public Sprite()
    {
        super();
        
        // Really, noting to do here for a generic sprite.
    }

    /**
     * Get the boundaries of the sprite image.
     * @return Bounding box representing the parent boundaries.
     */
    @Override
    public BoundingBox getBounds() {
        Bounds pb = getBoundsInParent();
        return new BoundingBox(getX(), getY(), pb.getWidth(), pb.getHeight());
    }

    /**
     * Detect collisions between this sprite and other solid objects.
     * @param other The other solid object.
     * @return True if there is a collision, false otherwise.
     */
    @Override
    public boolean detectCollision(SolidObject other) {
        return BoundingBox.detectCollision(getBounds(), other.getBounds());
    }

    /**
     * By default, sprites just ignore collisions.
     * @param other The object we have collided with
     */
    @Override
    public void handleCollision(SolidObject other) {
        //do nothing!
    }
}
