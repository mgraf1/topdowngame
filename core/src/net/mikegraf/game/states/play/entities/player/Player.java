package net.mikegraf.game.states.play.entities.player;

import java.util.HashMap;
import java.util.HashSet;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;

import net.mikegraf.game.states.play.controls.MyInput;
import net.mikegraf.game.states.play.entities.GameEntity;
import net.mikegraf.game.states.play.entities.behavior.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.behavior.movement.IMovementBehavior;
import net.mikegraf.game.states.play.entities.behavior.rendering.IRenderBehavior;
import net.mikegraf.game.states.play.entities.gameObjects.OperableGameEntity;
import net.mikegraf.game.states.play.entities.items.Item;

public class Player extends GameEntity {

    public static final int STARTING_INVENTORY_SIZE = 4;
    public static final String MOVE_LEFT_ANIMATION_NAME = "moveLeft";
    public static final String MOVE_UP_ANIMATION_NAME = "moveUp";
    public static final String MOVE_RIGHT_ANIMATION_NAME = "moveRight";
    public static final String MOVE_DOWN_ANIMATION_NAME = "moveDown";
    public static final float MAX_VELOCITY = 1.2f;

    private Array<Item> inventory;
    private int inventorySize;
    private HashMap<String, String> vectorHashToAnimationMap;
    private HashSet<OperableGameEntity> touchedObjects;

    public Player(String id, ICollisionBehavior collisionBehavior, IMovementBehavior movementBehavior,
            IRenderBehavior renderBehavior, Body body) {
        super(id, collisionBehavior, movementBehavior, renderBehavior, body);

        this.inventorySize = STARTING_INVENTORY_SIZE;
        this.velocity = MAX_VELOCITY;
        this.vectorHashToAnimationMap = createMovementAnimationMap();
        this.touchedObjects = new HashSet<OperableGameEntity>();
        this.inventory = new Array<Item>(STARTING_INVENTORY_SIZE);
    }

    private HashMap<String, String> createMovementAnimationMap() {
        HashMap<String, String> movementAnimationMap = new HashMap<String, String>();
        movementAnimationMap.put(new Vector2(-1, 0).toString(), MOVE_LEFT_ANIMATION_NAME);
        movementAnimationMap.put(new Vector2(0, 1).toString(), MOVE_UP_ANIMATION_NAME);
        movementAnimationMap.put(new Vector2(1, 0).toString(), MOVE_RIGHT_ANIMATION_NAME);
        movementAnimationMap.put(new Vector2(0, -1).toString(), MOVE_DOWN_ANIMATION_NAME);
        return movementAnimationMap;
    }

    @Override
    public void beforeMove(Vector2 movementVector) {
        String animation = vectorHashToAnimationMap.get(movementVector.toString());
        if (animation != null) {
            renderBehavior.setMode(animation);
        }

        if (MyInput.isPressed(MyInput.OPERATE)) {
            operateTouchedObjects();
        }
    }

    public boolean pickupItem(Item item) {
        if (canPickupItem()) {
            inventory.add(item);
            return true;
        }

        return false;
    }

    // Returns true if the player has the given item.
    public boolean hasItem(String type) {
        return getItemFromType(type) != null;
    }

    public void removeItem(String type) {
        Item item = getItemFromType(type);
        inventory.removeValue(item, true);
    }

    private Item getItemFromType(String type) {
        for (Item item : inventory) {
            if (item.getType().equals(type)) {
                return item;
            }
        }
        return null;
    }

    private boolean canPickupItem() {
        return inventory.size < inventorySize;
    }

    public void setInventorySize(int size) {
        inventorySize = size;
    }

    public Array<Item> getInventory() {
        return inventory;
    }

    public void touch(OperableGameEntity operable) {
        touchedObjects.add(operable);
    }

    public void stopTouching(OperableGameEntity operable) {
        touchedObjects.remove(operable);
    }

    public void operateTouchedObjects() {
        for (OperableGameEntity o : touchedObjects) {
            o.operate(this);
        }
    }

    public boolean isTouching(String objectId) {
        for (OperableGameEntity obj : touchedObjects) {
            if (objectId.equals(obj.getId())) {
                return true;
            }
        }
        return false;
    }

    public Vector2 getPosition() {
        return body.getPosition();
    }
}
