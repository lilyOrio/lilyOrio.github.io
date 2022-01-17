package com.lilystu.tankgame02;

import java.util.Vector;

/**
 * @author lily
 * @version 1.0
 * 0.4敌人坦克自由走动
 * 0.5防止坦克重叠
 */
public class EnemyTank extends Tank implements Runnable {
    Vector<Shot> shots = new Vector<>();
    Vector<EnemyTank> enemyTanks = new Vector<>();

    public Vector<EnemyTank> getEnemyTanks() {
        return enemyTanks;
    }

    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    public boolean isTouchTank() {
        switch (getDirect()) {
            case 0:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (this != enemyTank) {
                        //[x,x+40][y,y+60]
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            if (getX() >= enemyTank.getX()
                                && getX() <= enemyTank.getX() + 40
                                && getY() >= enemyTank.getY()
                                && getY() <= enemyTank.getY() + 60){
                                return true;
                            }
                            if (getX() + 40 >= enemyTank.getX()
                                && getX() + 40 <= enemyTank.getX() + 40
                                && getY() >= enemyTank.getY()
                                && getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                        } else //[x,x+60][y,y+40](x,y)(x+40,y)
                            if (enemyTank.getDirect() == 3 || enemyTank.getDirect() == 1) {
                                if (getX() >= enemyTank.getX()
                                    && getX() <= enemyTank.getX() + 60
                                    && getY() >= enemyTank.getY()
                                    && getY() <= enemyTank.getY() + 40){
                                    return true;
                                }
                                if (getX() + 40 >= enemyTank.getX()
                                    && getX() + 40 <= enemyTank.getX() + 60
                                    && getY() >= enemyTank.getY()
                                    && getY() <= enemyTank.getY() + 40) {
                                    return true;
                                }
                            }
                    }
                }
                break;
            case 1:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (this != enemyTank) {
                        //[x,x+40][y,y+60](x+60,y)(x+60,y+40)
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            if (getX()+60 >= enemyTank.getX()
                                && getX()+60 <= enemyTank.getX() + 40
                                && getY() >= enemyTank.getY()
                                && getY() <= enemyTank.getY() + 60){
                                return true;
                            }
                            if (getX() + 60 >= enemyTank.getX()
                                && getX() + 60 <= enemyTank.getX() + 40
                                && getY()+40 >= enemyTank.getY()
                                && getY()+40 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        } else //[x,x+60][y,y+40]
                            if (enemyTank.getDirect() == 3 || enemyTank.getDirect() == 1) {
                                if (getX()+60 >= enemyTank.getX()
                                    && getX()+60 <= enemyTank.getX() + 60
                                    && getY()>= enemyTank.getY()
                                    && getY()<= enemyTank.getY() + 40){
                                    return true;
                                }
                                if (getX()+60 >= enemyTank.getX()
                                    && getX()+60 <= enemyTank.getX() + 60
                                    && getY()+40 >= enemyTank.getY()
                                    && getY()+40 <= enemyTank.getY() + 40) {
                                    return true;
                                }
                            }
                    }
                }
                break;
            case 2:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (this != enemyTank) {
                        //[x,x+40][y,y+60](x,y+60)(x+40,y+60)
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            if (getX() >= enemyTank.getX()
                                && getX() <= enemyTank.getX() + 40
                                && getY()+60 >= enemyTank.getY()
                                && getY()+60 <= enemyTank.getY() + 60){
                                return true;
                            }
                            if (getX() + 40 >= enemyTank.getX()
                                && getX() + 40 <= enemyTank.getX() + 40
                                && getY()+60 >= enemyTank.getY()
                                && getY()+60 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        } else //[x,x+60][y,y+40]
                            if (enemyTank.getDirect() == 3 || enemyTank.getDirect() == 1) {
                                if (getX() >= enemyTank.getX()
                                    && getX() <= enemyTank.getX() + 60
                                    && getY()+60 >= enemyTank.getY()
                                    && getY()+60<= enemyTank.getY() + 40){
                                    return true;
                                }
                                if (getX() + 40 >= enemyTank.getX()
                                    && getX() + 40 <= enemyTank.getX() + 60
                                    && getY()+60 >= enemyTank.getY()
                                    && getY()+60 <= enemyTank.getY() + 40) {
                                    return true;
                                }
                            }
                    }
                }
                break;
            case 3:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (this != enemyTank) {
                        //[x,x+40][y,y+60](x,y)(x,y+40)
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            if (getX() >= enemyTank.getX()
                                && getX() <= enemyTank.getX() + 40
                                && getY() >= enemyTank.getY()
                                && getY() <= enemyTank.getY() + 60){
                                return true;
                            }
                            if (getX()  >= enemyTank.getX()
                                && getX() <= enemyTank.getX() + 40
                                && getY()+40 >= enemyTank.getY()
                                && getY()+40 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        } else //[x,x+60][y,y+40]
                            if (enemyTank.getDirect() == 3 || enemyTank.getDirect() == 1) {
                                if (getX() >= enemyTank.getX()
                                    && getX() <= enemyTank.getX() + 60
                                    && getY()>= enemyTank.getY()
                                    && getY()<= enemyTank.getY() + 40){
                                    return true;
                                }
                                if (getX() >= enemyTank.getX()
                                    && getX() <= enemyTank.getX() + 60
                                    && getY()+40 >= enemyTank.getY()
                                    && getY()+40 <= enemyTank.getY() + 40) {
                                    return true;
                                }
                            }
                    }
                }
                break;
        }
            return false;
    }

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    @Override
    public void run() {
        while (true) {
            if (isLive && shots.size() < 2) {
                Shot shotNew = null;
                switch (getDirect()) {
                    case 0:
                        shotNew = new Shot(getX() + 20, getY(), 0);
                        break;
                    case 1:
                        shotNew = new Shot(getX() + 60, getY() + 20, 1);
                        break;
                    case 2:
                        shotNew = new Shot(getX() + 20, getY() + 60, 2);
                        break;
                    case 3:
                        shotNew = new Shot(getX() + 20, getY() + 20, 3);
                        break;
                }
                shots.add(shotNew);
                new Thread(shotNew).start();
            }

            switch (getDirect()) {
                case 0:
                    for (int i = 0; i < 30; i++) {
                        if (!isTouchTank()) {
                            moveUp();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < 30; i++) {
                        if (!isTouchTank()) {
                            moveRight();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < 30; i++) {
                        if (!isTouchTank()) {
                            moveDown();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < 30; i++) {
                        if (!isTouchTank()) {
                            moveLeft();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }

            setDirect((int) (Math.random() * 4));
            //线程退出时机
            if (!isLive) {
                break;
            }
        }
    }
}
