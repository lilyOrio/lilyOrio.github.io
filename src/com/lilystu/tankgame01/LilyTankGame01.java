package com.lilystu.tankgame01;

import javax.swing.*;

/**
 * @author lily
 * @version 1.0
 */
public class LilyTankGame01 extends JFrame {
    MyPanel mp = null;

    public static void main(String[] args) {
        new LilyTankGame01();
    }

    public LilyTankGame01() {
        mp = new MyPanel();
        this.add(mp);
        this.setSize(1000, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
