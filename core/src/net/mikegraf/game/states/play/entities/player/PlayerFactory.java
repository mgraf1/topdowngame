package net.mikegraf.game.states.play.entities.player;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.physics.box2d.Body;

import net.mikegraf.game.audio.SoundEffectFactory;
import net.mikegraf.game.audio.SoundEffectIndex;
import net.mikegraf.game.states.play.entities.BehaviorFactory;
import net.mikegraf.game.states.play.entities.GameEntity;
import net.mikegraf.game.states.play.entities.GameEntityFactory;
import net.mikegraf.game.states.play.entities.bodies.BodyFactory;
import net.mikegraf.game.states.play.entities.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.view.IView;

public class PlayerFactory extends GameEntityFactory {

    private SoundEffectFactory soundEffectFactory;

    public PlayerFactory(BodyFactory bodyFactory, BehaviorFactory behaviorFactory,
            SoundEffectFactory soundEffectFactory) {
        super(bodyFactory, behaviorFactory);
        this.soundEffectFactory = soundEffectFactory;
    }

    @Override
    protected GameEntity constructGameEntity(ICollisionBehavior collisionB, IController controller, IView view,
            Body body, MapProperties props, AssetManager assetManager) {
        SoundEffectIndex sfxIndex = soundEffectFactory.createSoundEffectIndex("player", assetManager);
        return new Player(collisionB, controller, view, body, sfxIndex);
    }

}
