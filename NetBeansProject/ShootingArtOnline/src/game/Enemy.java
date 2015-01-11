/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import config.GameConfig;
import static game.Global.FrameCount;
import static game.Global.enemyBullet;
import static game.Global.getX;
import static game.Global.getY;
import static game.Global.playerBullet;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import java.util.Random;
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
	int num; //bullet number
	int energyMax = GameConfig.EnergyMax;
	int energy = energyMax;
	int BulletTime;
	int BoostUse = GameConfig.BoostMaxUse;
	int BoostTimeCount;
	float radius = GameConfig.radius;
	float theta;
	int HP = GameConfig.BaseMaxHP;

	Enemy(int x, int y) {
		this.x = x;
		this.y = y;
		this.isDead = false;
	}

	public boolean isDead() {
		return isDead;
	}

	public void update() {

		if (!Global.getNPC()) {
			routine();
		}
		x = Global.getEnemyX();
		y = Global.getEnemyY();
		x += vx;
		y += vy;
		Global.setEnemyX(x);
		Global.setEnemyY(y);

		if (FrameCount % 2 == 0 && energy <= energyMax) {
			this.energy += 2;
		}
	}

	void draw(GraphicsContext context) {

		context.setFill(Color.rgb(0, 255, 0, 1.0));
		context.fillOval(x, y, GameConfig.radius, GameConfig.radius);
		context.setFill(Color.hsb(220, 1, 1, 1));
		context.fillRect(770, 20, HP / 20, 20);
		context.setFill(Color.hsb(100, 1, 1, 1));
		context.fillRect(770, 45, energy / 2, 5);

	}

	public float getRadius() {
		return radius;
	}

	/**
	 * reduce enemy life
	 *
	 * @param i
	 */
	void reduceLife(int i) {
		HP -= playerBullet[i].damage;

		/**
		 * if HP <= 0 is dead
		 */
		if (HP <= 0) {
			HP = 0;
			isDead = true;
		}
	}

	// 弾を撃つ。
	void fire() {
		// ToDo
		if (!Global.getNPC()) {
			if (num != 0) {
				if (num == 1) {
					if (energy >= GameConfig.BeamGunEnergy) {
						if (BulletTime == 0) {
							float v = GameConfig.BeamGunSpeed;
							energy -= GameConfig.BeamGunEnergy / 2;
							theta = (float) atan2(getY() - y, getX() - x);
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
							energy -= GameConfig.SniperEnergy / 2;
							theta = (float) atan2(getY() - y, getX() - x);
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
							energy -= GameConfig.BigBeamEnergy / 2;
							theta = (float) atan2(getY() - y, getX() - x);
							setBullet(x, y, v, v, num, GameConfig.BigBeamDamage, GameConfig.BigBeamCombNum, theta, 90);
						}
						BulletTime++;
						if (BulletTime >= GameConfig.BigBeamTime) {
							BulletTime = 0;
						}
					}
				}
			}
		} else {
			if (num == 1) {
				if (energy >= GameConfig.BeamGunEnergy) {
					//if (BulletTime == 0) {
					float v = GameConfig.BeamGunSpeed;
					energy -= GameConfig.BeamGunEnergy / 2;
					theta = (float) atan2(getY() - y, getX() - x);
					setBullet(x, y, v, v, num, GameConfig.BeamGunDamage, GameConfig.BeamGunCombNum, theta, 8);
					//}
					BulletTime++;
					if (BulletTime >= GameConfig.BeamGunTime) {
						BulletTime = 0;
					}
				}
			} else if (num == 2) {
				if (energy >= GameConfig.SniperEnergy) {
					//if (BulletTime == 0) {
					float v = GameConfig.SniperSpeed;
					energy -= GameConfig.SniperEnergy / 2;
					theta = (float) atan2(getY() - y, getX() - x);
					setBullet(x, y, v, v, num, GameConfig.SniperDamage, GameConfig.SniperCombNum, theta, 15);
					//}
					BulletTime++;
					if (BulletTime >= GameConfig.SniperTime) {
						BulletTime = 0;
					}
				}
			} else if (num == 3) {
				if (energy >= GameConfig.BigBeamEnergy) {
					//if (BulletTime == 0) {
					float v = GameConfig.BigBeamSpeed;
					energy -= GameConfig.BigBeamEnergy / 2;
					theta = (float) atan2(getY() - y, getX() - x);
					setBullet(x, y, v, v, num, GameConfig.BigBeamDamage, GameConfig.BigBeamCombNum, theta, 90);
					//}
					BulletTime++;
					if (BulletTime >= GameConfig.BigBeamTime) {
						BulletTime = 0;
					}
				}
			}

		}
	}

	void setBullet(float x, float y, float vx, float vy, int num, int damage, int comb, float theta, int radius) {
		// 弾の色を適当に決める。
		int h = 100;

		for (Bullet enemyBullet1 : enemyBullet) {
			if (enemyBullet1.isDead()) {
				enemyBullet1.setBullet(x + (this.radius / 2), y + (this.radius / 2), vx, vy, num, h, damage /* * option.getDifficulty()*/, comb, theta, radius, 0);
				break;
			}
		}
	}

	//思考ルーチン
	void routine() {
		float dx = (getX() + GameConfig.radius / 2) - (x + getRadius() / 2);
		float dy = (getY() + GameConfig.radius / 2) - (y + getRadius() / 2);
		float dr = GameConfig.radius + radius;
		Random rnd = new Random();

		if (FrameCount % 60 == 1) {

			int vector = rnd.nextInt(361);
			vx = (int) (8 * cos(Math.toRadians(vector)));
			vy = (int) (8 * sin(Math.toRadians(vector)));
		}
		if (FrameCount % 60 == 1) {
			num = rnd.nextInt(3) + 1;
		}
		if (x + radius / 2 - (radius / 2) - 30 < 0) {
			vx = -vx;
		} else if (x + radius / 2 + (radius / 2) + 50 > GameConfig.WIDTH) {
			vx = -vx;
		}
		if (y + radius / 2 - (radius / 2) - 30 < 0) {
			vy = -vy;
		} else if (y + radius / 2 + (radius / 2) + 50 > GameConfig.HEIGHT) {
			vy = -vy;
		}
		if (dx * dx + dy * dy < 300 * 300) {
			num = 3;
		} else if (dx * dx + dy * dy > 800 * 800) {
			num = 3;
		}
	}
}
