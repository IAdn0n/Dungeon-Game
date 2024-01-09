/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package levels;



public class Level {
    
    private Tile[][] lvl;
    
    public Level(Tile[][] lvlData) {
        this.lvl = lvlData;
    }
    
    public Tile getSpriteIndex(int x, int y) {
        return lvl[y][x];
    }
    
    //GETTERS
    public Tile[][] getLvl() {return lvl; }
}
