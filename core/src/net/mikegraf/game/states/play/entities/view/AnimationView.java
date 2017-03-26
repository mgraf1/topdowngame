package net.mikegraf.game.states.play.entities.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;

import net.mikegraf.game.main.helpers.Box2dHelper;

public class AnimationView implements IView {

    private static final String FLASH_MODE = "flash";
    private static final float FLASH_TIME = .5f;
    private static final float FLASH_FREQUENCY = .5f;

    private AnimationIndex animationIndex;
    private float height;
    private float width;
    private Vector2 renderVector;
    private TextureRegion region;
    private ShaderProgram flashShader;
    private float flashTime;
    private boolean isFlashing;

    public AnimationView(AnimationIndex animationIndex, ShaderProgram flashShader) {
        this.animationIndex = animationIndex;
        this.renderVector = new Vector2();
        this.region = animationIndex.getKeyFrame(0);
        this.height = region.getRegionHeight();
        this.width = region.getRegionWidth();
        this.flashShader = flashShader;
        this.flashTime = 0f;
        this.isFlashing = false;
    }

    @Override
    public void render(SpriteBatch batch, float totalTime, Vector2 position) {
        Box2dHelper.toRenderCoords(renderVector, position, width, height);
        TextureRegion region = animationIndex.getKeyFrame(totalTime);
        batch.begin();

        if (isFlashing) {
            performFlash(batch, totalTime);
        }
        batch.draw(region, renderVector.x, renderVector.y);

        batch.setShader(null);
        batch.end();
    }

    private void performFlash(SpriteBatch batch, float totalTime) {
        batch.setShader(flashShader);
        float flashAmount = pulse(flashTime);
        if (flashTime < FLASH_TIME) {
            flashShader.setUniformf("u_whiteness", flashAmount);
            flashTime += totalTime;
        } else {
            isFlashing = false;
            flashTime = 0f;
        }
    }

    // Gets a value of a sin curve between -1 <-> 1
    private float pulse(float time) {
        float pi = 3.14f;
        return 0.5f * (1 + (float) (Math.sin(2 * pi * FLASH_FREQUENCY * (time + .25f))));
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
        if (mode.equals(FLASH_MODE)) {
            isFlashing = true;
        } else {
            animationIndex.setCurrentAnimation(mode);
        }
    }

    @Override
    public void dispose() {
        animationIndex.dispose();
    }
}
