package net.mikegraf.game.states.play.entities;

import com.google.inject.Inject;

import net.mikegraf.game.main.constants.TiledConstants;
import net.mikegraf.game.states.play.controls.PlayerInputHandler;
import net.mikegraf.game.states.play.entities.gameObjects.GameObjectBehaviorFactory;
import net.mikegraf.game.states.play.entities.items.ItemBehaviorFactory;
import net.mikegraf.game.states.play.entities.physics.BodyBuilding;
import net.mikegraf.game.states.play.entities.physics.BodyFactory;
import net.mikegraf.game.states.play.entities.player.PlayerBehaviorFactory;
import net.mikegraf.game.states.play.entities.triggers.TriggerBehaviorFactory;
import net.mikegraf.game.states.play.entities.view.AnimationFactory;

public class BehaviorBuilding {

    private AnimationFactory animationFactory;
    private BodyBuilding bodyBuilding;
    private PlayerInputHandler inputHandler;

    @Inject
    public BehaviorBuilding(AnimationFactory animationFactory, BodyBuilding bodyBuilding,
            PlayerInputHandler inputHandler) {
        this.animationFactory = animationFactory;
        this.bodyBuilding = bodyBuilding;
        this.inputHandler = inputHandler;
    }

    public BehaviorFactory getBehaviorFactory(String layerName) {
        BodyFactory bodyFactory = bodyBuilding.getBodyFactory(layerName);

        if (layerName.equals(TiledConstants.LAYER_TRIGGER)) {
            return new TriggerBehaviorFactory(bodyFactory);
        } else if (layerName.equals(TiledConstants.LAYER_ITEM)) {
            return new ItemBehaviorFactory(animationFactory, bodyFactory);
        } else if (layerName.equals(TiledConstants.LAYER_OBJECT)) {
            return new GameObjectBehaviorFactory(animationFactory, bodyFactory);
        } else if (layerName.equals(TiledConstants.LAYER_PLAYER)) {
            return new PlayerBehaviorFactory(animationFactory, bodyFactory, inputHandler);
        } else {
            throw new IllegalArgumentException("No layer named: " + layerName);
        }
    }

}
