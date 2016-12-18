package net.mikegraf.game.parsers.models;

public class SoundEffectData {

    private String name;
    private String path;

    public SoundEffectData(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }
}
