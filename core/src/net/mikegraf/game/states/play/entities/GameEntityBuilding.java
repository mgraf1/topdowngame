package net.mikegraf.game.states.play.entities;

import com.badlogic.gdx.maps.MapLayer;
import com.google.inject.Inject;

import net.mikegraf.game.audio.SoundEffectFactory;
import net.mikegraf.game.main.constants.TiledConstants;
import net.mikegraf.game.states.play.entities.gameObjects.GameObjectFactory;
import net.mikegraf.game.states.play.entities.items.ItemFactory;
import net.mikegraf.game.states.play.entities.player.PlayerFactory;
import net.mikegraf.game.states.play.entities.triggers.TriggerFactory;

public class GameEntityBuilding {

    private BehaviorBuilding behaviorBuilding;
    private SoundEffectFactory soundEffectFactory;

    @Inject
    public GameEntityBuilding(BehaviorBuilding behaviorBuilding, SoundEffectFactory soundEffectFactory) {
        this.behaviorBuilding = behaviorBuilding;
        this.soundEffectFactory = soundEffectFactory;
    }

    public GameEntityFactory getGameEntityFactory(MapLayer layer) {
        String layerName = layer.getName();
        BehaviorFactory behaviorFactory = behaviorBuilding.getBehaviorFactory(layerName);

        if (layerName.equals(TiledConstants.LAYER_TRIGGER)) {
            return new TriggerFactory(behaviorFactory);
        } else if (layerName.equals(TiledConstants.LAYER_ITEM)) {
            return new ItemFactory(behaviorFactory);
        } else if (layerName.equals(TiledConstants.LAYER_OBJECT)) {
            return new GameObjectFactory(behaviorFactory, soundEffectFactory);
        } else if (layerName.equals(TiledConstants.LAYER_PLAYER)) {
            return new PlayerFactory(behaviorFactory, soundEffectFactory);
        } else {
            throw new IllegalArgumentException("No layer named: " + layerName);
        }
    }
}
