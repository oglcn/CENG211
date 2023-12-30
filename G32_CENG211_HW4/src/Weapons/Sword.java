package Weapons;
import java.util.Random;

import Characters.Human;
import Opponents.Opponent;

public class Sword extends Weapon {

    public Sword() {
        super();

        this.attack1Description = "Slash. 100% combined attack damage";
        this.attack2Description = "Stab. 200% combined attack damage. 25% chance of failure";
    }

    @Override
    public int attack(Opponent target, int characterAttack, boolean useSecondaryType, Human<Weapon> human) {
        if (useSecondaryType) {
            // Stab: 25% chance of failure
            if (new Random().nextDouble() < 0.25) {
                return 0; // Attack failed
            }
            return 2 * (characterAttack + additionalAttack); // Success
        } else {
            // Slash
            return characterAttack + additionalAttack;
        }
    }
}