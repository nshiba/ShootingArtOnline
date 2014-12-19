package shootingartonline;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ShootingArtOnline extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		GamePanel pane = new GamePanel();
		primaryStage.setScene(new Scene(pane));
		pane.setPrefSize(1280, 800);
		pane.setMinSize(1280, 800);
		pane.setMaxSize(1280, 800);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}