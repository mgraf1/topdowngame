package net.mikegraf.game.states.play.actors;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import net.mikegraf.game.parsers.models.AnimationData;
import net.mikegraf.game.parsers.models.SpriteData;

public class AnimationFactory {

    public static final float ANIMATION_FRAME_DURATION = .25f;
    public static final String DEFAULT_ANIMATION_NAME = "default";

    public AnimationIndex createSpriteAnimationIndex(SpriteData spriteData, List<AnimationData> animationData) {
        Texture texture = new Texture(Gdx.files.internal(spriteData.getTexturePath()));
        int sheetWidth = spriteData.getSheetWidth();
        int sheetHeight = spriteData.getSheetHeight();

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

    public AnimationIndex createDefaultSpriteAnimationIndex(SpriteData spriteData) {
        Texture texture = new Texture(Gdx.files.internal(spriteData.getTexturePath()));
        AnimationIndex retVal = new AnimationIndex(texture);
        TextureRegion[][] tempFrames = TextureRegion.split(texture, texture.getWidth(), texture.getHeight());
        TextureRegion[] frames = new TextureRegion[1];
        frames[0] = tempFrames[0][0];
        Animation animation = new Animation(ANIMATION_FRAME_DURATION, frames);
        retVal.add(DEFAULT_ANIMATION_NAME, animation);
        return retVal;
    }
}
