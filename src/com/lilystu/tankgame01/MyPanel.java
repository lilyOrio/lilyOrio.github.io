package com.lilystu.tankgame01;

import javax.swing.*;
import java.awt.*;

/**
 * @author lily
 * @version 1.0
 * 绘图区
 */
public class MyPanel extends JPanel {
    Hero hero = null;

    public MyPanel() {
        hero = new Hero(100, 100);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);
        drawTank(hero.getX(),hero.getY(),g,0,0);
        drawTank(hero.getX()+60,hero.getY(),g,0,1);
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
            case 0://我方坦克
                g.setColor(Color.cyan);
                break;
            case 1://敌方坦克
                g.setColor(Color.yellow);
                break;
        }
//根据敌方坦克方向来绘制坦克
        switch (direct){
            case 0://上
            g.fill3DRect(x,y,10,60,false);
            g.fill3DRect(x+30,y,10,60,false);
            g.fill3DRect(x+10,y+10,20,40,false);
            g.fillOval(x+10,y+20,20,20);
            g.drawLine(x+20,y+30,x+20,y);
        }


    }
}
