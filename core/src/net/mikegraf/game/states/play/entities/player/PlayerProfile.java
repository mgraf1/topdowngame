package net.mikegraf.game.states.play.entities.player;

public class PlayerProfile {

    private int numLives;
    private int maxHealth;

    public PlayerProfile(int numLives, int maxHealth) {
        this.numLives = numLives;
        this.maxHealth = maxHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getNumLives() {
        return numLives;
    }

    public void die() {
        numLives--;
    }
}
