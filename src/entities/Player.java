/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import static utilz.Constants.PlayerConstants.*;
import static utilz.HelpMethods.*;
import javaproject.Game;
import levels.Tile;
import objects.InteractiveObject;
import utilz.*;


public class Player extends Entity{
    
    private BufferedImage[][][] animations;
    private int aniTick, aniIndex, aniSpeed =30 ;
    
    //players actions properties
    private int playerAction = IDLE;
    private boolean left, right, up, down;
    private int playerFacing;
    private boolean facingRight, facingLeft;
    private boolean moving = false, attacking = false, dying = false;
    private final float playerSpeed = ((float)Game.TILES_SIZE/100) * 1.3f;
    
    
    //level properties
    private Tile[][] lvl;
    
    //drawing a smaller hitbox
    private final float xDrawOffset = 7 ;
    private final float yDrawOffset = 17 ;
    
    private final float handXOffset = -1;
    private final float handYOffset = -5;
    
    
    //hands properties
    private InteractiveObject hand;
    
    
    
    public Player(float x, float y, int width, int height, int playerNum) {
        super(x, y, width, height);
        loadAnimations(playerNum);
        initHitbox(x, y, (width/2) + (32/Game.TILES_SIZE)*3 , height/2);
    }
    
    public void update() {
        updatePos();
        
        updateAnimationTick();
        setAnimation();
    }
    public void render(Graphics g) {
        g.drawImage(animations[playerFacing][playerAction][aniIndex], (int)(hitbox.x - xDrawOffset), (int)(hitbox.y - yDrawOffset), width, height, null);
        if (hand != null)
            hand.draw(g);
        //drawHitbox(g);
        
    }
    
            
    
