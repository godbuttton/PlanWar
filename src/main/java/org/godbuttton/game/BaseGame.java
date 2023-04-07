package org.godbuttton.game;

import org.godbuttton.MainWin;

import java.awt.*;

public class BaseGame {
    Image img;
    int x;
    int y;
    int width;
    int  height;
    double speed;
    MainWin frame;

    public BaseGame() {
    }

    public BaseGame(Image img, int x, int y,double speed) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public BaseGame(Image img, int x, int y, int width, int height, double speed, MainWin frame) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.frame = frame;
    }
    public void paintSelf(Graphics g)  {
        if(this.width>0) {
            g.drawImage(img,x,y,this.width,this.height,null);
        } else {
            g.drawImage(img,x,y,null);
        }
    }
    public Rectangle getRec() {
        return new Rectangle(x,y,width,height);
    }
    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public MainWin getFrame() {
        return frame;
    }

    public void setFrame(MainWin frame) {
        this.frame = frame;
    }
}
