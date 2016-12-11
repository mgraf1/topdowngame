package net.mikegraf.game.states.play.actors;

import java.util.HashMap;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/* Represents the Actor the user will control. */
public class Player {

    // Constants.
    public static final int STARTING_INVENTORY_SIZE = 4;
    public static final String MOVE_LEFT_ANIMATION_NAME = "moveLeft";
    public static final String MOVE_UP_ANIMATION_NAME = "moveUp";
    public static final String MOVE_RIGHT_ANIMATION_NAME = "moveRight";
    public static final String MOVE_DOWN_ANIMATION_NAME = "moveDown";

    // Instance variables.
    private static float MAX_VELOCITY = 1.2f;
    private Array<Item> inventory;
    private int inventorySize = STARTING_INVENTORY_SIZE;
    private B2DSprite sprite;
    private HashMap<String, String> vectorHashToAnimationMap;

    public Player(B2DSprite b2dSprite) {
        sprite = b2dSprite;
        sprite.setUserData(this);
        inventory = new Array<Item>(STARTING_INVENTORY_SIZE);
        vectorHashToAnimationMap = createMovementAnimationMap();
    }

    private HashMap<String, String> createMovementAnimationMap() {
        HashMap<String, String> movementAnimationMap = new HashMap<String, String>();

        movementAnimationMap.put(new Vector2(-1, 0).toString(), MOVE_LEFT_ANIMATION_NAME);
        movementAnimationMap.put(new Vector2(0, 1).toString(), MOVE_UP_ANIMATION_NAME);
        movementAnimationMap.put(new Vector2(1, 0).toString(), MOVE_RIGHT_ANIMATION_NAME);
        movementAnimationMap.put(new Vector2(0, -1).toString(), MOVE_DOWN_ANIMATION_NAME);

        return movementAnimationMap;
    }

    public void move(Vector2 v) {
        String animation = vectorHashToAnimationMap.get(v.toString());
        if (animation != null) {
            sprite.setAnimation(animation);
        }

        sprite.move(v.x * MAX_VELOCITY, v.y * MAX_VELOCITY);
    }

    public Vector2 getPosition() {
        return sprite.getPosition();
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
        inventory.removeValue(getItemFromType(type), true);
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
}
