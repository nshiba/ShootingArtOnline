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
	
	public static int mouseX;
	public static int mouseY;
	public static int myX;
	public static int myY;
	public static int enemyX;
	public static int enemyY;

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
	 * mouse set X
	 * @param x
	 */
	public static void setMouseX(double x){
		mouseX = (int)x;
		//System.out.println("setok");
	}
	/**
	 * mouse get X
	 * @return 
	 */
	public static int getMouseX(){
		return mouseX;
	}
	/**
	 * my machine set X
	 * @param myx
	 */
	public static void setX(int myx){
		 myX = myx;
		
	}
	/**
	 * my machine set Y
	 * @param y
	 */
	public static void setMouseY(double y){
		mouseY = (int)y;
	}
	/**
	 * get mouse Y
	 * @return 
	 */
	public static int getMouseY(){
		return mouseY;
	} 
	/**
	 * my machine get Y
	 * @param myy
	 */
	public static void setY(int myy){
		 myY = myy;
	}
	/**
	 * set enemy X
	 * @param enemyx
	 */
	public static void setEnemyX(int enemyx){
		enemyX = enemyx;
	}
	/**
	 * get enemy X
	 * @return 
	 */
	public static int getEnemyX() {
		return enemyX;
	}
	/**
	 * set enemy Y
	 * @param enemyy
	 */
	public static void setEnemyY(int enemyy){
		enemyY = enemyy;
	}
	/**
	 * get enemy Y
	 * @return 
	 */
	public static int getEnemyY() {
		return enemyY;
	}
}
