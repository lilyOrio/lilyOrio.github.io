package com.lilystu.tankgame02;

import java.io.*;
import java.util.Vector;

/**
 * @author lily
 * @version 1.0
 * 记录击毁敌方坦克的数量
 */
public class Recorder {
    private static int allEnemyTanks = 0;
    private static String filePath = "src\\myRecorder.txt";
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    static Vector<EnemyTank> enemyTanks = new Vector<>();
    static Vector<Node> nodes = new Vector<>();

    public static void tankRecord() {
        try {
            bw = new BufferedWriter(new FileWriter(filePath));
            bw.write(allEnemyTanks + "\r\n");
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                if (enemyTank.isLive) {
                    bw.write(enemyTank.getX()+"\t");
                    bw.write(enemyTank.getY()+"\t");
                    bw.write(enemyTank.getDirect()+"\t\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Vector<Node> getHistoryRecorder(){
        try {
            br = new BufferedReader(new FileReader(filePath));
            allEnemyTanks = Integer.parseInt(br.readLine());
            String line = " ";
            while ((line=br.readLine())!=null){
                String[] str = line.split("\t");
                int x = Integer.parseInt(str[0]);
                int y = Integer.parseInt(str[1]);
                int d = Integer.parseInt(str[2]);
                Node node = new Node(x,y,d);
                nodes.add(node);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br!=null){
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return nodes;
    }

    public static int getAllEnemyTanks() {
        return allEnemyTanks;
    }

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks_) {
        enemyTanks = enemyTanks_;
    }

    public static void addEnemyTanks() {
        allEnemyTanks++;
    }

    public static String getFilePath() {
        return filePath;
    }
}
