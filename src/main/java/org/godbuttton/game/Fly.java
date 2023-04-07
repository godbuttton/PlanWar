package org.godbuttton.game;

import org.godbuttton.MainWin;
import org.godbuttton.utils.Utils;

import java.awt.*;
import java.util.List;

public class Fly extends BaseGame{
    int healthPoint = 1;
    public Fly(Image img, int x, int y, int width, int height, double speed, MainWin frame,int hp) {
        super(img, x, y, width, height, speed, frame);
        this.healthPoint = hp;
    }
    public Fly(Image img, int x, int y, int width, int height, double speed, MainWin frame) {
        super(img, x, y, width, height, speed, frame);
    }

    public Fly() {
    }

    public Fly(Image img, int x, int y, double speed, int healthPoint) {
        super(img, x, y, speed);
        this.healthPoint = healthPoint;
    }

    public void  destroy() {
        this.x = -200;
        this.width=0;
        this.height=0;
        Utils.delList.add(this); // 记录删除的元素
    }
    /*
    * 销毁后时候
    * */
    public void  afterDestroy() {

    }

    /*
    加载动画销毁动画
    * */

    public void  playDestroyImg(){

    }
    public void  playDestroyImg(Graphics graphics) throws InterruptedException {
    }

    /*
    * 被击中的时候减去血量
    * */
    public void hpDecrease() throws InterruptedException {
        if(this.healthPoint>0) {
            this.healthPoint--;
        }

    }
    public void hpDecrease(int num) {
        if(this.healthPoint>0) {
            this.healthPoint =this.healthPoint-num;
        }
    }
}
