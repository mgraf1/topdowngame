package net.mikegraf.game.states.play.entities.items;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

import net.mikegraf.game.states.play.entities.GameEntity;
import net.mikegraf.game.states.play.entities.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.view.IView;

public class Item extends GameEntity {

    private String type;

    public Item(ICollisionBehavior collisionBehavior, IController controller,
            IView view, Body body, String type) {
        super(collisionBehavior, controller, view, body);
        this.type = type;
    }
    
    public void renderHud(SpriteBatch batch, float x, float y) {
    	view.render(batch, x, y);
    }

    public String getType() {
        return type;
    }
}
