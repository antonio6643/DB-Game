/**
 * Greenfoot doesn't have gif support.
 * 
 * @author Antonio
 * @version 3.13.2020
 */
import java.util.Calendar;
public class AnimationHandler  
{
    
    private Fighter entity;
    private String[] frames;
    private long delayMS;
    
    private int index = 0;
    private long lastFrame = 0;
    private boolean completed = false;
    
    public AnimationHandler(Fighter entity, String[] frames, long delayMS){
        this.entity = entity;
        this.frames = frames;
        this.delayMS = delayMS;
    }
    
    public void solve(){
        if(Knutility.tick()-this.lastFrame >= delayMS){
            this.index++;
            if(this.index >= frames.length){
                this.index = 0;
                this.completed = true;
            }
            this.entity.setImage(this.frames[this.index]);
            this.entity.scaleImage();
            this.lastFrame = Knutility.tick();
        }
    }
    
    public boolean hasCompleted(){
        return this.completed;
    }
    
}
