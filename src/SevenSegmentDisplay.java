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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class draws a seven segment display on the 
 * screen.  As per the standards in electronics, the
 * numbers in the display are as follows:
 * <pre>
 *   0
 *   -
 * 5| |1
 *   -6
 * 4| |2
 *   -
 *   3
 * </pre>
 * @author Robert Lowe
 */
public class SevenSegmentDisplay extends Pane {
    private boolean [] segment;
    private ImageView [] segmentImage;
    private Image middleOff;
    private Image sideOff;
    private Image middleOn;
    private Image sideOn;
    
    public SevenSegmentDisplay()
    {
        //allocate the arrays
        segment = new boolean[7];
        segmentImage = new ImageView[7];
        
        //load up the images
        middleOff = new Image(getClass().getResourceAsStream("image/7middleOff.png"));
        middleOn = new Image(getClass().getResourceAsStream("image/7middleOn.png"));
        sideOff = new Image(getClass().getResourceAsStream("image/7sideOff.png"));
        sideOn = new Image(getClass().getResourceAsStream("image/7sideOn.png"));
        
        //create the segments
        segmentImage[0] = new ImageView(middleOff);
        segmentImage[0].setX(3);
        segmentImage[0].setY(0);
        segmentImage[1] = new ImageView(sideOff);
        segmentImage[1].setX(27);
        segmentImage[1].setY(2);
        segmentImage[1].setRotate(180);
        segmentImage[2] = new ImageView(sideOff);
        segmentImage[2].setX(27);
        segmentImage[2].setY(29);
        segmentImage[2].setRotate(180);
        segmentImage[3] = new ImageView(middleOff);
        segmentImage[3].setX(3);
        segmentImage[3].setY(52);
        segmentImage[4] = new ImageView(sideOff);
        segmentImage[4].setX(0);
        segmentImage[4].setY(29);
        segmentImage[5] = new ImageView(sideOff);
        segmentImage[5].setX(0);
        segmentImage[5].setY(2);
        segmentImage[6] = new ImageView(middleOff);
        segmentImage[6].setX(3);
        segmentImage[6].setY(26);
        
        //add the segments to the display
        for(ImageView iv : segmentImage) {
            getChildren().add(iv);
        }
    }
    
    /**
     * Sets the display to a digit 0-9
     * @param digit - digit to display
     */
    public void setDigit(int digit) 
    {
        boolean [][] digitMap = { 
            { true, true, true, true, true, true, false},       //0
            { false, true, true, false, false, false, false},   //1
            { true, true, false, true, true, false, true},      //2
            { true, true, true, true, false, false, true},      //3
            { false, true, true, false, false, true, true},     //4
            { true, false, true, true, false, true, true},      //5
            { true, false, true, true, true, true, true},       //6
            { true, true, true, false, false, false, false},    //7
            { true, true, true, true, true, true, true},        //8
            { true, true, true, false, false, true, true}       //9
        };
        
        for(int i = 0; i<7; i++) {
            setSegment(i, digitMap[digit][i]);
        }
    }
    
    /**
     * Set the segment to either on or off.
     * @param num - segment to set
     * @param sate - on or off (true or false)
     */
    public void setSegment(int num, boolean state) 
    {
        // middle segments are divisible by 3
        if(num % 3 == 0) {
            segmentImage[num].setImage(state ? middleOn : middleOff);
        } else {
            segmentImage[num].setImage(state ? sideOn : sideOff);
        }
        
        segment[num] = state;
    }
    
    
    /**
     * Get the segment state
     */
    boolean getSegment(int num) 
    {
        return segment[num];
    }
}
