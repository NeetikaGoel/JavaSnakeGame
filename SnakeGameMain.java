package SnakeGame2D;

import java.awt.*;                   //ABSTRACT WINDOW TOOLKIT
import javax.swing.*;

public class SnakeGameMain {
   
    public static void main(String[] args) 
    {
        JFrame frame = new JFrame("  Snake Game  ");
        frame.setBounds(10, 10, 905, 700);
        frame.setResizable(true);
        frame.setLocation(300, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("SNAKE GAME");
        
        GamePanel panel = new GamePanel();
        panel.setBackground(Color.BLACK);
        frame.add(panel);
    
        
        frame.setVisible(true);

    }
}
