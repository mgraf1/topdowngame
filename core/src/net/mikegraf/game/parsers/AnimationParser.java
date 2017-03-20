package net.mikegraf.game.parsers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.XmlReader.Element;

import net.mikegraf.game.exceptions.ConfigFormatException;

public class AnimationParser extends Parser<HashMap<String, AnimationIndexData>> {

    private final String A_TEXTUREPATH = "texturePath";
    private final String T_ENTITY = "entity";
    private final String T_ENTITY_T_TYPE = "type";
    private final String T_ENTITY_T_TEXTUREPATH = "texturePath";
    private final String T_ENTITY_T_SHEETWIDTH = "sheetWidth";
    private final String T_ENTITY_T_SHEETHEIGHT = "sheetHeight";
    private final String T_ENTITY_T_ANIMATIONS = "animations";
    private final String T_ENTITY_T_ANIMATIONS_T_ANIMATION_T_NAME = "name";
    private final String T_ENTITY_T_ANIMATIONS_T_ANIMATION_T_STARTINGFRAME = "startingFrame";
    private final String T_ENTITY_T_ANIMATIONS_T_ANIMATION_T_ENDINGFRAME = "endingFrame";

    @Override
    protected HashMap<String, AnimationIndexData> handleParsing(Element root)
            throws IOException, ConfigFormatException {
        HashMap<String, AnimationIndexData> retVal = new HashMap<String, AnimationIndexData>();

        String textureFolder;
        try {
            textureFolder = root.getAttribute(A_TEXTUREPATH);
        } catch (GdxRuntimeException gre) {
            throw new ConfigFormatException("Animation must have" + " a file-wide texturePath attribute");
        }

        Array<Element> entityTags = root.getChildrenByName(T_ENTITY);
        for (Element entity : entityTags) {

            String type = parseElement(entity, T_ENTITY_T_TYPE, true);
            String texture = parseElement(entity, T_ENTITY_T_TEXTUREPATH, true);
            int sheetWidth = parseIntElement(entity, T_ENTITY_T_SHEETWIDTH, true);
            int sheetHeight = parseIntElement(entity, T_ENTITY_T_SHEETHEIGHT, true);

            List<AnimationData> animationData = null;
            Element animations = entity.getChildByName(T_ENTITY_T_ANIMATIONS);
            if (animations != null) {
                animationData = new ArrayList<AnimationData>();
                int numAnimations = animations.getChildCount();
                for (int i = 0; i < numAnimations; i++) {
                    Element animation = animations.getChild(i);
                    String animationName = parseElement(animation, T_ENTITY_T_ANIMATIONS_T_ANIMATION_T_NAME, true);
                    int startingFrame = parseIntElement(animation, T_ENTITY_T_ANIMATIONS_T_ANIMATION_T_STARTINGFRAME,
                            true);
                    int endingFrame = parseIntElement(animation, T_ENTITY_T_ANIMATIONS_T_ANIMATION_T_ENDINGFRAME, true);
                    animationData.add(new AnimationData(animationName, startingFrame, endingFrame));
                }
            }

            if (retVal.containsKey(type)) {
                throw new ConfigFormatException("Type : " + type + " already has a definition");
            }

            String fullTexturePath = textureFolder + texture;
            AnimationIndexData data = new AnimationIndexData(type, fullTexturePath, sheetWidth, sheetHeight,
                    animationData);
            retVal.put(type, data);
        }
        return retVal;
    }

}
