package Weapons;
import Characters.Human;
import Opponents.Opponent;

public class Spear extends Weapon {

    public Spear() {
        super();

        attack1Description = "Stab, 110% combined attack damage";
        attack2Description = "Throw, 200% combined attack damage. Skip next turn";
    }

    @Override
    public int attack(Opponent target, int characterAttack, boolean useSecondaryType, Human<Weapon> human) {
        if (useSecondaryType) {
            // Throw: Skip next turn
            human.setSkipTurn(true);
            
            return 2 * (characterAttack + additionalAttack);
        } else {
            // Stab
            return (int) (1.1 * (characterAttack + additionalAttack));
        }
    }
}