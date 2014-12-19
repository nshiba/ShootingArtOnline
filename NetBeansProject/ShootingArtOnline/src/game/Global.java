/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author snake00
 */
public class Global {
	
	public static int X;
	public static int Y;

	/**
	 * enemy bullet
	 */
	public static Bullet[] enemyBullet;
	/**
	 * my bullet
	 */
	public static Bullet[] playerBullet;
	
	/**
	 * frame count
	 */
	public static int FrameCount;
	
	/**
	 * my machine set X
	 */
	public static void setMouseX(double x){
		X = (int)x;
	}
	/**
	 * my machine get X
	 */
	public static int getX(){
		return X;
	}
	/**
	 * my machine set Y
	 */
	public static void setMouseY(double y){
		Y = (int)y;
	}
	/**
	 * my machine get Y
	 */
	public static int getY(){
		return Y;
	}
}
