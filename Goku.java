import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Goku extends Fighter
{
    
    private AnimationHandler fightAnimation;
    private AnimationHandler damageAnimation;
    private AnimationHandler transformAnimation;
    private String animationPrefix = "Goku/";
    
    public Goku(boolean IsPlayerOne){
        super(IsPlayerOne);
        this.setName("Goku");
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
                this.animationPrefix = "SuperSaiyanGoku/";
            }
        } else if(this.damageAnimation != null){
            this.damageAnimation.solve();
            if(this.damageAnimation.hasCompleted()){
                this.damageAnimation = null;
            }
        } else if(animationName == "Fight"){
            if(fightAnimation == null){
                
                int animationLength = 16;
                
                String[] fightAnims = new String[animationLength];
                
                for(int n=1; n <= animationLength; n++){ // Fill in the animation array
                    fightAnims[n-1] = this.animationPrefix+"AttackAnimation/Goku_Attack"+n+".png";
                }
                
                fightAnimation = new AnimationHandler(this, fightAnims, 70);
            }
            fightAnimation.solve();
        } else if(animationName == "Damaged"){
            int animLength = 8;
            
            String[] damageAnims = new String[animLength];
            
            for(int n=1; n <= animLength; n++){ // Fill in the animation array
                damageAnims[n-1] = this.animationPrefix+"DamageAnimation/Goku_Damage"+n+".png";
            }
            
            this.damageAnimation = new AnimationHandler(this, damageAnims, 60);
        } else {
            fightAnimation = null;
            this.setImage(this.animationPrefix+"Goku_"+animationName+(animationName.indexOf(".") == -1 ? ".png" : ""));
            this.scaleImage();
        }
    }
    
    public void transform(){
        super.transform();
        this.animationPrefix = "SuperSaiyanGoku/";
        
        int transformationLength = 6;
        
        String[] transformationFrames = new String[transformationLength];
        
        for(int n=1; n <= transformationLength; n++){ // Fill in the animation array
            transformationFrames[n-1] = "Goku/TransformationAnimation/Goku_Transformation"+n+".png";
        }
        
        this.transformAnimation = new AnimationHandler(this, transformationFrames, 80);
        
        this.setName("Super Saiyan Goku");
    }
    
}
