/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objects;

import entities.Player;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javaproject.Game;
import utilz.LoadSave;

public class InteractiveObject {
    //x and y are the position on an object in a map
    protected float x, y;
    //width and height are the size of the object
    protected int width, height;
    //if this interactive object is pickable / has been picked
    protected boolean pickable, picked = false;
    
    BufferedImage[] imgs;
    int imgIndex = 0;
    
    //id identifires
    protected int id;
    
    public static final int CHEST_ID = 0;
    public static final int KEY_ID = 1;
    public static final int BLOOD_JAR_ID = 2;
    public static final int CUP_ID = 3;
    public static final int BLOOD_CUP_ID = 4;
    public static final int WATER_CUP_ID = 5;
    public static final int VASE_ID = 6;
    public static final int BLOOD_VASE_ID = 7;
    public static final int WATER_VASE_ID = 8;
    public static final int WATER_JAR_ID = 9;
    public static final int GATE_ID = 10;
    public static final int RED_LIGHT_ID = 11;
    public static final int GREEN_LIGHT_ID = 12;
    public static final int LEVER_ID = 13;
    
    
    public InteractiveObject(float x, float y, int width, int height, boolean pickable) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.pickable = pickable;
        this.imgs = null;
    }
    
    //copy constructor
    public InteractiveObject(InteractiveObject other) {
        this.id = other.getId();
        this.x = other.getX();
        this.y = other.getY();
        this.width = other.getWidth();
        this.height = other.getHeight();
        this.pickable = other.getPickable();
        this.imgs = other.getImgs();
    }
    
    public InteractiveObject getCopy() {
        return new InteractiveObject(this);    
    }
    
    protected void loadImgs(int size ,String[] fileNames) {
        imgs = new BufferedImage[size];
        for (int i = 0 ; i < fileNames.length; i++) {
            imgs[i] = LoadSave.GetSpriteAtlas(fileNames[i]);
        }
    }
    
    public void update() {
        
    }
    public void update(Player player) {
        
    }
    
    public void draw(Graphics g) {
        g.drawImage(imgs[imgIndex], (int)x, (int)y, width, height ,null);
    }
    
    public void updatePos(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    //method that lets you interact with other interactive objects
    public void interact(Player player) {
        
    }
    public void interact(InteractiveObject other) {
        
    }
    
    
    //SETTERS
    public void setPicked(boolean picked) {this.picked = picked;}
    public void setId(int id) {this.id = id;}
    
    //GETTERS
    public float getX() {return x;}
    public float getY() {return y;}
    public int getXTile() {return (int)(x/Game.TILES_SIZE);}
    public int getYTile() {return (int)(y/Game.TILES_SIZE);}
    public int getId() {return id;} 
    public int getWidth() {return width;}
    public int getHeight() {return height;}
    public boolean getPickable() {return pickable;}
    public BufferedImage[] getImgs() {return imgs;}
    public boolean getPicked() {return picked;}
}
