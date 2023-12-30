package Characters;
import java.util.Random;

enum Job {
    KNIGHT, HUNTER, SQUIRE, VILLAGER;

    public static Job randomJob() {
        return values()[new Random().nextInt(values().length)];
    }
}