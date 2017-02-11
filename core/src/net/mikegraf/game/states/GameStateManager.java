package net.mikegraf.game.states;

import java.util.Stack;

import com.google.inject.Inject;

public class GameStateManager {

    // Instance variables.
    private Stack<GameState> gameStates;
    private GameStateFactory gameStateFactory;

    @Inject
    public GameStateManager(GameStateFactory gameStateFactory) {
        this.gameStateFactory = gameStateFactory;
        this.gameStates = new Stack<GameState>();
        pushState(StateType.PLAY);
    }

    public void update(float dt) {
        gameStates.peek().update(dt);
    }

    public void render(float totalTime) {
        gameStates.peek().render(totalTime);
    }

    public void setState(StateType state) {
        popState();
        pushState(state);
    }

    public void pushState(StateType state) {
        gameStates.push(gameStateFactory.createState(state));
    }

    public void popState() {
        GameState g = gameStates.pop();
        g.dispose();
    }

    public void winGame() {

    }
}
