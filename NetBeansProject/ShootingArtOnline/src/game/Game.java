/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import config.GameConfig;
import static config.GameConfig.EnemyBulletCount;
import static config.GameConfig.PlayerBulletCount;
import static game.Global.FrameCount;
import static game.Global.enemyBullet;
import static game.Global.playerBullet;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author snake00
 */
public class Game extends Task {

	Player player;
	Enemy enemy;
	Canvas pane;
	boolean TitleFlag = false;
	boolean GameFlag = true;
	boolean OptionFlag = false;
	boolean SelectFlag = false;
	GraphicsContext gc;
	Timeline timeline;

	public Game(Canvas pane) {
		this.pane = pane;
		gc = pane.getGraphicsContext2D();

	}

	public void run() {
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

	public void start(Game game) {

		setUpGame();
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long time) {

				gc = pane.getGraphicsContext2D();
				gc.setFill(Color.rgb(0, 0, 0, 1.0));
				gc.clearRect(0, 0, GameConfig.WIDTH, GameConfig.HEIGHT);
				run();

			}
		};

		timer.start();

	}

	private void setUpGame() {
		FrameCount = 0;

		player = new Player(100, 200);
		enemy = new Enemy(1000,750);
		/**
		 * enemy test 
		 */
		Global.setEnemyX(700);
		Global.setEnemyY(700);
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

	}

	private void Game() {
		bg();
		player.update();
		enemy.update();
		player.draw(gc);
		enemy.draw(gc);
	}

	@Override
	protected Object call() throws Exception {
		return null;
	}

}