    //method that updates the animation of a player
    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;
                // in case of attacking we end attacking here
                attacking = false;
            }
        }
    }
    
    private void resentAnimationTick() {
        aniTick = 0;
        aniIndex = 0;
    }
    
    //set the type of action the player currently doing
    private void setAnimation() {
        int startAni = playerAction;
        
        if (moving) playerAction = RUNNING;
        else playerAction = IDLE;

    
        //if the action of the player is changed we restart the animation tick
        if (startAni != playerAction) 
            resentAnimationTick();
    }
    
 
    
    //methods that loads sub images to the array
    private void loadAnimations(int playerNumber) {
          
        animations = new BufferedImage[2][3][6];
        
        String fileName = "PLAYER" + playerNumber;
        //idle
        int limit = GetSpriteAmount(IDLE);
        for (int i = 0; i < (limit*2); i++)
            animations[i/limit][0][i%limit] = LoadSave.GetSpriteAtlas(fileName + "/idle" + i +".png");
        
        //running
        limit = GetSpriteAmount(RUNNING);
        for (int i = 0; i < (limit*2); i++)
            animations[i/limit][1][i%limit] = LoadSave.GetSpriteAtlas(fileName + "/run"+ i +".png");
        
        //death
        limit = GetSpriteAmount(DYING);
        for (int i = 0; i < (limit*2); i++)
            animations[i/limit][2][i%limit] = LoadSave.GetSpriteAtlas(fileName + "/death" + i +".png");

          
         
    }
    
    public void loadLvl(Tile[][] lvl) {
        this.lvl = lvl;

    }
    
    
    
    //method that moves the player around
    private void updatePos() {
        //the character isnt moving unless one of the keys is presses
        moving = false;
        
        //incase were holding an item update its postion according to the player
        if (hand != null)
            hand.updatePos(hitbox.x+handXOffset, hitbox.y+handYOffset);
        
        //check facing pos;
        if (facingLeft && !facingRight)
            playerFacing = FACING_LEFT;
        else
            playerFacing = FACING_RIGHT;
        
       
        
        //if the character not moving 
        if (!left && !right && !up && !down)
            return;
        
        float xSpeed = 0;
        float ySpeed = 0;
        
        if(left)  //if pressing only A we go left
            xSpeed -= playerSpeed;
        if (right)  //if pressing only D we go right
            xSpeed += playerSpeed;
        if (up)
            ySpeed -= playerSpeed;
        if (down)
            ySpeed += playerSpeed;

        
        if (up || down) {
            if (CanMoveHere(hitbox.x, hitbox.y + ySpeed, hitbox.width, hitbox.height, lvl)) {
                    //update the y position
                    hitbox.y += ySpeed;
                    updateYPos(ySpeed);

            }else {
                    //if we hit the roof or in ground
                    hitbox.y = GetEntityYPosNextToWall(hitbox, ySpeed);
                    
                    updateYPos(ySpeed);
            }

        }
        if (right || left) {
            if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvl)) {
                    //update the x position
                    hitbox.x += xSpeed;

                    updateXPos(xSpeed);

            }else {
                    //if we hug the wall
                    hitbox.x = GetEntityXPosNextToWall(hitbox, xSpeed);
                    
                    updateXPos(xSpeed);
            }

        }
       
        
        
        moving = true;
    }
    
    //method only for updating x position
    private void updateXPos(float xSpeed) {       
        //update x pos
        if(CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvl)) {
            hitbox.x += xSpeed;
        }else { //hug the wall
            hitbox.x = GetEntityXPosNextToWall(hitbox, xSpeed);
        }
    }
    
    private void updateYPos(float ySpeed) {
        //update y pos
        if(CanMoveHere(hitbox.x, hitbox.y + ySpeed, hitbox.width, hitbox.height, lvl)) {
            hitbox.y += ySpeed;
        }else { //hug the ceiling
            hitbox.y = GetEntityYPosNextToWall(hitbox, ySpeed);
        }
    }

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }
    
    public InteractiveObject getNearbyObjects() {
        return GetObjectNearby(this);
    }
    
    public void pick() {
        
        InteractiveObject obj = getNearbyObjects();
        //in case holding something
        if (hand != null || obj == null) return;
        else if (!obj.getPickable()) return;
        
        int xTile = obj.getXTile(), yTile = obj.getYTile();
        hand = obj.getCopy();
        lvl[yTile][xTile].setObject(null);
    }
    
    public void drop() {
        if (hand == null) return;
        
        
        int xTile = hand.getXTile(), yTile = hand.getYTile();
        //if the space is occupied
        if (lvl[yTile][xTile].getObject() != null) return;
        
        lvl[yTile][xTile].setObject(hand.getCopy());
        lvl[yTile][xTile].getObject().setPicked(false);
        hand = null;

    }
    
    
    //SETTERS
    public void setLeft(boolean left) {this.left = left;}
    public void setRight(boolean right) {this.right = right;}
    public void setUp(boolean up) {this.up = up;}
    public void setDown(boolean down) {this.down = down;}
    
    public void setFacingRight(boolean facingRight) {this.facingRight = facingRight;}
    public void setFacingLeft(boolean facingLeft) {this.facingLeft = facingLeft;}
    
    public void setAttacking(boolean attacking) {this.attacking = attacking;}
    
    public void setHand(InteractiveObject hand) {
        if (hand != null)
            hand.updatePos(this.x + handXOffset, this.y + handYOffset);
        this.hand = hand;
        
    }
    
    //GETTERs
    public boolean getLeft() {return left;}
    public boolean getRight() {return right;}
    public boolean getUp() {return up;}
    public boolean getDown() {return down;}
    
    public float getX() {return hitbox.x;}
    public int getXTile() {return (int)(hitbox.x / Game.TILES_SIZE);}
    public float getY() {return hitbox.y;}
    public int getYTile() {return (int)(hitbox.y / Game.TILES_SIZE);}
    
    public Tile[][] getLvl() {return lvl;}
    
    public InteractiveObject getHand() {return hand;}

}
