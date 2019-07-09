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

import javafx.scene.layout.Pane;

/**
 * Display a nifty scoreboard as seven segment displays.
 * @author Robert Lowe
 */
public class ScoreBoard extends Pane
{
    private int player1;
    private int player2;
    private SevenSegmentDisplay[] p1Digit;
    private SevenSegmentDisplay[] p2Digit;
    
    ScoreBoard()
    {
        //set myself up for size
        setPrefSize(210, 60);
        
        //create the digits
        p1Digit = new SevenSegmentDisplay[2];
        p2Digit = new SevenSegmentDisplay[2];
        p1Digit[0] = new SevenSegmentDisplay();
        p1Digit[0].setLayoutX(0);
        getChildren().add(p1Digit[0]);
        p1Digit[1] = new SevenSegmentDisplay();
        p1Digit[1].setLayoutX(35);
        getChildren().add(p1Digit[1]);
        p2Digit[0] = new SevenSegmentDisplay();
        p2Digit[0].setLayoutX(140);
        getChildren().add(p2Digit[0]);
        p2Digit[1] = new SevenSegmentDisplay();
        p2Digit[1].setLayoutX(175);
        getChildren().add(p2Digit[1]);
        
        //set initial scores
        reset();
    }
    
    /**
     * Reset the scoreboard
     */
    public void reset()
    {
        setPlayer1(0);
        setPlayer2(0);
    }
    
    /**
     * Get player 1 score
     */
    public int getPlayer1()
    {
        return player1;
    }
    
    /**
     * Get player 2 score
     */
    public int getPlayer2()
    {
        return player2;
    }
   
    /** 
     * Set player 1 score
     */
    public void setPlayer1(int score)
    {
        this.player1 = score;
        updateDigits(score, p1Digit);
    }
    
    /**
     * Set player 2 score
     */
    public void setPlayer2(int score)
    {
        this.player2 = score;
        updateDigits(score, p2Digit);
    }
    
    
    /**
     * Update digits display
     * @param num - The number to display
     * @param display - The displays to use
     */
    protected void updateDigits(int num, SevenSegmentDisplay [] display) 
    {
        for(int i=display.length - 1; i>=0; i--) {
            display[i].setDigit(num % 10);
            num/=10;
        }
    }
}
