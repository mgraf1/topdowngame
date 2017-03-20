package net.mikegraf.game.audio;

import java.util.HashMap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.google.inject.Inject;
import net.mikegraf.game.parsers.SoundData;
import net.mikegraf.game.parsers.SoundEffectData;

public class SoundEffectFactory {

    private HashMap<String, SoundData> typeToSoundDataMap;

    @Inject
    public SoundEffectFactory(HashMap<String, SoundData> typeToSoundDataMap) {
        this.typeToSoundDataMap = typeToSoundDataMap;
    }

    public SoundEffectIndex createSoundEffectIndex(String type, AssetManager assetManager) {
        HashMap<String, Sound> soundEffectNameToSoundMap = new HashMap<String, Sound>();
        SoundData data = typeToSoundDataMap.get(type);

        if (data != null) {
            for (SoundEffectData effectData : data.getSoundEffectData()) {
                Sound sound = assetManager.get(effectData.getPath(), Sound.class);
                soundEffectNameToSoundMap.put(effectData.getName(), sound);
            }
            return new SoundEffectIndex(soundEffectNameToSoundMap);
        } else {
            return null;
        }
    }
}
