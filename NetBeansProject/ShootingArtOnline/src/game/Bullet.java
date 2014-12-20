/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

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
}
