package net.mikegraf.game.parsers.models;

public class FontData {

    private String name;
    private String path;

    public FontData(String name, String path) {
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
