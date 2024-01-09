/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javaproject.GamePanel;


import objects.InteractiveObject;


//implements interface
public class KeyBoardInputs implements KeyListener{
    
    private GamePanel gamePanel;
    
    public KeyBoardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.getGame().getPlayer1().setUp(false);
                break;
            case KeyEvent.VK_A:
                gamePanel.getGame().getPlayer1().setLeft(false);
                gamePanel.getGame().getPlayer1().setFacingLeft(true);
                break;
            case KeyEvent.VK_S:
                gamePanel.getGame().getPlayer1().setDown(false);
                break;
            case KeyEvent.VK_D:
                gamePanel.getGame().getPlayer1().setRight(false);
                gamePanel.getGame().getPlayer1().setFacingRight(false);
                break;
                
                
                
            case KeyEvent.VK_RIGHT:
                gamePanel.getGame().getPlayer2().setRight(false);
                gamePanel.getGame().getPlayer2().setFacingRight(false);
                break;
            case KeyEvent.VK_LEFT:
                gamePanel.getGame().getPlayer2().setLeft(false);
                gamePanel.getGame().getPlayer2().setFacingLeft(false);
                break;
            case KeyEvent.VK_DOWN:
                gamePanel.getGame().getPlayer2().setDown(false);
                break;
            case KeyEvent.VK_UP:
                gamePanel.getGame().getPlayer2().setUp(false);
                break;  
                

                
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.getGame().getPlayer1().setUp(true);
                break;
            case KeyEvent.VK_A:
                gamePanel.getGame().getPlayer1().setLeft(true);
                gamePanel.getGame().getPlayer1().setFacingLeft(true);
                break;
            case KeyEvent.VK_S:
                gamePanel.getGame().getPlayer1().setDown(true);
                break;
            case KeyEvent.VK_D:
                gamePanel.getGame().getPlayer1().setRight(true);
                gamePanel.getGame().getPlayer1().setFacingRight(true);
                break;
            case KeyEvent.VK_G:
                gamePanel.getGame().getPlayer1().pick();
                break;
            case KeyEvent.VK_F:
                gamePanel.getGame().getPlayer1().drop();
                break;
            case KeyEvent.VK_E:
                InteractiveObject obj = gamePanel.getGame().getPlayer1().getNearbyObjects();
                if (obj != null)
                    obj.interact(gamePanel.getGame().getPlayer1());
                break;
                
            case KeyEvent.VK_RIGHT:
                gamePanel.getGame().getPlayer2().setRight(true);
                gamePanel.getGame().getPlayer2().setFacingRight(true);
                break;
            case KeyEvent.VK_LEFT:
                gamePanel.getGame().getPlayer2().setLeft(true);
                gamePanel.getGame().getPlayer2().setFacingLeft(true);
                break;
            case KeyEvent.VK_DOWN:
                gamePanel.getGame().getPlayer2().setDown(true);
                break;
            case KeyEvent.VK_UP:
                gamePanel.getGame().getPlayer2().setUp(true);
                break;
            case KeyEvent.VK_L:
                gamePanel.getGame().getPlayer2().pick();
                break;
            case KeyEvent.VK_K:
                gamePanel.getGame().getPlayer2().drop();
                break;
            case KeyEvent.VK_P:
                obj = gamePanel.getGame().getPlayer2().getNearbyObjects();
                if (obj != null)
                    obj.interact(gamePanel.getGame().getPlayer1());
                break;
                
        }
    }
    
}
