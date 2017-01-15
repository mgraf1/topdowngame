package net.mikegraf.game.parsers.models;

public class BodyData {

    private String type;
    private String bodyType;
    private float damp;

    public BodyData(String type, String bodyType, float damp) {
        this.type = type;
        this.bodyType = bodyType;
        this.damp = damp;
    }

    public String getType() {
        return type;
    }

    public String getBodyType() {
        return bodyType;
    }

    public float getDamp() {
        return damp;
    }
}
