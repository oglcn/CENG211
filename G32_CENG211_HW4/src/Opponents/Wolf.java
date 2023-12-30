package Opponents;

import java.util.List;
import java.util.Random;

import Characters.Human;
import Weapons.Weapon;

public class Wolf extends Opponent {
    public Wolf(int opponentId) {
        super(opponentId);
    }

    // Copy constructor (with a different opponentId)
    public Wolf(Wolf wolf, int opponentId) {
        super(opponentId);
        this.points = wolf.points;
        this.attack = wolf.attack;
        this.speed = wolf.speed;
        this.isGuarding = wolf.isGuarding;
        this.skipTurn = wolf.skipTurn;
        this.ImmediateDoubleTurn = wolf.ImmediateDoubleTurn;
    }

    @Override
    public void performAction(List<Human<Weapon>> characters, List<Opponent> opponents) {
        Random rand = new Random();
        int action = rand.nextInt(3);

        switch (action) {
            case 0:
                Human<Weapon> target = characters.get(rand.nextInt(characters.size()));
                target.receiveDamage(this.attack);
                // Announce the action with the target's name, damage dealt, and target's
                // remaining points
                System.out.println("Wolf " + this.opponentId + " attacked " + target.getName() + " for " + this.attack
                        + " damage! " + target.getName() + " has " + target.getPoints() + " points remaining.");

                break;
            case 1:
                this.isGuarding = true;
                // Announce the action with the target's name, damage dealt, and target's
                // remaining points
                System.out.println("Wolf " + this.opponentId + " is guarding!");
                break;
            case 2:
                boolean result = callFriend(opponents); // Call a friend

                // Announce the result of the action
                if (result) {
                    System.out.println("Wolf " + this.opponentId + " called a friend!");
                } else {
                    System.out.println("Wolf " + this.opponentId + " failed to call a friend!");
                }
                break;
        }
    }

    public boolean callFriend(List<Opponent> opponents) {
        // Wolf's special action: callFriend
        if (new Random().nextDouble() < 0.20) { // 20% chance of success
            // Create a new identical wolf and add it to the list of opponents
            Wolf newWolf = new Wolf(this, opponents.size() + 1);
            opponents.add(newWolf);
            return true;
        }
        return false;
    }
}
