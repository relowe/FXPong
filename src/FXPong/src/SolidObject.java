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

/**
 * The solid object interface defines the functions that are needed
 * for basic collision detection and handling.
 * @author Robert Lowe
 */
public interface SolidObject {
    /**
     * Get the bounding box for collision detection.
     * @return Bounding box as defined by the solid object.
     */
    abstract BoundingBox getBounds();
    
    /**
     * Returns true if this box is in collision with the other box.
     * @param other The other SolidObject we are (maybe) colliding with.
     * @return True if we are in collision, false otherwise.
     */
    abstract boolean detectCollision(SolidObject other);
    
    /**
     * Handle collision with the other object.  It is assumed this
     * will be called by some outside arbiter according to the 
     * application at hand.
     * @param other The SolidObject we have collided with.
     */
    abstract void handleCollision(SolidObject other);
}
