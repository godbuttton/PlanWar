package org.godbuttton.game;

import org.godbuttton.MainWin;
import org.godbuttton.utils.GameResource;
import org.godbuttton.utils.GameStatus;
import org.godbuttton.utils.Utils;

import java.awt.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class EnemyPlan extends Fly{
    public static Long count = Long.valueOf(0);
    public EnemyPlan(Image img, int x, int y, int width, int height, double speed, MainWin frame) {
        super(img, x, y, width, height, speed, frame);
        count++;
    }
    public void playDestroyImg(Graphics g) throws InterruptedException {
        synchronized ( Utils.allList) {
            Utils.playAudio("enemy1_down.wav");
            ExplodeCartoon explodeCartoon = new ExplodeCartoon( GameResource.enemyExpordList,x,y,width,height,1,this);
            Utils.allList.add(explodeCartoon);
            explodeCartoon.playExpordCatoon(g);
        }
    }

    @Override
    public void destroy() {
        // 简单的移动位置在看不见的位置碰撞
       this.setX(-400);
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        // 检查玩家机和敌方机相撞
        if(this.getRec().intersects(this.frame.getPlayerPlan().getRec())) {
            System.out.println("检查玩家机和敌方机相撞");
            try {
                this.frame.getPlayerPlan().playDestroyImg(g);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        this.y= (int)(this.y + speed);

        for (Bullet bullet:
                Utils.bulletList) {
//            玩家子弹和飞机碰撞
            if(bullet.getRec().intersects(this.getRec())  && bullet.bulletType.equals("player")) {
                System.out.println("玩家子弹和敌方飞机碰撞了");
                try {
                    synchronized (Utils.allList) {
                        this.playDestroyImg(g);
                        this.frame.addScore();
                        this.frame.drawScore(g);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                bullet.destroy();
                bullet.afterDestroy();
            }
            //   敌方子弹和玩家飞机碰撞了
            if(bullet.getRec().intersects(this.frame.getPlayerPlan().getRec()) && bullet.bulletType.equals("enemy")) {
                System.out.println("敌方子弹和玩家飞机碰撞了");
                try {
                    this.frame.getPlayerPlan().playDestroyImg(g);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                bullet.destroy();
                bullet.afterDestroy();
            }
        }
    }
}
