package com.lilystu.tankgame02;

/**
 * @author lily
 * @version 1.0
 * 坦克（抽象父类）
 */
public abstract class Tank {
    private int x;
    private int y;
    private int direct;
    private int speed =1;
    boolean isLive = true;

    public void moveLeft() {
        if (getX()>0) {
            x -= speed;
        }
    }
    public void moveRight() {
        if (getX()+60<1000) {
            x+=speed;
        }
    }
    public void moveUp() {
        if (getY()>0){ y-=speed;}
    }
    public void moveDown() {
        if (getY()+60<750) {
            y+=speed;
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }


    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
