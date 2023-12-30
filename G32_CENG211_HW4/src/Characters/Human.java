package Characters;

import java.util.Random;

import Exceptions.InsufficientStaminaException;
import Exceptions.SpecialAlreadyUsedException;
import Opponents.Opponent;
import Weapons.*;

public class Human<W extends Weapon> implements Character<W> {
    private int humanId;
    private String name;
    private int points;
    private int stamina;
    private int attack;
    private int speed;
    private W weapon;
    private Job job;
    private boolean specialUsed;
    private boolean isGuarding;
    private boolean doubleTurn;
    private boolean skipTurn;
    float attackModifier;

    public Human(String name, W weapon, int humanId) {
        Random rand = new Random();
        this.name = name;
        this.points = 100 + rand.nextInt(51); // 100 to 150
        this.stamina = 10;
        this.attack = 20 + rand.nextInt(21); // 20 to 40
        this.speed = 10 + rand.nextInt(90); // 10 to 99
        this.weapon = weapon;
        this.job = Job.randomJob();
        this.specialUsed = false;
        this.isGuarding = false;
        this.humanId = humanId;
        this.doubleTurn = false;
        this.attackModifier = 1.0f;
        this.skipTurn = false;
    }

    public int getSpeed() {
        return speed;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public int getCharacterId() {
        return humanId;
    }

    @Override
    public void punch(Opponent target) throws InsufficientStaminaException {
        resetGuard();

        if (this.stamina < 1) {
            // Announce that the character does not have enough stamina to perform the
            // action and throw an exception
            System.out.println("Insufficient stamina for punch.");
            throw new InsufficientStaminaException("Insufficient stamina for punch.");
        }
        int damage = (int) (0.8 * this.attack);
        target.receiveDamage(damage);
        this.stamina -= 1;
        // Announce the action with the target's name, damage dealt, and target's
        // remaining points. Also announce the character's remaining stamina.
        System.out.println(this.name + " punched " + target.toString() + " for " + damage + " damage! "
                + target.toString() + " has " + target.getPoints() + " points remaining. " + this.name + " has "
                + this.stamina + " stamina remaining.");
    }

    @Override
    public void attackWithWeapon(W weapon, Opponent target, boolean secondary) throws InsufficientStaminaException {
        resetGuard();

        if (this.stamina < 2) {
            throw new InsufficientStaminaException("Insufficient stamina for weapon attack.");
        }
        boolean useSecondaryType = secondary;
        int damage = weapon.attack(target, this.attack, useSecondaryType, (Human<Weapon>) this);

        // Apply damage to the target
        target.receiveDamage(damage);

        // Adjust stamina based on weapon type and attack type
        adjustStaminaForWeaponAttack(weapon, useSecondaryType);

        // Announce the attack and the damage with primary or secondary info. Also
        // announce the character's remaining stamina.
        String attackType = useSecondaryType ? "secondary" : "primary";
        System.out.println(this.name + " attacked " + target.toString() + " for " + damage + " damage with "
                + attackType + " attack! "
                + target.toString() + " has " + target.getPoints() + " points remaining. " + this.name + " has "
                + this.stamina + " stamina remaining.");

    }

    @Override
    public void guard() {
        // Guard logic (increasing stamina and reducing incoming damage)
        this.stamina += 3;
        this.isGuarding = true;
    }

    @Override
    public void run() {
        // Logic for running away
        System.out.println("The character is running away! Game Ends. Bye!");
    }

    /**
     * Performs the special action of the Human character.
     * The behavior of the special action depends on the character's job.
     * 
     * @param target the opponent on which the special action is performed
     * @return the attack modifier applied by the special action
     * @throws SpecialAlreadyUsedException if the special action has already been
     *                                     used
     * @throws IllegalStateException       if the character's job is unknown
     */
    @Override
    public float specialAction(Opponent target) throws SpecialAlreadyUsedException {
        resetGuard();

        if (specialUsed) {
            throw new SpecialAlreadyUsedException("Special action already used.");
        }
        
        switch (this.job) {
            case KNIGHT:
                // Knight can skip the current turn and deals 3 × attack on his next turn
                attackModifier = 3.0f;
                // Announce the action with the target's name, damage dealt, and target's
                // remaining points
                System.out.println("Knight " + this.name + " skipped the current turn and will deal " + attackModifier
                        + " times the damage on the next turn.");
                // Skip the current turn
                break;
            case HUNTER:
                // Hunter can attack for 0.5 × attack in the current turn and have two turns
                // back-to-back for his next turn. Note that this does not mean he gets a turn
                // immediately after the current one.
                attackModifier = 0.5f;
                this.doubleTurn = true;

                // Initiate the attack
                target.receiveDamage((int) (attackModifier * this.attack));

                // Announce the action with the target's name, damage dealt, and target's
                // remaining points
                System.out.println("Hunter " + this.name + " attacked " + target.toString() + " for " + attackModifier
                        + " times the damage! " + target.toString() + " has " + target.getPoints()
                        + " points remaining. " + this.name + " has " + this.stamina + " stamina remaining.");

                break;
            case SQUIRE:
                // Implement Squire's special action
                // Squire can attack for 0.5 × attack in the current turn and increase his
                // stamina to 10.
                attackModifier = 0.5f;
                this.stamina = 10;

                // Initiate the attack
                target.receiveDamage((int) (attackModifier * this.attack));

                // Announce the action with the target's name, damage dealt, and target's
                // remaining points
                System.out.println("Squire " + this.name + " attacked " + target.toString() + " for " + attackModifier
                        + " times the damage! " + target.toString() + " has " + target.getPoints()
                        + " points remaining. " + this.name + " has " + this.stamina + " stamina remaining.");
                break;
            case VILLAGER:
                // Villager has no special action
                System.out.println("Villager " + this.name + " has no special action.");
                break;
            default:
                throw new IllegalStateException("Unknown job type.");
        }

        specialUsed = true;

        return attackModifier;
    }

    private void adjustStaminaForWeaponAttack(W weapon, boolean useSecondaryType) {
        // Adjust stamina based on the weapon type and attack type
        /*
         * attackWithWeapon<W>: The character selects an opponent and one the two attack
         * types of
         * his weapons. The selected opponent is damaged according to the rules of
         * weapons described
         * above. All attacks with sword and spear reduce stamina by 2. Bow’s single
         * arrow attack uses
         * 1 stamina, and its double arrow attack reduces stamina by 3.
         */
        if (weapon instanceof Sword || weapon instanceof Spear) {
            this.stamina -= 2;
        } else if (weapon instanceof Bow) {
            if (useSecondaryType) {
                this.stamina -= 3;
            } else {
                this.stamina -= 1;
            }
        }

    }

    // Additional methods like getters and setters

    // Method for the character to receive damage
    public void receiveDamage(int damage) {
        // Adjust damage based on any active defenses or states
        if (isGuarding()) {
            damage *= 0.25; // Reduces incoming damage to 25% if guarding

        }

        // Apply damage to the character's points
        this.points -= damage;

        // Ensure points do not drop below 0
        this.points = Math.max(this.points, 0);

        // Check if the character is defeated
        if (this.points <= 0) {
            handleDefeat();
        }
    }

    private boolean isGuarding() {
        return isGuarding;
    }

    public void resetGuard() {
        this.isGuarding = false;
    }

    private void handleDefeat() {
        // Handle the character's defeat (e.g., remove from the game, display message)
        System.out.println(this.name + " has been defeated.");
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public String getAttack() {
        return Integer.toString(attack);
    }

    public boolean getDoubleTurn() {
        return doubleTurn;
    }

    public void resetDoubleTurn() {
        this.doubleTurn = false;
    }

    public String getStamina() {
        return Integer.toString(stamina);
    }

    public void setSkipTurn(boolean b) {
        this.skipTurn = b;
    }

    public boolean getSkipTurn() {
        return skipTurn;
    }

    public void resetSkipTurn() {
        this.skipTurn = false;
    }
}
