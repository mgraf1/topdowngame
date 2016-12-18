package net.mikegraf.game.parsers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.XmlReader.Element;

import net.mikegraf.game.exceptions.ConfigFormatException;
import net.mikegraf.game.parsers.models.AnimationData;
import net.mikegraf.game.parsers.models.SpriteData;

/* Responsible for parsing the Sprite Definition file. */
public class SpriteParser extends Parser<HashMap<String, SpriteData>> {

    // Constants.
    private final String A_TEXTUREPATH = "texturePath";
    private final String T_SPRITE = "sprite";
    private final String T_SPRITE_T_TYPE = "type";
    private final String T_SPRITE_T_TEXTUREPATH = "texturePath";
    private final String T_SPRITE_T_DAMP = "damp";
    private final String T_SPRITE_T_SHEETWIDTH = "sheetWidth";
    private final String T_SPRITE_T_SHEETHEIGHT = "sheetHeight";
    private final String T_SPRITE_T_BODYTYPE = "bodytype";
    private final String T_SPRITE_T_ANIMATIONS = "animations";
    private final String T_SPRITE_T_ANIMATIONS_T_ANIMATION_T_NAME = "name";
    private final String T_SPRITE_T_ANIMATIONS_T_ANIMATION_T_STARTINGFRAME = "startingFrame";
    private final String T_SPRITE_T_ANIMATIONS_T_ANIMATION_T_ENDINGFRAME = "endingFrame";

    @Override
    protected HashMap<String, SpriteData> handleParsing(Element root) throws IOException, ConfigFormatException {

        HashMap<String, SpriteData> retVal = new HashMap<String, SpriteData>();

        // Get the path of the folder all sprite textures reside in.
        String textureFolder;
        try {
            textureFolder = root.getAttribute(A_TEXTUREPATH);
        } catch (GdxRuntimeException gre) {
            throw new ConfigFormatException("Sprite must have" + " a file-wide texturePath attribute");
        }

        Array<Element> spriteTags = root.getChildrenByName(T_SPRITE);
        for (Element sprite : spriteTags) {

            String spriteType = parseElement(sprite, T_SPRITE_T_TYPE, true);
            String spriteTexture = parseElement(sprite, T_SPRITE_T_TEXTUREPATH, true);
            String spriteBodytype = parseElement(sprite, T_SPRITE_T_BODYTYPE, true);
            float spriteDamp = parseFloatElement(sprite, T_SPRITE_T_DAMP, true);
            int spriteSheetWidth = parseIntElement(sprite, T_SPRITE_T_SHEETWIDTH, true);
            int spriteSheetHeight = parseIntElement(sprite, T_SPRITE_T_SHEETHEIGHT, true);

            List<AnimationData> animationData = null;
            Element animations = sprite.getChildByName(T_SPRITE_T_ANIMATIONS);
            if (animations != null) {
                animationData = new ArrayList<AnimationData>();
                int numAnimations = animations.getChildCount();
                for (int i = 0; i < numAnimations; i++) {
                    Element animation = animations.getChild(i);
                    String animationName = parseElement(animation, T_SPRITE_T_ANIMATIONS_T_ANIMATION_T_NAME, true);
                    int startingFrame = parseIntElement(animation, T_SPRITE_T_ANIMATIONS_T_ANIMATION_T_STARTINGFRAME,
                            true);
                    int endingFrame = parseIntElement(animation, T_SPRITE_T_ANIMATIONS_T_ANIMATION_T_ENDINGFRAME, true);
                    animationData.add(new AnimationData(animationName, startingFrame, endingFrame));
                }
            }

            if (retVal.containsKey(spriteType)) {
                throw new ConfigFormatException("Sprite type : " + spriteType + " already has a definition");
            }

            String fullTexturePath = textureFolder + spriteTexture;
            SpriteData data = new SpriteData(spriteType, fullTexturePath, spriteBodytype, spriteDamp, spriteSheetWidth,
                    spriteSheetHeight, animationData);
            retVal.put(spriteType, data);
        }
        return retVal;
    }
}
