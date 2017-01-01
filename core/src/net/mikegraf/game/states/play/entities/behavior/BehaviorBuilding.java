package net.mikegraf.game.states.play.entities.behavior;

import com.google.inject.Inject;

import net.mikegraf.game.main.constants.TiledConstants;
import net.mikegraf.game.states.play.entities.behavior.rendering.AnimationFactory;
import net.mikegraf.game.states.play.entities.gameObjects.GameObjectBehaviorFactory;
import net.mikegraf.game.states.play.entities.items.ItemBehaviorFactory;
import net.mikegraf.game.states.play.entities.player.PlayerBehaviorFactory;
import net.mikegraf.game.states.play.entities.triggers.TriggerBehaviorFactory;

public class BehaviorBuilding {

    private AnimationFactory animationFactory;

    @Inject
    public BehaviorBuilding(AnimationFactory animationFactory) {
        this.animationFactory = animationFactory;
    }

    public BehaviorFactory getBehaviorFactory(String layerName) {
        if (layerName.equals(TiledConstants.LAYER_TRIGGER)) {
            return new TriggerBehaviorFactory();
        } else if (layerName.equals(TiledConstants.LAYER_ITEM)) {
            return new ItemBehaviorFactory(animationFactory);
        } else if (layerName.equals(TiledConstants.LAYER_OBJECT)) {
            return new GameObjectBehaviorFactory(animationFactory);
        } else if (layerName.equals(TiledConstants.LAYER_PLAYER)) {
            return new PlayerBehaviorFactory(animationFactory);
        } else {
            throw new IllegalArgumentException("No layer named: " + layerName);
        }
    }

}
