package net.mikegraf.game.states.gameOver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.google.inject.Guice;
import com.google.inject.Injector;

import net.mikegraf.game.main.MyGdxGame;
import net.mikegraf.game.menus.FontFactory;
import net.mikegraf.game.menus.IFontLoader;
import net.mikegraf.game.states.GameState;

public class GameOver extends GameState {

    private static final float GAME_OVER_TEXT_X = ((MyGdxGame.V_WIDTH * MyGdxGame.SCALE) / 2) - 235;
    private static final float GAME_OVER_TEXT_Y = ((MyGdxGame.V_HEIGHT * MyGdxGame.SCALE) / 2) - 75;

    private AssetManager assetManager;
    private IFontLoader fontLoader;
    private FontFactory fontFactory;
    private BitmapFont gameOverFont;

    public GameOver(MyGdxGame g) {
        super(g);

        this.assetManager = new AssetManager();

        Injector injector = Guice.createInjector(new GameOverModule());
        this.fontFactory = injector.getInstance(FontFactory.class);
        this.fontLoader = injector.getInstance(IFontLoader.class);

        fontLoader.loadFontData(assetManager);
        assetManager.finishLoading();
        this.gameOverFont = this.fontFactory.createFont(FontFactory.NOVEMBER, assetManager);
    }

    @Override
    public void handleInput() {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(float totalTime) {
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        renderGameOverText();
        renderGameOverButtons();
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }

    private void renderGameOverText() {
        sb.begin();
        gameOverFont.draw(sb, "GAME OVER!", GAME_OVER_TEXT_X, GAME_OVER_TEXT_Y);
        sb.end();
    }

    private void renderGameOverButtons() {

    }
}
