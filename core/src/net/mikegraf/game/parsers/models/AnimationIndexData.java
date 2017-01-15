package net.mikegraf.game.parsers.models;

import java.util.List;

public class AnimationIndexData {

    private String type;
    private String texturePath;
    private int sheetWidth;
    private int sheetHeight;
    private List<AnimationData> animationData;

    public AnimationIndexData(String type, String texturePath, int sheetWidth, int sheetHeight,
            List<AnimationData> animationData) {
        this.type = type;
        this.texturePath = texturePath;
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
