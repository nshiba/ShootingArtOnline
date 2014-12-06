//クラス
//Arcade arcade;    //アーケードモードを管理
Bullet[] enemyBullet;    //敵の弾
Bullet[] playerBullet;    //自機の弾
Title title;    //タイトル画面の制御
Enemy enemy; //敵の動き、思考ルーチン制御
Option option;    //オプション画面の制御と、全体への反映
Player player;    //自機の制御
Select select;    //キャラの選択画面
//Versus versus;    //対戦モードの制御

//判定
boolean TitleFlag = true;
boolean GameFlag = false;
boolean OptionFlag = false;
boolean SelectFlag = false;

//定数
int PlayerBulletCount = 100;  // 主人公の撃てる弾の最大数
int EnemyBulletCount = 100;  // 敵の撃てる弾の最大数
int radius = 70; //機体の大きさ

int PlayerNum = 1; //最大プレイ人数
int Difficulty = 1; //標準難易度

int BaseMaxHP = 10000; //バランス型の最大HP
int EnergyMax = 300; //バランス型の最大エネルギー
int SniperEnergy = 30; //スナイパーのエネルギー消費量
int SniperDamage = 500; //スナイパーのダメージ
int SniperCombNum = 100; //スナイパーのダウン値
int SniperSpeed = 20;
int SniperTime = 50;
int BeamGunEnergy = 2; //ビームマシンガンのエネルギー消費量
int BeamGunDamage = 20; //ビームマシンガンのダメージ
int BeamGunCombNum = 4; //ビームガンダウン値
int BeamGunSpeed = 10;
int BeamGunTime = 2;
int BigBeamEnergy = 80; //ゲロビのエネルギー消費量
int BigBeamDamage = 1000; //ゲロビのダメージ
int BigBeamCombNum = 100; //ゲロビのダウン値
int BigBeamSpeed = 15;
int BigBeamTime = 120;
int SordDamage = 500; //接近攻撃のダメージ
int SordCombNum = 3; //接近攻撃の最大コンボ数
int BoostEnergy = 30; //ブースト時のエネルギー消費量
int BoostMaxUse = 3;  //バランス型の連続ブースト使用回数
int BoostSpeed = 10; //ブースト時の速度上昇
int BoostTime = 50; //ブースト時間(フレーム)
int DawnNum = 100; //ダウン値
int DawnTime = 120; //ダウンしてる時間（フレーム）

void setup() {
  size(1280, 720);
  frameRate(60);
  colorMode(HSB, 360, 100, 100);
  smooth();
  noStroke();
 
  title = new Title();
 option = new Option(Difficulty); 
 select = new Select(BaseMaxHP, EnergyMax, BoostSpeed, 180, 220);
  setupGame();
}

void setupGame() {
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

void draw() {
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
void Game() {
  // 自機が死亡している？
  if (player.isDead()) {
    // ゲームオーバー処理を行う。
    updateGameOver();
  }
  // 敵が死亡している？
  else if (enemy.isDead()) {
    // ゲームクリア処理を行う。
    updateGameClear();
  }
  else {
    // ゲームを進行させる。
    updateGame();
  }
}

void updateGameOver() {
  enemy.update();
  enemy.draw();
  for (int i = 0; i < enemyBullet.length; i++) {
    enemyBullet[i].update();
    enemyBullet[i].draw();
  }
  // システムメッセージを表示する。
  fill(200);
  textSize(32);
  text("You  Lose", width / 2 - 90, height / 2);
  textSize(10);
  text("Press R Key to restart.", width / 2 - 58, height / 2 + 20);
  text(" Press Q Key to quiet. ", width / 2 - 58, height / 2 + 30);

  // Rキーでリトライする。
  if (keyPressed && key == 'r') {
    setupGame();
  }
  //Ｑキーでセレクト
  if (keyPressed && key == 'q') {
    frameCount = 0;
    SelectFlag = !SelectFlag;
    GameFlag = !GameFlag;
  }
}
void updateGameClear() {
  player.update();
  player.draw();
  for (int i = 0; i < playerBullet.length; i++) {
    playerBullet[i].update();
    playerBullet[i].draw();
  }

  // システムメッセージを表示する。
  fill(200);
  textSize(32);
  text("You  Win!", width / 2 - 90, height / 2);
  textSize(10);
  text("Press R Key to restart.", width / 2 - 58, height / 2 + 20);
  text(" Press Q Key to quiet. ", width / 2 - 58, height / 2 + 30);

  // Rキーでリトライする。
  if (keyPressed && key == 'r') {
    setupGame();
  }
  //Ｑキーでセレクト
  if (keyPressed && key == 'q') {
    frameCount = 0;
    SelectFlag = !SelectFlag;
     GameFlag = !GameFlag;
  }
}

void bg() {
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

void updateGame() {
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
    // 自機が弾を撃つ。
    player.fire();

    // 敵が弾を撃つ。
    enemy.fire();

    player.draw();
    enemy.draw();
    for (int i = 0; i < playerBullet.length; i++) {
      playerBullet[i].draw();
    }
    for (int i = 0; i < enemyBullet.length; i++) {
      enemyBullet[i].draw();
    }
    // 自機と敵の弾との衝突判定。
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

  // 敵と自機の弾との衝突判定。
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

void keyPressed() {
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

void keyReleased() {
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

