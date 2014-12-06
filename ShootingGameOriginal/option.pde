class Option {
  int Difficulty;
  
  Option(int Difficulty) {
   this.Difficulty = Difficulty; 
  }
  int getDifficulty(){
    return Difficulty;
  }
  void draw(){
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
  void cursol() {
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
