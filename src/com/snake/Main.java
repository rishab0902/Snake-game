package com.snake;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        // frame screen setup
        JFrame frame = new JFrame();
        //title of frame
        frame.setTitle("Snake Game");
        //size of frame
        frame.setBounds(10, 10, 905, 700);
        /*
        visibility of frame
         */

        frame.setVisible(true);
        /*
        to setting frame size as fixed
        */
        frame.setResizable(false);
        /*
        closing of frame by clicking on close button
         */
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*
        setting background color of Frame
         */
        frame.setBackground(Color.DARK_GRAY);
         /*
         making objects of class Gameplay
          */


        Gameplay gameplay = new Gameplay();
        frame.add(gameplay);

    }
}
