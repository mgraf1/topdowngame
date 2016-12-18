package net.mikegraf.game.states.play.actors;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

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

    public void dispose(World world) {
        sprite.dispose(world);
    }

    public void renderHud(SpriteBatch sb, float x, float y) {
        sb.draw(texture, x, y);
    }

    public String getType() {
        return type;
    }
}
