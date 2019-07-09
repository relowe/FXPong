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
 * BoundingBox objects are immutable objects which help with
 * access aligned bounding box collision detection among solid objects.
 * @author Robert Lowe
 */
public class BoundingBox {
    /**
     * Left coordinate of the bounding box.
     */
    private double left;
    
    /**
     * Top coordinate of the bounding box.
     */
    private double top;
    
    /**
     * Width of the bounding box.
     */
    private double width;
    
    /**
     * Height of the bounding box.
     */
    private double height;
    
    
    /**
     * Construct the bounding box with the given dimensions.
     * @param left
     * @param top
     * @param width
     * @param height 
     */
    public BoundingBox(double left, double top, double width, double height)
    {
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
    }
    
    /**
     * Get the left coordinate of the bounding box
     * @return The left coordinate. 
     */
    public double getLeft()
    {
        return this.left;
    }
    
    /**
     * Get the top coordinate of the bounding box.
     * @return The top coordinate.
     */
    public double getTop()
    {
        return this.top;
    }
    
    /**
     * Get the width of the bounding box.
     * @return The box's width
     */
    public double getWidth()
    {
        return this.width;
    }
    
    /**
     * Get the hieght of the bounding box.
     * @return The box's height
     */
    public double getHeight()
    {
        return this.height;
    }
     
    /**
     * Perform axis aligned bounding box collision detection. 
     * between two bounding boxes.
     * @return True if the boxes are in collision, false otherwise.
     */
    public static boolean detectCollision(BoundingBox b1, BoundingBox b2)
    {
        /*
         * This is the standard form of axis aligned boundind box (AABB)
         * collision detection.  I read this in an example bit of game
         * code in a 1980's computer magazine of some sort, but it 
         * has been so many years ago, that I could not tell you 
         * which.  This is simple, fast, and functional!
         */
        double x1 = b1.getLeft();
        double x2 = b2.getLeft();
        double w1 = b1.getWidth();
        double w2 = b2.getWidth();
        double y1 = b1.getTop();
        double y2 = b2.getTop();
        double h1 = b1.getHeight();
        double h2 = b2.getHeight();
        
        return (x1 < x2 + w2) &&
               (x1 + w1 > x2) &&
               (y1 < y2 + h2) &&
               (y1 + h1 > y2);
    }
}
