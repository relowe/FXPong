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
 * The computer paddle is a pong paddle which is controlled by the
 * computer.  It responds to the ball's current position and heading.
 * @author Robert Lowe
 */
public class ComputerPaddle extends Paddle {
    private static double COMPUTER_RATE = 200;
    
    /**
     * The ball we are trying to hit.
     */
    private Ball ball;
    
    /** 
     * Construct the computer paddle, absorbing the ball reference.
     */
    public ComputerPaddle(Ball ball)
    {
        super();
        
        this.ball = ball;
    }
    
    /**
     * Move the paddle, changing direction according to the state of 
     * the world.
     * @param t Elapsed time
     */
    @Override
    public void move(double t) 
    {
        setDy(0);  //assume we are good
        
        //only move if the ball is headed our way on our side.
        if(ball.getDx() > 0 && ball.getX() > getX()/2.0) {
            double cy = getY() + getBounds().getHeight()/2.0;
            if(ball.getY() < cy) {
                setDy(-COMPUTER_RATE);
            } else if(ball.getY() > cy) {
                setDy(COMPUTER_RATE);
            }
        }
        
        //move with physics
        super.move(t);
    }
}
