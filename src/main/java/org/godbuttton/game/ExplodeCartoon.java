package org.godbuttton.game;

import org.godbuttton.MainWin;
import org.godbuttton.utils.GameResource;
import org.godbuttton.utils.Utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ExplodeCartoon extends BaseGame{
    List<Image> frames= new ArrayList<>();
    int cartoonTime =  1; // 动画默认的时间
    Fly orginObject;

    public ExplodeCartoon(List<Image> frames, int x,int y, int width, int heigt, int cartoonTime,Fly o) {
        this.orginObject = o;
        this.x = x;
        this.y =y;
        this.frames =frames;
        this.width = width;
        this.height = heigt;
    }

    public void  before () {
        orginObject.destroy();
    }
    public void after() {
        orginObject.afterDestroy();
    }
    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);

    }
    public  void playExpordCatoon(Graphics g) {
        this.playCatton(g,this.frames,x,y,width,height,cartoonTime);
    }

    // 在画板指定敌方播放指定的图片
    public void playCatton(Graphics g, List<Image> list, int x, int y, int width, int height, int time) {
        long sTime =  System.currentTimeMillis();
        Timer timer=  new Timer(true);
        //启动同一个线程
        int frameCount =  list.size();
        ExplodeCartoon.this.before();
        System.out.println("size"+list.size());
        timer.schedule(new TimerTask() {
            private int index =0;
            @Override
            public void run() {
                synchronized ( Utils.allList) {
                    System.out.println(index);
                    if (index >= frameCount) {
                        // 播放完毕的时候
                        ExplodeCartoon.this.after();
                        Utils.delList.add(ExplodeCartoon.this);
                        timer.cancel();
                        return;
                    }
                    ExplodeCartoon.this.setImg(list.get(index));
                    index++;
                }
            }
        },10,(long)(time*1000/frameCount));
    }
}
