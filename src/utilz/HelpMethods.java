/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilz;

import java.awt.geom.Rectangle2D;
import javaproject.Game;
import levels.Tile;
import objects.InteractiveObject;
import entities.Player;


public class HelpMethods {
    
    //method that check if it is allowed to move to a specific location
    // COLLISION DETECTION
    public static boolean CanMoveHere(float x, float y, float width, float height, Tile[][] lvl) {
        
        if (!IsSolid(x, y, lvl)) //check fot the left corner
            if (!IsSolid(x+width, y+height, lvl)) //check bottom right
                if (!IsSolid(x+width, y, lvl)) //check top right corner
                    if (!IsSolid(x, y+height, lvl))
                        return true;
        
        return false;
    }
    
    //method that checks if there is a solid object in specific location
    //meaning if there is you shouldn't be able to move there
    private static boolean IsSolid(float x, float y, Tile[][] lvl) {
        //if its outside the window
        if (x < 0 || x >= Game.GAME_WIDTH)
            return true;
        if (y < 0 || y >= Game.GAME_HEIGHT)
            return true;
        
        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;
        
        boolean solid = lvl[(int)yIndex][(int)xIndex].getIsSolid();
        //there is only 48 sprites (solid) and 11th sprite is blank space
       
        return solid;
        
    }
    
    
    //method that get the position next to wall
    //incase the player is hugging the wall
    public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
        
        int currentTile = (int)(hitbox.x / Game.TILES_SIZE);
        
        //right
        if (xSpeed > 0) {
            int tileXPos = currentTile * Game.TILES_SIZE;
            int xOffset = (int)(Game.TILES_SIZE - hitbox.width);
            //   tileXPos
            //------X           |-----
            //      | offsit  _ |
            //      |<------>| ||
            //      |        | ||
            //      |        |_||
            //
            // X is the tileXPos
            // so X + offsit should be the postion of the player;
            return tileXPos + xOffset - 1;
            //-1 so its not inside the edge but outside it by a mere pixel
        }else { //left
            return currentTile * Game.TILES_SIZE;
        }
    }
    
    //very similar to the above method but for Y position
    //the y position in case of hugging the ground or we hit the wall
    public static float GetEntityYPosNextToWall(Rectangle2D.Float hitbox, float ySpeed) {
        
        int currentTile = (int)(hitbox.y / Game.TILES_SIZE);
        
        //going down(falling)
        if (ySpeed > 0) {
            int tileYPos = currentTile * Game.TILES_SIZE;
            int yOffset = (int)(Game.TILES_SIZE - hitbox.height);
            //  tileYPos
            //------Y ^        |-----
            //      | | yOffsit|
            //      | V        |
            //      || |       |
            //____ _||_|       |
            //
            //so Y + offsit should be the position of the player
            return tileYPos + yOffset - 1;
        } else { //going up (jumping)
            return currentTile * Game.TILES_SIZE ;
        }
        
        
    }
    
    //method that help get hold of interactive object near player
    public static InteractiveObject GetObjectNearby(Player player) {
        int currentXTile = player.getXTile();
        int currentYTile = player.getYTile();

        //check the tile player's standing on
        InteractiveObject obj = player.getLvl()[currentYTile][currentXTile].getObject();
        if (obj != null) return obj;
        
        //check the tile below the player
        if (currentYTile < Game.TILES_IN_HEIGHT-1) {
            obj = player.getLvl()[currentYTile+1][currentXTile].getObject();
            if (obj != null) return obj;
        }
        
        //check the tile to the right of the player
        if (currentXTile < Game.TILES_IN_WIDTH-1) {
            obj = player.getLvl()[currentYTile][currentXTile+1].getObject();
            if (obj != null) return obj;
        }
        
        //check the tile above the player
        if (currentYTile > 0) {
            obj = player.getLvl()[currentYTile-1][currentXTile].getObject();
            if (obj != null) return obj;
        }
        
        //check the tile to the left of the player
        if (currentXTile > 0) {
            obj = player.getLvl()[currentYTile][currentXTile-1].getObject();
            if (obj != null) return obj;
        }
        
        //no near by objects
        return null;
    }
    

    
 }
