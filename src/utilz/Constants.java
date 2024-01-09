/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilz;


public class Constants {
    
    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }
    
    public static class PlayerSpawn {

        public static int PLAYER_1_XSPAWN = 21;
        public static int PLAYER_1_YSPAWN = 24;
        
        public static int PLAYER_2_XSPAWN = 24;
        public static int PLAYER_2_YSPAWN = 14;
    }
    
    public static class PlayerConstants {
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int DYING = 2;
        
        public static final int FACING_RIGHT = 0;
        public static final int FACING_LEFT = 1;

        
        public static int GetSpriteAmount(int player_action) {
            switch(player_action) {
                case RUNNING:
                    return 6;
                case IDLE:
                case DYING:
                    return 4;
                default:
                    return 1;
            }
        }
        
        
    }
    
    
}
