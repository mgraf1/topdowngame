package net.mikegraf.game.parsers.models;

public class BodyData {

    private String type;
    private String bodyType;
    private float damp;
    private float velocity;

    public BodyData(String type, String bodyType, float damp, float velocity) {
        this.type = type;
        this.bodyType = bodyType;
        this.damp = damp;
        this.velocity = velocity;
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

    public float getVelocity() {
        return velocity;
    }
}
