package net.mikegraf.game.states.play.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import net.mikegraf.game.main.MyGdxGame;
import net.mikegraf.game.states.play.entities.items.Item;
import net.mikegraf.game.states.play.entities.player.Player;
import net.mikegraf.game.states.play.entities.view.AnimationIndex;

public class PlayHud {

    private static final int INVENTORY_SPACER = 5;
    private static final int HEALTH_SPACER = 10;
    private static final float LIFE_COUNTER_PLAYER_SCALE = .4f;
    private static final float LIFE_COUNTER_PLAYER_ICON_X = 10;
    private static final float LIFE_COUNTER_PLAYER_ICON_Y = 10;
    private static final float LIFE_COUNTER_TEXT_X = 25;
    private static final float LIFE_COUNTER_TEXT_Y = 25;
    private static final float HEALTH_METER_X = 0;
    private static final float HEALTH_METER_Y = 225;
    private static final String EMPTY_HEALTH_ANIMATION = "empty";
    private static final String FULL_HEALTH_ANIMATION = "full";

    private TextureRegion inventorySquareTexture;
    private float[] inventoryLocations;
    private Array<Item> playerInventory;
    private Player player;
    private BitmapFont hudFont;
    private AnimationIndex healthAnimation;

    public PlayHud(Player player, BitmapFont hudFont, TextureRegion iventoryTexture, AnimationIndex healthAnimation) {
        this.inventorySquareTexture = iventoryTexture;
        this.inventoryLocations = new float[Player.STARTING_INVENTORY_SIZE];

        for (int i = 0; i < inventoryLocations.length; i++) {
            this.inventoryLocations[i] = MyGdxGame.V_WIDTH - ((i + 1) * this.inventorySquareTexture.getRegionWidth())
                    - ((i + 1) * INVENTORY_SPACER);
        }

        this.playerInventory = player.getInventory();
        this.player = player;
        this.hudFont = hudFont;
        this.hudFont.getData().setScale(.5f);
        this.healthAnimation = healthAnimation;
    }

    public void render(SpriteBatch sb) {
        sb.begin();

        renderInventory(sb);
        renderLivesCounter(sb);
        renderHealth(sb);

        sb.end();
    }

    private void renderHealth(SpriteBatch batch) {
        int maxHealth = player.getMaxHealth();
        int currHealth = player.getCurrentHealth();

        healthAnimation.setCurrentAnimation(FULL_HEALTH_ANIMATION);
        TextureRegion region = healthAnimation.getKeyFrame(0f);
        for (int i = 0; i < currHealth; i++) {
            batch.draw(region, HEALTH_METER_X + (i * HEALTH_SPACER), HEALTH_METER_Y);
        }

        healthAnimation.setCurrentAnimation(EMPTY_HEALTH_ANIMATION);
        region = healthAnimation.getKeyFrame(0f);

        for (int i = currHealth; i < maxHealth; i++) {
            batch.draw(region, HEALTH_METER_X + (i * HEALTH_SPACER), HEALTH_METER_Y);
        }
    }

    private void renderInventory(SpriteBatch batch) {
        Color c = batch.getColor();
        for (int i = 0; i < inventoryLocations.length; i++) {
            batch.setColor(.85f, .85f, .85f, .5f);
            batch.draw(inventorySquareTexture, inventoryLocations[i], INVENTORY_SPACER);
        }
        batch.setColor(c);

        for (int i = 0; i < playerInventory.size; i++) {

            Item item = playerInventory.get(i);
            if (item != null) {
                item.renderHud(batch, inventoryLocations[i], INVENTORY_SPACER, 1f);
            }
        }
    }

    private void renderLivesCounter(SpriteBatch batch) {
        player.renderHud(batch, LIFE_COUNTER_PLAYER_ICON_X, LIFE_COUNTER_PLAYER_ICON_Y, LIFE_COUNTER_PLAYER_SCALE);
        hudFont.draw(batch, "x" + player.getNumLives(), LIFE_COUNTER_TEXT_X, LIFE_COUNTER_TEXT_Y);
    }
}
