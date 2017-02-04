package net.mikegraf.game.states.play.levels;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import net.mikegraf.game.main.MyGdxGame;
import net.mikegraf.game.menus.FontFactory;
import net.mikegraf.game.states.play.entities.items.Item;
import net.mikegraf.game.states.play.entities.player.Player;

public class PlayHud {

    private static final int INVENTORY_SPACER = 5;
    private static final float LIFE_COUNTER_PLAYER_SCALE = .4f;
    private static final float LIFE_COUNTER_PLAYER_ICON_X = 10;
    private static final float LIFE_COUNTER_PLAYER_ICON_Y = 10;
    private static final float LIFE_COUNTER_TEXT_X = 25;
    private static final float LIFE_COUNTER_TEXT_Y = 25;

    private TextureRegion inventorySquareTexture;
    private float[] inventoryLocations;
    private Array<Item> playerInventory;
    private Player player;
    private BitmapFont hudFont;

    public PlayHud(TextureRegion iventoryTexture, Player player, FontFactory fontFactory, AssetManager assetManager) {
        this.inventorySquareTexture = iventoryTexture;
        this.inventoryLocations = new float[Player.STARTING_INVENTORY_SIZE];

        for (int i = 0; i < inventoryLocations.length; i++) {
            this.inventoryLocations[i] = MyGdxGame.V_WIDTH - ((i + 1) * this.inventorySquareTexture.getRegionWidth())
                    - ((i + 1) * INVENTORY_SPACER);
        }

        this.playerInventory = player.getInventory();
        this.player = player;
        this.hudFont = fontFactory.createFont(FontFactory.NOVEMBER, assetManager);
        this.hudFont.getData().setScale(.5f);
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
            if (item != null) {
                item.renderHud(sb, inventoryLocations[i], INVENTORY_SPACER, 1f);
            }
        }

        renderLivesCounter(sb);

        sb.end();
    }

    private void renderLivesCounter(SpriteBatch batch) {
        player.renderHud(batch, LIFE_COUNTER_PLAYER_ICON_X, LIFE_COUNTER_PLAYER_ICON_Y, LIFE_COUNTER_PLAYER_SCALE);
        hudFont.draw(batch, "x" + player.getNumLives(), LIFE_COUNTER_TEXT_X, LIFE_COUNTER_TEXT_Y);
    }
}
