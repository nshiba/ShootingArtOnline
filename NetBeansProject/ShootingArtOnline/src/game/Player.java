/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import config.GameConfig;
import static game.Global.getEnemyX;
import static game.Global.getEnemyY;
import static game.Global.playerBullet;
import static java.lang.Math.atan2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author snake00
 */
public class Player {

	private int x;
	private int y;
	int num; //bulet number
	private boolean isDead;
	int vx = 3, vy = 3;
	int energyMax = GameConfig.EnergyMax; //select.getEnergy();
	int energy = energyMax;
	int BulletTime;
	int BoostUse = GameConfig.BoostMaxUse;
	int BoostTimeCount;
	float theta;

	Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.isDead = false;
	}

	public boolean isDead() {
		return isDead;
	}

	void fire() {
		if (num != 0) {
			if (num == 1) {
				if (energy >= GameConfig.BeamGunEnergy) {
					if (BulletTime == 0) {
						float v = GameConfig.BeamGunSpeed;
						energy -= GameConfig.BeamGunEnergy;
						theta = (float) atan2(getEnemyY() - y, getEnemyX() - x);
						setBullet(x, y, v, v, num, GameConfig.BeamGunDamage, GameConfig.BeamGunCombNum, theta, 8);
					}
					BulletTime++;
					if (BulletTime >= GameConfig.BeamGunTime) {
						BulletTime = 0;
					}
				}
			} else if (num == 2) {
				if (energy >= GameConfig.SniperEnergy) {
					if (BulletTime == 0) {
						float v = GameConfig.SniperSpeed;
						energy -= GameConfig.SniperEnergy;
						theta = (float) atan2(getEnemyY() - y, getEnemyX() - x);
						setBullet(x, y, v, v, num, GameConfig.SniperDamage, GameConfig.SniperCombNum, theta, 15);
					}
					BulletTime++;
					if (BulletTime >= GameConfig.SniperTime) {
						BulletTime = 0;
					}
				}
			} else if (num == 3) {
				if (energy >= GameConfig.BigBeamEnergy) {
					if (BulletTime == 0) {
						float v = GameConfig.BigBeamSpeed;
						energy -= GameConfig.BigBeamEnergy;
						theta = (float) atan2(getEnemyY() - y, getEnemyX() - x);
						setBullet(x, y, v, v, num, GameConfig.BigBeamDamage, GameConfig.BigBeamCombNum, theta, 90);
					}
					BulletTime++;
					if (BulletTime >= GameConfig.BigBeamTime) {
						BulletTime = 0;
					}
				}
			}
		}
	}

	public void update() {
		if (Global.getMouseX() < x) {
			x -= vx;
		} else {
			x += vx;
		}
		if (Global.getMouseY() < y) {
			y -= vy;
		} else {
			y += vy;
		}

	}

	void draw(GraphicsContext context) {

		context.setFill(Color.rgb(255, 0, 0, 1.0));
		context.fillOval(x, y, GameConfig.radius, GameConfig.radius);

	}

	private void setBullet(int x, int y, float v, float v0, int num, int damage, int comb, float theta, int radius) {
		int h = 100;
		// 死んでいる弾を再利用する。
		for (int i = 0; i < playerBullet.length; i++) {
			if (playerBullet[i].isDead()) {
				playerBullet[i].setBullet(x, y, vx, vy, num, h, damage  /*  * select.getDamageSet()*/, comb, theta, radius, 1);
				break;
			}
		}
	}

}
