package net.mikegraf.game.states.play.actors.gameobjects;

import net.mikegraf.game.states.play.actors.B2DSprite;

public class GameObject {

    protected B2DSprite sprite;

    public GameObject(B2DSprite b2dSprite) {
        sprite = b2dSprite;
        sprite.setUserData(this);
    }

}
