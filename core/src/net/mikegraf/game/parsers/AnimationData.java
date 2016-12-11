package net.mikegraf.game.parsers;

public class AnimationData {

    private String name;
    private int startingFrame;
    private int endingFrame;

    public AnimationData(String name, int startingFrame, int endingFrame) {
        this.name = name;
        this.startingFrame = startingFrame;
        this.endingFrame = endingFrame;
    }

    public String getName() {
        return name;
    }

    public int getStartingFrame() {
        return startingFrame;
    }

    public int getEndingFrame() {
        return endingFrame;
    }
}
