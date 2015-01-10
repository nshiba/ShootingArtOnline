package shootingartonlineserver;

import java.io.Serializable;

public class PlayerBean implements Serializable{
    private int x,y = 0;
    private int bulletType = 0;
    private int playerNumber = 0;

    /**
     * @return the x
     */
    public float getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public float getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the bulletType
     */
    public int getBulletType() {
        return bulletType;
    }

    /**
     * @param bulletType the bulletType to set
     */
    public void setBulletType(int bulletType) {
        this.bulletType = bulletType;
    }

    /**
     * @return the playerNumber
     */
    public int getPlayerNumber() {
        return playerNumber;
    }

    /**
     * @param playerNumber the playerNumber to set
     */
    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }
}