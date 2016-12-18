package net.mikegraf.game.parsers.models;

/* Represents the data necessary to instantiate a level */
public class LevelData {

    // Instance variables.
    private String name;
    private String fileName;

    public LevelData(String name, String fileName) {
        this.name = name;
        this.fileName = fileName;
    }

    public String getName() {
        return name;
    }

    public String getFileName() {
        return fileName;
    }
}
