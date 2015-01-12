/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author snake00
 */
public class Title {
	
	private boolean titleFlag;
	
	void draw(GraphicsContext context){
		
	}
	/**
	 * get title flag
	 * @return 
	 */
	public boolean getTitleFlag(){
		return titleFlag;
	}
	/**
	 * set title flag
	 * @param b 
	 */
	public void setTitleFlag(boolean b){
		this.titleFlag = b;
	}
	
}
