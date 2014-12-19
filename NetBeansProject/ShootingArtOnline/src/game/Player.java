/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import config.GameConfig;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;

/**
 *
 * @author snake00
 */
public class Player {

	private int x;
	private int y;
	private boolean isDead;
	int vx = 3, vy = 3;

	Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.isDead = false;
	}

	public boolean isDead() {
		return isDead;
	}

	public void update() {
		if (Global.getX() < x) {
			x -= vx;
		} else {
			x += vx;
		}
		if (Global.getY() < y) {
			y -= vy;
		} else {
			y += vy;
		}

	}

	void draw(GraphicsContext context) {

		context.setFill(Color.rgb(255, 0, 0, 1.0));
		context.fillOval(x, y, GameConfig.radius, GameConfig.radius);

	}

}
