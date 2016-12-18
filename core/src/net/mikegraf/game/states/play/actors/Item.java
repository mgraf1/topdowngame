package net.mikegraf.game.states.play.actors;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Item {

    private TextureRegion texture;
    private String type;
    private B2DSprite sprite;

    public Item(B2DSprite b2dSprite, String itemType) {
        sprite = b2dSprite;
        sprite.setUserData(this);
        texture = sprite.getTextureRegion();
        type = itemType;
    }

    public void prepareForDisposal() {
        sprite.readyForDisposal = true;
        sprite = null;
    }

    public void renderHud(SpriteBatch sb, float x, float y) {
        sb.draw(texture, x, y);
    }

    public String getType() {
        return type;
    }
}
