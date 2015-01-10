/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import config.GameConfig;
import static game.Global.FrameCount;
import static game.Global.enemyBullet;
import static game.Global.getMouseX;
import static game.Global.getMouseY;
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
	float radius = GameConfig.radius;
	float theta;
	int HP = GameConfig.BaseMaxHP;

	Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.isDead = false;
		Global.setBoostUse(false);
	}

	public boolean isDead() {
		return isDead;
	}

	public float calcTheta() {
		return (float) atan2(Global.getEnemyY() - y, Global.getEnemyX() - x);
	}

	void fire(boolean isFire) {
		if (isFire == true) {
			num = Global.getMyBulletNum();
			System.out.println("bullet num -> " + num);
			if (num == 1) {
				if (energy >= GameConfig.BeamGunEnergy) {
					if (BulletTime == 0) {
						float v = GameConfig.BeamGunSpeed;
						energy -= GameConfig.BeamGunEnergy;
						theta = calcTheta(); //(float) atan2(getEnemyY() - y, getEnemyX() - x);
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
						theta = calcTheta(); //(float) atan2(getEnemyY() - y, getEnemyX() - x);
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
						theta = calcTheta(); //(float) atan2(getEnemyY() - y, getEnemyX() - x);
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

		if (Global.getBoostUse()) {
			if (energy >= GameConfig.BoostEnergy) {
				if(BoostUse > 0){
				BoostUse--;
				BulletTime = 0;
				BoostTimeCount = 0;
				energy -= GameConfig.BoostEnergy;
				}
				Global.setBoostUse(false);
			}
		}
		if (BoostTimeCount == 0) {
			if (BoostUse < 3 && BoostUse > 0) {
				vx = 3 + 3;//GameConfig.BoostSpeed; //select.getBoostSpeed();
				vy = 3 + 3;//GameConfig.BoostSpeed; //select.getBoostSpeed();
			}
		}
		if (BoostUse < 3) {
			BoostTimeCount++;
		}
		if (BoostTimeCount == GameConfig.BoostTime) {
			BoostUse = GameConfig.BoostMaxUse;
			vx = 3;
			vy = 3;
		}
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

		if (FrameCount % 2 == 0) {
			if (energy <= energyMax) {
				energy += 2;
			}
		}
		Global.setX(x);
		Global.setY(y);
	}

	void draw(GraphicsContext context
	) {

		context.setFill(Color.rgb(255, 0, 0, 1.0));
		context.fillOval(x, y, GameConfig.radius, GameConfig.radius);
		context.setFill(Color.hsb(220, 1, 1, 1));
		context.fillRect(510 - (HP / 20), 20, HP / 20, 20);
		context.setFill(Color.hsb(100, 1, 1, 1));
		context.fillRect(510 - (energy / 2), 45, (energy / 2), 5);

	}

	/**
	 * 空いてる弾倉に弾を生成
	 *
	 * @param x
	 * @param y
	 * @param vx
	 * @param vy
	 * @param num
	 * @param damage
	 * @param comb
	 * @param theta
	 * @param radius
	 */
	private void setBullet(int x, int y, float vx, float vy, int num, int damage, int comb, float theta, int radius) {
		int h = 100;
		for (Bullet playerBullet1 : playerBullet) {
			if (playerBullet1.isDead()) {
				playerBullet1.setBullet(x + (this.radius / 2), y + (this.radius / 2), vx, vy, num, h, damage /*  * select.getDamageSet()*/, comb, theta, radius, 1);
				System.out.println("make bullet");
				break;
			}
		}
	}

	public float getRadius() {
		return radius;
	}

	public void reduceLife(int i) {
		HP -= enemyBullet[i].damage;

		// 体力が無くなると死亡する。
		if (HP <= 0) {
			HP = 0;
			isDead = true;
		}
	}

}
