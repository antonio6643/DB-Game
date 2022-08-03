import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class HealthBar extends Actor
{
    
    private World myWorld;
    private Fighter fighter;
    
    public HealthBar(Fighter fighter){
        this.myWorld = this.getWorld();
        this.fighter = fighter;
        
        GreenfootImage healthImage = new GreenfootImage(200, 20);
        healthImage.setColor(Color.BLACK);
        healthImage.fill();
        healthImage.setColor(Color.GREEN);
        healthImage.fillRect(2, 2, 196, 16);
        //healthImage.drawString("Health: 500", 50, 10);
        
        this.setImage(healthImage);
        
    }
    
    public void updateUI(){
        GreenfootImage healthImage = new GreenfootImage(200, 20);
        
        float ratio = 196/((float)this.fighter.getMaxHealth());
        
        healthImage.setColor(Color.BLACK);
        healthImage.fill();
        healthImage.setColor(Color.GREEN);
        healthImage.fillRect(2, 2, (int)(ratio*this.fighter.getHealth()), 16);
        
        this.setImage(healthImage);
    }
}
