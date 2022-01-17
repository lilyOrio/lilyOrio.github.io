package com.lilystu.tankgame02;


import java.util.Vector;

/**
 * @author lily
 * @version 1.0
 * 我的坦克
 */
public class Hero extends Tank {
    Shot shot = null;
    Vector<Shot> shots = new Vector<>();

    public Hero(int x, int y) {
        super(x, y);
    }

    public void shotEnemyTank() {
        switch (getDirect()) {
            case 0:
//                shots.add(new Shot(getX()+20,getY(),0));
                shot = new Shot(getX() + 20, getY(), 0);
                break;
            case 1:
//                shots.add(new Shot(getX()+60,getY()+20,1));
                shot = new Shot(getX() + 60, getY() + 20, 1);
                break;
            case 2:
//                shots.add(new Shot(getX()+20,getY()+60,2));
                shot = new Shot(getX() + 20, getY() + 60, 2);
                break;
            case 3:
//                shots.add(new Shot(getX()+20,getY()+20,3));
                shot = new Shot(getX() + 20, getY() + 20, 3);
                break;
        }
        shots.add(shot);
        Thread thread = new Thread(shot);
        thread.start();

        System.out.println(shots.size());
    }
}
