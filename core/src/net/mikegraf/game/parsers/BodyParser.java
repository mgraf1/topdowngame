package net.mikegraf.game.parsers;

import java.io.IOException;
import java.util.HashMap;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader.Element;

import net.mikegraf.game.exceptions.ConfigFormatException;
import net.mikegraf.game.parsers.models.BodyData;

public class BodyParser extends Parser<HashMap<String, BodyData>> {

    private final String T_ENTITY = "entity";
    private final String T_ENTITY_T_TYPE = "type";
    private final String T_ENTITY_T_DAMP = "damp";
    private final String T_ENTITY_T_BODYTYPE = "bodytype";

    @Override
    protected HashMap<String, BodyData> handleParsing(Element root) throws IOException, ConfigFormatException {
        HashMap<String, BodyData> retVal = new HashMap<String, BodyData>();

        Array<Element> entityTags = root.getChildrenByName(T_ENTITY);
        for (Element entity : entityTags) {
            String type = parseElement(entity, T_ENTITY_T_TYPE, true);
            float damp = parseFloatElement(entity, T_ENTITY_T_DAMP, true);
            String bodyType = parseElement(entity, T_ENTITY_T_BODYTYPE, true);

            if (retVal.containsKey(type)) {
                throw new ConfigFormatException("Type : " + type + " already has a definition");
            }
            BodyData data = new BodyData(type, bodyType, damp);
            retVal.put(type, data);
        }

        return retVal;
    }

}
