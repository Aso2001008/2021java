package rpgcreature;
import java.util.*;

public class Golem extends Monster{
    public Golem(){
        super("ゴーレム",100,500,5);
    }
    @Override
    public void attack(Creature opponent) {
        
        Random r = new Random();
        int damage = r.nextInt(6)+5;
        System.out.printf("%sの攻撃！\n",getName());
        if(r.nextInt(100) < 5){
            System.out.printf("%sのクリティカルヒット！\n",getName());
            damage = 30;
        }
        
        opponent.damaged(damage,opponent);
        
        displayMessage(opponent,damage);
        
    }
}
