package net.mikegraf.game.states.play.actors;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationIndex {

    private HashMap<String, Animation> index;
    private String currentAnimationName;

    public AnimationIndex() {
        index = new HashMap<String, Animation>();
    }

    public void add(String name, Animation animation) {
        index.put(name, animation);
        if (currentAnimationName == null) {
            currentAnimationName = name;
        }
    }

    public TextureRegion getKeyFrame(float totalTime) {
        return index.get(currentAnimationName).getKeyFrame(totalTime, true);
    }

    public void setCurrentAnimation(String name) {
        currentAnimationName = name;
    }
}
