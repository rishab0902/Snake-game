package com.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
/*
implementing Keylistener and ActionListener as to control and move the snake by using keyboard kees
 */

public class Gameplay extends JPanel implements KeyListener, ActionListener {

    public Gameplay() {
        /*
        implementing KeyKistener methods and the timing
         */
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        timing = new Timer(speedDelay, this);
        timing.start();
    }
   /*
   Implementation
   of length of snake
   using Array of Integer Type
    */

    private int[] snakexlength = new int[750];
    private int[] snakeylength = new int[750];


    private boolean right = false;
    private boolean left = false;
    private boolean up = false;
    private boolean down = false;

    /*
    implementation of Snake's head and body of all different orientation
    up right down left and body image
     */

    private ImageIcon rightmouth;
    private ImageIcon leftmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;
    private ImageIcon bodyimage;

    /*
    co-ordinates or position of enemyball
     */

    private int[] enemyxpos = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575,
            600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850};

    private int[] enemyypos = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575,
            600, 625};
    /*
    Initializing enemyball image variable
     */

    private ImageIcon enemyimage;
    /*
    implementation of how to get Random random positioning of enemy ball
     */
    private Random random = new Random();

    private int xpos = random.nextInt(34);
    private int ypos = random.nextInt(23);
    /*
       initializing Timing and speed of snake that is 50 seconds
     */


    private Timer timing;
    private int speedDelay = 50;

    private int lengthofsnake = 3;
    private int moves = 0;
    private int scores = 0;

    private ImageIcon titleImage;


    public void paint(Graphics g) {
        /*
        initial position of snake
        co-ordinates of points
         */

        if (moves == 0) {
            snakexlength[0] = 100;
            snakexlength[1] = 75;
            snakexlength[2] = 50;


            snakeylength[0] = 100;
            snakeylength[1] = 100;
            snakeylength[2] = 100;


        }


        //border of title image
        g.setColor(Color.white);
        /*
        x=left Margin
        y=top Margin
         */
        g.drawRect(24, 10, 851, 55);
        //border of gameplay
        g.setColor(Color.white);
        g.drawRect(24, 74, 851, 577);

        //board of gameplay
        //that is dark grey color
        g.setColor(Color.BLACK);
        g.fillRect(25, 75, 850, 575);
        /*
           ImageIcon:-Creates an ImageIcon from the specified file.
         */

        titleImage = new ImageIcon("stitle.jpg");
        titleImage.paintIcon(this, g, 25, 11);


        //draw the scores
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        //adding the score section
        g.drawString("Scores : " + scores, 780, 30);
        // adding the length section

        g.drawString("Length : " + lengthofsnake, 780, 50);


        rightmouth = new ImageIcon("3.png");
        rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
/*
positioning of snake head with respect to its direction
 */

        for (int a = 0; a < lengthofsnake; a++) {
            if (a == 0 && right) {
                rightmouth = new ImageIcon("3.png");
                rightmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
            }
            if (a == 0 && left) {
                leftmouth = new ImageIcon("left.png");
                leftmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
            }
            if (a == 0 && up) {
                upmouth = new ImageIcon("2.png");
                upmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
            }
            if (a == 0 && down) {
                downmouth = new ImageIcon("down.png");
                downmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
            }

            if (a != 0) {
                bodyimage = new ImageIcon("bodymain.png");
                bodyimage.paintIcon(this, g, snakexlength[a], snakeylength[a]);
            }
        }
/*
Adding image of enemyball
 */
        enemyimage = new ImageIcon("enemy.png");
//positioning of enemy ball randomly
        if (enemyxpos[xpos] == snakexlength[0] && enemyypos[ypos] == snakeylength[0]) {
            lengthofsnake++;
            scores++;
            xpos = random.nextInt(34);
            ypos = random.nextInt(23);
        }
        enemyimage.paintIcon(this, g, enemyxpos[xpos], enemyypos[ypos]);

        /*
        initializing ending of game
         */

        for (int b = 1; b < lengthofsnake; b++) {
            if (snakexlength[b] == snakexlength[0] && snakeylength[b] == snakeylength[0]) {
                right = false;
                left = false;
                up = false;
                down = false;

                g.setColor(Color.WHITE);
                g.setFont(new Font("arial", Font.BOLD, 50));
                g.drawString("Game Over!", 300, 300);

                g.setFont(new Font("arial", Font.BOLD, 20));
                g.drawString("Spcae to RESTART", 350, 340);

            }
        }

        g.dispose();
    }
    /*
    for taking inputs from keyboard
     */

    @Override
    /*movement algorithms

     */
    public void keyPressed(KeyEvent e) {
        /*
        for moving right
         */

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            moves++;
            if (!left) {
                right = true;
            } else {
                right = false;
                left = true;
            }
            up = false;
            down = false;
        }
        /*
        for moving left
         */
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            moves++;
            if (!right) {
                left = true;
            } else {
                left = false;
                right = true;
            }
            up = false;
            down = false;
        }
        /*
        for moving up
         */
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            moves++;
            if (!down) {
                up = true;
            } else {
                up = false;
                down = true;
            }
            left = false;
            right = false;
        }
        /*
        for moving down
         */
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            moves++;
            if (!up) {
                down = true;
            } else {
                down = false;
                up = true;
            }
            left = false;
            right = false;
        }
        /*
        for restarting with space key
         */
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            scores = 0;
            moves = 0;
            lengthofsnake = 3;
            repaint();
        }

    }


    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }

    /*
   for performing actions based on keyboard inputs
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
        /*
        -for shifting each previous body of snake by 25pixels when head moves 25 pixels when snake is moving right
         */

        if (right) {
            for (int i = lengthofsnake - 1; i >= 0; i--) {
                snakeylength[i + 1] = snakeylength[i];
            }
            for (int i = lengthofsnake; i >= 0; i--) {
                if (i == 0) {
                    snakexlength[i] = snakexlength[i] + 25;
                } else {
                    snakexlength[i] = snakexlength[i - 1];
                }
                if (snakexlength[i] > 850) {
                    snakexlength[i] = 25;
                }
            }

            repaint();
        }
        /*
        -for shifting each previous body of snake by 25pixels when head moves 25 pixels when snake is moving left
         */
        if (left) {
            for (int i = lengthofsnake - 1; i >= 0; i--) {
                snakeylength[i + 1] = snakeylength[i];
            }
            for (int i = lengthofsnake; i >= 0; i--) {
                if (i == 0) {
                    snakexlength[i] = snakexlength[i] - 25;
                } else {
                    snakexlength[i] = snakexlength[i - 1];
                }
                if (snakexlength[i] < 25) {
                    snakexlength[i] = 850;
                }
            }

            repaint();
        }
           /*
        -for shifting each previous body of snake by 25pixels when head moves 25 pixels when snake is moving up
         */
        if (up) {
            for (int i = lengthofsnake - 1; i >= 0; i--) {
                snakexlength[i + 1] = snakexlength[i];
            }
            for (int i = lengthofsnake; i >= 0; i--) {
                if (i == 0) {
                    snakeylength[i] = snakeylength[i] - 25;
                } else {
                    snakeylength[i] = snakeylength[i - 1];
                }
                if (snakeylength[i] < 75) {
                    snakeylength[i] = 625;
                }
            }

            repaint();
        }
        /*
        -for shifting each previous body of snake by 25pixels when head moves 25 pixels when snake is moving down
         */
        if (down) {
            for (int i = lengthofsnake - 1; i >= 0; i--) {
                snakexlength[i + 1] = snakexlength[i];
            }
            for (int i = lengthofsnake; i >= 0; i--) {
                if (i == 0) {
                    snakeylength[i] = snakeylength[i] + 25;
                } else {
                    snakeylength[i] = snakeylength[i - 1];
                }
                if (snakeylength[i] > 625) {
                    snakeylength[i] = 75;
                }
            }

            repaint();
        }

    }


}









