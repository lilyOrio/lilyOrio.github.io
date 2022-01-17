package com.lilystu.tankgame02;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

/**
 * @author lily
 * @version 1.0
 */
public class LilyTankGame02 extends JFrame {
    MyPanel mp = null;

    public static void main(String[] args) {
        new LilyTankGame02();
    }

    public LilyTankGame02() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入1（新游戏）/2（继续游戏）：");
        String key = scanner.next();
        mp = new MyPanel(key);
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);
        this.addKeyListener(mp);
        this.setSize(1300, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("关闭窗口");
                Recorder.tankRecord();
                System.exit(0);
            }
        });
    }
}
