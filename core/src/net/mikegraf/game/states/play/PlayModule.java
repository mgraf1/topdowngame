package net.mikegraf.game.states.play;

import java.io.IOException;
import java.util.HashMap;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import net.mikegraf.game.audio.SoundEffectFactory;
import net.mikegraf.game.exceptions.ConfigFormatException;
import net.mikegraf.game.parsers.SoundParser;
import net.mikegraf.game.parsers.SpriteParser;
import net.mikegraf.game.parsers.WorldParser;
import net.mikegraf.game.parsers.models.LevelData;
import net.mikegraf.game.parsers.models.SoundData;
import net.mikegraf.game.parsers.models.SpriteData;
import net.mikegraf.game.states.play.actors.AnimationFactory;
import net.mikegraf.game.states.play.actors.ShapeFactory;
import net.mikegraf.game.states.play.actors.gameobjects.GameObjectFactory;
import net.mikegraf.game.states.play.levels.LevelFactory;
import net.mikegraf.game.states.play.triggers.TriggerFactory;

public class PlayModule extends AbstractModule {

    private static final String SPRITE_DEF_PATH = "xml/spriteDef.xml";
    private static final String SOUND_DEF_PATH = "xml/soundDef.xml";
    private static final String WORLD_DEF_PATH = "xml/worldDef.xml";

    private Play playState;

    public PlayModule(Play playState) {
        this.playState = playState;
    }

    @Override
    protected void configure() {
        bind(TriggerFactory.class);
        bind(GameObjectFactory.class);
        bind(Play.class).toInstance(playState);
        bind(LevelFactory.class);
        bind(ShapeFactory.class);
        bind(AnimationFactory.class);
        bind(SoundEffectFactory.class);
    }

    @Provides
    public HashMap<String, SpriteData> provideStringToSpriteDatMap() {
        SpriteParser ap = new SpriteParser();
        HashMap<String, SpriteData> map = null;
        try {
            map = ap.parseFile(SPRITE_DEF_PATH);
        } catch (ConfigFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    @Provides
    public LevelData[][] provideLevelDataArray() {
        WorldParser worldParser = new WorldParser();
        LevelData[][] levelData = null;
        try {
            levelData = worldParser.parseFile(WORLD_DEF_PATH);
        } catch (ConfigFormatException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(2);
        }
        return levelData;
    }

    @Provides
    public HashMap<String, SoundData> provideTypeToSoundDataMap() {
        SoundParser sp = new SoundParser();
        HashMap<String, SoundData> map = null;
        try {
            map = sp.parseFile(SOUND_DEF_PATH);
        } catch (ConfigFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
