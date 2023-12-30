package Opponents;
import java.util.List;
import java.util.Random;

import Characters.Human;
import Weapons.Weapon;

public class Orc extends Opponent {
    public Orc(int opponentId) {
        super(opponentId);
    }

    @Override
    public void performAction(List<Human<Weapon>> characters, List<Opponent> opponents) {

        // Don't perform action if skipTurn is true
        if (skipTurn) {
            skipTurn = false;
            return;
        }

        Random rand = new Random();
        int action = rand.nextInt(3);

        switch (action) {
            case 0:
                Human<Weapon> target = characters.get(rand.nextInt(characters.size()));
                target.receiveDamage(this.attack);
                // Announce the action with the target's name, damage dealt, and target's remaining points
                System.out.println("Orc " + this.opponentId + " attacked " + target.getName() + " for " + this.attack + " damage! " + target.getName() + " has " + target.getPoints() + " points remaining.");
                break;
            case 1:
                this.isGuarding = true;
                // Announce the action with the target's name, damage dealt, and target's remaining points
                System.out.println("Orc " + this.opponentId + " is guarding!");
                break;
            case 2:
                target = characters.get(rand.nextInt(characters.size()));
                heavyHit(target);
                // Announce the action with the target's name, damage dealt, and target's remaining points
                System.out.println("Orc " + this.opponentId + " used heavyHit on " + target.getName() + " for " + this.attack + " damage! " + target.getName() + " has " + target.getPoints() + " points remaining.");
                // Implement the logic for skipping the next turn
                skipTurn = true;
                break;
        }
    }

    public void heavyHit(Human<Weapon> target) {
        // Orc's special action: heavyHit
        int damage = (int) (1.5 * this.attack);
        target.receiveDamage(damage);
        // Logic for skipping the next turn
        skipTurn = true;

    }
}