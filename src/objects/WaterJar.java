/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objects;

import entities.Player;
import utilz.LoadSave;

public class WaterJar extends InteractiveObject{
     public WaterJar(float x, float y, int width, int height) {
        super(x, y, width, height, false);
        id = WATER_JAR_ID;
        
        loadImgs(LoadSave.WATER_JAR.length, LoadSave.WATER_JAR);
    }
    
    @Override
    public void interact (Player player) {
        if (player.getHand() == null) return;
        else if (player.getHand().getId() == CUP_ID) {
            player.getHand().setId(WATER_CUP_ID); //we used the key
        }
    }
    @Override
    public void interact(InteractiveObject other) {
        
    }
}
