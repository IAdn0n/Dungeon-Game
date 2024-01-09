/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objects;

import entities.Player;
import utilz.LoadSave;

/**
 *
 * @author alkam
 */
public class Chest extends InteractiveObject{
    private boolean opened = false;
    
    public Chest(float x, float y, int width, int height) {
        super(x, y, width, height, false);
        id = CHEST_ID;
        loadImgs(LoadSave.CHEST.length, LoadSave.CHEST);
    }
    
    
    
    @Override
    public void interact (Player player) {
        if (opened)open();
        else if (player.getHand() == null) return;
        else if (player.getHand().getId() == KEY_ID) {
            opened = true;
            open();
            player.setHand(null); //we used the key
        }
    }
    @Override
    public void update(Player player) {
        
    }
    
    
    private void open() {
        if (imgIndex == 0) imgIndex = 1;
        else imgIndex = 0;
    }
}
