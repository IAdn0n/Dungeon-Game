/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objects;

import entities.Player;
import javaproject.Game;
import utilz.LoadSave;

public class Key extends InteractiveObject{
    
    public Key(float x, float y, int width, int height) {
        super(x, y, width, height, true);
        id = KEY_ID;
        
        loadImgs(LoadSave.KEY.length, LoadSave.KEY);
    }
    //copy construtor
    public Key(Key other) {
        super(other);
    }
    
    @Override
    public Key getCopy() {
        return new Key(this);
    }
    
    @Override
    public void interact(Player player) {
        int xTile = (int)(x/Game.TILES_SIZE);
        int yTile = (int)(y/Game.TILES_SIZE);
        
        player.setHand(this);
        this.setPicked(true);
        System.out.println("PICKED");

        player.getLvl()[yTile][xTile].setObject(null);
    }
    @Override
    public void interact(InteractiveObject other) {
        
    }
}
