package net.mikegraf.game.states.gameOver;

import java.io.IOException;
import java.util.HashMap;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import net.mikegraf.game.exceptions.ConfigFormatException;
import net.mikegraf.game.menus.FontFactory;
import net.mikegraf.game.menus.FontLoader;
import net.mikegraf.game.menus.IFontLoader;
import net.mikegraf.game.parsers.FontParser;
import net.mikegraf.game.parsers.Parser;
import net.mikegraf.game.parsers.models.FontData;

public class GameOverModule extends AbstractModule {

    private static final String FONT_DEF_PATH = "xml/fontDef.xml";

    private HashMap<String, FontData> fontDataMap;

    @Override
    protected void configure() {
        bind(FontFactory.class).asEagerSingleton();
        bind(IFontLoader.class).to(FontLoader.class);
    }

    @Provides
    public HashMap<String, FontData> providesStringToFontDataMap() {
        if (fontDataMap == null) {
            FontParser fontParser = new FontParser();
            fontDataMap = parseConfigFile(fontParser, FONT_DEF_PATH);
        }
        return fontDataMap;
    }

    private <T> T parseConfigFile(Parser<T> parser, String path) {
        T data = null;
        try {
            data = parser.parseFile(path);
        } catch (ConfigFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
