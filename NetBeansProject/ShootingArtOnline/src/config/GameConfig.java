/*
 * Game Config Constant
 */

package config;

/**
 *
 * @author snake00
 */
public class GameConfig {
	
	/*** Constant ***/
	/**base constant**/
	/*base machine constant*/
	/**
	 * my Bullet Max Count
	 */
	final public static int PlayerBulletCount = 100;
	/**
	 * enemy Bullet Max Count
	 */
	final public static int EnemyBulletCount = 100;
	/**
	 * machine size
	 */
	final public static int radius = 70;
	/**
	 * base Max HP
	 */
	final public static int BaseMaxHP = 10000;
	/**
	 * Base Max Energy
	 */
	final  public static int EnergyMax = 300;
	
	/**bullet constant**/
	/*sniper bullet constant*/
	/**
	 * sniper energy use
	 */
	final public static int SniperEnergy = 30;
	/**
	 * sniper hit damge
	 */
	final public static int SniperDamage = 500;
	/**
	 * sniper down count
	 */
	final public static int SniperCombNum = 100;
	/**
	 * sniper speed
	 */
	final public static int SniperSpeed = 20;
	/**
	 * sniper cool time
	 */
	final public static int SniperTime = 50;
	/*beam gun constant*/
	/**
	 * beam gun energy use
	 */
	final public static int BeamGunEnergy = 2;
	/**
	 * beam gun hit damage
	 */
	final public static int BeamGunDamage = 20;
	/**
	 * beam gun down count
	 */
	final public static int BeamGunCombNum = 4;
	/**
	 * beam gun speed
	 */
	final public static int BeamGunSpeed = 15;
	/**
	 * beam gun cool time
	 */
	final public static int BeamGunTime = 2;
	/*big beam constant*/
	/**
	 * big beam energy use
	 */
	final public static int BigBeamEnergy = 80;
	/**
	 * big beam hit damage
	 */
	final public static int BigBeamDamage = 1000;
	/**
	 * big beam down count
	 */
	final public static int BigBeamCombNum = 100;
	/**
	 * big beam speed
	 */
	final public static int BigBeamSpeed = 15;
	/**
	 * big beam cool time
	 */
	final public static int BigBeamTime = 120;
	
	/**boost constant**/
	/*base boost constant*/
	/**
	 * boost energy use
	 */
	final public static int BoostEnergy = 30;
	/**
	 * base machine boost use count
	 */
	final public static int BoostMaxUse = 3;
	/**
	 * boost speed(normal speed+)
	 */
	final public static int BoostSpeed = 10;
	/**
	 * boost time count (frame 1sec = 60 frame)
	 */
	final public static int BoostTime = 50;
	
	/**window constant**/
	/**
	 * window width size
	 */
	final public static int WIDTH = 1280;
	final public static int HEIGHT = 800;
}
