/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shootingartonline;

import game.Game;
import static game.Global.setMouseX;
import static game.Global.setMouseY;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author snake00
 */
public class GamePanel extends Pane implements Initializable {

	public Canvas canvas;
	
	private Game game;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		this.getChildren().add(canvas);
		game = new Game(canvas);
		game.start(game);
		System.out.println("game start!!");
	}	

	@FXML
	private void testOnAction(ActionEvent event) {
		
		
	}

	@FXML
	private void getMouseLocate(MouseEvent event) {
		setMouseX(event.getSceneX());
		setMouseY(event.getSceneY());
		System.out.println(event.getSceneX());
	}

	@FXML
	private void keyAction(KeyEvent event) {
	}
	
}
