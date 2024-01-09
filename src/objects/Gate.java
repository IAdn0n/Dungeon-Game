/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objects;

import java.awt.Graphics;

import entities.Player;
import levels.Tile;
import utilz.LoadSave;

/**
 *
 * @author alkam
 */
public class Gate extends InteractiveObject{
    private int jars[] = {0, 0, 0, 0};
    private int order[];
    
    public Gate(float x, float y, int width, int height) {
        super(x, y, width, height, false);
        id = GATE_ID;
        loadImgs(LoadSave.GATE.length, LoadSave.GATE);
        order = new int[4];
        
    }
    
    public Gate(float x, float y, int width, int height, Tile[][] lvl) {
        super(x, y, width, height, false);
        id = GATE_ID;
        loadImgs(LoadSave.GATE.length, LoadSave.GATE);
        order = new int[4];
        setOrder(lvl);
    }
    //what is jar's type and order
    private void setOrder(Tile[][] lvl) {
        for (int i = 0; i < 4; i++) {
            int prop = lvl[18][13+(i*2)].getProp();
            switch (prop) {
                case 180 -> order[i] = BLOOD_VASE_ID;
                case 181 -> order[i] = WATER_VASE_ID;         
                default -> order[i] = VASE_ID;
            }
        }
        
    }
    
    @Override
    public void draw(Graphics g) {
        for (int i = 0; i < 3; i++)
            g.drawImage(imgs[imgIndex], (int)x + (i*(width/3)), (int)y, width/3, height ,null);
    }
    
    //method that updates the gate itself
    @Override
    public void update(Player player){
        
        for (int i = 0; i < 4; i++) {
            jars[i] = player.getLvl()[23][2+i].getObject().getId();
                    
            if (jars[i] != order[i]) {
                setOpen(false, player);
                return;
            }
        }
        
        setOpen(true, player);
    }
    
    //method thay opens/closes the gate
    private void setOpen(boolean open, Player player) {
        if (open) imgIndex = 1;
        else imgIndex = 0;
        
        for (int i = 0; i < 3; i++) {
            player.getLvl()[10][17+i].setIsSolid(!open);
        }
    }
}
