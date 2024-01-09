/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objects;

import utilz.LoadSave;


public class Cup extends InteractiveObject{
    public Cup(float x, float y, int width, int height) {
        super(x, y, width, height, true);
        id = CUP_ID;
        
        loadImgs(LoadSave.CUP.length, LoadSave.CUP);
    }
    //copy construtor
    public Cup(Cup other) {
        super(other);
        setId(other.id);
    }
    
    @Override
    public Cup getCopy() {
        return new Cup(this);
    }
    
    @Override
    public void setId(int id) {
        this.id = id;
        if (id == CUP_ID)
            imgIndex = 0;
        else if (id == BLOOD_CUP_ID)
            imgIndex = 1;
        else if (id == WATER_CUP_ID)
            imgIndex = 2;
    }
}
