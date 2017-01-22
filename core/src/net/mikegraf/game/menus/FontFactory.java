package net.mikegraf.game.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class FontFactory {

	private static final String FONT_PATH = "fonts/";
	private static final String NOVEMBER_FILE_NAME = "november.fnt";
	
	public BitmapFont createFont(FontType type) { 
		FileHandle handle = null;
		switch (type) {
		
		case NOVEMBER:
			handle = Gdx.files.internal(FONT_PATH + NOVEMBER_FILE_NAME);
			break;
		}
		
		return new BitmapFont(handle);
	}
	
}
