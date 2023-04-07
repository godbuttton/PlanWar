package org.godbuttton.utils;
import com.sun.media.jfxmedia.AudioClip;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;


public class GameResource {
    public static Image bgImage=Toolkit.getDefaultToolkit().getImage("src/main/resources/image/background.png");
    public static Image enemyBossImage=Toolkit.getDefaultToolkit().getImage("src/main/resources/image/enemy3_n1.PNG");
    public static Image enemy1Image=Toolkit.getDefaultToolkit().getImage("src/main/resources/image/enemy1.PNG");
    public static Image enemy2Image=Toolkit.getDefaultToolkit().getImage("src/main/resources/image/enemy2.PNG");
    public static Image playerImage=Toolkit.getDefaultToolkit().getImage("src/main/resources/image/life.PNG");
    public static Image playerBullet=Toolkit.getDefaultToolkit().getImage("src/main/resources/image/bullet1.PNG");
    public static Image enemyBullet=Toolkit.getDefaultToolkit().getImage("src/main/resources/image/bullet2.PNG");
    public static Image meExpordImage1=Toolkit.getDefaultToolkit().getImage("src/main/resources/image/me_destroy_1.png");
    public static Image meExpordImage2=Toolkit.getDefaultToolkit().getImage("src/main/resources/image/me_destroy_2.png");
    public static Image meExpordImage3=Toolkit.getDefaultToolkit().getImage("src/main/resources/image/me_destroy_3.png");
    public static Image meExpordImage4=Toolkit.getDefaultToolkit().getImage("src/main/resources/image/me_destroy_4.png");

    public static Image enemyExpordImage1=Toolkit.getDefaultToolkit().getImage("src/main/resources/image/enemy1_down1.png");
    public static Image enemyExpordImage2=Toolkit.getDefaultToolkit().getImage("src/main/resources/image/enemy1_down2.png");
    public static Image enemyExpordImage3=Toolkit.getDefaultToolkit().getImage("src/main/resources/image/enemy1_down3.png");
    public static Image enemyExpordImage4=Toolkit.getDefaultToolkit().getImage("src/main/resources/image/enemy1_down4.png");

    public static Image enemyBossExpordImage1=Toolkit.getDefaultToolkit().getImage("src/main/resources/image/enemy3_down1.png");
    public static Image enemyBossExpordImage2=Toolkit.getDefaultToolkit().getImage("src/main/resources/image/enemy3_down2.png");
    public static Image enemyBossExpordImage3=Toolkit.getDefaultToolkit().getImage("src/main/resources/image/enemy3_down3.png");
    public static Image enemyBossExpordImage4=Toolkit.getDefaultToolkit().getImage("src/main/resources/image/enemy3_down4.png");
    public static Image enemyBossExpordImage5=Toolkit.getDefaultToolkit().getImage("src/main/resources/image/enemy3_down5.png");
    public static Image enemyBossExpordImage6=Toolkit.getDefaultToolkit().getImage("src/main/resources/image/enemy3_down6.png");
    public static Image bomSupply=Toolkit.getDefaultToolkit().getImage("src/main/resources/image/bomb_supply.png");
    public static Image bom=Toolkit.getDefaultToolkit().getImage("src/main/resources/image/bomb.png");

    public static Image gameOver=Toolkit.getDefaultToolkit().getImage("src/main/resources/image/gameover.png");
    public static Image restart=Toolkit.getDefaultToolkit().getImage("src/main/resources/image/again.png");
    public static Font gameFont= Utils.getFont("src/main/resources/font/font.ttf");
//    boss 爆炸动画list
    public static java.util.List<Image> enemyBossExpordList = new ArrayList<Image>(Arrays.asList(enemyBossExpordImage1,enemyBossExpordImage2,enemyBossExpordImage3,enemyBossExpordImage4,enemyBossExpordImage5,enemyBossExpordImage6));
    //敌人爆炸list
    public static java.util.List<Image> enemyExpordList = new ArrayList<Image>(Arrays.asList(enemyExpordImage1,enemyExpordImage2,enemyExpordImage3,enemyExpordImage4));

    public static java.util.List<Image> meExpordList = new ArrayList<Image>(Arrays.asList(meExpordImage1,meExpordImage2,meExpordImage3,meExpordImage4));
}
