// 弾クラス
class Bullet {
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

  Bullet() {
  }

  // フィールド値を設定する。
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

  // 位置を更新する。
  void update() {
    if (isDead) {
      return;
    }
    if (frameCount % 50 == 0) {   
      if (orNum == 1) {
        theta = atan2(enemy.getY() - y, enemy.getX() - x);
        orNum = 3;
      }
      else if(orNum == 0){
        theta = atan2(player.getY() - y, player.getX() - x);
        orNum = 3;
      }
    }
    x += vx * cos(theta);
    y += vy * sin(theta);

    // 領域外に出たら消す。
    if (x < 0 || x > width || y < 0 || y > height) {
      isDead = true;
    }
  }

  // 弾を殺す。
  void kill() {
    isDead = true;
  }

  // 描画する。
  void draw() {
    if (isDead) {
      return;
    }
    if (num == 1) {
      fill(100, 100, 100, 180);
      ellipse(x, y, radius, radius);
    }
    else if (num == 2) {
      fill(300, 100, 100, 180);
      ellipse(x, y, radius, radius);
    }
    else if (num == 3) {
      fill(60, 100, 100, 130);
      ellipse(x, y, radius, radius);
    }
  }
}

