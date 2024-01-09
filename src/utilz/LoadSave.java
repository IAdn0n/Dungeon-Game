/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilz;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStream;

import java.util.Scanner;
import javax.imageio.ImageIO;

import javaproject.Game;
import levels.Tile;
import objects.*;


public class LoadSave {
    
    public static final String[] LEVEL_DATA = {"TILES/", "PROPS/"};
    public static final String[] KEY = {"PROPS/62.png", "PROPS/63.png"};
    public static final String[] CHEST = {"OBJECTS/chest_close.png","OBJECTS/chest_open.png"};
    public static final String[] BLOOD_JAR = {"PROPS/210.png"};
    public static final String[] WATER_JAR = {"PROPS/213.png"};
    public static final String[] CUP = {"PROPS/176.png", "OBJECTS/blood_cup.png", "OBJECTS/water_cup.png"};
    public static final String[] VASE = {"PROPS/17.png", "PROPS/231.png", "PROPS/233.png"};
    public static final String[] GATE = {"OBJECTS/GATE.png", "Tiles/493.png"};
    public static final String[] LIGHT = {"OBJECTS/red_light.png", "OBJECTS/green_light.png"};
    public static final String[] LEVER = {"OBJECTS/lever_down.png", "OBJECTS/lever_up.png"};
    
    
    
    public static final String[] LEVEL_ONE = {"res/level_one_walls.txt",
                                              "res/level_one_floor.txt", 
                                              "res/level_one_props.txt",
                                              "res/level_one_objects.txt"};
   
    
    //method that stores an image into a variable
    public static BufferedImage GetSpriteAtlas(String fileName) {
        BufferedImage img = null;
        //loading specific image into a stream
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
        
        
        if (is == null)
            return img;
        
        //load the image into img variable
        try {
            img = ImageIO.read(is);  
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try{
                is.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        
        return img;
    }
    
    //method to build the world/level
    //2d array represents the window each index is a tile
    public static Tile[][] GetLevelData(String[] fileName) {
        
        Tile[][] lvl = new Tile[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
        
        FileInputStream f1 = null, f2 = null, f3 = null;
        Scanner s1 = null, s2 = null, s3 = null;
        
        try{
            f1 = new FileInputStream(fileName[0]);
            s1 = new Scanner(f1);
            f2 = new FileInputStream(fileName[1]);
            s2 = new Scanner(f2);
            f3 = new FileInputStream(fileName[2]);
            s3 = new Scanner(f3);
        
        }
        catch(FileNotFoundException e) {
            System.out.println("file does not exists");
        }
        
        
        for (int i = 0; i < Game.TILES_IN_HEIGHT; i++) {
            for (int j = 0; j < Game.TILES_IN_WIDTH; j++) {
                int wall = 493;
                int floor = 494;
                int prop = 239;
                
                //reading from wall
                if (s1.hasNextInt())
                    wall = s1.nextInt();
      
                //reading from floor
                if (s2.hasNextInt())
                    floor = s2.nextInt();
                
                //reading from props
                if (s3.hasNextInt())
                    prop = s3.nextInt();
                
                if (prop == 293)
                    System.out.println(i  + j );
                lvl[i][j] = new Tile(wall, floor, prop, !(wall==493));
            }
            
            //go to the next line
            if (s1.hasNextLine())
                s1.nextLine();
            if (s2.hasNextLine())
                s2.nextLine();
            if (s3.hasNextLine())
                s3.nextLine();

        }
        
        
        //the thing abouve the gatte
        for (int i = 0; i < 5; i++)
            lvl[8][i+16].setWall(299+i);
        
        
        setRandomBanners(lvl);
        
        setOtherSolids(lvl);

        
        //initialize objects
        try{
            f1 = new FileInputStream(fileName[3]);
            s1 = new Scanner(f1);
        
        }
        catch(FileNotFoundException e) {
            System.out.println("file does not exists");
        }
        
        int n = 0;
        if (s1.hasNextInt()) {
            n = s1.nextInt();
            s1.nextLine();
        }
        
        for (int i = 0; i < n; i++) {
            String type = s1.nextLine();
            
            int xTile = s1.nextInt(), yTile = s1.nextInt();
            int xPos = xTile * Game.TILES_SIZE, yPos = yTile * Game.TILES_SIZE;
            s1.nextLine();
            
            int width = s1.nextInt(), height = s1.nextInt();
            s1.nextLine();
            
            boolean solid = s1.nextInt() == 1;
            s1.nextLine();
            
            //skip the --------- line in the txt file
            if (s1.hasNextLine()) 
                s1.nextLine();
            
            
            //indicate the obj type
            InteractiveObject obj = null;
            switch (type) {
                case "chest" -> obj = new Chest(xPos, yPos, width*Game.TILES_SIZE, height*Game.TILES_SIZE);
                case "key" -> obj = new Key(xPos, yPos, width*Game.TILES_SIZE, height*Game.TILES_SIZE);
                case "cup" -> obj = new Cup(xPos, yPos, width*Game.TILES_SIZE, height*Game.TILES_SIZE);
                case "vase" -> obj = new Vase(xPos, yPos, width*Game.TILES_SIZE, height*Game.TILES_SIZE);
                case "blood-jar" -> obj = new BloodJar(xPos, yPos, width*Game.TILES_SIZE, height*Game.TILES_SIZE);
                case "water-jar" -> obj = new WaterJar(xPos, yPos, width*Game.TILES_SIZE, height*Game.TILES_SIZE);
                case "gate" -> obj = new Gate(xPos, yPos, width*Game.TILES_SIZE, height*Game.TILES_SIZE, lvl);
                case "light" -> obj = new Light(xPos, yPos, width*Game.TILES_SIZE, height*Game.TILES_SIZE);
                case "lever" -> obj = new Lever(xPos, yPos, width*Game.TILES_SIZE, height*Game.TILES_SIZE);
            }
            
            if (type.equals("gate")) {
                for (int w = 0; w < width; w++)
                    lvl[yTile+height-1][xTile+w].setIsSolid(true);
            }
            
            //set the object pos in a map
            for (int w = 0; w < width; w++) {
                for (int h = 0; h < height; h++) {
                    lvl[yTile+w][xTile+h].setObject(obj);
                    if (solid)
                        lvl[yTile+w][xTile+h].setIsSolid(true);
                }
            }
           
        }
        
        
        
      
        return lvl;
    }
    
    private static void setRandomBanners(Tile[][] lvl) {
        for (int i = 0; i < 4; i++) {
            int rand = (int)(Math.random() * 1.99f);
            lvl[18][13+(i*2)].setProp(180+rand);
            lvl[19][13+(i*2)].setProp(195+rand);
        }
    }
    
    private static void setOtherSolids(Tile[][]lvl) {
        //bed in the right bottom room
        for (int i = 0; i < 3; i++) {
            lvl[12+i][13].setIsSolid(true);
            lvl[12+i][14].setIsSolid(true);
        }
        
        //bed and boxes and two jars in top right room
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2;j++)
                lvl[5+i][13+j].setIsSolid(true);
        
        lvl[3][13].setIsSolid(true);
        lvl[3][14].setIsSolid(true);
        
        lvl[3][21].setIsSolid(true);
        
        //two jars in the top left
        lvl[6][1].setIsSolid(true);
        lvl[6][2].setIsSolid(true);
        
        
    }
    

}
