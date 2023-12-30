package Opponents;

import java.util.List;
import java.util.Random;

import Characters.Human;
import Weapons.Weapon;

public class Goblin extends Opponent {
    public Goblin(int opponentId) {
        super(opponentId);
    }

    @Override
    public void performAction(List<Human<Weapon>> characters, List<Opponent> opponents) {
        Random rand = new Random();
        int action = rand.nextInt(3);

        switch (action) {
            case 0:
                /*
                 * Attack: Randomly select a specific character and deal damage to them a value
                 * that is equal
                 * to the opponent’s attack stat.
                 */
                Human<Weapon> target = characters.get(rand.nextInt(characters.size()));
                target.receiveDamage(this.attack);
                // Announce the action with the target's name, damage dealt, and target's
                // remaining points
                System.out.println("Goblin " + this.opponentId + " attacked " + target.getName() + " for " + this.attack
                        + " damage! " + target.getName() + " has " + target.getPoints() + " points remaining.");
                break;
            case 1:
                /*
                 * Guard: Don’t do anything this turn and receive %50 less damage until the
                 * start of the next
                 * turn.
                 */
                this.isGuarding = true;
                // Announce the action with the target's name, damage dealt, and target's
                // remaining points
                System.out.println("Goblin " + this.opponentId + " is guarding!");
                break;
            case 2:
                /*
                 * Special: Perform a special action unique to the opponent’s type:
                 */
                target = characters.get(rand.nextInt(characters.size()));
                rushingAttack(target);
                // Announce the action with the target's name, damage dealt, and target's
                // remaining points
                System.out.println("Goblin " + this.opponentId + " used rushingAttack on " + target.getName() + " for "
                        + this.attack + " damage! " + target.getName() + " has " + target.getPoints()
                        + " points remaining.");

                // Implement the logic for the additional turn
                break;
        }
    }

    public void rushingAttack(Human<Weapon> target) {
        // Goblin's special action: rushingAttack
        /*
         * - Goblin: rushingAttack (It attacks normally and gets another turn
         * immediately after
         * the current one. It deals damage 0.7 × attack stat for both of these turns.)
         */
        int damage = (int) (0.7 * this.attack);
        target.receiveDamage(damage);
        // Logic for the additional turn
        ImmediateDoubleTurn = true;


    }
}
