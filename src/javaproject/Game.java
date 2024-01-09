/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject;

import java.awt.Graphics;

import entities.Player;
import levels.LevelManager;
import utilz.Constants.PlayerSpawn;

public class Game implements Runnable{
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    
    //game-loop
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    
    private Player player1, player2;
    private LevelManager levelManager;
    
    //the default size of each Tile in the game window
    public final static int TILES_DEFAULT_SIZE = 16;
    //how we should scale everything(sprites, levels, etc)
    public final static float SCALE = 1.5f;
    //how many tiles the game should be in width and height
    public final static int TILES_IN_WIDTH = 25;
    public final static int TILES_IN_HEIGHT = 25;
    public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
    
    public Game() {
        initClasses();
        
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        
        
        
        startGameLoop();
    }
    
    //methods that initalize every class in game
    private void initClasses() {
        levelManager = new LevelManager(this);
        player1 = new Player(PlayerSpawn.PLAYER_1_XSPAWN * TILES_SIZE, PlayerSpawn.PLAYER_1_YSPAWN * TILES_SIZE, (int)(TILES_SIZE*1.5), (int)(TILES_SIZE*1.5), 1);
        player2 = new Player(PlayerSpawn.PLAYER_2_XSPAWN * TILES_SIZE, PlayerSpawn.PLAYER_2_YSPAWN * TILES_SIZE, (int)(TILES_SIZE*1.5), (int)(TILES_SIZE*1.5), 2);
        
        
        player1.loadLvl(levelManager.getCurrentLevel().getLvl());
        player2.loadLvl(levelManager.getCurrentLevel().getLvl());
    }
    
    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    public void update() {
        player1.update();
        player2.update();
        levelManager.update();
    }
    
    public void render(Graphics g) {
        levelManager.draw(g);      
        player1.render(g);
        player2.render(g);
        levelManager.drawAfterPlayer(g);
    }

    @Override //game-loop
    public void run() {
        
        //how long each frame lasts in nano seconds
        // 1 second == 1,000,000,000 nano seconds
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;
        
        long previousTime = System.nanoTime();
        
        //fps, ups checker properties
        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();
        
        double deltaU = 0;
        double deltaF = 0;
                
        while(true) {
            //if the duration had passed
            long currentTime = System.nanoTime();
            
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;
            
            if (deltaU >= 1) {
                //update
                update();
                updates++;
                deltaU--;
            }
            if (deltaF >= 1) {
                //render
                gamePanel.repaint();
                frames++;
                deltaF--;
            }
            
            //checks the frames per second;
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }
    
    public void windowFocusLost() {
        player1.resetDirBooleans();
        player2.resetDirBooleans();
    }
    
    //getter for player
    public Player getPlayer1() {return player1;}
    public Player getPlayer2() {return player2;}
}
