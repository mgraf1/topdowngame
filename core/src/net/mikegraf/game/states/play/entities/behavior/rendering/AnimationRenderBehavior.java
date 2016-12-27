package net.mikegraf.game.states.play.entities.behavior.rendering;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class AnimationRenderBehavior implements IRenderBehavior {

    private AnimationIndex animationIndex;

    public AnimationRenderBehavior(AnimationIndex animationIndex) {
        this.animationIndex = animationIndex;
    }

    @Override
    public void render(SpriteBatch batch, float totalTime, Vector2 position) {
        TextureRegion region = animationIndex.getKeyFrame(totalTime);
        batch.draw(region, position.x, position.y);
    }

    @Override
    public void setMode(String mode) {
        animationIndex.setCurrentAnimation(mode);
    }

    @Override
    public void dispose() {
        animationIndex.dispose();
    }
}
