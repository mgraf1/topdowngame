package net.mikegraf.game.parsers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.XmlReader.Element;

import net.mikegraf.game.exceptions.ConfigFormatException;
import net.mikegraf.game.parsers.models.SoundData;
import net.mikegraf.game.parsers.models.SoundEffectData;

/* Responsible for parsing the Sound Definition file. */
public class SoundParser extends Parser<HashMap<String, SoundData>> {

    // Constants.
    private static final String A_SOUNDEFFECTPATH = "soundEffectPath";
    private static final String T_OBJECTWSOUND = "objectWithSound";
    private static final String T_OBJECTWSOUND_T_TYPE = "type";
    private static final String T_OBJECTWSOUND_T_SOUNDS = "sounds";
    private static final String T_OBJECTWSOUND_T_SOUNDS_T_SOUND_T_NAME = "name";
    private static final String T_OBJECTWSOUND_T_SOUNDS_T_SOUND_T_PATH = "path";

    @Override
    protected HashMap<String, SoundData> handleParsing(Element root) throws IOException, ConfigFormatException {

        HashMap<String, SoundData> retVal = new HashMap<String, SoundData>();

        String soundEffectFolder;
        try {
            soundEffectFolder = root.getAttribute(A_SOUNDEFFECTPATH);
        } catch (GdxRuntimeException gre) {
            throw new ConfigFormatException("Sound effect must have" + " a file-wide soundEffectPath attribute");
        }

        Array<Element> soundTags = root.getChildrenByName(T_OBJECTWSOUND);
        for (Element sound : soundTags) {

            String type = parseElement(sound, T_OBJECTWSOUND_T_TYPE, true);

            List<SoundEffectData> soundEffectData = null;
            Element soundEffects = sound.getChildByName(T_OBJECTWSOUND_T_SOUNDS);
            if (soundEffects != null) {
                soundEffectData = new ArrayList<SoundEffectData>();
                int numSounds = soundEffects.getChildCount();
                for (int i = 0; i < numSounds; i++) {
                    Element soundEffect = soundEffects.getChild(i);
                    String name = parseElement(soundEffect, T_OBJECTWSOUND_T_SOUNDS_T_SOUND_T_NAME, true);
                    String path = parseElement(soundEffect, T_OBJECTWSOUND_T_SOUNDS_T_SOUND_T_PATH, true);
                    String fullSoundPath = soundEffectFolder + path;
                    soundEffectData.add(new SoundEffectData(name, fullSoundPath));
                }
            }

            if (retVal.containsKey(type)) {
                throw new ConfigFormatException("Sound type : " + type + " already has a definition");
            }

            SoundData data = new SoundData(type, soundEffectData);
            retVal.put(type, data);
        }
        return retVal;
    }
}
