package net.mikegraf.game.states.play;

import java.io.IOException;
import java.util.HashMap;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import net.mikegraf.game.audio.SoundEffectFactory;
import net.mikegraf.game.exceptions.ConfigFormatException;
import net.mikegraf.game.menus.FontFactory;
import net.mikegraf.game.parsers.AnimationParser;
import net.mikegraf.game.parsers.BodyParser;
import net.mikegraf.game.parsers.FontParser;
import net.mikegraf.game.parsers.Parser;
import net.mikegraf.game.parsers.SoundParser;
import net.mikegraf.game.parsers.WorldParser;
import net.mikegraf.game.parsers.models.AnimationIndexData;
import net.mikegraf.game.parsers.models.BodyData;
import net.mikegraf.game.parsers.models.FontData;
import net.mikegraf.game.parsers.models.LevelData;
import net.mikegraf.game.parsers.models.SoundData;
import net.mikegraf.game.states.play.controls.PlayerInputHandler;
import net.mikegraf.game.states.play.entities.BehaviorBuilding;
import net.mikegraf.game.states.play.entities.GameEntityBuilding;
import net.mikegraf.game.states.play.entities.bodies.BodyBuilding;
import net.mikegraf.game.states.play.entities.bodies.ShapeFactory;
import net.mikegraf.game.states.play.entities.view.AnimationFactory;
import net.mikegraf.game.states.play.levels.LevelFactory;

public class PlayModule extends AbstractModule {

    private static final String ANIMATION_DEF_PATH = "xml/animationDef.xml";
    private static final String BODY_DEF_PATH = "xml/bodyDef.xml";
    private static final String FONT_DEF_PATH = "xml/fontDef.xml";
    private static final String SOUND_DEF_PATH = "xml/soundDef.xml";
    private static final String WORLD_DEF_PATH = "xml/worldDef.xml";

    private Play playState;
    private HashMap<String, FontData> fontDataMap;
    private HashMap<String, AnimationIndexData> animationDataMap;
    private HashMap<String, BodyData> bodyDataMap;
    private HashMap<String, SoundData> soundDataMap;
    private LevelData[][] levelData;

    public PlayModule(Play playState) {
        this.playState = playState;
    }

    @Override
    protected void configure() {
        bind(BehaviorBuilding.class);
        bind(BodyBuilding.class);
        bind(ShapeFactory.class);
        bind(Play.class).toInstance(playState);
        bind(LevelFactory.class);
        bind(AnimationFactory.class).asEagerSingleton();
        bind(SoundEffectFactory.class).asEagerSingleton();
        bind(GameEntityBuilding.class);
        bind(PlayerInputHandler.class);
        bind(FontFactory.class).asEagerSingleton();
    }

    @Provides
    public HashMap<String, FontData> providesStringToFontDataMap() {
        if (fontDataMap == null) {
            FontParser fontParser = new FontParser();
            fontDataMap = parseConfigFile(fontParser, FONT_DEF_PATH);
        }
        return fontDataMap;
    }

    @Provides
    public HashMap<String, AnimationIndexData> provideStringToAnimationIndexDataMap() {
        if (animationDataMap == null) {
            AnimationParser animationParser = new AnimationParser();
            animationDataMap = parseConfigFile(animationParser, ANIMATION_DEF_PATH);
        }
        return animationDataMap;
    }

    @Provides
    public HashMap<String, BodyData> provideStringToBodyDataMap() {
        if (bodyDataMap == null) {
            BodyParser bodyParser = new BodyParser();
            bodyDataMap = parseConfigFile(bodyParser, BODY_DEF_PATH);
        }
        return bodyDataMap;
    }

    @Provides
    public LevelData[][] provideLevelDataArray() {
        if (levelData == null) {
            WorldParser worldParser = new WorldParser();
            levelData = parseConfigFile(worldParser, WORLD_DEF_PATH);
        }
        return levelData;
    }

    @Provides
    public HashMap<String, SoundData> provideTypeToSoundDataMap() {
        if (soundDataMap == null) {
            SoundParser soundParser = new SoundParser();
            soundDataMap = parseConfigFile(soundParser, SOUND_DEF_PATH);
        }
        return soundDataMap;
    }

    private <T> T parseConfigFile(Parser<T> parser, String path) {
        T data = null;
        try {
            data = parser.parseFile(path);
        } catch (ConfigFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
