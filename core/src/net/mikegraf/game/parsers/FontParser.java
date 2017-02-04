package net.mikegraf.game.parsers;

import java.io.IOException;
import java.util.HashMap;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.XmlReader.Element;

import net.mikegraf.game.exceptions.ConfigFormatException;
import net.mikegraf.game.parsers.models.FontData;

public class FontParser extends Parser<HashMap<String, FontData>> {

    private static final String A_FONTPATH = "fontPath";
    private static final String T_FONT = "font";
    private static final String T_FONT_T_NAME = "name";
    private static final String T_FONT_T_PATH = "path";

    @Override
    protected HashMap<String, FontData> handleParsing(Element root) throws IOException, ConfigFormatException {

        String fontFolder = null;
        try {
            fontFolder = root.getAttribute(A_FONTPATH);
        } catch (GdxRuntimeException gre) {
            throw new ConfigFormatException("Font must have" + " a file-wide fontPath attribute");
        }

        HashMap<String, FontData> retVal = new HashMap<String, FontData>();

        Array<Element> fontTags = root.getChildrenByName(T_FONT);
        for (Element font : fontTags) {
            String name = parseElement(font, T_FONT_T_NAME, true);
            String path = parseElement(font, T_FONT_T_PATH, true);

            FontData data = new FontData(name, fontFolder + path);
            retVal.put(name, data);
        }
        return retVal;
    }

}
