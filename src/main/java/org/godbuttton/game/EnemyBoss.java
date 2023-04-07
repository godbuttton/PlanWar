package org.godbuttton.game;
import org.godbuttton.MainWin;
import org.godbuttton.utils.GameResource;
import org.godbuttton.utils.GameStatus;
import org.godbuttton.utils.Utils;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class EnemyBoss extends EnemyPlan{
    int direct=1;
    int sumHp =1;
    Timer timer =null;
    public EnemyBoss(Image img, int x, int y, int width, int height, double speed, MainWin frame,int hp) {
        super(img, x, y, width, height, speed, frame);
        this.healthPoint = hp;
        this.sumHp = hp;
        timer=  new Timer();
        Utils.timerList.add(timer);
        if(this.healthPoint>0 && this.frame.status.equals(GameStatus.RUNING)) {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Bullet PlayerPlanBullet = (Bullet)(Utils.creatGameObject(new Bullet(GameResource.enemyBullet, EnemyBoss.this.getX()+80,EnemyBoss.this.getY()+200,5,11,8,EnemyBoss.this.frame,"enemy")));
                    Utils.playAudio("bullet.wav");
                }
            },0,1000);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        timer.cancel();
    }

    @Override
    public void afterDestroy() {
        super.afterDestroy();
        this.x = -1000;
        Utils.delList.add(this);
        this.frame.status =  GameStatus.PASSED;
    }

    @Override
    public void paintSelf(Graphics g) {
        // boss机和玩家的机型相碰撞
        if(this.getRec().intersects(this.frame.getPlayerPlan().getRec())) {
            System.out.println("boss机和玩家的机型相碰撞");
            try {
                this.frame.getPlayerPlan().playDestroyImg(g);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        // 子弹和敌机碰撞
        for (Bullet bullet:Utils.bulletList) {
            if(bullet.getRec().intersects(this.getRec()) && bullet.bulletType.equals("player")) {
                System.out.println("子弹和敌机BOSS碰撞");
                this.hpDecrease(2);
                if(this.healthPoint<1) {
                    try {
                        this.playDestroyImg(g);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                bullet.destroy();
                bullet.afterDestroy();
            }
        }

        g.drawImage(img,x,y,null);
        g.setColor(Color.WHITE);
        g.fillRect(20,35,150,10);
        g.fillRoundRect(20,35,150,10,5,5);
        g.setColor(Color.RED);
        int width = (int)(((float)this.healthPoint/(float)this.sumHp)*150);
        g.fillRect(20,35,width,10);
        if(GameStatus.RUNING.equals(this.frame.status)) {
            if(this.x>this.frame.getWidth()-165) {
                this.direct = -1;
            }
            if(this.x<0) {
                this.direct = 1;
            }
            this.x= (int)(this.x + speed*direct);
        }
    }

    public void playDestroyImg(Graphics g) throws InterruptedException {
        ExplodeCartoon explodeCartoon = new ExplodeCartoon( GameResource.enemyBossExpordList,x,y,width,height,2,this);
        Utils.allList.add(explodeCartoon);
        explodeCartoon.playExpordCatoon(g);
    }
}
