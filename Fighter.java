import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
    
public class Fighter extends Actor {
    
    private static double playerScale = 1.5;
    
    private String name;
    private int health;
    private int MaxHealth = 500;
    private int MaxKi = 200;
    private int ki = 0;
    private int speed = 7;
    private boolean player;
    
    private boolean blocking = false;
    private boolean charging = false;
    private boolean canMove = true;
    private boolean melee = false;
    private boolean lockAnimation = false;
    
    private double multiplier = 1;
    private double slow = 1;
    
    private FightWorld myWorld;
    
    private String state = "Idle";
    private boolean transformed = false;
    private int hOffset;
    private int wOffset;
    private boolean justDamaged = false;
        
    public Fighter(boolean isPlayerOne){
        GreenfootImage fighterImage = this.getImage();
        this.health = this.MaxHealth;
        
        fighterImage.scale(
            (int)(fighterImage.getWidth()*this.playerScale),
            (int)(fighterImage.getHeight()*this.playerScale)
        );
        
        hOffset = fighterImage.getHeight()/2;
        wOffset = fighterImage.getWidth()/2;
        
        this.myWorld = (FightWorld)(this.getWorld());
        this.player = isPlayerOne;
        
    }
    
    public void act() {
            
        int deltaX = 0;
        int deltaY = 0;
        
        /* Charging has been disabled since there are no ki attacks.
        if(this.charging){
            this.ki = Knutility.clampInteger(this.ki + 1, 0, this.MaxKi);
            if(this.ki >= this.MaxKi){
                this.charging = false;
            }
        } else {
            if(Greenfoot.isKeyDown(this.player == true ? "q" : "u") && this.ki < this.MaxKi){
                this.charging = true;
            }
        }*/
        // The ternary operators below determine keysets for Player 1 and Player 2
        if(Greenfoot.isKeyDown(this.player == true ? "e" : "o")){
            if(!this.blocking && !this.charging && !this.melee){
                this.blocking = true;
            }
        } else {
            this.blocking = false;
        }
        
        if(Greenfoot.isKeyDown(this.player == true ? "f" : "h")){
            if(!this.melee && !this.blocking && !this.charging){
                this.melee = true;
            }
        } else {
            this.melee = false;
        }
        
        if(Greenfoot.isKeyDown(this.player == true ? "x" : "m") && !this.transformed){
            if(this.ki >= this.MaxKi){
                this.transform();
            }
        }
        
        if(this.canMove && !this.blocking && !this.melee && !this.charging){
            
            FightWorld myWorld = (FightWorld)(this.getWorld());
            
            int hOffset = this.heightOffset();
            int wOffset = this.widthOffset();
            
            if(Greenfoot.isKeyDown(this.player == true ? "a" : "j")){
                deltaX -= 1;
            }
            
            if(Greenfoot.isKeyDown(this.player == true ? "d" : "l")){
                deltaX += 1;
            }
            
            if(Greenfoot.isKeyDown(this.player == true ? "w" : "i") && this.getY() > hOffset){
                deltaY -= 1;
            }
            
            if(Greenfoot.isKeyDown(this.player == true ? "s" : "k") && this.getY() < myWorld.ground-hOffset){
                deltaY += 1;
            }
            
            int trueSpeed = (int)(this.speed*(this.slow));
            
            int oldX = this.getX();
            int oldY = this.getY();
            
            this.setLocation(
                Knutility.clampInteger(this.getX() + (deltaX*trueSpeed), wOffset, myWorld.width-wOffset),
                Knutility.clampInteger(this.getY() + (deltaY*trueSpeed), hOffset, myWorld.ground-hOffset)
            );
            
            if(this.getIntersectingObjects(Fighter.class).size() > 0){ // Make sure there's no overlap
                this.setLocation(oldX, oldY);
            }
            
        }
        
        this.defineState(deltaX, deltaY);
        
        this.justDamaged = false;
    }
    
    private void defineState(int deltaX, int deltaY){
        this.state = "Idle";
        
        boolean imFlying = isFlying();
        
        if(imFlying){
            this.state = "FlyIdle";
        }
        
        if(this.justDamaged){
            this.state = "Damaged";
        }
        
        if(this.blocking){
            this.state = "Block";
        }
        
        if(this.charging){
            this.state = "Charge";
        }
        
        if(this.melee){
            //System.out.println("Fighting for "+this.getName());
            this.state = "Fight";
        }
        
        if(deltaY != 0){
            this.state = deltaY == 1 ? "FlyDown" : "FlyUp";
        }
        
        if(deltaX != 0){
            if(deltaX == 1){
                this.state = this.player ? "FlyForward" : "FlyBack";
            } else {
                this.state = this.player ? "FlyBack" : "FlyForward";
            }
        }
                
    }
        
    private boolean isFlying(){
        return this.getY() > this.myWorld.ground-this.heightOffset();
    }
    
    public void scaleImage(){
        GreenfootImage fighterImage = this.getImage();
        
        fighterImage.scale(
            (int)(fighterImage.getWidth()*this.playerScale),
            (int)(fighterImage.getHeight()*this.playerScale)
        );
        
        if(this.player){
            fighterImage.mirrorHorizontally();
        }
    }
    
    public void transform(){
        this.ki = 0;
        if(!this.transformed){
            this.transformed = true;
            this.health = this.MaxHealth;
            this.multiplier = 1.5;
        }
    }
    
    // Getters & Setters (ugly below)
    public int getHealth(){
        return this.health;
    }
    
    public void addHealth(int delta){
        this.health = Knutility.clampInteger(this.health + delta, 0, this.MaxHealth);
    }
    
    public void takeDamage(int amnt){
        this.health = Knutility.clampInteger(this.health - (this.blocking ? amnt/2 : amnt), 0, this.MaxHealth);
        this.justDamaged = true;
        this.ki = Knutility.clampInteger(this.ki + 1, 0, this.MaxKi);
    }
    
    public int getMaxHealth(){
        return this.MaxHealth;
    }
    
    
    public int getKi(){
        return this.ki;
    }
    
    public int getMaxKi(){
        return this.MaxKi;
    }
    
    
    public int getSpeed(){
        return this.speed;
    }
    
    public void setSpeed(int kspeed){
        this.speed = kspeed;
    }
    
    public void setSlow(int slowness){
        this.slow = 1-slowness;
    }
    
    
    public double getMultiplier(){
        return this.multiplier;
    }
    
    
    public String getState(){
        return this.state;
    }
    
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String kname){
        this.name = kname;
    }
    
    
    public boolean isBlocking(){
        return this.blocking;
    }
    
    public boolean isMelee(){
        return this.melee;
    }
    
    public boolean canMove(){
        return this.canMove;
    }
    
    public void disable(){
        this.canMove = false;
    }
    
    
    public int heightOffset(){
        //return this.getImage().getHeight()/2;
        return this.hOffset;
    }
    
    public int widthOffset(){
        //return this.getImage().getWidth()/2;
        return this.wOffset;
    }
    
    // Overrides
    
    public boolean isTouchingEnemy(){
        return this.getObjectsInRange((this.wOffset+this.hOffset), Fighter.class).size() > 0;
    }
    
}
