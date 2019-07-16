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

import javafx.animation.Animation.Status;
import javafx.scene.layout.Pane;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This is a pane which plays the game of pong!
 * @author Robert Lowe
 */
public class PongPane extends Pane {
    private Ball ball;
    private PlayerPaddle player;
    private ComputerPaddle computer;
    private ScoreBoard score;
    private Timeline timer;
    private int limit;
    private static final double FRAME_DURATION = 1.0 / 30.0;
    
    public PongPane()
    {
        setPrefSize(640,480);
        //create the background 
        Rectangle bg = new Rectangle();
        getChildren().add(bg);
        bg.setFill(Color.BLACK);
        bg.setX(0);
        bg.setY(0);
        bg.widthProperty().bind(widthProperty());
        bg.heightProperty().bind(heightProperty());
        
        //create the basic assets
        ball = new Ball();
        player = new PlayerPaddle();
        computer = new ComputerPaddle(ball);
        score = new ScoreBoard();
        
        score.setLayoutX(getPrefWidth()/2 - score.getPrefWidth()/2 );
        score.setLayoutY(5);
        getChildren().addAll(ball, player, computer, score);
        
        //position the assets
        reset();
        
        //pass along keyboard events to the player paddle
        setFocusTraversable(true);
        setOnKeyPressed(e->{ play(); player.handleKeyEvent(e);});
        setOnKeyReleased(e->{ play(); player.handleKeyEvent(e);});
        
        //create the timeline
        timer = new Timeline();
        timer.getKeyFrames().add(new KeyFrame(Duration.seconds(FRAME_DURATION), e->updateFrame()));
        timer.setCycleCount(Timeline.INDEFINITE);
        
        
        //click to pause / run
        setOnMouseClicked(e -> {
            if(timer.getStatus() == Status.RUNNING) {
                pause();
            } else {
                play();
            }
        });
    }
    
    /**
     * Reset's the game back to the default state.
     */
    public void reset()
    {
        double w = getPrefWidth();
        double h = getPrefHeight();
        
        //reset the paddles
        player.setY(h/2.0 - player.getBounds().getHeight()/2.0);
        player.setX(5);
        computer.setY(h/2.0 - player.getBounds().getHeight()/2.0);
        computer.setX(w-5-computer.getBounds().getWidth());
        score.reset();
        
        resetBall();
    }
    
    /**
     * Reset the ball, either to default or after a point is scored.
     */
    public void resetBall()
    {
        //position the ball
        ball.setX(getPrefWidth()/2.0 - ball.getBounds().getWidth()/2.0);
        ball.setY(getPrefHeight()/2.0 - ball.getBounds().getHeight()/2.0);
        
        ball.setDx(Math.random() < 0.5 ? -100 : 100);
        ball.setDy(Math.random() < 0.5 ? -300 : 300);
    }
    
    /**
     * Play the game
     */
    public void play()
    {
        timer.play();
    }
    
    /**
     * Pause the game
     */
    public void pause()
    {
        timer.pause();
    }
    
    /**
     * Gets the point limit of the game.
     * @return point limit
     */
    public int getLimit() 
    {
        return limit;
    }
    
    /**
     * Set the point limit of the game.
     * @param limit The point limit
     */
    public void setLimit(int limit) 
    {
        this.limit = limit;
    }
    
    /**
     * Determine if the game is over.
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver()
    {
        return false;
    }
    
    /**
     * Update each frame of the game. (This is the main game loop.)
     */
    private void updateFrame()
    {
        player.move(FRAME_DURATION);
        computer.move(FRAME_DURATION);
        ball.move(FRAME_DURATION);
        
        //keep the paddles on the screen, handle ball collisions with them
        Paddle [] paddles = {player, computer};
        for(Paddle p : paddles) {
            //handle going off the top
            if(p.getY() < 0) {
                p.setY(0);
            }
            
            //handle going off the bottom
            if(p.getY() > getPrefHeight()-p.getBounds().getHeight()) {
                p.setY(getPrefHeight() - p.getBounds().getHeight());
            }
            
            if(ball.detectCollision(p)) {
                ball.handleCollision(p);
            }
        }
        
        //bounce off the bottom edges
        if((ball.getDy() < 0 && ball.getY() < 0) || 
           (ball.getDy() > 0 && ball.getY() + ball.getBounds().getHeight() > getPrefHeight())) {
            ball.setDy(-ball.getDy());
        }
        
        //handle when ball leaves the screen
        if(ball.getX() < 0) {
            //computer scores
            score.setPlayer2(score.getPlayer2() + 1);
            resetBall();
        } else if(ball.getX() + ball.getBounds().getWidth() > getPrefWidth()) {
            //player scores
            score.setPlayer1(score.getPlayer1() + 1);
            resetBall();
        }
    }
}
