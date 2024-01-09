/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;


//abstract class is class you cannot creat object of
public abstract class Entity {
    //x and y are the position on an entity
    protected float x, y;
    //width and height are the size of the entity(hitbox)
    protected int width, height;
    protected Rectangle2D.Float hitbox;
    
    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
    }
    
    //temporery
    protected void drawHitbox(Graphics g) {
        g.setColor(Color.PINK);
        g.drawRect((int)hitbox.x, (int)hitbox.y, (int)hitbox.width, (int)hitbox.height);
    }
    
    
    protected void initHitbox(float x, float y, float width, float height) {
        hitbox = new Rectangle2D.Float(x, y, width, height);
    }
    /*protected void updateHitbox() {
        hitbox.x = (int)x;
        hitbox.y = (int)y;
    }*/
    
    //GETTERS
    public Rectangle2D.Float getHitbox() {return hitbox;}
}
