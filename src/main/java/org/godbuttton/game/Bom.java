package org.godbuttton.game;

import org.godbuttton.MainWin;
import org.godbuttton.utils.GameStatus;

import java.awt.*;

public class Bom extends Fly{
    public Bom(Image img, int x, int y, int width, int height, double speed, MainWin frame, int hp) {
        super(img, x, y, width, height, speed, frame, hp);
    }

    public Bom(Image img, int x, int y, int width, int height, double speed, MainWin frame) {
        super(img, x, y, width, height, speed, frame);
    }

    public Bom(Image img, int x, int y, double speed, int healthPoint) {
        super(img, x, y, speed, healthPoint);
    }

    public Bom() {
        super();
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        if(this.frame.status.equals(GameStatus.RUNING)) {
            this.y= (int)(this.y + speed);
        }
        if(this.getRec().intersects(this.frame.getPlayerPlan().getRec())) {
            // 如果炸弹和飞机碰撞了
            this.frame.getPlayerPlan().addBom();
            this.destroy();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
