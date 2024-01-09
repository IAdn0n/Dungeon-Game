/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objects;

import utilz.LoadSave;

public class Light extends InteractiveObject{
    public Light(float x, float y, int width, int height) {
        super(x, y, width, height, false);
        id = RED_LIGHT_ID;
        loadImgs(LoadSave.LIGHT.length, LoadSave.LIGHT);
        
    }
    
    @Override
    public void update() {
        if (imgIndex == 0) {
            imgIndex = 1;
            id = GREEN_LIGHT_ID;
        }
        else {
            imgIndex = 0;
            id = RED_LIGHT_ID;
        }
        
        
    }
}
