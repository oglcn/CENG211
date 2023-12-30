package Game;

import Exceptions.InsufficientStaminaException;

public class App {
    public static void main(String[] args) {
        TBGame game = new TBGame();
        try {
            game.startGame();
        } catch (NumberFormatException e) {
            //e.printStackTrace();
            throw e;
        } catch (InsufficientStaminaException e) {
            e.printStackTrace();
            
        }
    }
}

