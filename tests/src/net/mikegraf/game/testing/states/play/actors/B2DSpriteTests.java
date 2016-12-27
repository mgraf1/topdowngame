package net.mikegraf.game.testing.states.play.actors;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import net.mikegraf.game.states.play.actors.B2DSprite;
import net.mikegraf.game.states.play.entities.behavior.rendering.AnimationIndex;

@RunWith(net.mikegraf.game.testing.GdxTestRunner.class)
public class B2DSpriteTests {

    private AnimationIndex animationIndex;
    private TextureRegion textureRegion;
    private Body body;
    private B2DSprite sprite;

    @Before
    public void myBefore() {
        textureRegion = mock(TextureRegion.class);
        when(textureRegion.getRegionWidth()).thenReturn(0);
        when(textureRegion.getRegionHeight()).thenReturn(0);
        animationIndex = mock(AnimationIndex.class);
        when(animationIndex.getKeyFrame(0f)).thenReturn(textureRegion);
        body = mock(Body.class);
        when(body.isActive()).thenReturn(true);
        sprite = new B2DSprite(animationIndex, body);
    }

    @Test
    public void renderSpriteBatchBeginCalled() {
        when(body.getPosition()).thenReturn(new Vector2(0, 0));
        SpriteBatch batch = mock(SpriteBatch.class);

        sprite.render(batch, 0f);

        verify(batch, times(1)).begin();
    }

    @Test
    public void renderSpriteBatchEndCalled() {
        when(body.getPosition()).thenReturn(new Vector2(0, 0));
        SpriteBatch batch = mock(SpriteBatch.class);

        sprite.render(batch, 0f);

        verify(batch, times(1)).end();
    }

    @Test
    public void renderSpriteBatchDrawCalled() {
        when(body.getPosition()).thenReturn(new Vector2(0, 0));
        SpriteBatch batch = mock(SpriteBatch.class);

        sprite.render(batch, 0f);

        verify(batch, times(1)).draw(textureRegion, 0, 0);
    }

    @Test
    public void moveBodySetLinearVelocityCalled() {
        sprite.move(0f, 0f);

        verify(body, times(1)).setLinearVelocity(0.0f, 0.0f);
    }

    @Test
    public void setAnimationOnIndexCalled() {
        String animationName = "TestName";
        sprite.setAnimation(animationName);

        verify(animationIndex, times(1)).setCurrentAnimation(animationName);
    }
}
