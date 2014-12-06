class Title {
  int versusH = 0;
  int optionH = 0;

  void draw() {

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
  void cursol() {
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

