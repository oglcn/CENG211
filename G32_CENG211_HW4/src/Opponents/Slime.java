package Opponents;

import java.util.List;
import java.util.Random;

import Characters.Human;
import Weapons.Weapon;

public class Slime extends Opponent {
    public Slime(int opponentId) {
        super(opponentId);
    }

    @Override
    public void performAction(List<Human<Weapon>> characters, List<Opponent> opponents) {
        Random rand = new Random();
        int action = rand.nextInt(3); // 0-Attack, 1-Guard, 2-Special

        switch (action) {
            case 0:
                // Attack
                Human<Weapon> target = characters.get(rand.nextInt(characters.size()));
                target.receiveDamage(this.attack);
                // Announce the action with the target's name, damage dealt, and target's
                // remaining points
                System.out.println("Slime " + this.opponentId + " attacked " + target.getName() + " for " + this.attack
                        + " damage! " + target.getName() + " has " + target.getPoints() + " points remaining.");
                break;
            case 1:
                // Guard
                this.isGuarding = true;
                // Announce the action with the target's name, damage dealt, and target's
                // remaining points
                System.out.println("Slime " + this.opponentId + " is guarding!");

                break;
            case 2:
                // Special: Absorb
                target = characters.get(rand.nextInt(characters.size()));
                absorb(target);
                // Announce the action with the target's name, damage dealt, and target's
                // remaining points
                System.out.println("Slime " + this.opponentId + " absorbed " + target.getName() + " for " + this.attack
                        + " damage! " + target.getName() + " has " + target.getPoints() + " points remaining." + "Slime now has " + this.points + " points.");
                break;
        }
    }

    public void absorb(Human<Weapon> target) {
        // Slime's special action: absorb
        int damage = this.attack;
        target.receiveDamage(damage);
        this.points = Math.min(this.points + damage, 150);
    }
}
