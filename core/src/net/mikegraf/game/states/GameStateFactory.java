package net.mikegraf.game.states;

import net.mikegraf.game.main.MyGdxGame;
import net.mikegraf.game.states.gameOver.GameOver;
import net.mikegraf.game.states.play.Play;

public class GameStateFactory {

    private MyGdxGame game;

    public GameStateFactory(MyGdxGame game) {
        this.game = game;
    }

    public GameState createState(StateType state) {
        switch (state) {
        case PLAY:
            return new Play(game);
        case GAME_OVER:
            return new GameOver(game);
        default:
            throw new IllegalArgumentException("No state: " + state.name());
        }
    }
}
