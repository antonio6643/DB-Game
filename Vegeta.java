import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Vegeta extends Fighter
{
    
    private AnimationHandler fightAnimation;
    private AnimationHandler damageAnimation;
    private AnimationHandler transformAnimation;
    private String animationPrefix = "Vegeta/";
    
    public Vegeta(boolean IsPlayerOne){
        super(IsPlayerOne);
        this.setName("Vegeta");
    }
    
    public void act(){
        super.act();
        this.animate();
    }
    
    public void animate(){
        String animationName = this.getState();
        if(this.transformAnimation != null){
            this.transformAnimation.solve();
            if(this.transformAnimation.hasCompleted()){
                this.transformAnimation = null; 
                this.animationPrefix = "SuperSaiyanVegeta/";
            }
        } else if(this.damageAnimation != null){
            this.damageAnimation.solve();
            if(this.damageAnimation.hasCompleted()){
                this.damageAnimation = null;
            }
        } else if(animationName == "Fight"){
            if(fightAnimation == null){ // Create the fightAnimation if it doesn't exist.
                
                int animationLength = 9;
                
                String[] fightAnims = new String[animationLength];
                
                for(int n=1; n <= animationLength; n++){
                    fightAnims[n-1] = this.animationPrefix+"AttackAnimation/Vegeta_Attack"+n+".png";
                }
                
                fightAnimation = new AnimationHandler(this, fightAnims, 60);
            }
            fightAnimation.solve();
        } else if(animationName == "Damaged"){
            int animLength = 8;
            
            String[] damageAnims = new String[animLength];
            
            for(int n=1; n <= animLength; n++){ // Fill in the animation array
                damageAnims[n-1] = this.animationPrefix+"DamageAnimation/Vegeta_Damage"+n+".png";
            }
            
            this.damageAnimation = new AnimationHandler(this, damageAnims, 60);
        } else {
            fightAnimation = null;
            this.setImage(this.animationPrefix+"Vegeta_"+animationName+(animationName.indexOf(".") == -1 ? ".png" : ""));
            this.scaleImage();
        }
    }
    
    public void transform(){
        super.transform(); // Do global transformation stuff(full health, etc.)
        this.animationPrefix = "SuperSaiyanVegeta/";
        
        int transformationLength = 6;
        
        String[] transformationFrames = new String[transformationLength];
        
        for(int n=1; n <= transformationLength; n++){ // Fill in the animation array
            transformationFrames[n-1] = "Vegeta/TransformationAnimation/Vegeta_Transformation"+n+".png";
        }
        
        this.transformAnimation = new AnimationHandler(this, transformationFrames, 80);
        
        this.setName("Super Saiyan Vegeta");
    }
    
}
