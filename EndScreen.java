import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class EndScreen extends World
{
    public EndScreen(int winner, long fightStart){    
        super(1024, 600, 1);
        
        Fighter victor = null;
        Color winnerColor = Color.YELLOW;
        
        // Show the winner and breakdown
        String winnerText = "It's a tie";
        if(winner == 1){
            winnerText = "Goku Wins!";
            winnerColor = Color.ORANGE;
            victor = new Goku(true);
        } else if(winner == 2){
            winnerText = "Vegeta Wins!";
            winnerColor = Color.BLUE;
            victor = new Vegeta(false);
        }
        
        String duration = Knutility.formatTimestamp(Knutility.tick()-fightStart);
        
        TextLabel mainText = new TextLabel(winnerText, 50, winnerColor);
        TextLabel timeDuration = new TextLabel("Time: "+duration, 40, Color.GREEN);
        
        this.addObject(mainText, 1024/2, 600/2);
        this.addObject(timeDuration, 1024/2, (600/2)+100);
        
        if(victor != null){
            victor.disable();
            this.addObject(victor, 1024/2, 600/4);
        }
    }
}
