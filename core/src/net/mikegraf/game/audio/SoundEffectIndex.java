package net.mikegraf.game.audio;

import java.util.HashMap;

import com.badlogic.gdx.audio.Sound;

public class SoundEffectIndex {

    private HashMap<String, Sound> soundEffectNameToSoundMap;

    public SoundEffectIndex(HashMap<String, Sound> soundEffectNameToSoundMap) {
        this.soundEffectNameToSoundMap = soundEffectNameToSoundMap;
    }

    public void playSound(String soundName) {
        Sound sound = soundEffectNameToSoundMap.get(soundName);
        sound.play();
    }

    public void dispose() {
        for (String key : soundEffectNameToSoundMap.keySet()) {
            Sound sound = soundEffectNameToSoundMap.get(key);
            sound.dispose();
        }
        soundEffectNameToSoundMap.clear();
    }
}
