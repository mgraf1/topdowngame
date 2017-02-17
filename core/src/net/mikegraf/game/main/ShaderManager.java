package net.mikegraf.game.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class ShaderManager {

    private static ShaderProgram flashShader;

    public static void createShaders() {
        FileHandle flashFragHandler = Gdx.files.internal("shaders/flash.frag");
        FileHandle flashVertHandler = Gdx.files.internal("shaders/flash.vert");

        String flashFragText = flashFragHandler.readString();
        String flashVertText = flashVertHandler.readString();

        flashShader = new ShaderProgram(flashVertText, flashFragText);
    }

    public static ShaderProgram getFlashShader() {
        return flashShader;
    }
}
