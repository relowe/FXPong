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
 * This is a sprite which moves according to velocity and 
 * acceleration object.  It utilizes the kinematic equations:<br/>
 *    d = v_i * t + 1/2 * a * t^2 <br/>
 *    v_f = v_i + a * t <br/>
 * With updating taking place via the move function.  Each time
 * move is called, the t value is passed in.
 * 
 * This sprite maintains a velocity vector (dx, dy) and an 
 * acceleartion vector (ax, ay)
 * @author Robert Lowe
 */
public class PhysicsSprite extends Sprite {
    /** 
     * X component of the velocity vector
     */
    private double dx;
    
    /**
     * Y component of the velocity vector
     */
    private double dy;
    
    /** 
     * X component of the acceleration vector.
     */
    private double ax;
    
    /**
     * Y component of the acceleration vector.
     */
    private double ay;
    
    /**
     * Create an empty sprite at rest.
     */
    public PhysicsSprite()
    {
        dx = 0.0;
        dy = 0.0;
        ax = 0.0;
        ay = 0.0;
    }
    
    /** 
     * Get the x component of the velocity vector.
     * @return The vector component as a double
     */
    public double getDx()
    {
        return dx;
    }
    
    /**
     * Get the y component of the velocity vector.
     * @return The vector component as a double
     */
    public double getDy()
    {
        return dy;
    }
    
    /** 
     * Get the x component of the acceleration vector.
     * @return The vector component as a double
     */
    public double getAx()
    {
        return ax;
    }
    
    /**
     * Get the y component of the acceleration vector.
     * @return The vector component as a double
     */
    public double getAy()
    {
        return ay;
    }
    
    /**
     * Set the x component of the velocity vector.
     * @param dx X velocity
     */
    public void setDx(double dx)
    {
        this.dx = dx;
    }
    
    /**
     * Set the y component of the velocity vector.
     * @param dy Y velocity
     */
    public void setDy(double dy)
    {
        this.dy = dy;
    }
    
    /**
     * Set the x component of the acceleration vector.
     * @param ax X acceleration
     */
    public void setAx(double ax)
    {
        this.ax = ax;
    }
    
    /**
     * Set the y component of the acceleration vector.
     * @param ay Y acceleration
     */
    public void setAy(double ay)
    {
        this.ay = ay;
    }
    
    /** 
     * Set both components of the velocity vector.
     * @param dx X velocity
     * @param dy Y velocity
     */
    public void setVelocity(double dx, double dy)
    {
        setDx(dx);
        setDy(dy);
    }
    
    /**
     * Set both components of the acceleration vector.
     * @param ax
     * @param ay
     */
    public void setAcceleration(double ax, double ay)
    {
        setAx(ax);
        setAy(ay);
    }
    
    /**
     * Update the position and velocity of the PhysicsSprite
     * based on the current velocity and acceleration vectors.
     * It is assumed that acceleration remains constant for the full
     * time period t.
     * @param t Elapsed time since last movement
     */
    public void move(double t) 
    {
        //update position
        setX(getX() + getDx() * t + 0.5 * getAx() * t * t);
        setY(getY() + getDy() * t + 0.5 * getAy() * t * t);
        
        
        //update velocity
        setDx(getDx() + getAx() * t);
        setDy(getDy() + getAy() * t);
    }
}
