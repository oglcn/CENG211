package Game;
class Turn {
    private String ownerId; // ID of an opponent or character
    private double attackModifier;
    private boolean isCharacterTurn; // To distinguish between a character's turn and an opponent's turn

    public Turn(String ownerId, double attackModifier, boolean isCharacterTurn) {
        this.ownerId = ownerId;
        this.attackModifier = attackModifier;
        this.isCharacterTurn = isCharacterTurn;
    }

    // Getter and Setter methods
    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public double getAttackModifier() {
        return attackModifier;
    }

    public void setAttackModifier(double attackModifier) {
        this.attackModifier = attackModifier;
    }

    public boolean isCharacterTurn() {
        return isCharacterTurn;
    }

    public void setCharacterTurn(boolean isCharacterTurn) {
        this.isCharacterTurn = isCharacterTurn;
    }
}
