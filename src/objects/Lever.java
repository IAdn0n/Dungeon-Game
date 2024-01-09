/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objects;

import entities.Player;
import static java.lang.Thread.sleep;
import utilz.LoadSave;

/**
 *
 * @author alkam
 */
public class Lever extends InteractiveObject{
    public Lever(float x, float y, int width, int height) {
        super(x, y, width, height, false);
        id = LEVER_ID;
        loadImgs(LoadSave.LEVER.length, LoadSave.LEVER);
    }
    
    @Override
    public void interact (Player player) {
        int xTile = this.getXTile();

        int[] lightXTile= new int[2];

        if (xTile == 17) {  //right lever
            lightXTile[0] = 13;
            lightXTile[1] = 14;
        }
        else if (xTile == 18) {  //middle lever
            lightXTile[0] = 13;
            lightXTile[1] = 15;
        }
        else if (xTile == 19) {  //left lever
            lightXTile[0] = 14;
            //lightXTile[1] = 15;
        }
        
        //flip the lights
        for (int i : lightXTile) {
            if (player.getLvl()[1][i].getObject() != null)
                player.getLvl()[1][i].getObject().update();
        }
        changeImg();
        
        player.getLvl()[3][1].getObject().update(player);
        
    }
    
    private void changeImg(){     
        if (imgIndex == 0) imgIndex = 1;
        else imgIndex = 0;
        
    }
}
