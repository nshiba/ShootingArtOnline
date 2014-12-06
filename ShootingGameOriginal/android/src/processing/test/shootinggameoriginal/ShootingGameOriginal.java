package processing.test.shootinggameoriginal;

import processing.core.*; 
import processing.data.*; 
import processing.opengl.*; 

import android.view.MotionEvent; 
import android.view.KeyEvent; 
import android.graphics.Bitmap; 
import java.io.*; 
import java.util.*; 

public class ShootingGameOriginal extends PApplet {

//\u30af\u30e9\u30b9
//Arcade arcade;    //\u30a2\u30fc\u30b1\u30fc\u30c9\u30e2\u30fc\u30c9\u3092\u7ba1\u7406
Bullet[] enemyBullet;    //\u6575\u306e\u5f3e
Bullet[] playerBullet;    //\u81ea\u6a5f\u306e\u5f3e
Title title;    //\u30bf\u30a4\u30c8\u30eb\u753b\u9762\u306e\u5236\u5fa1
Enemy enemy; //\u6575\u306e\u52d5\u304d\u3001\u601d\u8003\u30eb\u30fc\u30c1\u30f3\u5236\u5fa1
Option option;    //\u30aa\u30d7\u30b7\u30e7\u30f3\u753b\u9762\u306e\u5236\u5fa1\u3068\u3001\u5168\u4f53\u3078\u306e\u53cd\u6620
Player player;    //\u81ea\u6a5f\u306e\u5236\u5fa1
Select select;    //\u30ad\u30e3\u30e9\u306e\u9078\u629e\u753b\u9762
//Versus versus;    //\u5bfe\u6226\u30e2\u30fc\u30c9\u306e\u5236\u5fa1

//\u5224\u5b9a
boolean TitleFlag = true;
boolean GameFlag = false;
boolean OptionFlag = false;
boolean SelectFlag = false;

//\u5b9a\u6570
int PlayerBulletCount = 100;  // \u4e3b\u4eba\u516c\u306e\u6483\u3066\u308b\u5f3e\u306e\u6700\u5927\u6570
int EnemyBulletCount = 100;  // \u6575\u306e\u6483\u3066\u308b\u5f3e\u306e\u6700\u5927\u6570
int radius = 70; //\u6a5f\u4f53\u306e\u5927\u304d\u3055

int PlayerNum = 1; //\u6700\u5927\u30d7\u30ec\u30a4\u4eba\u6570
int Difficulty = 1; //\u6a19\u6e96\u96e3\u6613\u5ea6

int BaseMaxHP = 10000; //\u30d0\u30e9\u30f3\u30b9\u578b\u306e\u6700\u5927HP
int EnergyMax = 300; //\u30d0\u30e9\u30f3\u30b9\u578b\u306e\u6700\u5927\u30a8\u30cd\u30eb\u30ae\u30fc
int SniperEnergy = 30; //\u30b9\u30ca\u30a4\u30d1\u30fc\u306e\u30a8\u30cd\u30eb\u30ae\u30fc\u6d88\u8cbb\u91cf
int SniperDamage = 500; //\u30b9\u30ca\u30a4\u30d1\u30fc\u306e\u30c0\u30e1\u30fc\u30b8
int SniperCombNum = 100; //\u30b9\u30ca\u30a4\u30d1\u30fc\u306e\u30c0\u30a6\u30f3\u5024
int SniperSpeed = 20;
int SniperTime = 50;
int BeamGunEnergy = 2; //\u30d3\u30fc\u30e0\u30de\u30b7\u30f3\u30ac\u30f3\u306e\u30a8\u30cd\u30eb\u30ae\u30fc\u6d88\u8cbb\u91cf
int BeamGunDamage = 20; //\u30d3\u30fc\u30e0\u30de\u30b7\u30f3\u30ac\u30f3\u306e\u30c0\u30e1\u30fc\u30b8
int BeamGunCombNum = 4; //\u30d3\u30fc\u30e0\u30ac\u30f3\u30c0\u30a6\u30f3\u5024
int BeamGunSpeed = 10;
int BeamGunTime = 2;
int BigBeamEnergy = 80; //\u30b2\u30ed\u30d3\u306e\u30a8\u30cd\u30eb\u30ae\u30fc\u6d88\u8cbb\u91cf
int BigBeamDamage = 1000; //\u30b2\u30ed\u30d3\u306e\u30c0\u30e1\u30fc\u30b8
int BigBeamCombNum = 100; //\u30b2\u30ed\u30d3\u306e\u30c0\u30a6\u30f3\u5024
int BigBeamSpeed = 15;
int BigBeamTime = 120;
int SordDamage = 500; //\u63a5\u8fd1\u653b\u6483\u306e\u30c0\u30e1\u30fc\u30b8
int SordCombNum = 3; //\u63a5\u8fd1\u653b\u6483\u306e\u6700\u5927\u30b3\u30f3\u30dc\u6570
int BoostEnergy = 30; //\u30d6\u30fc\u30b9\u30c8\u6642\u306e\u30a8\u30cd\u30eb\u30ae\u30fc\u6d88\u8cbb\u91cf
int BoostMaxUse = 3;  //\u30d0\u30e9\u30f3\u30b9\u578b\u306e\u9023\u7d9a\u30d6\u30fc\u30b9\u30c8\u4f7f\u7528\u56de\u6570
int BoostSpeed = 10; //\u30d6\u30fc\u30b9\u30c8\u6642\u306e\u901f\u5ea6\u4e0a\u6607
int BoostTime = 50; //\u30d6\u30fc\u30b9\u30c8\u6642\u9593(\u30d5\u30ec\u30fc\u30e0)
int DawnNum = 100; //\u30c0\u30a6\u30f3\u5024
int DawnTime = 120; //\u30c0\u30a6\u30f3\u3057\u3066\u308b\u6642\u9593\uff08\u30d5\u30ec\u30fc\u30e0\uff09

public void setup() {
 
  frameRate(60);
  colorMode(HSB, 360, 100, 100);
  smooth();
  noStroke();
 
  title = new Title();
 option = new Option(Difficulty); 
 select = new Select(BaseMaxHP, EnergyMax, BoostSpeed, 180, 220);
  setupGame();
}

public void setupGame() {
  frameCount = 0;

  player = new Player();
  enemy = new Enemy();
 

  playerBullet = new Bullet[PlayerBulletCount];
  for (int i = 0; i < playerBullet.length; i++) {
    playerBullet[i] = new Bullet();
  }
  enemyBullet = new Bullet[EnemyBulletCount];
  for (int i = 0; i < enemyBullet.length; i++) {
    enemyBullet[i] = new Bullet();
  }
}

public void draw() {
  bg();
  if(TitleFlag){
    title.draw();
  }
  if(GameFlag) {
  Game();
  }
  if(OptionFlag){
    option.draw();
  }
  if(SelectFlag){
    select.draw();
  }
}
public void Game() {
  // \u81ea\u6a5f\u304c\u6b7b\u4ea1\u3057\u3066\u3044\u308b\uff1f
  if (player.isDead()) {
    // \u30b2\u30fc\u30e0\u30aa\u30fc\u30d0\u30fc\u51e6\u7406\u3092\u884c\u3046\u3002
    updateGameOver();
  }
  // \u6575\u304c\u6b7b\u4ea1\u3057\u3066\u3044\u308b\uff1f
  else if (enemy.isDead()) {
    // \u30b2\u30fc\u30e0\u30af\u30ea\u30a2\u51e6\u7406\u3092\u884c\u3046\u3002
    updateGameClear();
  }
  else {
    // \u30b2\u30fc\u30e0\u3092\u9032\u884c\u3055\u305b\u308b\u3002
    updateGame();
  }
}

public void updateGameOver() {
  enemy.update();
  enemy.draw();
  for (int i = 0; i < enemyBullet.length; i++) {
    enemyBullet[i].update();
    enemyBullet[i].draw();
  }
  // \u30b7\u30b9\u30c6\u30e0\u30e1\u30c3\u30bb\u30fc\u30b8\u3092\u8868\u793a\u3059\u308b\u3002
  fill(200);
  textSize(32);
  text("You  Lose", width / 2 - 90, height / 2);
  textSize(10);
  text("Press R Key to restart.", width / 2 - 58, height / 2 + 20);
  text(" Press Q Key to quiet. ", width / 2 - 58, height / 2 + 30);

  // R\u30ad\u30fc\u3067\u30ea\u30c8\u30e9\u30a4\u3059\u308b\u3002
  if (keyPressed && key == 'r') {
    setupGame();
  }
  //\uff31\u30ad\u30fc\u3067\u30bb\u30ec\u30af\u30c8
  if (keyPressed && key == 'q') {
    frameCount = 0;
    SelectFlag = !SelectFlag;
    GameFlag = !GameFlag;
  }
}
public void updateGameClear() {
  player.update();
  player.draw();
  for (int i = 0; i < playerBullet.length; i++) {
    playerBullet[i].update();
    playerBullet[i].draw();
  }

  // \u30b7\u30b9\u30c6\u30e0\u30e1\u30c3\u30bb\u30fc\u30b8\u3092\u8868\u793a\u3059\u308b\u3002
  fill(200);
  textSize(32);
  text("You  Win!", width / 2 - 90, height / 2);
  textSize(10);
  text("Press R Key to restart.", width / 2 - 58, height / 2 + 20);
  text(" Press Q Key to quiet. ", width / 2 - 58, height / 2 + 30);

  // R\u30ad\u30fc\u3067\u30ea\u30c8\u30e9\u30a4\u3059\u308b\u3002
  if (keyPressed && key == 'r') {
    setupGame();
  }
  //\uff31\u30ad\u30fc\u3067\u30bb\u30ec\u30af\u30c8
  if (keyPressed && key == 'q') {
    frameCount = 0;
    SelectFlag = !SelectFlag;
     GameFlag = !GameFlag;
  }
}

public void bg() {
  if(GameFlag) {
  background(0);
  }
  if(TitleFlag) {
    background(0, 100, 80, 100);
  }
  if(OptionFlag) {
    background(0, 0, 80, 100);
    fill(200, 100, 100, 130);
    rect(20, 20, 1240, 680);
  }
  if(SelectFlag){
    background(220, 100, 100, 5);
    fill(230, 80, 60, 70);
    rect(20, 20, 1240, 680);
  }
}

public void updateGame() {
  if(keyPressed && key == 'q') {
    GameFlag = !GameFlag;
    TitleFlag = !TitleFlag;
  }
  if (frameCount < 100) {
    fill(200);
    textSize(32);
    text("Ready", width / 2 - 46, height / 2);
    player.draw();
    enemy.draw();
  }
  else if (frameCount < 150) {
    player.draw();
    enemy.draw();
    fill(200);
    textSize(32);
    text("Go!", width / 2 - 24, height / 2);
  }
  else {
    enemy.update();
    player.update();
    for (int i = 0; i < playerBullet.length; i++) {
      playerBullet[i].update();
    }
    for (int i = 0; i < enemyBullet.length; i++) {
      enemyBullet[i].update();
    }
    // \u81ea\u6a5f\u304c\u5f3e\u3092\u6483\u3064\u3002
    player.fire();

    // \u6575\u304c\u5f3e\u3092\u6483\u3064\u3002
    enemy.fire();

    player.draw();
    enemy.draw();
    for (int i = 0; i < playerBullet.length; i++) {
      playerBullet[i].draw();
    }
    for (int i = 0; i < enemyBullet.length; i++) {
      enemyBullet[i].draw();
    }
    // \u81ea\u6a5f\u3068\u6575\u306e\u5f3e\u3068\u306e\u885d\u7a81\u5224\u5b9a\u3002
    for (int i = 0; i < enemyBullet.length; i++) {
      if (enemyBullet[i].isDead())
        continue;

      float dx = enemyBullet[i].getX() - player.getX();
      float dy = enemyBullet[i].getY() - player.getY();
      float r = enemyBullet[i].getRadius() + player.getRadius();

      if (dx * dx + dy * dy < r * r) {
        player.reduceLife(i);
        enemyBullet[i].kill();
      }
    }
  }

  // \u6575\u3068\u81ea\u6a5f\u306e\u5f3e\u3068\u306e\u885d\u7a81\u5224\u5b9a\u3002
  for (int i = 0; i < playerBullet.length; i++) {
    if (playerBullet[i].isDead())
      continue;

    float dx = playerBullet[i].getX() - enemy.getX();
    float dy = playerBullet[i].getY() - enemy.getY();
    float r = playerBullet[i].getRadius() + enemy.getRadius();

    if (dx * dx + dy * dy <  r * r) {
      playerBullet[i].kill();
      enemy.reduceLife(i);
    }
  }
}

public void keyPressed() {
  if (key == 'a') {
    player.num = 1;
  }
  else if (key == 's') {
    player.num = 2;
  }
  else if (key == 'd') {
    player.num = 3;
  }
  else if (key == 'x') {
    player.BulletTime = 0;
    player.num = 5;
  }
  else { 
    player.num = 0;
  }
}

public void keyReleased() {
  if (key == 'a') {
    player.num = 0;
  }
  else if (key == 's') {
    player.num = 0;
  }
  else if (key == 'd') {
    player.num = 0;
  }
}

// \u5f3e\u30af\u30e9\u30b9
class Bullet {
  float x, y;             // \u4f4d\u7f6e
  float vx, vy;            // \u901f\u5ea6
  float radius;           // \u534a\u5f84
  boolean isDead = true;  // \u6709\u52b9\u30d5\u30e9\u30b0
  float h;                // \u8272
  int num;
  float damage;
  int comb;
  float theta;
  float orNum;


  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }

