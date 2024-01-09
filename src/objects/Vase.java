/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objects;

import entities.Player;
import utilz.LoadSave;


public class Vase extends InteractiveObject{
    public Vase(float x, float y, int width, int height) {
        super(x, y, width, height, false);
        id = VASE_ID;
        
        loadImgs(LoadSave.VASE.length, LoadSave.VASE);
    }
    
    @Override
    public void interact (Player player) {
        if (player.getHand() == null) return;
        else if (player.getHand().getId() == BLOOD_CUP_ID) {
            player.getHand().setId(CUP_ID); //we used the key
            setId(BLOOD_VASE_ID);
            
        }
        else if (player.getHand().getId() == WATER_CUP_ID) {
            player.getHand().setId(CUP_ID); //we used the key
            setId(WATER_VASE_ID);
        }
        player.getLvl()[8][17].getObject().update(player);
    }
    
    @Override
    public void setId(int id) {
        this.id = id;
        if (id == VASE_ID)
            imgIndex = 0;
        else if (id == BLOOD_VASE_ID)
            imgIndex = 1;
        else if (id == WATER_VASE_ID)
            imgIndex = 2;
    }
}
