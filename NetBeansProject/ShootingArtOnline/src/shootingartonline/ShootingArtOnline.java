package shootingartonline;


import static config.GameConfig.*;
import game.Global;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ShootingArtOnline extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
        TestUI client = new TestUI();
        Thread t = new Thread(client);
        t.start();

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
			 * bullet change +1 if bullet = 3 -> 0
			 */
			if (event.getCode() == KeyCode.SPACE /*|| event.getCode() == KeyCode.UNDEFINED*/) {
				System.out.println("space pressed");
				if (Global.getMyBulletNum() == 3) {
					Global.setMyBulletNum(1);
				} else {
					int num = Global.getMyBulletNum();
					num++;
					Global.setMyBulletNum(num);
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException ex) {
					Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
				}
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
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}