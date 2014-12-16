/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shootingartonline;

import game.Game;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author snake00
 */
public class TitleController implements Initializable {
	@FXML
	private Button testButton;
	@FXML
	public Pane gamePanel;
	
	private Game game;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		game = new Game(gamePanel);
	}	

	@FXML
	private void testOnAction(ActionEvent event) {
		
		game.start(game);
		System.out.println("game start!!");
	}

	@FXML
	private void getMouseLocate(MouseEvent event) {
	}

	@FXML
	private void keyAction(KeyEvent event) {
	}
	
}
