package Game;

import java.util.*;

import Characters.Human;
import Exceptions.InsufficientStaminaException;
import Exceptions.NotAUniqueNameException;
import Exceptions.SpecialAlreadyUsedException;
import Opponents.Goblin;
import Opponents.Opponent;
import Opponents.Orc;
import Opponents.Slime;
import Opponents.Wolf;
import Weapons.Bow;
import Weapons.Spear;
import Weapons.Sword;
import Weapons.Weapon;

public class TBGame {
    private List<Opponent> opponents;
    private List<Human<Weapon>> characters;
    private Deque<Turn> turnOrder;
    private Scanner scanner;

    public TBGame() {
        opponents = new ArrayList<>();
        characters = new ArrayList<>();
        turnOrder = new LinkedList<>();
        scanner = new Scanner(System.in);
    }

    public void startGame() throws NumberFormatException, InsufficientStaminaException {
        Menu menu = new Menu();
        Initializer initializer = new Initializer();

        menu.displayMainMenu();
        int choice = scanner.nextInt();

        if (choice == 1) {
            initializer.initializeOpponents();
            initializer.initializeCharacters();
            menu.announceOpponents();
            menu.announcePlayers();
            playGame();
        } else {
            System.out.println("Exiting game. Thank you!");
        }
    }

    private void playGame() throws NumberFormatException, InsufficientStaminaException {
        while (!isGameOver()) {
            displayGameState();
            if (turnOrder.isEmpty()) {
                initializeTurnOrder(); // Re-populate the turn order if it becomes empty

                // Announce the turn order
                System.out.print("Turn order: ");
                for (Turn turn : turnOrder) {
                    if (turn.isCharacterTurn()) {
                        // Find the character with the given id using lambda expression

                        Characters.Character<Weapon> character = characters.stream()
                                .filter(c -> c.getCharacterId() == Integer.parseInt(turn.getOwnerId())).findFirst()
                                .orElse(null);

                        System.out.print((character).getName() + " | ");
                    } else {
                        System.out.print("(" + opponents.get(Integer.parseInt(turn.getOwnerId())).getOpponentId() + ") "
                                + opponents.get(Integer.parseInt(turn.getOwnerId())).getClass().getSimpleName()
                                + " | ");
                    }
                }
                System.out.println();
                System.out.println();
            }

            Turn currentTurn = turnOrder.poll();
            if (currentTurn.isCharacterTurn()) {
                executeCharacterTurn(characters.get(Integer.parseInt(currentTurn.getOwnerId())), currentTurn);
            } else {
                executeOpponentTurn(opponents.get(Integer.parseInt(currentTurn.getOwnerId())), currentTurn);
            }

            // Check for dead characters and opponents and remove them from the turn order
            // queue
            turnOrder.removeIf(turn -> {
                Characters.Character<Weapon> character = characters.stream()
                        .filter(c -> c.getCharacterId() == Integer.parseInt(turn.getOwnerId())).findFirst()
                        .orElse(null);
                if (turn.isCharacterTurn()) {
                    // Announce the character's death if it died
                    if (character.getPoints() <= 0) {
                        System.out.println(character.getName() + " died!");
                    }
                    return character.getPoints() <= 0;
                } else {
                    // Announce the opponent's death if it died
                    if (opponents.get(Integer.parseInt(turn.getOwnerId())).getPoints() <= 0) {
                        System.out.println(opponents.get(Integer.parseInt(turn.getOwnerId())).toString() + " died!");
                    }
                    return opponents.get(Integer.parseInt(turn.getOwnerId())).getPoints() <= 0;
                }
            });

            

        }

        announceGameResult();

    }

    private void initializeTurnOrder() {
        // Create a list of all characters and opponents with their speeds
        List<Turn> turns = new ArrayList<>();
        for (Human<Weapon> character : characters) {
            turns.add(new Turn(String.valueOf(character.getCharacterId()), 1.0, true)); // Assuming no attack modifier
                                                                                        // at start
        }
        for (Opponent opponent : opponents) {
            turns.add(new Turn(String.valueOf(opponent.getOpponentId()), 1.0, false));
        }

        // Sort turns based on speed
        turns.sort(Comparator.comparingInt(t -> {
            if (t.isCharacterTurn()) {
                return characters.get(Integer.parseInt(t.getOwnerId())).getSpeed();
            } else {
                return opponents.get(Integer.parseInt(t.getOwnerId())).getSpeed();
            }
        }));

        // Populate the turn order queue
        turnOrder = new LinkedList<>(turns);
    }

