package net.mikegraf.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import net.mikegraf.game.main.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = MyGdxGame.V_WIDTH * MyGdxGame.SCALE;
		config.height = MyGdxGame.V_HEIGHT * MyGdxGame.SCALE;
		new LwjglApplication(new MyGdxGame(), config);
	}
}
