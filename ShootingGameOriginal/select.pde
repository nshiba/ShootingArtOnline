class Select {
  int HP;
  int energy;
  int BoostSpeed;
  int ch1H, ch2H, ch3H;
  int h1, h2;
  float damageSet;

  int getHP() {
    return HP;
  }
  int getEnergy() {
    return energy;
  }
  int getBoostSpeed() {
    return BoostSpeed;
  }
  int getH1() {
    return h1;
  }
  int getH2() {
    return h2;
  }
  float getDamageSet() {
    return damageSet;
  }

  Select(int HP, int energy, int BoostSpeed, int h1, int h2) {
    this.HP = HP;
    this.energy = energy;
    this.BoostSpeed = BoostSpeed;
    this.h1 = h1;
    this.h2 = h2;
    this.damageSet = 1.0;
  }
  void draw() {
 
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
    ellipse(330, 300, player.getRadius() * 4 / 1.5, player.getRadius() * 4 / 1.5);
    fill(0, 100, 100);
    ellipse(630, 300, player.getRadius() * 4, player.getRadius() * 4);
    fill(280, 100, 100);
    ellipse(630, 300, player.getRadius() * 4 / 1.5, player.getRadius() * 4 / 1.5);
    fill(110, 100, 100);
    ellipse(950, 300, player.getRadius() * 4, player.getRadius() * 4);
    fill(80, 100, 100);
    ellipse(950, 300, player.getRadius() * 4 / 1.5, player.getRadius() * 4 / 1.5);
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
  void cursol() {
    if (mouseX > 230 && mouseY > 200 && mouseX < 430 && mouseY < 400) {
      ch1H = 360;
      ch2H = 0;
      ch3H = 0;
      if (mousePressed) {
        h1 = 180;
        h2 = 220;
        HP = 10000;
        BoostSpeed = 10;
        damageSet = 1.0;
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
        damageSet = 1.3;
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
        damageSet = 0.8;
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

