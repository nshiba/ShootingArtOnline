/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import static config.GameConfig.EnemyBulletCount;
import static config.GameConfig.PlayerBulletCount;
import static game.Global.FrameCount;
import static game.Global.enemyBullet;
import static game.Global.playerBullet;
import javafx.concurrent.Task;
import javafx.scene.layout.Pane;

/**
 *
 * @author snake00
 */
public class Game extends Task implements Runnable {

	Player player;
	Enemy enemy;
	Pane pane;
	boolean TitleFlag = false;
	boolean GameFlag = true;
	boolean OptionFlag = false;
	boolean SelectFlag = false;

	public Game(Pane pane) {
		this.pane = pane;

	}

	@Override
	protected Object call() throws Exception {
		return null;
	}

	@Override
	public void run() {
		while (TitleFlag == true) {
			if (GameFlag) {
				Game();
			}
			/*if (OptionFlag) {
			 option.draw();
			 }*/
			if (SelectFlag) {
				//select.draw();
			}
		}

	}

	public void start(Game game) {
		setUpGame();
		new Thread(game).start();
	}

	private void setUpGame() {
		FrameCount = 0;

		player = new Player();
		enemy = new Enemy();

		playerBullet = new Bullet[PlayerBulletCount];
		for (int i = 0; i < playerBullet.length; i++) {
			playerBullet[i] = new Bullet();
		}
		enemyBullet = new Bullet[EnemyBulletCount];
		for (int i = 0; i < enemyBullet.length; i++) {
			enemyBullet[i] = new Bullet();
		}
		bg();
	}

	private void bg() {
		pane.setStyle("-fx-background-color: black");
	}

	private void Game() {

	}

}
