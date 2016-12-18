package net.mikegraf.game.parsers.models;

import java.util.List;

public class SoundData {

    private String type;
    private List<SoundEffectData> soundEffectData;

    public SoundData(String type, List<SoundEffectData> soundEffectData) {
        this.type = type;
        this.soundEffectData = soundEffectData;
    }

    public String getType() {
        return type;
    }

    public List<SoundEffectData> getSoundEffectData() {
        return soundEffectData;
    }
}
