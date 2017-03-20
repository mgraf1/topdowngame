package net.mikegraf.game.menus;

import java.util.HashMap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.google.inject.Inject;
import net.mikegraf.game.parsers.FontData;

public class FontLoader implements IFontLoader {

    private HashMap<String, FontData> fontDataMap;

    @Inject
    public FontLoader(HashMap<String, FontData> fontDataMap) {
        this.fontDataMap = fontDataMap;
    }

    @Override
    public void loadFontData(AssetManager assetManager) {
        for (String key : fontDataMap.keySet()) {
            FontData fontData = fontDataMap.get(key);
            assetManager.load(fontData.getPath(), BitmapFont.class);
        }
    }

}
