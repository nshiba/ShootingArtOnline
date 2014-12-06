// 自機クラス
class Player {  
  // 変化する値
  float x = 100, y = 200;      // 位置
  float radius = 70;    // 半径
  boolean isDead;  // 有効フラグ
  int num;  //弾の番号
  int HP = BaseMaxHP; //HP
  float theta;
  int vx = 3, vy = 3;
  int h1 = select.getH1(), h2 = select.getH2();
  int energyMax = select.getEnergy();
  int energy = energyMax;
  int BulletTime;
  int BoostUse = BoostMaxUse;
  int BoostTimeCount;
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

  Player() {
    this.x = x;
    this.y = y;
    this.radius = radius;
  }

  // 位置座標を更新する。
  void update() {
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
        vx = select.getBoostSpeed();
        vy = select.getBoostSpeed();
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

    if (mouseX < x) {
      x -= vx;
    }
    else {
      x += vx;
    }
    if (mouseY < y) {
      y -= vy;
    }
    else {
      y += vy;
    }
  }

  // 弾を撃つ。
  void fire() {    
    if (num != 0) {
      if (num == 1) {
        if (energy >= BeamGunEnergy) {
          if (BulletTime == 0) {
            float v = BeamGunSpeed;    
            energy -= BeamGunEnergy;   
            theta = atan2(enemy.getY() - player.y, enemy.getX() - player.x);
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
            energy -= SniperEnergy;   
            theta = atan2(enemy.getY() - player.y, enemy.getX() - player.x);
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
            energy -= BigBeamEnergy;   
            theta = atan2(enemy.getY() - player.y, enemy.getX() - player.x);
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

  // 体力を減らす。
  void reduceLife(int i) {
    HP -= enemyBullet[i].damage;

    // 体力が無くなると死亡する。
    if (HP <= 0) {
      HP = 0;
      isDead = true;
    }
  }

  // 弾を生成する。
  void setBullet(float x, float y, float vx, float vy, int num, int damage, int comb, float theta, float radius) {
    // 弾の色を決める。
    int h = 100;
    // 死んでいる弾を再利用する。
    for (int i = 0; i < playerBullet.length; i++) {
      if (playerBullet[i].isDead()) {
        playerBullet[i].setBullet(x, y, vx, vy, num, h, damage * select.getDamageSet(), comb, theta, radius, 1);
        break;
      }
    }
  }

  // 描画する。
  void draw() {
    fill(select.getH1(), 100, 100);
    ellipse(x, y, radius, radius);
    fill(select.getH2(), 100, 100);
    ellipse(x, y, radius / 1.5, radius / 1.5); 
    drawLifeBar();
  }

  void drawLifeBar() {
    fill(220, 100, 100, 180);
    rect(510 - (HP / 20), 20, HP / 20, 20);
    fill(100, 100, 100, 170);
    rect(510 - (energy / 2), 45, (energy / 2), 5);
    if (frameCount % 2 == 0) {
      if (energy <= energyMax) {
        energy += 2;
      }
    }
  }
}

