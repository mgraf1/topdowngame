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
    private final String T_ENTITY_T_BODYTYPE = "bodytype";
    private final String T_ENTITY_T_DAMP = "damp";
    private final String T_ENTITY_T_VELOCITY = "velocity";

    @Override
    protected HashMap<String, BodyData> handleParsing(Element root) throws IOException, ConfigFormatException {
        HashMap<String, BodyData> retVal = new HashMap<String, BodyData>();

        Array<Element> entityTags = root.getChildrenByName(T_ENTITY);
        for (Element entity : entityTags) {
            String type = parseElement(entity, T_ENTITY_T_TYPE, true);
            String bodyType = parseElement(entity, T_ENTITY_T_BODYTYPE, true);
            float damp = parseFloatElement(entity, T_ENTITY_T_DAMP, true);
            float velocity = parseFloatElement(entity, T_ENTITY_T_VELOCITY, true);

            if (retVal.containsKey(type)) {
                throw new ConfigFormatException("Type : " + type + " already has a definition");
            }
            BodyData data = new BodyData(type, bodyType, damp, velocity);
            retVal.put(type, data);
        }

        return retVal;
    }

}
