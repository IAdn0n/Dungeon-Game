/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javaproject.Game;
import utilz.*;
import objects.InteractiveObject;


public class LevelManager {
    
    private Game game;
    private BufferedImage[] levelSprite;
    private BufferedImage[] propSprite;
    
    private Level levelOne;

    
    
    public LevelManager(Game game) {
        this.game = game;

        importOutsideSprites();
        levelOne = new Level(LoadSave.GetLevelData(LoadSave.LEVEL_ONE));

    }
    
    
    //method that only draws the walls of the level
    private void drawWalls(Graphics g) {
        for (int i = 0; i < Game.TILES_IN_HEIGHT; i++) {
            for (int j = 0 ;j < Game.TILES_IN_WIDTH; j++) {
                int index = levelOne.getSpriteIndex(j, i).getWall();

                
                g.drawImage(levelSprite[index], Game.TILES_SIZE * j, Game.TILES_SIZE * i, Game.TILES_SIZE, Game.TILES_SIZE,null);
            } 
        }
    }
    
    //method that only draws the floor on the level
    private void drawFloor(Graphics g) {
        for (int i = 0; i < Game.TILES_IN_HEIGHT; i++) {
            for (int j = 0 ;j < Game.TILES_IN_WIDTH; j++) {
                int index = levelOne.getSpriteIndex(j, i).getFloor();

                
                g.drawImage(levelSprite[index], Game.TILES_SIZE * j, Game.TILES_SIZE * i, Game.TILES_SIZE, Game.TILES_SIZE,null);
            } 
        }
    }
    
    ///method that draw the static props on the level
    private void drawProps(Graphics g) {
        for (int i = 0; i < Game.TILES_IN_HEIGHT; i++) {
            for (int j = 0 ;j < Game.TILES_IN_WIDTH; j++) {
                int index = levelOne.getSpriteIndex(j, i).getProp();
                g.drawImage(propSprite[index], Game.TILES_SIZE * j, Game.TILES_SIZE * i, Game.TILES_SIZE, Game.TILES_SIZE,null);
            } 
        }
        
        
    }
    
    private void drawObjects(Graphics g) {
        for (int i = 0; i < Game.TILES_IN_HEIGHT; i++) {
            for (int j = 0 ;j < Game.TILES_IN_WIDTH; j++) {
                InteractiveObject obj = levelOne.getSpriteIndex(j, i).getObject();
                if (obj != null) obj.draw(g);
            }
        }
    }
    
    private void drawObjectsAfter(Graphics g) {
        for (int i = 17; i < 20; i++) {
            int index = levelOne.getSpriteIndex(i, 8).getWall();
            g.drawImage(levelSprite[index], Game.TILES_SIZE * i, Game.TILES_SIZE * 8, Game.TILES_SIZE, Game.TILES_SIZE,null);
        }
        
        //draw the gate after the player if player is in the above room
        if (game.getPlayer2().getYTile() < 10) {
            levelOne.getSpriteIndex(17 ,10).getObject().draw(g);
        }
        
        
    }
    
    public void draw(Graphics g) {
        drawFloor(g);
        drawWalls(g);
        drawProps(g);  
        drawObjects(g);
    }
    
    public void drawAfterPlayer(Graphics g) {
        drawObjectsAfter(g);
    }
    
    public void update() {
        
    }

    private void importOutsideSprites() {
        levelSprite = new BufferedImage[498];
            
        //this part loads the walls and floors
        String fileName = LoadSave.LEVEL_DATA[0];
        String extention = ".png";
        
        for (int i = 0; i < 498; i++) {
            levelSprite[i] = LoadSave.GetSpriteAtlas(fileName + i + extention);
            if (levelSprite[i] == null) {
                levelSprite[i] = LoadSave.GetSpriteAtlas(fileName + 493 + extention);
            }    
        }
        
        propSprite = new BufferedImage[240];
        
        //this part loads the props
        fileName = LoadSave.LEVEL_DATA[1];
        
        for (int i = 0; i < 240; i++) {
            propSprite[i] = LoadSave.GetSpriteAtlas(fileName + i + extention);
        }
        
    }
    
    public Level getCurrentLevel() {
        return levelOne;
    }
    
    
}
