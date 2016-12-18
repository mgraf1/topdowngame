package net.mikegraf.game.audio;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.google.inject.Inject;

import net.mikegraf.game.parsers.models.SoundData;
import net.mikegraf.game.parsers.models.SoundEffectData;

public class SoundEffectFactory {

    private HashMap<String, SoundData> typeToSoundDataMap;

    @Inject
    public SoundEffectFactory(HashMap<String, SoundData> typeToSoundDataMap) {
        this.typeToSoundDataMap = typeToSoundDataMap;
    }

    public SoundEffectIndex createSoundEffectIndex(String type) {
        HashMap<String, Sound> soundEffectNametoSoundMap = new HashMap<String, Sound>();
        SoundData data = typeToSoundDataMap.get(type);

        if (data != null) {
            for (SoundEffectData effectData : data.getSoundEffectData()) {
                Sound sound = Gdx.audio.newSound(Gdx.files.internal(effectData.getPath()));
                soundEffectNametoSoundMap.put(effectData.getName(), sound);
            }
            return new SoundEffectIndex(soundEffectNametoSoundMap);
        } else {
            return null;
        }
    }
}
