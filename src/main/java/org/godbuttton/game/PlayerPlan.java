package org.godbuttton.game;

import org.godbuttton.MainWin;
import org.godbuttton.utils.GameResource;
import org.godbuttton.utils.GameStatus;
import org.godbuttton.utils.Utils;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.stream.Collectors;

public class PlayerPlan extends Fly {
    int bomNum = 0;
    public PlayerPlan() {
    }
    @Override
    public void setImg(Image img) {
        super.setImg(img);
    }


    @Override
    public void afterDestroy() {
        super.afterDestroy();
        Utils.delList.add(this);
        this.frame.status = GameStatus.STOP;
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void playDestroyImg(Graphics g) throws InterruptedException {
        if(!GameStatus.RUNING.equals(this.frame.status)) {
            return;
        }
        Utils.playAudio("me_down.wav");
        ExplodeCartoon explodeCartoon = new ExplodeCartoon( GameResource.meExpordList,x,y,width,height,1,this);
        Utils.allList.add(explodeCartoon);
        explodeCartoon.playExpordCatoon(g);
    }


    public void addBom() {
        this.bomNum++;
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        this.drawBom(g);
    }

    public void drawBom(Graphics g) {
        g.drawImage(GameResource.bom,20,50,20,20,null);
        g.setFont(new Font("仿宋",Font.BOLD,16));
        g.setColor(Color.white);
        g.drawString(this.bomNum+"",45,66);
    }
    public void releaseBom() {
        if( this.bomNum <=0) {
            return;
        }
        Utils.playAudio("use_bomb.wav");
        this.bomNum--;
        Utils.enemyPlanList.stream().forEach((Fly x)->{
            System.out.println("敌方每个战斗机都滴血");
            try {
                x.playDestroyImg(this.frame.currentGraphics);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        for (Object item:Utils.allList
             ) {
            if (item instanceof EnemyBoss) {
                System.out.println("boss扣除5滴血");
                ((EnemyBoss) item).hpDecrease(5);
            }
        }
        Utils.bulletList = Utils.bulletList
                        .stream()
                        .filter((Bullet x)->{
                            if(x.bulletType.equals("player")) {
                                return true;
                            } else  {
                                return false;
                            }
                        })
                        .collect(Collectors.toList());
    }
    public PlayerPlan(Image img, int x, int y, int width, int height, double speed, MainWin frame) {
        super(img, x, y, width, height, speed, frame);
        frame.keyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==37 && PlayerPlan.this.x>10) { // 向左
                    PlayerPlan.super.x = PlayerPlan.super.x -20;
                }
                if (e.getKeyCode()==38 &&  PlayerPlan.this.y>0 ) { // 向上
                    PlayerPlan.super.y = PlayerPlan.super.y - 20;
                }
                if (e.getKeyCode()==39&& PlayerPlan.this.x<(PlayerPlan.this.frame.getWidth()-58)) { // 右
                    PlayerPlan.super.x = PlayerPlan.super.x + 20;
                }
                if (e.getKeyCode()==40 && PlayerPlan.this.y<(PlayerPlan.this.frame.getHeight()-55) ) { // 向下
                    PlayerPlan.super.y = PlayerPlan.super.y + 20;
                }
                if (e.getKeyCode()==32) {
                    System.out.println(PlayerPlan.this.getX());
                    Bullet PlayerPlanBullet = (Bullet)(Utils.creatGameObject(new Bullet(GameResource.playerBullet, PlayerPlan.this.getX()+20,PlayerPlan.this.getY()+2,5,11,8,PlayerPlan.this.frame,"player")));
                    Utils.playAudio("bullet.wav");
                }
                if(e.getKeyCode()==66) {
                    PlayerPlan.this.releaseBom();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode()==37) { // 向左
                }
                if (e.getKeyCode()==38) { // 向上
                }
                if (e.getKeyCode()==39) { // 右
                }
                if (e.getKeyCode()==40) { // 向下
                }
            }
        };
       frame.addKeyListener(frame.keyListener);
    }
}
