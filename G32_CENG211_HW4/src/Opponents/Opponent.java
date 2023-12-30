package Opponents;

import java.util.List;
import java.util.Random;

import Characters.Human;
import Weapons.Weapon;

abstract public class Opponent {
    protected int opponentId;
    protected int points;
    protected int attack;
    protected int speed;
    protected boolean isGuarding;
    protected boolean skipTurn;
    protected boolean ImmediateDoubleTurn;

    public Opponent(int opponentId) {
        Random rand = new Random();
        this.opponentId = opponentId;
        this.points = 50 + rand.nextInt(101); // 50 to 150
        this.attack = 5 + rand.nextInt(21); // 5 to 25
        this.speed = 1 + rand.nextInt(90); // 1 to 90
        this.isGuarding = false;
        this.skipTurn = false;
        this.ImmediateDoubleTurn = false;
    }

    public abstract void performAction(List<Human<Weapon>> characters, List<Opponent> opponents);

    public void receiveDamage(int damage) {
        if (isGuarding) {
            damage /= 2; // Reduce damage by 50% if guarding

            // Announce the reduction in damage due to guarding
            System.out.println("Guarding! Damage reduced by 50%!");
        }
        this.points = Math.max(this.points - damage, 0);
    }

    @Override
    public String toString() {
        return "(" + this.opponentId + ") " + this.getClass().getSimpleName();
    }

    // Getters and setters

    public int getOpponentId() {
        return opponentId;
    }

    public int getPoints() {
        return points;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isGuarding() {
        return isGuarding;
    }

    public void setGuarding(boolean isGuarding) {
        this.isGuarding = isGuarding;
    }

    public String getAttack() {
        return Integer.toString(attack);
    }

    public boolean getImmediateDoubleTurn() {
        return ImmediateDoubleTurn;
    }

    public void resetImmediateDoubleTurn() {
        ImmediateDoubleTurn = false;
    }
}