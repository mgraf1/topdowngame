package net.mikegraf.game.menus;

import java.util.HashMap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.google.inject.Inject;

import net.mikegraf.game.parsers.models.FontData;

public class FontFactory {

    public static final String NOVEMBER = "november";

    private HashMap<String, FontData> nameToFontDataMap;

    @Inject
    public FontFactory(HashMap<String, FontData> nameToFontDataMap) {
        this.nameToFontDataMap = nameToFontDataMap;
    }

    public BitmapFont createFont(String fontName, AssetManager assetManager) {

        if (nameToFontDataMap.containsKey(fontName)) {
            FontData data = nameToFontDataMap.get(fontName);
            return assetManager.get(data.getPath());
        } else {
            throw new IllegalArgumentException("No font " + fontName);
        }
    }

}
