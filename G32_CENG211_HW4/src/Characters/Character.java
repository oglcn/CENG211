package Characters;
import Exceptions.InsufficientStaminaException;
import Exceptions.SpecialAlreadyUsedException;
import Opponents.*;



public interface Character<W> {
    void punch(Opponent target) throws InsufficientStaminaException;
    void attackWithWeapon(W weapon, Opponent target, boolean secondary) throws InsufficientStaminaException;
    void guard();
    void run();
    float specialAction(Opponent target) throws SpecialAlreadyUsedException;
    void receiveDamage(int damage);
    String getName();
    int getPoints();
    int getCharacterId();


}

