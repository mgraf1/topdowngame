package net.mikegraf.game.states.play.entities;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.physics.box2d.World;

import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.physics.PhysicsModel;
import net.mikegraf.game.states.play.entities.view.IView;

public abstract class BehaviorFactory {

    public abstract PhysicsModel createPhysicsModel(World world, MapObject mapObject);

    public abstract IView createView(MapProperties props, AssetManager assetManager);

    public abstract IController createController(MapProperties props);
}
