/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import config.GameConfig;
import static game.Global.playerBullet;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author snake00
 */
public class Enemy {

	private int x;
	private int y;
	private boolean isDead;
	int vx = 3, vy = 3;
	float radius;
	int HP;

	Enemy(int x, int y) {
		this.x = x;
		this.y = y;
		this.isDead = false;
	}

	public boolean isDead() {
		return isDead;
	}

	public void update() {
		x = Global.getEnemyX();
		y = Global.getEnemyY();
	}

	void draw(GraphicsContext context) {

		context.setFill(Color.rgb(0, 255, 0, 1.0));
		context.fillOval(x, y, GameConfig.radius, GameConfig.radius);

	}

	public float getRadius() {
		return radius;
	}

	void reduceLife(int i) {
		HP -= playerBullet[i].damage;

		// 体力が無くなると死亡する。
		if (HP <= 0) {
			HP = 0;
			isDead = true;
		}
	}
}
