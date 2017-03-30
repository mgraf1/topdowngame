package net.mikegraf.game.states.play.entities.triggers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import net.mikegraf.game.states.play.entities.BehaviorFactory;
import net.mikegraf.game.states.play.entities.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.collision.TriggerCollisionBehavior;
import net.mikegraf.game.states.play.entities.controller.DefaultController;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.physics.BodyFactory;
import net.mikegraf.game.states.play.entities.physics.PhysicsModel;
import net.mikegraf.game.states.play.entities.view.IView;
import net.mikegraf.game.states.play.entities.view.NoRenderView;

public class TriggerBehaviorFactory extends BehaviorFactory {

    private BodyFactory bodyFactory;

    public TriggerBehaviorFactory(BodyFactory bodyFactory) {
        this.bodyFactory = bodyFactory;
    }

    @Override
    public IView createView(MapProperties props, AssetManager assetManager) {
        return new NoRenderView();
    }

    @Override
    public IController createController(MapProperties props) {
        return new DefaultController();
    }

    @Override
    public PhysicsModel createPhysicsModel(World world, MapObject mapObject) {
        Body body = bodyFactory.createBody(world, mapObject);
        ICollisionBehavior collisionBehavior = new TriggerCollisionBehavior();
        return new PhysicsModel(body, collisionBehavior, 0);
    }

}
