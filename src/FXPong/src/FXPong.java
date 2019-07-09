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
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

/**
 * The pong window!
 * @author Robert Lowe
 */
public class FXPong extends Application {
    @Override
    public void start(Stage primaryStage)
    {
        //setup and display the stage
        primaryStage.setTitle("FX Pong");
        PongPane pong = new PongPane();
        Scene scene = new Scene(pong);
        primaryStage.setScene(scene);
        pong.requestFocus();
        primaryStage.show();
    }
    
    
    public static void main(String [] args) {
        launch(args);
    }
}
