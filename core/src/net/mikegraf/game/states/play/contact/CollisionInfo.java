package net.mikegraf.game.states.play.contact;

import net.mikegraf.game.states.play.Play;
import net.mikegraf.game.states.play.entities.GameEntity;

public class CollisionInfo {

    private Play playState;
    private GameEntity otherEntity;

    public CollisionInfo(Play playState, GameEntity otherEntity) {
        this.playState = playState;
        this.otherEntity = otherEntity;
    }

    public Play getPlayState() {
        return playState;
    }

    public GameEntity getOtherEntity() {
        return otherEntity;
    }
}
