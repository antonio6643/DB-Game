import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class KiBar extends Actor
{
    
    private World myWorld;
    private Fighter fighter;
    
    public KiBar(Fighter fighter){
        this.myWorld = this.getWorld();
        this.fighter = fighter;
        
        GreenfootImage kiImage = new GreenfootImage(180, 20);
        kiImage.setColor(Color.BLACK);
        kiImage.fill();
        kiImage.setColor(Color.YELLOW);
        kiImage.fillRect(2, 2, 176, 16);
        kiImage.setColor(Color.WHITE);
        //kiImage.drawString("ki: 500", 50, 10);
        
        this.setImage(kiImage);
        
    }
    
    public void updateUI(){
        GreenfootImage kiImage = new GreenfootImage(180, 20);
        
        float ratio = 176/((float)this.fighter.getMaxKi());
        
        kiImage.setColor(Color.BLACK);
        kiImage.fill();
        kiImage.setColor(Color.YELLOW);
        kiImage.fillRect(2, 2, (int)(ratio*this.fighter.getKi()), 16);
        kiImage.setColor(Color.WHITE);
        
        this.setImage(kiImage);
    }
}