  public float getRadius() {
    return radius / 2;
  }

  public boolean isDead() {
    return isDead;
  }

  Bullet() {
  }

  // \u30d5\u30a3\u30fc\u30eb\u30c9\u5024\u3092\u8a2d\u5b9a\u3059\u308b\u3002
  public void setBullet(float x, float y, float vx, float vy, 
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

    // \u5f3e\u3092\u6709\u52b9\u306b\u3059\u308b\u3002
    isDead = false;
  }

  // \u4f4d\u7f6e\u3092\u66f4\u65b0\u3059\u308b\u3002
  public void update() {
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

    // \u9818\u57df\u5916\u306b\u51fa\u305f\u3089\u6d88\u3059\u3002
    if (x < 0 || x > width || y < 0 || y > height) {
      isDead = true;
    }
  }

  // \u5f3e\u3092\u6bba\u3059\u3002
  public void kill() {
    isDead = true;
  }

  // \u63cf\u753b\u3059\u308b\u3002
  public void draw() {
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

// \u6575\u30af\u30e9\u30b9
class Enemy {
  // \u5909\u5316\u3057\u306a\u3044\u5024
  int maxLife;                 // \u4f53\u529b\u306e\u6700\u5927\u5024

  // \u5909\u5316\u3059\u308b\u5024
  float x = 1100, y = 520;      // \u4f4d\u7f6e
  float vx, vy;
  float radius = 70;    // \u534a\u5f84
  boolean isDead;  // \u6709\u52b9\u30d5\u30e9\u30b0
  int HP = BaseMaxHP * option.getDifficulty();        // \u73fe\u5728\u306e\u4f53\u529b
  int num; //\u5f3e\u306e\u756a\u53f7
  float theta;
  int BulletTime;
  int energyMax = EnergyMax;
  int energy = energyMax;
  int BoostUse = BoostMaxUse;
  int BoostTimeCount;
  int vector;
  int comb;

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }

  public float getRadius() {
    return radius / 2;
  }

  public boolean isDead() {
    return isDead;
  }

  Enemy() {
    this.x = x;
    this.y = y;
    this.radius = radius;
    this.HP = BaseMaxHP;
  }

  // \u4f4d\u7f6e\u3092\u5909\u66f4\u3059\u308b\u3002
  public void update() {
    // ToDo

    routine(); //\u601d\u8003\u30eb\u30fc\u30c1\u30f3\u95a2\u6570\u547c\u3073\u51fa\u3057

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

  // \u5f3e\u3092\u6483\u3064\u3002
  public void fire() {
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

  public void setBullet(float x, float y, float vx, float vy, int num, int damage, int comb, float theta, int radius) {
    // \u5f3e\u306e\u8272\u3092\u9069\u5f53\u306b\u6c7a\u3081\u308b\u3002
    int h = 100;

    // \u6b7b\u3093\u3067\u3044\u308b\u5f3e\u3092\u518d\u5229\u7528\u3059\u308b\u3002
    for (int i = 0; i < enemyBullet.length; i++) {
      if (enemyBullet[i].isDead()) {
        enemyBullet[i].setBullet(x, y, vx, vy, num, h, damage * option.getDifficulty(), comb, theta, radius * 1.2f, 0);
        break;
      }
    }
  }

  // \u4f53\u529b\u3092\u6e1b\u3089\u3059\u3002
  public void reduceLife(int i) {
    HP -= playerBullet[i].damage;

    // \u4f53\u529b\u304c\u7121\u304f\u306a\u308b\u3068\u6b7b\u4ea1\u3059\u308b\u3002
    if (HP <= 0) {
      HP = 0;
      isDead = true;
    }
  }

  // \u63cf\u753b\u3059\u308b\u3002
  public void draw() {
    fill(70, 100, 100);
    ellipse(x, y, radius, radius);
    fill(0, 0, 0);
    ellipse(x, y, radius / 1.5f, radius / 1.5f); 

    drawLifeBar();
  }

  public void drawLifeBar() {
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

  //\u601d\u8003\u30eb\u30fc\u30c1\u30f3
  public void routine() { 
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

class Option {
  int Difficulty;
  
  Option(int Difficulty) {
   this.Difficulty = Difficulty; 
  }
  public int getDifficulty(){
    return Difficulty;
  }
  public void draw(){
    fill(0);
    textSize(30);
    text("Configrations",70,100);
    textSize(15);
    text("Press X to BoostDash", 90, 140);
    text("Press A to BeamGun", 90, 170);
    text("Press S to Sniper", 90, 210);
    text("Press D to Thunder Ball", 90, 250);
    text("Press Q to Return to Title", 90, 380);
    text("Difficulty Level to Push Number Key( 1 - 9 )", 600, 140); 
    text(Difficulty, 1000, 140);
    fill(100, 100, 100, 180);
    ellipse(160, 185, 8, 8);
    fill(300, 100, 100, 180);
    ellipse(160, 225, 15, 15);
    fill(60, 100, 100, 130);
    ellipse(160, 310, 90, 90);
    cursol();
  }
  public void cursol() {
    if(keyPressed && key == 'q') {
      OptionFlag = !OptionFlag;
      TitleFlag = !TitleFlag;
    }
    if(keyPressed && key == '1') {
      Difficulty = 1;
    }else if(keyPressed && key == '2') {
      Difficulty = 2;
    }else if(keyPressed && key == '3') {
      Difficulty = 3;
    }else if(keyPressed && key == '4') {
      Difficulty = 4;
    }else if(keyPressed && key == '5') {
      Difficulty = 5;
    }else if(keyPressed && key == '6') {
      Difficulty = 6;
    }else if(keyPressed && key == '7') {
      Difficulty = 7;
    }else if(keyPressed && key == '8') {
      Difficulty = 8;
    }else if(keyPressed && key == '9') {
      Difficulty = 9;
    }
  }
}
// \u81ea\u6a5f\u30af\u30e9\u30b9
class Player {  
  // \u5909\u5316\u3059\u308b\u5024
  float x = 100, y = 200;      // \u4f4d\u7f6e
  float radius = 70;    // \u534a\u5f84
  boolean isDead;  // \u6709\u52b9\u30d5\u30e9\u30b0
  int num;  //\u5f3e\u306e\u756a\u53f7
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

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }

  public float getRadius() {
    return radius / 2;
  }

  public boolean isDead() {
    return isDead;
  }

  Player() {
    this.x = x;
    this.y = y;
    this.radius = radius;
  }

  // \u4f4d\u7f6e\u5ea7\u6a19\u3092\u66f4\u65b0\u3059\u308b\u3002
  public void update() {
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

  // \u5f3e\u3092\u6483\u3064\u3002
  public void fire() {    
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

  // \u4f53\u529b\u3092\u6e1b\u3089\u3059\u3002
  public void reduceLife(int i) {
    HP -= enemyBullet[i].damage;

    // \u4f53\u529b\u304c\u7121\u304f\u306a\u308b\u3068\u6b7b\u4ea1\u3059\u308b\u3002
    if (HP <= 0) {
      HP = 0;
      isDead = true;
    }
  }

  // \u5f3e\u3092\u751f\u6210\u3059\u308b\u3002
  public void setBullet(float x, float y, float vx, float vy, int num, int damage, int comb, float theta, float radius) {
    // \u5f3e\u306e\u8272\u3092\u6c7a\u3081\u308b\u3002
    int h = 100;
    // \u6b7b\u3093\u3067\u3044\u308b\u5f3e\u3092\u518d\u5229\u7528\u3059\u308b\u3002
    for (int i = 0; i < playerBullet.length; i++) {
      if (playerBullet[i].isDead()) {
        playerBullet[i].setBullet(x, y, vx, vy, num, h, damage * select.getDamageSet(), comb, theta, radius, 1);
        break;
      }
    }
  }

  // \u63cf\u753b\u3059\u308b\u3002
  public void draw() {
    fill(select.getH1(), 100, 100);
    ellipse(x, y, radius, radius);
    fill(select.getH2(), 100, 100);
    ellipse(x, y, radius / 1.5f, radius / 1.5f); 
    drawLifeBar();
  }

  public void drawLifeBar() {
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

class Select {
  int HP;
  int energy;
  int BoostSpeed;
  int ch1H, ch2H, ch3H;
  int h1, h2;
  float damageSet;

  public int getHP() {
    return HP;
  }
  public int getEnergy() {
    return energy;
  }
  public int getBoostSpeed() {
    return BoostSpeed;
  }
  public int getH1() {
    return h1;
  }
  public int getH2() {
    return h2;
  }
  public float getDamageSet() {
    return damageSet;
  }

  Select(int HP, int energy, int BoostSpeed, int h1, int h2) {
    this.HP = HP;
    this.energy = energy;
    this.BoostSpeed = BoostSpeed;
    this.h1 = h1;
    this.h2 = h2;
    this.damageSet = 1.0f;
  }
  public void draw() {
 
    if (frameCount > 30) {
      cursol();
    }
    textSize(30);
    fill(0);
    text("Choose Your Character", 30, 50);
    textSize(15);
    text("HP", 150, 460);
    text("SPEED", 130, 490);
    text("DAMAGE", 120, 520);
    text("ENERGY", 120, 550);
    fill(ch1H);
    rect(230, 200, 200, 200);
    fill(ch2H);
    rect(530, 200, 200, 200);
    fill(ch3H);
    rect(850, 200, 200, 200); 
    fill(180, 100, 100);
    ellipse(330, 300, player.getRadius() * 4, player.getRadius() * 4);
    fill(220, 100, 100);
    ellipse(330, 300, player.getRadius() * 4 / 1.5f, player.getRadius() * 4 / 1.5f);
    fill(0, 100, 100);
    ellipse(630, 300, player.getRadius() * 4, player.getRadius() * 4);
    fill(280, 100, 100);
    ellipse(630, 300, player.getRadius() * 4 / 1.5f, player.getRadius() * 4 / 1.5f);
    fill(110, 100, 100);
    ellipse(950, 300, player.getRadius() * 4, player.getRadius() * 4);
    fill(80, 100, 100);
    ellipse(950, 300, player.getRadius() * 4 / 1.5f, player.getRadius() * 4 / 1.5f);
    fill(167, 54, 84);
    rect(230, 447, 180, 15);
    rect(230, 477, 180, 15);
    rect(230, 507, 180, 15);
    rect(230, 537, 180, 15);
    rect(530, 447, 190, 15);
    rect(530, 477, 120, 15);
    rect(530, 507, 200, 15);
    rect(530, 537, 190, 15);
    rect(850, 447, 150, 15);
    rect(850, 477, 200, 15);
    rect(850, 507, 160, 15);
    rect(850, 537, 200, 15);
  }
  public void cursol() {
    if (mouseX > 230 && mouseY > 200 && mouseX < 430 && mouseY < 400) {
      ch1H = 360;
      ch2H = 0;
      ch3H = 0;
      if (mousePressed) {
        h1 = 180;
        h2 = 220;
        HP = 10000;
        BoostSpeed = 10;
        damageSet = 1.0f;
        energy = 300; 
        SelectFlag = !SelectFlag;
        GameFlag = !GameFlag;
        setupGame();
      }
    }
    else  if (mouseX > 530 && mouseY > 200 && mouseX < 730 && mouseY < 400) {
      ch1H = 0;
      ch2H = 360;
      ch3H = 0;
      if (mousePressed) {
        h1 = 0;
        h2 = 280;
        HP = 12000;
        BoostSpeed = 6;
        damageSet = 1.3f;
        energy = 330; 
        SelectFlag = !SelectFlag;
        GameFlag = !GameFlag;
        setupGame();
      }
    }
    else  if (mouseX > 850 && mouseY > 200 && mouseX < 1050 && mouseY < 400) {
      ch1H = 0;
      ch2H = 0;
      ch3H = 360;
      if (mousePressed) {
        h1 = 110;
        h2 = 80;
        HP = 9000;
        BoostSpeed = 15;
        damageSet = 0.8f;
        energy = 400; 
        SelectFlag = !SelectFlag;
        GameFlag = !GameFlag;
        setupGame();
      }
    }
    else {
      ch1H = 0;
      ch2H = 0;
      ch3H = 0;
    }
     if( keyPressed && key == 'q') {
      SelectFlag = !SelectFlag;
      TitleFlag = !TitleFlag;
    }
  }
}

class Title {
  int versusH = 0;
  int optionH = 0;

  public void draw() {

    fill(0);
    textSize(100);
    text("Assault Shooting", width / 2 - 380, height / 2 - 200);
    textSize(10);
    text("made by snake00", 1150, 710);
    cursol();

    textSize(50);
    fill(versusH);
    text("VERSUS", width / 2 - 70, height / 2 - 100);
    fill(optionH);
    text("CONFIG", width / 2 - 70, height / 2 );
  }
  public void cursol() {
    if (mouseX > width / 2 - 70 && mouseX < width / 2 + 110 && mouseY > height / 2 - 150 && mouseY < height / 2 - 70) {
      versusH = 200;
      optionH = 0;
      if (mousePressed) {
        frameCount = 0;
        TitleFlag = !TitleFlag;
        SelectFlag = !SelectFlag;
      }
    }
    else if (mouseX > width / 2 - 80 && mouseX < width / 2 + 120 && mouseY > height / 2 - 50 && mouseY < height / 2 + 30) {
      versusH = 0;
      optionH = 200;
      if(mousePressed) {
        TitleFlag = !TitleFlag;
        OptionFlag = !OptionFlag;
      }
    }else {
  versusH = 0;
  optionH = 0;
    }
  }
}


  public int sketchWidth() { return 1280; }
  public int sketchHeight() { return 720; }
}
