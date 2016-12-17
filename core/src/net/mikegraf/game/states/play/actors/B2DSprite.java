package net.mikegraf.game.states.play.actors;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import net.mikegraf.game.states.play.levels.B2DVars;

public class B2DSprite {

    private Body body;
    private AnimationIndex animation;
    private float width;
    private float height;
    private Object userData;
    private boolean readyForDisposal;
    private boolean readyForHiding;

    public B2DSprite(AnimationIndex animation, Body body) {
        this.animation = animation;
        this.body = body;
        this.body.setUserData(this);

        TextureRegion region = animation.getKeyFrame(0);
        this.width = region.getRegionWidth();
        this.height = region.getRegionHeight();
        this.readyForDisposal = false;
        this.readyForHiding = false;
    }

    public void render(SpriteBatch sb, float totalTime) {
        if (body.isActive()) {
            Vector2 position = body.getPosition();
            sb.begin();
            sb.draw(animation.getKeyFrame(totalTime), position.x * B2DVars.PPM - width / 2,
                    position.y * B2DVars.PPM - height / 2);
            sb.end();
        }
    }

    public void hide() {
        readyForHiding = true;
    }

    public void show() {
        readyForHiding = false;
    }

    public void setAnimation(String animationName) {
        animation.setCurrentAnimation(animationName);
    }

    public Vector2 getPosition() {
        return body.getPosition();
    }

    public void move(float x, float y) {
        body.setLinearVelocity(x, y);
    }

    public void setUserData(Object obj) {
        userData = obj;
    }

    public Object getUserData() {
        return userData;
    }

    public TextureRegion getTextureRegion() {
        return animation.getKeyFrame(0);
    }

    public boolean isReadyForDisposal() {
        return readyForDisposal;
    }

    public boolean isReadyForHiding() {
        return readyForHiding;
    }

    public void prepareForDisposal() {
        readyForDisposal = true;
    }

    public boolean isHidden() {
        return !body.isActive();
    }
}
