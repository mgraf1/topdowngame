package net.mikegraf.game.states.play.entities.items;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.maps.MapProperties;

import net.mikegraf.game.main.constants.TiledConstants;
import net.mikegraf.game.states.play.entities.BehaviorFactory;
import net.mikegraf.game.states.play.entities.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.collision.ItemCollisionBehavior;
import net.mikegraf.game.states.play.entities.controller.DefaultController;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.view.AnimationFactory;
import net.mikegraf.game.states.play.entities.view.AnimationIndex;
import net.mikegraf.game.states.play.entities.view.AnimationView;
import net.mikegraf.game.states.play.entities.view.IView;

public class ItemBehaviorFactory extends BehaviorFactory {

    private AnimationFactory animationFactory;

    public ItemBehaviorFactory(AnimationFactory animationFactory) {
        this.animationFactory = animationFactory;
    }

    @Override
    public IView createView(MapProperties props, AssetManager assetManager) {
        String texture = props.get(TiledConstants.ENTITY_TEXTURE, String.class);
        AnimationIndex animationIndex = animationFactory.createAnimationIndex(texture, assetManager);
        ShaderProgram flashShader = animationFactory.createShader(AnimationFactory.SHADER_FLASH);
        return new AnimationView(animationIndex, flashShader);
    }

    @Override
    public ICollisionBehavior createCollisionBehavior(MapProperties props) {
        return new ItemCollisionBehavior();
    }

    @Override
    public IController createController(MapProperties props) {
        return new DefaultController();
    }

}
