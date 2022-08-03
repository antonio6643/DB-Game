import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A class that allows more control over how text is displayed on the screen.
 * 
 * @author Antonio
 * @version 3.13.2020
 */
public class TextLabel extends Actor
{
    
    public TextLabel(String text, int textSize){
        GreenfootImage img = new GreenfootImage(text, textSize, Color.WHITE, null, Color.BLACK);
        this.setImage(img);
    }
    
    public TextLabel(String text, int textSize, Color textColor){
        GreenfootImage img = new GreenfootImage(text, textSize, textColor, null, Color.BLACK);
        this.setImage(img);
    }
    
}
