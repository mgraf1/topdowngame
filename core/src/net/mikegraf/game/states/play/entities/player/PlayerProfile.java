package net.mikegraf.game.states.play.entities.player;

public class PlayerProfile {

    private int numLives;

    public PlayerProfile(int numLives) {
        this.numLives = numLives;
    }

    public int getNumLives() {
        return numLives;
    }

    public void die() {
        numLives--;
    }
}
