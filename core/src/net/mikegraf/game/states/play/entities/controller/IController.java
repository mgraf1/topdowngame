package net.mikegraf.game.states.play.entities.controller;

import com.badlogic.gdx.math.Vector2;

import net.mikegraf.game.states.play.entities.GameEntity;

public interface IController {

    public Vector2 update(GameEntity entity, float deltaTime);

}
