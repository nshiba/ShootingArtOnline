/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootingartonline;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Naoya
 */
public class ShootingArtOnline extends Application {

	 @Override
    public void start(Stage primaryStage) throws IOException {
	Parent root = FXMLLoader.load(ShootingArtOnline.class.getResource("resource/title.fxml"));
	primaryStage.setScene(new Scene(root));
	primaryStage.show();
    }

    public static void main(String[] args) {
	launch(args);
    }
}
