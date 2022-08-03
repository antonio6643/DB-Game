import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class FightWorld extends World
{
    public static final int width = 1024;
    public static final int height = 600;
    public static final int ground = height-5; //(int)(0.75*height);
        
    private Fighter Player1;
    private Fighter Player2; // May be an NPC
    
    private TextLabel p1Name;
    private TextLabel p2Name;
    
    private HealthBar p1Health;
    private HealthBar p2Health;
    
    private KiBar p1Ki;
    private KiBar p2Ki;
    
    private static int step = 0;
    private long fightStart;
    
    
    public FightWorld(){ // Default for testing
        
        super(width, height, 1);
        
        this.Player1 = new Goku(true);
        this.Player2 = new Vegeta(false);
        
        this.Player1.getImage().mirrorHorizontally(); // So they face right
        
        GreenfootImage background = new GreenfootImage("Stages/WorldTournament.jpg");
        background.scale(width, height);
        this.setBackground(background);
                
        // Drawing the health bars/stats UI
        this.p1Name = new TextLabel("Goku", 40);
        this.p1Health = new HealthBar(this.Player1);
        this.p1Ki = new KiBar(this.Player1);
        
        this.p2Name = new TextLabel("Vegeta", 40);
        this.p2Health = new HealthBar(this.Player2);
        this.p2Ki = new KiBar(this.Player2);
        
        this.addObject(p1Name, 50, 20);
        this.addObject(p1Health, (p1Health.getImage().getWidth()/2)+30, 50);
        this.addObject(p1Ki, (p1Ki.getImage().getWidth()/2)+30, 72);
        
        this.addObject(p2Name, this.width-70, 20);
        this.addObject(p2Health, width-(p2Health.getImage().getWidth()/2)-30, 50);
        this.addObject(p2Ki, width-(p2Ki.getImage().getWidth()/2)-30, 72);
        
        this.addObject(this.Player1, width/4, ground-this.Player1.heightOffset());
        this.addObject(this.Player2, 3*width/4, ground-this.Player1.heightOffset());
    
        this.fightStart = Knutility.tick();
    }
    
    public void act(){
        if(this.Player1.isTouchingEnemy()){ // Melee damage
            if(Player1.isMelee()){
                Player2.takeDamage((int)(2*Player1.getMultiplier()));
            }
            if(Player2.isMelee()){
                Player1.takeDamage((int)(2*Player2.getMultiplier()));
            }
        }
        
        // Check if a player won
        
        boolean playerOneWin = this.Player2.getHealth() <= 0;
        boolean playerTwoWin = this.Player1.getHealth() <= 0;
        
        EndScreen ending = null;
        
        if(playerOneWin && playerTwoWin){ // Its a tie
            ending = new EndScreen(0, this.fightStart);
        } else if(playerOneWin){
            ending = new EndScreen(1, this.fightStart);
        } else if(playerTwoWin){
            ending = new EndScreen(2, this.fightStart);
        }
        
        if(ending != null){
            Greenfoot.setWorld(ending);
        }
        
        // Update all the UIs
        this.p1Health.updateUI();
        this.p2Health.updateUI();
        this.p1Ki.updateUI();
        this.p2Ki.updateUI();
    }
    
}
