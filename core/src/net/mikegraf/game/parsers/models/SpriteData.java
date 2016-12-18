package net.mikegraf.game.parsers.models;

import java.util.List;

public class SpriteData {

    // Instance variables.
    private String type;
    private String texturePath;
    private String bodyType;
    private float damp;
    private int sheetWidth;
    private int sheetHeight;
    private List<AnimationData> animationData;

    public SpriteData(String type, String texturePath, String bodyType, float damp, int sheetWidth, int sheetHeight,
            List<AnimationData> animationData) {
        this.type = type;
        this.texturePath = texturePath;
        this.bodyType = bodyType;
        this.damp = damp;
        this.sheetWidth = sheetWidth;
        this.sheetHeight = sheetHeight;
        this.animationData = animationData;
    }

    public String getType() {
        return type;
    }

    public String getTexturePath() {
        return texturePath;
    }

    public float getDamp() {
        return damp;
    }

    public String getBodyType() {
        return bodyType;
    }

    public int getSheetWidth() {
        return sheetWidth;
    }

    public int getSheetHeight() {
        return sheetHeight;
    }

    public List<AnimationData> getAnimationData() {
        return animationData;
    }
}
