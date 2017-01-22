package net.mikegraf.game.states.play.entities.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import net.mikegraf.game.main.helpers.Box2dHelper;

public class AnimationView implements IView {

    private AnimationIndex animationIndex;
    private float height;
    private float width;
    private Vector2 renderVector;
    private TextureRegion region;

    public AnimationView(AnimationIndex animationIndex) {
        this.animationIndex = animationIndex;
        this.renderVector = new Vector2();

        this.region = animationIndex.getKeyFrame(0);
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
    public void render(SpriteBatch batch, float x, float y, float scale) {
    	batch.draw(region, x, y, width * scale, height * scale); 
    }
    
    @Override
    public void render(SpriteBatch batch, float x, float y) {
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
