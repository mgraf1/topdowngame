package net.mikegraf.game.states.play.levels;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import net.mikegraf.game.main.MyGdxGame;
import net.mikegraf.game.states.play.actors.Item;
import net.mikegraf.game.states.play.actors.Player;

public class PlayHud {

    private static final int INVENTORY_SPACER = 5;

    private TextureRegion inventorySquareTexture;
    private float[] inventoryLocations;
    private Array<Item> playerInventory;

    public PlayHud(TextureRegion iventoryTexture, Player player) {

        inventorySquareTexture = iventoryTexture;
        inventoryLocations = new float[Player.STARTING_INVENTORY_SIZE];

        for (int i = 0; i < inventoryLocations.length; i++) {
            inventoryLocations[i] = MyGdxGame.V_WIDTH - ((i + 1) * inventorySquareTexture.getRegionWidth())
                    - ((i + 1) * INVENTORY_SPACER);
        }

        playerInventory = player.getInventory();
    }

    public void render(SpriteBatch sb) {
        sb.begin();
        Color c = sb.getColor();
        for (int i = 0; i < inventoryLocations.length; i++) {
            sb.setColor(.85f, .85f, .85f, .5f);
            sb.draw(inventorySquareTexture, inventoryLocations[i], INVENTORY_SPACER);
        }
        sb.setColor(c);

        for (int i = 0; i < playerInventory.size; i++) {

            Item item = playerInventory.get(i);
            if (item != null)
                item.renderHud(sb, inventoryLocations[i], INVENTORY_SPACER);
        }

        sb.end();
    }
}
