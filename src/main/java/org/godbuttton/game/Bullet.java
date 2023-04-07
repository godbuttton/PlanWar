package org.godbuttton.game;

import org.godbuttton.MainWin;
import org.godbuttton.utils.GameStatus;
import org.godbuttton.utils.Utils;

import java.awt.*;

//定义子弹类
public class Bullet extends Fly{
    String bulletType = "player";
    public Bullet(Image img, int x, int y, int width, int height, double speed, MainWin frame,String bulletType) {
        super(img, x, y, width, height, speed, frame);
        this.bulletType = bulletType;
    }
    public Bullet(Image img, int x, int y, int width, int height, double speed, MainWin frame) {
        super(img, x, y, width, height, speed, frame);
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        if(this.frame.status.equals(GameStatus.RUNING)) { // 运行中才执行此方法
            if("player".equals(this.bulletType)) {
                this.y= (int)(this.y - speed);
            } else {
                this.y= (int)(this.y + speed);
            }
        }
//        屏幕外的子弹全部删除
        if(this.x>480 || this.y>700 || this.x<0  || this.y<0) {
            Utils.delList.add(this);
        }
    }
}
