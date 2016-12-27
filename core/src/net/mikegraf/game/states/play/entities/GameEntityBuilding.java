package net.mikegraf.game.states.play.entities;

import com.badlogic.gdx.maps.MapLayer;
import com.google.inject.Inject;

import net.mikegraf.game.audio.SoundEffectFactory;
import net.mikegraf.game.main.constants.TiledConstants;
import net.mikegraf.game.states.play.entities.behavior.BehaviorFactory;
import net.mikegraf.game.states.play.entities.behavior.rendering.AnimationFactory;
import net.mikegraf.game.states.play.entities.bodies.BodyFactory;
import net.mikegraf.game.states.play.entities.gameObjects.GameObjectBehaviorFactory;
import net.mikegraf.game.states.play.entities.gameObjects.GameObjectFactory;
import net.mikegraf.game.states.play.entities.items.ItemBehaviorFactory;
import net.mikegraf.game.states.play.entities.items.ItemFactory;
import net.mikegraf.game.states.play.entities.player.PlayerBehaviorFactory;
import net.mikegraf.game.states.play.entities.player.PlayerFactory;
import net.mikegraf.game.states.play.entities.triggers.TriggerBehaviorFactory;
import net.mikegraf.game.states.play.entities.triggers.TriggerFactory;

public class GameEntityBuilding {

    private AnimationFactory animationFactory;
    private BodyFactory bodyFactory;
    private SoundEffectFactory soundEffectFactory;

    @Inject
    public GameEntityBuilding(AnimationFactory animationFactory, BodyFactory bodyFactory,
            SoundEffectFactory soundEffectFactory) {
        this.animationFactory = animationFactory;
        this.bodyFactory = bodyFactory;
        this.soundEffectFactory = soundEffectFactory;
    }

    public GameEntityFactory getGameEntityFactory(MapLayer layer) {
        String layerName = layer.getName();

        if (layerName.equals(TiledConstants.LAYER_TRIGGER)) {
            BehaviorFactory behaviorFactory = new TriggerBehaviorFactory();
            return new TriggerFactory(bodyFactory, behaviorFactory);
        } else if (layerName.equals(TiledConstants.LAYER_ITEM)) {
            BehaviorFactory behaviorFactory = new ItemBehaviorFactory(animationFactory);
            return new ItemFactory(bodyFactory, behaviorFactory);
        } else if (layerName.equals(TiledConstants.LAYER_OBJECT)) {
            BehaviorFactory behaviorFactory = new GameObjectBehaviorFactory(animationFactory);
            return new GameObjectFactory(bodyFactory, behaviorFactory, soundEffectFactory);
        } else if (layerName.equals(TiledConstants.LAYER_PLAYER)) {
            BehaviorFactory behaviorFactory = new PlayerBehaviorFactory(animationFactory);
            return new PlayerFactory(bodyFactory, behaviorFactory);
        } else {
            throw new IllegalArgumentException("No layer named: " + layerName);
        }
    }
}