    private void executeCharacterTurn(Human<Weapon> character, Turn turn) throws InsufficientStaminaException {
        if (character == null)
            return;

        // Check if the turn should be skipped
        if (character.getSkipTurn()) {
            // Announce the skipped turn
            System.out.println(character.getName() + "'s turn skipped!");
            character.resetSkipTurn();
            // Re-insert the turn into the queue for the next round
            turnOrder.offer(turn);
            return;
        }
        

        if (character.getDoubleTurn()) {
            character.resetDoubleTurn();
            // Give the character another turn immediately after this one. Insert at the beginning of the queue
            turnOrder.offerFirst(turn);
        }

        // Announce the character's turn
        System.out.println("It's " + character.getName() + "'s turn! Stamina: " + character.getStamina() + "");

        // Decision-making process
        Scanner scanner = new Scanner(System.in);
        Opponent target = null;

        // Prompt the player to select an action
        System.out.println("Select an action:");
        System.out.println("[1] Punch | 1 stamina");
        System.out.println("[2] Attack with Weapon | 1-3 stamina");
        System.out.println("[3] Guard | 0 stamina");
        System.out.println("[4] Special Action | 0 stamina");
        System.out.println("[5] Run");
        int actionChoice = scanner.nextInt();

        // Announce the action chosen
        System.out.println("You chose: " + actionChoice);

        // If the action requires a target, prompt the player to select a target
        if (actionChoice == 1 || actionChoice == 2 || actionChoice == 4) {
            // Prompt the player to select a target opponent
            System.out.println("Select a target opponent:");
            for (int i = 0; i < opponents.size(); i++) {
                System.out.println(opponents.get(i).toString());
            }
            int targetChoice = scanner.nextInt();

            // Lambda expression to find the opponent with the given id
            Opponents.Opponent opponent = opponents.stream()
                    .filter(c -> c.getOpponentId() == targetChoice).findFirst()
                    .orElse(null);

            if (opponent != null) {
                target = opponents.get(targetChoice);
            } else {
                System.out.println("Invalid target choice. Defaulting to first opponent.");
                target = opponents.get(0);
            }
        }

        switch (actionChoice) {
            case 1:
                character.punch(target);
                break;
            case 2:
                // Prompt the player to select primary or secondary weapon functionality
                System.out.println("Select weapon functionality:");
                System.out.println("[1] - Primary: " + character.getWeapon().getAttack1Description());
                System.out.println("[2] - Secondary: " + character.getWeapon().getAttack2Description());
                int weaponChoice = scanner.nextInt();
                boolean secondary = weaponChoice == 2;
                if (weaponChoice != 1 && weaponChoice != 2) {
                    System.out.println("Invalid weapon choice. Defaulting to primary.");
                    secondary = false;
                }
                character.attackWithWeapon(character.getWeapon(), target, secondary);
                break;
            case 3:
                character.guard();
                // Announce the action
                System.out.println(character.getName() + " is guarding!");
                break;
            case 4:
                try {
                    character.specialAction(target);
                } catch (SpecialAlreadyUsedException e) {
                    System.out.println("Special action already used. Defaulting to punch.");
                    character.punch(target);
                }
                break;
            case 5:
                character.run();
                // Display the game state and exit the game
                displayGameState();
                System.out.println("Game over. Thank you!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid action choice. Defaulting to punch.");
                character.punch(target);
                break;
        }

        // Re-insert the turn into the queue for the next round
        turnOrder.offer(turn);
    }

    private void executeOpponentTurn(Opponent opponent, Turn turn) {
        if (opponent == null)
            return;

        // Announce the opponent's turn
        System.out.println("It's " + opponent.toString() + "'s turn!");

        // Opponent performs an action
        opponent.performAction(characters, opponents);

        // Check if ImmediateDoubleTurn is true
        if (opponent.getImmediateDoubleTurn()) {
            opponent.resetImmediateDoubleTurn();
            // Give the opponent another turn immediately after this one. Insert at the beginning of the queue
            turnOrder.offerFirst(turn);
        }

        // Re-insert the turn into the queue for the next round
        turnOrder.offer(turn);
    }

    private boolean isGameOver() {
        // Check if the game is over (all characters or opponents defeated)
        characters.removeIf(character -> character.getPoints() <= 0);
        opponents.removeIf(opponent -> opponent.getPoints() <= 0);
        return characters.isEmpty() || opponents.isEmpty();

    }

