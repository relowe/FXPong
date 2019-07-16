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

import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

/**
 * This is the player's paddle in the game of pong.
 * @author Robert Lowe
 */
public class PlayerPaddle extends Paddle {
    public static final double PLAYER_RATE = 500.0;
    
    /**
     * Create the paddle, which really just invokes the super
     * constructor.
     */
    public PlayerPaddle() 
    {
        super();
    }
    
    /**
     * Handle keyboard events (from the player, presumably)
     * The following keys move up: k, w, up arrow
     * The following keys move down: j, s, down arrow
     * @param e The keyboard event
     */
    public void handleKeyEvent(KeyEvent e)
    {
        boolean up = false;
        boolean down = false;
        
        //detect up and down movement
        KeyCode k = e.getCode();
        if( k == KeyCode.K || k == KeyCode.W || k == KeyCode.UP) {
            up = true;
        } else if( k == KeyCode.J || k == KeyCode.S || k == KeyCode.DOWN){
            down = true;
        }
        
        //handle key released events
        if(e.getEventType() == KeyEvent.KEY_RELEASED && (up || down)) {
            setDy(0);  //stop movement on release
        }
        
        //handle key pressed events.
        if(e.getEventType() == KeyEvent.KEY_PRESSED) {
            if(up) {
                setDy(-PLAYER_RATE);
            } else if(down) {
                setDy(PLAYER_RATE);
            }
        }
    }
}
