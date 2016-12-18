package net.mikegraf.game.states.play.actors.gameobjects;

import net.mikegraf.game.audio.SoundEffectIndex;
import net.mikegraf.game.states.play.actors.B2DSprite;
import net.mikegraf.game.states.play.actors.IDisposableActor;

public class GameObject implements IDisposableActor {

    protected B2DSprite sprite;
    protected SoundEffectIndex soundEffectIndex;
    protected String name;

    public GameObject(B2DSprite sprite, SoundEffectIndex soundEffectIndex, String name) {
        this.name = name;
        this.soundEffectIndex = soundEffectIndex;
        this.sprite = sprite;
        this.sprite.setUserData(this);
    }

    public String getName() {
        return name;
    }

    @Override
    public void dispose() {
        if (soundEffectIndex != null) {
            soundEffectIndex.dispose();
        }
        soundEffectIndex = null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        GameObject other = (GameObject) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
}
