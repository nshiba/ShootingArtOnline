/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootingartonline;

import config.GameConfig;
import game.Game;
import static game.Global.setMouseX;
import static game.Global.setMouseY;
import javafx.scene.canvas.Canvas;
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
		canvas = new Canvas(GameConfig.WIDTH, GameConfig.HEIGHT);
		this.getChildren().add(canvas);
		game = new Game(canvas);
		game.start(game);
		setMouseEvent();
		System.out.println("game start!!");
	}

	private void getMouseLocate(MouseEvent event) {
		setMouseX(event.getSceneX());
		setMouseY(event.getSceneY());
		System.out.println(event.getSceneX());
	}

	private void keyAction(KeyEvent event) {
	}

	private void setMouseEvent() {
		/**
		 * set mouse locate event
		 */
		this.setOnMouseMoved((MouseEvent event) -> {
			setMouseX(event.getSceneX());
			setMouseY(event.getSceneY());
			//System.out.println(event.getSceneX());
		});
		/**
		 * set key event
		 * space -> bullet change
		 * boolean boost -> false or true;
		 */
		this.setOnKeyPressed((KeyEvent event) -> {
			
		});
	}

}
