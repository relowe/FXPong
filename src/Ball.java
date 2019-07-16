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

import javafx.scene.image.Image;

/**
 * This is the ball for a game of pong.
 * @author Robert Lowe
 */
public class Ball extends PhysicsSprite {
    /**
     * Construct the ball and load its graphics.
     */
    public Ball()
    {
        setImage(new Image(getClass().getResourceAsStream("image/ball.png")));
    }
    
    
    /**
     * Bounce!
     * @param The thing we are bouncing off of.
     */
    @Override
    public void handleCollision(SolidObject other)
    {
        BoundingBox ob = other.getBounds();
        
        //handle x direction modification
        if(ob.getLeft() < getX()) {
            //see if we need to invert
            if(getDx() < 0) {
                setDx(-getDx());
            }
            
            //give it a little kick
            setDx(getDx()+10);
        } else {
            //see if we need to invert
            if(getDx() > 0) {
                setDx(-getDx());
            }
            
            //give it a little kick
            setDx(getDx()-10);
        }
        
        //do a little y trajectory change
        double cy = ob.getTop() + ob.getHeight() / 2.0;
        double p = (getY() - cy) /ob.getHeight();
        setDy(getDy() + p * 150);
        
    }
}
