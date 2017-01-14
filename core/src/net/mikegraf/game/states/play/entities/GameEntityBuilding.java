package net.mikegraf.game.states.play.entities;

import com.badlogic.gdx.maps.MapLayer;
import com.google.inject.Inject;

import net.mikegraf.game.audio.SoundEffectFactory;
import net.mikegraf.game.main.constants.TiledConstants;
import net.mikegraf.game.states.play.entities.bodies.BodyBuilding;
import net.mikegraf.game.states.play.entities.bodies.BodyFactory;
import net.mikegraf.game.states.play.entities.gameObjects.GameObjectFactory;
import net.mikegraf.game.states.play.entities.items.ItemFactory;
import net.mikegraf.game.states.play.entities.player.PlayerFactory;
import net.mikegraf.game.states.play.entities.triggers.TriggerFactory;

public class GameEntityBuilding {

    private BehaviorBuilding behaviorBuilding;
    private BodyBuilding bodyBuilding;
    private SoundEffectFactory soundEffectFactory;

    @Inject
    public GameEntityBuilding(BehaviorBuilding behaviorBuilding, BodyBuilding bodyBuilding,
            SoundEffectFactory soundEffectFactory) {
        this.behaviorBuilding = behaviorBuilding;
        this.bodyBuilding = bodyBuilding;
        this.soundEffectFactory = soundEffectFactory;
    }

    public GameEntityFactory getGameEntityFactory(MapLayer layer) {
        String layerName = layer.getName();
        BehaviorFactory behaviorFactory = behaviorBuilding.getBehaviorFactory(layerName);
        BodyFactory bodyFactory = bodyBuilding.getBodyFactory(layerName);

        if (layerName.equals(TiledConstants.LAYER_TRIGGER)) {
            return new TriggerFactory(bodyFactory, behaviorFactory);
        } else if (layerName.equals(TiledConstants.LAYER_ITEM)) {
            return new ItemFactory(bodyFactory, behaviorFactory);
        } else if (layerName.equals(TiledConstants.LAYER_OBJECT)) {
            return new GameObjectFactory(bodyFactory, behaviorFactory, soundEffectFactory);
        } else if (layerName.equals(TiledConstants.LAYER_PLAYER)) {
            return new PlayerFactory(bodyFactory, behaviorFactory);
        } else {
            throw new IllegalArgumentException("No layer named: " + layerName);
        }
    }
}
