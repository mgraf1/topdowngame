package net.mikegraf.game.states.play.contact;

import net.mikegraf.game.states.play.entities.GameEntity;
import net.mikegraf.game.states.play.levels.Level;

public class CollisionInfo {

    private Level level;
    private GameEntity thisEntity;
    private GameEntity otherEntity;

    public CollisionInfo(Level level, GameEntity thisEntity, GameEntity otherEntity) {
        this.level = level;
        this.thisEntity = thisEntity;
        this.otherEntity = otherEntity;
    }

    public void setOtherEntity(GameEntity entity) {
        this.otherEntity = entity;
    }

    public Level getLevel() {
        return level;
    }

    public GameEntity getThisEntity() {
        return thisEntity;
    }

    public GameEntity getOtherEntity() {
        return otherEntity;
    }
}
