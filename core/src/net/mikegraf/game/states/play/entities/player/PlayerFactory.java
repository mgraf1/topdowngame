package net.mikegraf.game.states.play.entities.player;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.MapProperties;

import net.mikegraf.game.audio.SoundEffectFactory;
import net.mikegraf.game.audio.SoundEffectIndex;
import net.mikegraf.game.states.play.entities.BehaviorFactory;
import net.mikegraf.game.states.play.entities.GameEntity;
import net.mikegraf.game.states.play.entities.GameEntityFactory;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.physics.PhysicsModel;
import net.mikegraf.game.states.play.entities.view.IView;

public class PlayerFactory extends GameEntityFactory {

    private SoundEffectFactory soundEffectFactory;

    public PlayerFactory(BehaviorFactory behaviorFactory, SoundEffectFactory soundEffectFactory) {
        super(behaviorFactory);
        this.soundEffectFactory = soundEffectFactory;
    }

    @Override
    protected GameEntity constructGameEntity(PhysicsModel physModel, IView view, IController controller,
            MapProperties props, AssetManager assetManager) {
        SoundEffectIndex sfxIndex = soundEffectFactory.createSoundEffectIndex("player", assetManager);
        return new Player(physModel, view, controller, sfxIndex);
    }

}
