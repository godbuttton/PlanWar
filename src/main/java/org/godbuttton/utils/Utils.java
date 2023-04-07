package org.godbuttton.utils;


import com.sun.media.jfxmedia.AudioClip;
import org.godbuttton.game.Bullet;
import org.godbuttton.game.EnemyPlan;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Utils {

    final  static AudioFilePlayer player = new AudioFilePlayer ();

    public static List<Bullet>  bulletList= new ArrayList<Bullet> ();
    public static List<EnemyPlan>  enemyPlanList= new ArrayList<EnemyPlan> ();

    public static final List<Object>   allList =  new CopyOnWriteArrayList<>();
    public static List<Object>   delList =  new CopyOnWriteArrayList<>();
    public static List<Timer>   timerList =  new CopyOnWriteArrayList<>();

    public static  Object creatGameObject (Object obj) {
        synchronized(allList) {
            allList.add(obj);
            if( obj instanceof Bullet ) {
                bulletList.add((Bullet) obj);
            }
            if( obj.getClass()== EnemyPlan.class) {
                enemyPlanList.add((EnemyPlan) obj);
            }
            return obj;
        }
    }
    public static void clearTimer() {
        for (Timer timer:
             timerList) {
            timer.cancel();
        }
    }
    public static void playAudio(String fileName) {
        String musicPath = "src/main/resources/music/" +fileName;
        Thread playMusic =  new Thread(new Runnable() {
            @Override
            public void run() {
                player.play(musicPath);
            }
        },"play");
        playMusic.setDaemon(true);
        playMusic.start();
    }

    public static Font getFont(String name) {
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, Files.newInputStream(Paths.get(name)));
        } catch (Exception e) {
        }
        return font;
    }
}
