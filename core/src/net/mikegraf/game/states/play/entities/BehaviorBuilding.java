package net.mikegraf.game.states.play.entities;

import com.google.inject.Inject;

import net.mikegraf.game.main.constants.TiledConstants;
import net.mikegraf.game.states.play.controls.PlayerInputHandler;
import net.mikegraf.game.states.play.entities.gameObjects.GameObjectBehaviorFactory;
import net.mikegraf.game.states.play.entities.items.ItemBehaviorFactory;
import net.mikegraf.game.states.play.entities.player.PlayerBehaviorFactory;
import net.mikegraf.game.states.play.entities.triggers.TriggerBehaviorFactory;
import net.mikegraf.game.states.play.entities.view.AnimationFactory;

public class BehaviorBuilding {

    private AnimationFactory animationFactory;
    private PlayerInputHandler inputHandler;

    @Inject
    public BehaviorBuilding(AnimationFactory animationFactory, PlayerInputHandler inputHandler) {
        this.animationFactory = animationFactory;
        this.inputHandler = inputHandler;
    }

    public BehaviorFactory getBehaviorFactory(String layerName) {
        if (layerName.equals(TiledConstants.LAYER_TRIGGER)) {
            return new TriggerBehaviorFactory();
        } else if (layerName.equals(TiledConstants.LAYER_ITEM)) {
            return new ItemBehaviorFactory(animationFactory);
        } else if (layerName.equals(TiledConstants.LAYER_OBJECT)) {
            return new GameObjectBehaviorFactory(animationFactory);
        } else if (layerName.equals(TiledConstants.LAYER_PLAYER)) {
            return new PlayerBehaviorFactory(animationFactory, inputHandler);
        } else {
            throw new IllegalArgumentException("No layer named: " + layerName);
        }
    }

}
