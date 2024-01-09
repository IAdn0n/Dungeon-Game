/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JFrame;

public class GameWindow {
    private JFrame jframe;
    public GameWindow(GamePanel gamePanel) {
        jframe = new JFrame();
            
        //terminate the program ones you close the window
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //assemble the window
        jframe.add(gamePanel);
         
        jframe.setResizable(false);
        //create a window with prefered size of its component (JPanel)
        jframe.pack();
        //put the window on the center
        jframe.setLocationRelativeTo(null);
        
        //method that dedicts when the game loses focus
        jframe.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                gamePanel.getGame().windowFocusLost();
            }
        });
        
        
        //show window screen
        jframe.setVisible(true);
    }
}
