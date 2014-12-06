// 敵クラス
class Enemy {
  // 変化しない値
  int maxLife;                 // 体力の最大値

  // 変化する値
  float x = 1100, y = 520;      // 位置
  float vx, vy;
  float radius = 70;    // 半径
  boolean isDead;  // 有効フラグ
  int HP = BaseMaxHP * option.getDifficulty();        // 現在の体力
  int num; //弾の番号
  float theta;
  int BulletTime;
  int energyMax = EnergyMax;
  int energy = energyMax;
  int BoostUse = BoostMaxUse;
  int BoostTimeCount;
  int vector;
  int comb;

  float getX() {
    return x;
  }

  float getY() {
    return y;
  }

  float getRadius() {
    return radius / 2;
  }

  boolean isDead() {
    return isDead;
  }

  Enemy() {
    this.x = x;
    this.y = y;
    this.radius = radius;
    this.HP = BaseMaxHP;
  }

  // 位置を変更する。
  void update() {
    // ToDo

    routine(); //思考ルーチン関数呼び出し

    if (num == 5) {
      if (energy >= BoostEnergy) {
        BoostUse--;
        BoostTimeCount = 0;
        energy -= BoostEnergy;
        num = 0;
      }
    }
    if (BoostTimeCount == 0) {
      if (BoostUse < 3 && BoostUse > 0) {
        vx = BoostSpeed * 2;
        vy = BoostSpeed * 2;
      }
    }
    if (BoostUse < 3) {
      BoostTimeCount++;
    }
    if (BoostTimeCount == BoostTime) {
      BoostUse = BoostMaxUse;
      vx = 3;
      vy = 3;
    }
    x += vx;
    y += vy;
  }

  // 弾を撃つ。
  void fire() {
    // ToDo
    if (num != 0) {
      if (num == 1) {
        if (energy >= BeamGunEnergy) {
          if (BulletTime == 0) {
            float v = BeamGunSpeed;    
            energy -= BeamGunEnergy / 2;   
            theta = atan2(player.getY() - y, player.getX() - x);
            setBullet(x, y, v, v, num, BeamGunDamage, BeamGunCombNum, theta, 8);
          }
          BulletTime++;
          if (BulletTime >= BeamGunTime) {
            BulletTime = 0;
          }
        }
      }
      else if (num == 2) {
        if (energy >= SniperEnergy) {
          if (BulletTime == 0) {
            float v = SniperSpeed;    
            energy -= SniperEnergy / 2;   
            theta = atan2(player.getY() - y, player.getX() - x);
            setBullet(x, y, v, v, num, SniperDamage, SniperCombNum, theta, 15);
          }
          BulletTime++;
          if (BulletTime >= SniperTime) {
            BulletTime = 0;
          }
        }
      }
      else if (num == 3) {
        if (energy >= BigBeamEnergy) {
          if (BulletTime == 0) {
            float v = BigBeamSpeed;    
            energy -= BigBeamEnergy / 2;   
            theta = atan2(player.getY() - y, player.getX() - x);
            setBullet(x, y, v, v, num, BigBeamDamage, BigBeamCombNum, theta, 90);
          }
          BulletTime++;
          if (BulletTime >= BigBeamTime) {
            BulletTime = 0;
          }
        }
      }
    }
  }

  void setBullet(float x, float y, float vx, float vy, int num, int damage, int comb, float theta, int radius) {
    // 弾の色を適当に決める。
    int h = 100;

    // 死んでいる弾を再利用する。
    for (int i = 0; i < enemyBullet.length; i++) {
      if (enemyBullet[i].isDead()) {
        enemyBullet[i].setBullet(x, y, vx, vy, num, h, damage * option.getDifficulty(), comb, theta, radius * 1.2, 0);
        break;
      }
    }
  }

  // 体力を減らす。
  void reduceLife(int i) {
    HP -= playerBullet[i].damage;

    // 体力が無くなると死亡する。
    if (HP <= 0) {
      HP = 0;
      isDead = true;
    }
  }

  // 描画する。
  void draw() {
    fill(70, 100, 100);
    ellipse(x, y, radius, radius);
    fill(0, 0, 0);
    ellipse(x, y, radius / 1.5, radius / 1.5); 

    drawLifeBar();
  }

  void drawLifeBar() {
    fill(220, 100, 100, 180);
    rect(770, 20, HP / 20, 20);
    fill(100, 100, 100, 170);
    rect(770, 45, energy / 2, 5);
    if (frameCount % 2 == 0) {
      if (energy <= energyMax) {
        energy += 3;
      }
    }
  }

  //思考ルーチン
  void routine() { 
    float dx = player.getX() - x;
    float dy = player.getY() - y;
    float dr = player.getRadius() + radius;
    if (frameCount % 60 == 1) {
      int vector = (int)random(0, 360);
      vx = 8 * cos(radians(vector));
      vy = 8 * sin(radians(vector));
    }
    if(frameCount % 60 == 1) {
       num = (int)random(1, 3);
    }
    if (x - (radius / 2)  - 30 < 0) {
      vx = -vx;
    }
    else if ( x + (radius / 2)  + 30 > width) {
      vx = -vx;
    }
    if (y - (radius / 2)  - 30 < 0) {
      vy = -vy;
    }
    else if (y + (radius / 2)  + 30 > height) {
      vy = -vy;
    }
    if (dx * dx + dy * dy < 300 * 300) {
      num = 3;
    }else if(dx * dx + dy * dy > 800 * 800) {
      num = 3;
    }
  }
}

