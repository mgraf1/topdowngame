package net.mikegraf.game.states.play.contact;

import net.mikegraf.game.states.play.Play;
import net.mikegraf.game.states.play.actors.B2DSprite;
import net.mikegraf.game.states.play.actors.Player;

public class CollisionInfo {

    private Play playState;
    private Player player;
    private B2DSprite otherSprite;

    public Play getPlayState() {
        return playState;
    }

    public void setPlayState(Play playState) {
        this.playState = playState;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public B2DSprite getOtherSprite() {
        return otherSprite;
    }

    public void setOtherSprite(B2DSprite otherSprite) {
        this.otherSprite = otherSprite;
    }
}
