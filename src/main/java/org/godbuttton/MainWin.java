package org.godbuttton;

import org.godbuttton.game.*;
import org.godbuttton.utils.GameStatus;
import org.godbuttton.utils.GameResource;
import org.godbuttton.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainWin extends JFrame {
    public long startTime= System.currentTimeMillis();
    public Graphics currentGraphics = null;
    public long startSecond= 0;
    int score=0;
    public String status = GameStatus.UNSTART;
    public KeyListener keyListener;
    private Image vImage = null;
    private int width = 480;
    private int heigt=700;
    private PlayerPlan playerPlan =(PlayerPlan) (Utils.creatGameObject(new PlayerPlan(GameResource.playerImage,390,550,40,48,0,this)));
    //子弹
    private EnemyPlan enemyPlan = (EnemyPlan)( Utils.creatGameObject(new EnemyPlan(GameResource.enemy1Image, (int)(Math.random()*12)*50,0,59,27,2,this)));
    public PlayerPlan getPlayerPlan() {
        return playerPlan;
    }

    public void lunch() {
        this.setVisible(true);
        this.setSize(width,heigt);
        this.setLocationRelativeTo(null);
        this.setTitle("飞机大战");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        Utils.playAudio("game_music.ogg");
        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                Boolean overFlag =  GameStatus.PASSED.equals(status) ||GameStatus.STOP.equals(status);
                if(e.getButton()==1 && GameStatus.UNSTART.equals(status)) {
                    status = GameStatus.RUNING;
                }
                if(e.getButton()==1 ) {
                    int x= e.getX();
                    int y=  e.getY();
                    System.out.println(x+":"+y);
                    if(x<400 && x>100 && y>500 && y<540 &&overFlag ) {
//                        说明点击的是重新开始
                        EnemyPlan.count = 0L;
                        Utils.bulletList.clear();
                        Utils.allList.clear();
                        Utils.delList.clear();
                        if(keyListener!=null) {
                            MainWin.this.removeKeyListener(keyListener);
                        }
                        MainWin.this.score = 0;
                        playerPlan =(PlayerPlan) (Utils.creatGameObject(new PlayerPlan(GameResource.playerImage,390,550,40,48,0,MainWin.this)));
                        status = GameStatus.RUNING;
                    }
                    if(x<400 && x>100 && y>550 && y<590 && overFlag ) {
//                        说明点击的是结束游戏
                        Utils.bulletList.clear();
                        Utils.allList.clear();
                        Utils.delList.clear();
                        System.exit(0);
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        while (true) {
            repaint();
            if(status.equals(GameStatus.RUNING)) {
                autoCreat();
            }
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void addScore() {
        this.score++;
    }
    public void  drawScore(Graphics g) {
        g.setFont(new Font("仿宋",Font.BOLD,16));
        g.setColor(Color.white);
        g.drawString("score:  ",70,64);
        g.drawString(this.score +"",120,65);
    }
    /*
    * 自动生成子弹
    *
    **/
    public  void  autoCreat() {
        long endTime = System.currentTimeMillis();
        if (endTime-startTime > 3000) {
            startTime = endTime;
            startSecond ++;
            Utils.creatGameObject(new EnemyPlan(GameResource.enemy1Image, (int)(Math.random()*10)*48,0,57,43,2,this));
            if (startSecond%4 ==3 ) {
                Utils.creatGameObject(new Bom(GameResource.bomSupply, (int)(Math.random()*10)*48,0,30,40,1,this));
            }
        }
        if(EnemyPlan.count==3) {
            Utils.creatGameObject(new EnemyBoss(GameResource.enemyBossImage, 190,0,165,217,2,this,100));
        }
    }

    public void  gameInit() {

    }
    @Override
    public void paint(Graphics g) {
        if(vImage==null) {
            vImage=createImage(width,heigt);
        }
        this.currentGraphics = g;
        Graphics gImage= vImage.getGraphics();
        gImage.fillRect(0,0,width,heigt);
        if(GameStatus.UNSTART.equals(status)) { // 未开始
            gImage.drawImage(GameResource.bgImage,0,20,480,700,this);
            gImage.drawImage(GameResource.enemyBossImage,140,50,null);
            gImage.drawImage(GameResource.playerImage,200,460,null);
            gImage.setFont(new Font("Console",Font.BOLD,14));
            gImage.drawString("↑ : 向上" ,70,360);
            gImage.drawString( "→ : 向右" ,360,360);
            gImage.drawString( "↓ : 向上" ,70,380 );
            gImage.drawString("← : 向左",360,380);
            gImage.drawString( "空格: 射击" ,70,400 );
            gImage.drawString("B : 炸弹 ",360,400);
            gImage.drawImage(GameResource.playerImage,200,460,null);
            gImage.setFont(new Font("仿宋",Font.BOLD,40));
            gImage.drawString("开始游戏",140,580);
        }
        if(GameStatus.RUNING.equals(status)) { // 运行中
            gImage.drawImage(GameResource.bgImage,0,0,480,700,this);
            this.drawScore(gImage);
            synchronized (Utils.allList) {
                Utils.delList.forEach(Utils.allList::remove);
                Utils.delList.clear();
                for (Object item : Utils.allList) {
                    ((BaseGame) item).paintSelf(gImage);
                }
            }
        }
        if(GameStatus.STOP.equals(status)) { // 游戏失败
            gImage.drawImage(GameResource.bgImage,0,20,480,700,this);
            gImage.drawImage(GameResource.meExpordImage3,playerPlan.getX() +10,playerPlan.getY() +10,this);
            synchronized (Utils.allList) {
                for (Object item : Utils.allList) {
                    ((BaseGame) item).paintSelf(gImage);
                }
            }
            Utils.clearTimer();
            gImage.drawImage(GameResource.restart,100,500,null);
            gImage.drawImage(GameResource.gameOver,100,550,null);
        }
        if(GameStatus.PASSED.equals(status)) { // 游戏通过
            gImage.drawImage(GameResource.bgImage,0,20,480,700,this);
            Font font = GameResource.gameFont;
            gImage.setFont(new Font( font.getFontName(),Font.BOLD,40));
            gImage.setColor(Color.white);;
            gImage.drawString("恭喜您,通关了!",100,300);
            gImage.drawImage(GameResource.restart,100,500,null);
            gImage.drawImage(GameResource.gameOver,100,550,null);
            JButton jButton =  new JButton(new Icon() {
                @Override
                public void paintIcon(Component c, Graphics g, int x, int y) {
                    g.drawImage(GameResource.restart,100,500,null);
                }

                @Override
                public int getIconWidth() {
                    return 0;
                }

                @Override
                public int getIconHeight() {
                    return 0;
                }
            });
            this.add(jButton);
        }
        g.drawImage(vImage,0,0,null);
    }
}
