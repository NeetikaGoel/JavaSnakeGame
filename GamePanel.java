package SnakeGame2D;
import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;

import java.awt.event.*;
import java.util.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener
{
    
    private boolean right=true;
    private boolean left=false;
    private boolean down=false;
    private boolean up=false;

    private int [] snakexlength=new int[750];
    private int [] snakeylength=new int[750];
    private int snakelength=3;

    private int [] xPos={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,
        400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    private int [] yPos={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,
        450,475,500,525,550,575,600,625};
    
    private Random random=new Random();

    private int enemyX;
    private int enemyY;

    private int moves=0;
    private int score=0;

    private boolean gameOver=false;

    private ImageIcon snaketitle=new ImageIcon(getClass().getResource("snaketitle.jpg"));
    private ImageIcon rightmouth=new ImageIcon(getClass().getResource("rightmouth.png"));
    private ImageIcon leftmouth=new ImageIcon(getClass().getResource("leftmouth.png"));
    private ImageIcon upmouth=new ImageIcon(getClass().getResource("upmouth.png"));
    private ImageIcon downmouth=new ImageIcon(getClass().getResource("downmouth.png"));
    private ImageIcon snakebody=new ImageIcon(getClass().getResource("snakeimage.png"));
    private ImageIcon enemy=new ImageIcon(getClass().getResource("enemy.png"));

    private Timer timer;
    private int delay=75;


    GamePanel()
    {
        timer = new Timer(delay, this);
        timer.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        newEnemy();

    }


    @Override
    public void paint(Graphics g) {
        //TODO Auto-generated method stub
        super.paint(g);

        g.setColor(Color.white);
        g.drawRect(24, 10, 851, 55);
        g.drawRect(24, 74, 851, 576);

        snaketitle.paintIcon(this, g, 25, 11);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(25, 75, 850, 575);

        if (moves==0)
        {
            snakexlength[0]=100;
            snakexlength[1]=75;
            snakexlength[2]=50;

            snakeylength[0]=100;
            snakeylength[1]=100;
            snakeylength[2]=100;

            
        }

        if (left)
        {
            leftmouth.paintIcon(this, g, snakexlength[0],snakeylength[0]);
        }

        if (right)
        {
            rightmouth.paintIcon(this, g, snakexlength[0],snakeylength[0]);
        }

        if (up)
        {
            upmouth.paintIcon(this, g, snakexlength[0],snakeylength[0]);
        }

        if (down)
        {
            downmouth.paintIcon(this, g, snakexlength[0],snakeylength[0]);
        }

        for (int i=1;i<snakelength;i++)
        {
            snakebody.paintIcon(this, g, snakexlength[i],snakeylength[i]);
        }

        enemy.paintIcon(this, g,enemyX,enemyY);

        if (gameOver)
        {
            g.setColor(Color.WHITE);
            g.setFont(new Font ("Helvetica",Font.BOLD,50));
            g.drawString("GAME OVER !!", 300, 300);

            g.setFont(new Font ("Helvetica",Font.PLAIN,20));
            g.drawString("PRESS SPACEBAR TO RESTART GAME !!", 275, 350);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Georgia", Font.PLAIN,14));
        g.drawString("SCORE : " + score, 750,30);
        g.drawString("LENGTH : " + snakelength, 750,50);

        g.dispose();

    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        //TODO Auto-generated method stub throw new 
        //UnsupportedOperationException("Unimplemented method 'actionPerformed'");


        for (int i=snakelength-1; i>0;i--)
        {
            snakexlength[i]=snakexlength[i-1];
            snakeylength[i]=snakeylength[i-1];
        }


        if (right)
        {
            snakexlength[0]= snakexlength[0]+25;
        }

        if (left)
        {
            snakexlength[0]= snakexlength[0]-25;
        }

        if (down)
        {
            snakeylength[0]= snakeylength[0]+25;
        }

        if (up)
        {
            snakeylength[0]= snakeylength[0]-25;
        }

        if (snakexlength[0]<25)
        {
            snakexlength[0]=850;
        }

        if (snakexlength[0]>850)
        {
            snakexlength[0]=25;
        }

        if (snakeylength[0]<75)
        {
            snakeylength[0]=625;
        }

        if (snakeylength[0]>625)
        {
            snakeylength[0]=75;
        }

        collisionWithEnemy();

        collisionWithBody();

        repaint();

    }

    private void newEnemy() 
    {
        enemyX=xPos[random.nextInt(34)];
        enemyY=yPos[random.nextInt(23)];

        for (int i=snakelength-1; i>=0; i--)
        {
            if (snakexlength[0]==enemyX && snakeylength[0]==enemyY)
            {
                newEnemy();
            }
        }
    }

    private void collisionWithEnemy() 
    {
        if (snakexlength[0]==enemyX && snakeylength[0]==enemyY)
        {
            
            newEnemy();
            snakelength++;
            score++;
           
        }

    }

    private void collisionWithBody()
    {
        for (int i=snakelength-1;i>0;i--)
        {
            if (snakexlength[i]==snakexlength[0] && snakeylength[i]==snakeylength[0])
            {
                timer.stop();
                gameOver=true;
            }
        } 
    }

    private void restart()
    {
        gameOver=false;
        moves=0;
        score=0;
        left=false;
        right=true;
        up=false;
        down=false;
        timer.start();
        repaint();
        snakelength=3;
        newEnemy();
    }


  @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");

        if (e.getKeyCode()==KeyEvent.VK_SPACE)
        {
            restart();
        }

        if (e.getKeyCode()==KeyEvent.VK_LEFT && (!right))
        {
            left=true;
            up=false;
            down=false;
            right=false;
            moves++;
        }

        if (e.getKeyCode()==KeyEvent.VK_RIGHT && (!left))
        {
            left=false;
            up=false;
            down=false;
            right=true;
            moves++;
        }

        if (e.getKeyCode()==KeyEvent.VK_UP && (!down))
        {
            left=false;
            up=true;
            down=false;
            right=false;
            moves++;
        }

        if (e.getKeyCode()==KeyEvent.VK_DOWN  && (!up))
        {
            left=false;
            up=false;
            down=true;
            right=false;
            moves++;
        }
  
    }


@Override
public void keyTyped(KeyEvent e) {}


@Override
public void keyReleased(KeyEvent e) {}


}
