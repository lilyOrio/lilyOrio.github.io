package com.lilystu.tankgame02;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

/**
 * @author lily
 * @version 1.0
 * 绘图区
 * 0.4敌人坦克被击中
 */
@SuppressWarnings({"all"})
public class MyPanel extends JPanel implements KeyListener, Runnable {
    private final static int UP = 0;//0上,1右,2下,3左
    private final static int DOWN = 2;
    private final static int LEFT = 3;
    private final static int RIGHT = 1;
    Hero hero = null;
    Vector<EnemyTank> enemyTanks = new Vector<>();
    Vector<Node>nodes= new Vector<>();
    int enemyTankSize = 7;
    Vector<Bomb> bombs = new Vector<>();
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;


    public MyPanel(String key) {
        hero = new Hero(800, 100);
        File file = new File(Recorder.getFilePath());
        if (file.exists()) {
            nodes=Recorder.getHistoryRecorder();
        }else {
            System.out.println("游戏存档有误！重新开始游戏");
            key="1";
        }
        Recorder.setEnemyTanks(enemyTanks);
        switch (key){
            case "1":
                newGame();
                break;
            case "2":
                continueGame(nodes);
                break;
        }
        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
    }
    public void newGame(){
        for (int i = 0; i < enemyTankSize; i++) {
            EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
            enemyTank.setEnemyTanks(enemyTanks);
            enemyTank.setDirect(DOWN);
            new Thread(enemyTank).start();
            enemyTanks.add(enemyTank);
        }
    }
    public void continueGame(Vector<Node> nodes){
        for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);
            EnemyTank enemyTank = new EnemyTank(node.getX(), node.getY());
            enemyTank.setEnemyTanks(enemyTanks);
            enemyTank.setDirect(node.getD());
            new Thread(enemyTank).start();
            enemyTanks.add(enemyTank);
        }
    }
    public void showInfo(Graphics g){
        g.setColor(Color.BLACK);
        Font font = new Font("宋体",Font.BOLD,25);
        g.setFont(font);
        g.drawString("你累积击毁敌方坦克",1020,30);
        drawTank(1020,60,g,UP,0);
        g.setColor(Color.BLACK);
        g.drawString(Recorder.getAllEnemyTanks()+"",1080,100);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);
        showInfo(g);
        if (hero != null && hero.isLive) {
            drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 1);
        }
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if (bomb.life > 6) {
                g.drawImage(image1, bomb.x, bomb.y, 60, 60, this);
                System.out.println("0bomb!");
            } else if (bomb.life > 3) {
                g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
            } else {
                g.drawImage(image3, bomb.x, bomb.y, 60, 60, this);
            }
            bomb.lifeDown();
            if (bomb.life == 0) {
                bombs.remove(bomb);
            }
        }
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            if (enemyTank.isLive) {
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 0);
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    Shot shot = enemyTank.shots.get(j);
                    if (enemyTank.shots.size() == 0) {
                        break;
                    }
                    if (shot.isLive()) {
                        g.draw3DRect(shot.getX(), shot.getY(), 1, 1, false);
                    } else {
                        enemyTank.shots.remove(shot);
                    }
                }
            } else {
                enemyTanks.remove(enemyTank);
            }
        }

        for (int i = 0; i < hero.shots.size(); i++) {
            Shot shot = hero.shots.get(i);
            if (shot.isLive()) {
                g.draw3DRect(shot.getX(), shot.getY(), 1, 1, false);
            } else {
                hero.shots.remove(shot);
            }
        }

    }
    //画出坦克

    /**
     * @param x
     * @param y
     * @param g
     * @param direct 坦克运动方向
     * @param type   坦克类型
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        switch (type) {
            case 0://敌方坦克
                g.setColor(Color.cyan);
                break;
            case 1://我方坦克
                g.setColor(Color.yellow);
                break;
        }
//根据敌方坦克方向来绘制坦克
        switch (direct) {
            case UP://0上,1右,2下,3左
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y);
                break;
            case RIGHT://0上,1右,2下,3左
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x + 60, y + 20);
                break;
            case DOWN://0上,1右,2下,3左
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y + 60);
                break;
            case LEFT://0上,1右,2下,3左
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x, y + 20);
                break;
        }
    }

    public void hitEnemyTank() {
        for (int j = 0; j < hero.shots.size(); j++) {
            Shot shot = hero.shots.get(j);
            if (shot != null && shot.isLive()) {
                for (int i = 0; i < enemyTanks.size(); i++) {
                    hitTank(shot, enemyTanks.get(i));
                }
            }
        }
    }

    public void hitHero() {
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            for (int j = 0; j < enemyTank.shots.size(); j++) {
                Shot shot = enemyTank.shots.get(j);
                if (hero.isLive && shot.isLive()) {
                    hitTank(shot, hero);
                }
            }
        }
    }

    public void hitTank(Shot shot, Tank enemyTank) {//enemyTank->tank
        switch (enemyTank.getDirect()) {
            case UP:
            case DOWN:
                if (shot.getY() > enemyTank.getY() && shot.getY() < enemyTank.getY() + 60 &&
                        shot.getX() > enemyTank.getX() && shot.getX() < enemyTank.getX() + 40) {
                    shot.setLive(false);
                    enemyTank.isLive = false;
                    if (enemyTank instanceof EnemyTank){
                        Recorder.addEnemyTanks();
                    }
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                    System.out.println("击中了！");
                }
                break;
            case LEFT:
            case RIGHT:
                if (shot.getY() > enemyTank.getY() && shot.getY() < enemyTank.getY() + 40 &&
                        shot.getX() > enemyTank.getX() && shot.getX() < enemyTank.getX() + 60) {
                    shot.setLive(false);
                    enemyTank.isLive = false;
                    if (enemyTank instanceof EnemyTank){
                        Recorder.addEnemyTanks();
                    }
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                    System.out.println("击中了！");
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            hero.setDirect(UP);
            hero.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            hero.setDirect(DOWN);
            hero.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            hero.setDirect(LEFT);
            hero.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            hero.setDirect(RIGHT);
            hero.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_J) {
            if (hero.isLive && hero.shots.size() < 5) {
                hero.shotEnemyTank();
            }
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {//不断重绘
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            hitEnemyTank();
            hitHero();
            this.repaint();
        }
    }
}
