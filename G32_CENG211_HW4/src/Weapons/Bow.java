package Weapons;
import Characters.Human;
import Opponents.Opponent;

public class Bow extends Weapon {

    public Bow() {
        super();

        this.attack1Description = "Shoot a single arrow, 80% combined attack damage";
        this.attack2Description = "Shoot two arrows, 250% combined attack damage";
    }


    @Override
    public int attack(Opponent target, int characterAttack, boolean useSecondaryType, Human<Weapon> human) {
        if (useSecondaryType) {
            // Shoot two arrows
            return (int) (2.5 * (characterAttack + additionalAttack));
        } else {
            // Shoot a single arrow
            return (int) (0.8 * (characterAttack + additionalAttack));
        }
    }
}