/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author snake00
 */
public class Title {

	private boolean titleFlag;
	private final Image image = new Image("resource/sao_back_2.jpg");
	private final Font titleFontBig = new Font("Italic", 100);
	private final Font titleFontSmall = new Font("Italic", 80);
	private final Font menuFont = new Font("Italic", 50);
	private float[] titleLogo;
	public boolean isVS = false;
	public boolean isCPU = false;

	public Title() {
		titleLogo = new float[4];
		for (int i = 0; i < titleLogo.length; i++) {
			titleLogo[i] = 0;
		}
	}

	void draw(GraphicsContext context) {
		/**
		 * background
		 */
		context.drawImage(image, -150, 0);
		/**
		 * title logo
		 */
		if (Global.FrameCount < 80) {
			context.setFill(Color.hsb(200, 0, 0, 1));
			context.setFont(titleFontBig);
			context.fillText("S", 90, 400);
			context.fillText("A", 90, 500);
			context.fillText("O", 90, 600);

		} else if (Global.FrameCount < 425) {
			context.setFill(Color.hsb(200, 0, 0, 1));

			context.setFont(titleFontBig);
			context.fillText("S", 90, 400);

			if (Global.FrameCount > 60) {
				if (Global.FrameCount > 60 && Global.FrameCount < 160) {
					titleLogo[0] += 0.01;
					context.setFont(titleFontSmall);
					context.setFill(Color.hsb(200, 0, 0, titleLogo[0]));
					context.fillText("h", 140, 400);
					context.fillText("o", 180, 400);
					context.fillText("o", 220, 400);
					context.fillText("t", 260, 400);
					context.fillText("i", 285, 400);
					context.fillText("n", 300, 400);
					context.fillText("g", 340, 400);
				} else if (Global.FrameCount >= 160) {
					context.setFont(titleFontSmall);
					context.setFill(Color.hsb(200, 0, 0, 1));
					context.fillText("h", 140, 400);
					context.fillText("o", 180, 400);
					context.fillText("o", 220, 400);
					context.fillText("t", 260, 400);
					context.fillText("i", 285, 400);
					context.fillText("n", 300, 400);
					context.fillText("g", 340, 400);
				}
				if (Global.FrameCount > 60 && Global.FrameCount < 245) {
					context.setFill(Color.hsb(200, 0, 0, 1));
					context.setFont(titleFontBig);
					context.fillText("A", 90 + Global.FrameCount - 60, 500);
				} else if (Global.FrameCount >= 245) {
					context.setFont(titleFontBig);
					context.setFill(Color.hsb(200, 0, 0, 1));
					context.fillText("A", 275, 500);
				}
				if (Global.FrameCount > 245 && Global.FrameCount < 345) {
					titleLogo[1] += 0.01;
					context.setFill(Color.hsb(200, 0, 0, titleLogo[1]));
					context.setFont(titleFontSmall);
					context.fillText("r", 340, 500);
					context.fillText("t", 370, 500);
				} else if (Global.FrameCount >= 345) {
					context.setFill(Color.hsb(200, 0, 0, 1));
					context.setFont(titleFontSmall);
					context.fillText("r", 340, 500);
					context.fillText("t", 370, 500);

				}
				if (Global.FrameCount > 60 && Global.FrameCount < 325) {
					context.setFill(Color.hsb(200, 0, 0, 1));
					context.setFont(titleFontBig);
					context.fillText("O", 90 + Global.FrameCount - 60, 600);
				} else if (Global.FrameCount >= 325) {
					context.setFill(Color.hsb(200, 0, 0, 1));
					context.setFont(titleFontBig);
					context.fillText("O", 355, 600);

				}
				if (Global.FrameCount > 325 && Global.FrameCount < 425) {
					titleLogo[2] += 0.01;
					context.setFill(Color.hsb(200, 0, 0, titleLogo[2]));
					context.setFont(titleFontSmall);
					context.fillText("n", 425, 600);
					context.fillText("l", 470, 600);
					context.fillText("i", 490, 600);
					context.fillText("n", 510, 600);
					context.fillText("e", 560, 600);
				} else if (Global.FrameCount >= 425) {
					context.setFill(Color.hsb(200, 0, 0, 1));
					context.setFont(titleFontSmall);
					context.fillText("n", 425, 600);
					context.fillText("l", 470, 600);
					context.fillText("i", 490, 600);
					context.fillText("n", 510, 600);
					context.fillText("e", 560, 600);
				}
			}

		} else {
			context.setFill(Color.hsb(200, 0, 0, 1));

			context.setFont(titleFontBig);
			context.fillText("S", 90, 400);

			context.setFont(titleFontSmall);
			context.fillText("h", 140, 400);
			context.fillText("o", 180, 400);
			context.fillText("o", 220, 400);
			context.fillText("t", 260, 400);
			context.fillText("i", 285, 400);
			context.fillText("n", 300, 400);
			context.fillText("g", 340, 400);

			context.setFont(titleFontBig);
			context.fillText("A", 275, 500);

			context.setFont(titleFontSmall);
			context.fillText("r", 340, 500);
			context.fillText("t", 370, 500);

			context.setFont(titleFontBig);
			context.fillText("O", 355, 600);

			context.setFont(titleFontSmall);
			context.fillText("n", 425, 600);
			context.fillText("l", 470, 600);
			context.fillText("i", 490, 600);
			context.fillText("n", 510, 600);
			context.fillText("e", 560, 600);

			/**
			 * menu
			 */
			if (Global.FrameCount > 450 && Global.FrameCount < 525) {
				titleLogo[3] += 0.01;
				context.setFill(Color.hsb(250, 1, 1, titleLogo[3]));
				context.setFont(menuFont);
				context.fillText("VS MODE", 820, 400);
				context.setFill(Color.hsb(250, 1, 1, titleLogo[3]));
				context.setFont(menuFont);
				context.fillText("CPU MODE", 850, 500);
			} else if (Global.FrameCount >= 525) {
				if (Global.getMouseX() > 820 && Global.getMouseX() < 1200 && Global.getMouseY() > 350 && Global.getMouseY() < 400) {
					context.setFill(Color.hsb(270, 1, 1, 1));
					if(Global.getMousePressed()){
						setIsVS(true);
						setTitleFlag(false);
					}
				} else {
					context.setFill(Color.hsb(250, 1, 1, 0.75));
				}
				context.setFont(menuFont);
				context.fillText("VS MODE", 820, 400);
				if (Global.getMouseX() > 850 && Global.getMouseX() < 1250 && Global.getMouseY() > 450 && Global.getMouseY() < 500) {
					context.setFill(Color.hsb(270, 1, 1, 1));
					if(Global.getMousePressed()){
						setIsCPU(true);
						setTitleFlag(false);
					}
				} else {
					context.setFill(Color.hsb(250, 1, 1, 0.75));
				}
				context.setFont(menuFont);
				context.fillText("CPU MODE", 850, 500);
			}
		}
	}

	/**
	 * get title flag
	 *
	 * @return
	 */
	public boolean getTitleFlag() {
		return titleFlag;
	}

	/**
	 * set title flag
	 *
	 * @param b
	 */
	public void setTitleFlag(boolean b) {
		this.titleFlag = b;
		System.out.println("ok");
		Global.FrameCount = 0;
	}

	/**
	 * @return the isVS
	 */
	public boolean isIsVS() {
		return isVS;
	}

	/**
	 * @param isVS the isVS to set
	 */
	public void setIsVS(boolean isVS) {
		this.isVS = isVS;
	}

	/**
	 * @return the isCPU
	 */
	public boolean isIsCPU() {
		return isCPU;
	}

	/**
	 * @param isCPU the isCPU to set
	 */
	public void setIsCPU(boolean isCPU) {
		this.isCPU = isCPU;
	}

}
