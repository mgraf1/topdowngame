package net.mikegraf.game.states.play.entities.behavior.rendering;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import net.mikegraf.game.main.helpers.Box2dHelper;

public class AnimationRenderBehavior implements IRenderBehavior {

    private AnimationIndex animationIndex;
    private float height;
    private float width;
    private Vector2 renderVector;

    public AnimationRenderBehavior(AnimationIndex animationIndex) {
        this.animationIndex = animationIndex;
        this.renderVector = new Vector2();

        TextureRegion region = animationIndex.getKeyFrame(0);
        this.height = region.getRegionHeight();
        this.width = region.getRegionWidth();
    }

    @Override
    public void render(SpriteBatch batch, float totalTime, Vector2 position) {
        Box2dHelper.toRenderCoords(renderVector, position, width, height);
        TextureRegion region = animationIndex.getKeyFrame(totalTime);
        batch.begin();
        batch.draw(region, renderVector.x, renderVector.y);
        batch.end();
    }
    
    @Override
    public void render(SpriteBatch batch, float x, float y) {
    	TextureRegion region = animationIndex.getKeyFrame(0);
        batch.draw(region, x, y);
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
