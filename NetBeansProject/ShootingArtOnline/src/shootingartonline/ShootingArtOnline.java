package shootingartonline;


import com.sun.javaws.ui.ApplicationIconGenerator;
import static config.GameConfig.*;
import game.Global;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import static game.Global.*;

public class ShootingArtOnline extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
//		SocketOutput output = new SocketOutput();
//		Thread t2 = new Thread(output);
//		t2.start();
		

		GamePanel pane = new GamePanel();
		primaryStage.setScene(new Scene(pane));
		pane.setPrefSize(WIDTH, HEIGHT);
		//pane.setMinSize(WIDTH, HEIGHT);
		//pane.setMaxSize(WIDTH, HEIGHT);
		primaryStage.setResizable(false);
		primaryStage.show();
		
		/**
		 * set key event space -> bullet change boolean boost -> false
		 * or true;
		 */
		primaryStage.getScene().setOnKeyPressed((KeyEvent event) -> {
			System.out.println("key pressed -> "+event.getCode());
			/**
			 * bullet pressed key a -> 1 s -> 2 d -> 3
			 */
			if(event.getCode() == KeyCode.A){
				Global.setMyBulletNum(1);
				Global.setMyBulletFire(true);
			}else if(event.getCode() == KeyCode.S){
				Global.setMyBulletNum(2);
				Global.setMyBulletFire(true);
			}else if(event.getCode() == KeyCode.D){
				Global.setMyBulletNum(3);
				Global.setMyBulletFire(true);
			}else{
				Global.setMyBulletNum(0);
				Global.setMyBulletFire(false);
			}
			
			/**
			 * use boost  speed up my machine
			 */
			if(event.getCode() == KeyCode.X) {
				System.out.println("X pressed");
				if(!Global.getBoostUse()){
				Global.setBoostUse(true);
				}
			}

		});
		
		primaryStage.getScene().setOnKeyReleased((KeyEvent event) -> {
			Global.setMyBulletNum(0);
			Global.setMyBulletFire(false);
			});
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}