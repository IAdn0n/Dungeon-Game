/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package levels;

import objects.InteractiveObject;

public class Tile {
    private int wall;
    private int floor;
    private int prop;
    boolean isSolid;
    InteractiveObject object;
    
    
    public Tile() {
        this.wall = 493;
        this.floor = 494;
        this.prop = 239;
        this.isSolid = false;
        object = null;
    }
    
    public Tile(int wall, int floor, int prop, boolean isSolid) {
        this.wall = wall;
        this.floor = floor;
        this.prop = prop;
        this.isSolid = isSolid;
        this.object = null;
    }
    
    public Tile(int wall, int floor, int prop, boolean isSolid, InteractiveObject object) {
        this.wall = wall;
        this.floor = floor;
        this.prop = prop;
        this.isSolid = isSolid;
        this.object = object;
    }
    

    
    
    //SETTERS
    public void setWall(int wall) {this.wall = wall;}
    public void setFloor(int floor) {this.floor = floor;}
    public void setProp(int prop) {this.prop = prop;}
    public void setIsSolid(boolean isSolid) {this.isSolid = isSolid;}
    public void setObject(InteractiveObject object) {this.object = object;}
    
    //GETTERS
    public int getWall() {return wall;}
    public int getFloor() {return floor;}
    public int getProp() {return prop;}
    public boolean getIsSolid() {return isSolid;}
    public InteractiveObject getObject() {return object;}
            
    
}
