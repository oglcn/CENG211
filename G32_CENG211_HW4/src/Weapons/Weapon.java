package Weapons;
import java.util.Random;

import Characters.Human;
import Opponents.Opponent;

abstract public class Weapon {

    protected String attack1Description;
    protected String attack2Description;

    protected int additionalAttack;

    public Weapon() {
        this.additionalAttack = 10 + new Random().nextInt(11); // 10 to 20
    }

    public int getAdditionalAttack() {
        return additionalAttack;
    }

    public abstract int attack(Opponent target, int characterAttack, boolean useSecondaryType, Human<Weapon> human);

    public String getAttack1Description() {
        return attack1Description;
    }

    public String getAttack2Description() {
        return attack2Description;
    }

    
}
