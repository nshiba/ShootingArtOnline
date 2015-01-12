/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import config.GameConfig;
import static game.Global.FrameCount;
import static game.Global.getEnemyX;
import static game.Global.getEnemyY;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author snake00
 */
public class Bullet {

	float x, y;             // 位置
	float vx, vy;            // 速度
	float radius;           // 半径
	boolean isDead = true;  // 有効フラグ
	float h;                // 色
	int num;
	float damage;
	int comb;
	float theta;
	float orNum;

	public Bullet() {
	}

	boolean isDead() {
		return this.isDead;
	}

	/**
	 * 弾情報から弾を生成
	 *
	 * @param x
	 * @param y
	 * @param vx
	 * @param vy
	 * @param num
	 * @param h
	 * @param damage
	 * @param comb
	 * @param theta
	 * @param radius
	 * @param orNum -> 自機弾か敵弾か
	 */
	void setBullet(float x, float y, float vx, float vy,
		int num, int h, float damage, int comb, float theta, float radius, int orNum) {
		this.x = x;
		this.y = y;
		this.num = num;
		this.h = h;
		this.damage = damage;
		this.comb = comb;
		this.vx = vx;
		this.vy = vy;
		this.theta = theta;
		this.radius = radius;
		this.orNum = orNum;

		// 弾を有効にする。
		isDead = false;
	}

	/**
	 * 位置を更新する。
	 *
	 */
	void update() {
		if (isDead) {
			return;
		}
		if (FrameCount % 50 == 0) {
			if (orNum == 1) {
//				System.out.println("player bullet reset theta -> "+theta);
				theta = (float) atan2(getEnemyY() - y, getEnemyX() - x);
//				System.out.println("player bullet theta -> "+ theta);
				orNum = 3;
			} else if (orNum == 0) {
//				System.out.println("enemy bullet reset theta -> "+theta);
				theta = (float) atan2(Global.getY() - y, Global.getX() - x);
//				System.out.println("enemy bullet theta -> "+ theta);
				orNum = 3;
			}
		}
		this.x += vx * cos(theta);
		this.y += vy * sin(theta);

		// 領域外に出たら消す。
		if (x < 0 || x > GameConfig.WIDTH || y < 0 || y > GameConfig.HEIGHT) {
			isDead = true;
		}
	}

	/**
	 * bullet kill
	 */
	void kill() {
		isDead = true;
	}

	/**
	 * get X
	 *
	 * @return
	 */
	public float getX() {
		return x;
	}

	/**
	 * get Y
	 *
	 * @return
	 */
	public float getY() {
		return y;
	}

	/**
	 * get radius
	 *
	 * @return
	 */
	public float getRadius() {
		return radius;
	}

	/**
	 * draw bullet
	 */
	void draw(GraphicsContext context) {
		if (isDead) {
			return;
		}
		if (num == 1) {

			context.setFill(Color.hsb(100, 1, 1, 1));
			context.fillOval(x, y, radius, radius);

		} else if (num == 2) {

			context.setFill(Color.hsb(300, 1, 1, 1));
			context.fillOval(x, y, radius, radius);
		} else if (num == 3) {

			context.setFill(Color.hsb(60, 1, 1, 1));
			context.fillOval(x, y, radius, radius);
		}
	}
}
