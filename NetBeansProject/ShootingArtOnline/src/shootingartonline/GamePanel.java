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
import javafx.event.EventHandler;
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
public class GamePanel extends Pane {

	public Canvas canvas;

	private Game game;

	/**
	 * Initializes the controller class.
	 */
	public GamePanel() {
		// TODO
		canvas = new Canvas(1280, 800);
		this.getChildren().add(canvas);
		game = new Game(canvas);
		game.start(game);
		setMouseEvent();
		System.out.println("game start!!");
	}

	private void getMouseLocate(MouseEvent event) {
		setMouseX(event.getSceneX());
		setMouseY(event.getSceneY());
//		System.out.println(event.getSceneX());
	}

	private void keyAction(KeyEvent event) {
	}

	private void setMouseEvent() {
		this.setOnMouseMoved((MouseEvent event) -> {
			setMouseX(event.getSceneX());
			setMouseY(event.getSceneY());
//			System.out.println(event.getSceneX());
		});
	}

}
