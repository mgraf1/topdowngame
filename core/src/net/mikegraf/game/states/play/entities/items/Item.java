package net.mikegraf.game.states.play.entities.items;

import net.mikegraf.game.states.play.entities.GameEntity;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.physics.PhysicsModel;
import net.mikegraf.game.states.play.entities.view.IView;

public class Item extends GameEntity {

    private String type;

    public Item(PhysicsModel physModel, IView view, IController controller, String type) {
        super(physModel, view, controller);
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
