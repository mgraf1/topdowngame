package net.mikegraf.game.states.play.entities.view;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.google.inject.Inject;

import net.mikegraf.game.main.ShaderManager;
import net.mikegraf.game.parsers.AnimationData;
import net.mikegraf.game.parsers.AnimationIndexData;

import java.util.HashMap;
import java.util.List;

public class AnimationFactory {

    public static final float ANIMATION_FRAME_DURATION = .25f;
    public static final String DEFAULT_ANIMATION_NAME = "default";

    public static final String SHADER_FLASH = "flash";

    private HashMap<String, AnimationIndexData> typeToAnimationIndexDataMap;

    @Inject
    public AnimationFactory(HashMap<String, AnimationIndexData> typeToAnimationIndexDataMap) {
        this.typeToAnimationIndexDataMap = typeToAnimationIndexDataMap;
    }

    public AnimationIndex createAnimationIndex(String type, AssetManager assetManager) {
        AnimationIndexData data = typeToAnimationIndexDataMap.get(type);
        List<AnimationData> animationData = data.getAnimationData();
        if (animationData == null) {
            return createDefaultSpriteAnimationIndex(data, assetManager);
        } else {
            return createSpriteAnimationIndex(data, animationData, assetManager);
        }
    }

    public ShaderProgram createShader(String type) {
        if (type.equals(SHADER_FLASH)) {
            return ShaderManager.getFlashShader();
        }

        throw new IllegalArgumentException("No shader: " + type);
    }

    private AnimationIndex createSpriteAnimationIndex(AnimationIndexData indexData, List<AnimationData> animationData,
            AssetManager assetManager) {
        Texture texture = assetManager.get(indexData.getTexturePath(), Texture.class);
        int sheetWidth = indexData.getSheetWidth();
        int sheetHeight = indexData.getSheetHeight();

        TextureRegion[][] tempFrames = TextureRegion.split(texture, texture.getWidth() / sheetWidth,
                texture.getHeight() / sheetHeight);

        AnimationIndex retVal = new AnimationIndex(texture);
        for (AnimationData data : animationData) {
            int startingFrame = data.getStartingFrame();
            int endingFrame = data.getEndingFrame();
            int numFrames = endingFrame - startingFrame + 1;
            TextureRegion[] frames = new TextureRegion[numFrames];

            for (int i = startingFrame, numFrame = 0; i <= endingFrame; i++, numFrame++) {
                int rowNum = i / sheetWidth;
                frames[numFrame] = tempFrames[rowNum][i - (rowNum * sheetWidth)];
            }

            Animation animation = new Animation(ANIMATION_FRAME_DURATION, frames);
            retVal.add(data.getName(), animation);
        }
        return retVal;
    }

    private AnimationIndex createDefaultSpriteAnimationIndex(AnimationIndexData indexData, AssetManager assetManager) {
        Texture texture = assetManager.get(indexData.getTexturePath(), Texture.class);
        AnimationIndex retVal = new AnimationIndex(texture);
        TextureRegion[][] tempFrames = TextureRegion.split(texture, texture.getWidth(), texture.getHeight());
        TextureRegion[] frames = new TextureRegion[1];
        frames[0] = tempFrames[0][0];
        Animation animation = new Animation(ANIMATION_FRAME_DURATION, frames);
        retVal.add(DEFAULT_ANIMATION_NAME, animation);
        return retVal;
    }
}