    private void displayGameState() {
        // Display the current state of the game
        System.out.println();
        System.out.println("Characters:");
        for (Human<Weapon> character : characters) {
            // Display character details like name, points, stamina
            System.out.println(character.getName() + " | " + character.getPoints() + " points" + " | "
                    + character.getStamina() + " stamina");

        }

        System.out.println("Opponents:");
        for (Opponent opponent : opponents) {
            System.out.println(opponent.toString() + " - " + opponent.getPoints() + " points");
        }

        System.out.println();

    }

    private void announceGameResult() {
        // Announce the result of the game
        if (characters.isEmpty()) {
            System.out.println("All characters defeated. Game over!");
        } else {
            // Get Alive characters
            List<Human<Weapon>> aliveCharacters = new ArrayList<>();
            for (Human<Weapon> character : characters) {
                if (character.getPoints() > 0) {
                    aliveCharacters.add(character);
                }
            }
            // Announce the winner
            System.out.println("The following characters are alive:");
            for (Human<Weapon> character : aliveCharacters) {
                System.out.println(character.getName());
            }
            System.out.println("All opponents defeated. You win!");
        }
    }

    // Inner class for Menu
    class Menu {
        public void displayMainMenu() {
            System.out.println("Welcome to TBGame!");
            System.out.println("[1] Start Game");
            System.out.println("[2] Exit");
            System.out.print("Choose an option: ");
        }

        public void announcePlayers() {
            System.out.println("The following characters are playing:");
            for (Human<Weapon> character : characters) {
                System.out.println(character.getName());
                System.out.println("Points: " + character.getPoints());
                System.out.println("Attack: " + character.getAttack());
                System.out.println("Speed: " + character.getSpeed());
                System.out.println("Stamina: " + character.getStamina());
                System.out.println("Weapon: " + character.getWeapon().getClass().getSimpleName());
                System.out.println();
            }
        }

        // Announce the opponents
        public void announceOpponents() {
            System.out.println("The following opponents appeared:");
            for (Opponent opponent : opponents) {
                System.out.println(opponent.toString());
                System.out.println("Points: " + opponent.getPoints());
                System.out.println("Attack: " + opponent.getAttack());
                System.out.println("Speed: " + opponent.getSpeed());
                System.out.println();

            }
        }

    }

    // Inner class for Initializer
    class Initializer {
        public void initializeOpponents() {
            Random rand = new Random();
            int numberOfOpponents = 1 + rand.nextInt(4); // 1 to 4 opponents
            for (int i = 0; i < numberOfOpponents; i++) {
                Opponent opponent = createRandomOpponent(i);
                opponents.add(opponent);
            }
        }

        private Opponent createRandomOpponent(int id) {
            Random rand = new Random();
            int opponentType = rand.nextInt(4); // Random number between 0 and 3

            switch (opponentType) {
                case 0:
                    return new Slime(id);
                case 1:
                    return new Goblin(id);
                case 2:
                    return new Orc(id);
                case 3:
                    return new Wolf(id);
                default:
                    throw new IllegalStateException("Invalid opponent type");
            }
        }

        private Weapon createRandomWeapon() {
            Random rand = new Random();
            int weaponType = rand.nextInt(3); // Random number between 0 and 2

            switch (weaponType) {
                case 0:
                    return new Sword();
                case 1:
                    return new Spear();
                case 2:
                    return new Bow();
                default:
                    throw new IllegalStateException("Invalid weapon type");
            }
        }

        public void initializeCharacters() {
            System.out.print("Enter the number of characters to create (up to 3): ");

            int numCharacters = scanner.nextInt();
            scanner.nextLine(); // Consume the trailing newline

            // Check if the number of characters is valid
            if (numCharacters < 1 || numCharacters > 3) {
                System.out.println("Invalid number of characters. Exiting game. Thank you!");
                System.exit(0);
            }

            Set<String> characterNames = new HashSet<>(); // To ensure unique names
            for (int i = 0; i < numCharacters; i++) { // Loop to create characters
                try {
                    System.out.print("Enter name for your " + (i + 1) + "st character: ");
                    String name = scanner.nextLine();

                    if (characterNames.contains(name)) {
                        throw new NotAUniqueNameException(
                                "Name '" + name + "' is already taken. Please choose a different name.");
                    }

                    // Pick a random weapon for the character
                    Weapon weapon = createRandomWeapon();

                    // Create the character
                    Human<Weapon> newCharacter = new Human<>(name, weapon, i);
                    characters.add(newCharacter);
                    characterNames.add(name);

                    // Optionally, print character details here
                    System.out.println("Created character: " + name + " with " + weapon.getClass().getSimpleName());
                } catch (NotAUniqueNameException e) {
                    System.out.println(e.getMessage());
                    i--; // Retry the current iteration
                }
            }
        }
    }
}
