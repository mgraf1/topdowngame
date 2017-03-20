package net.mikegraf.game.states.play;

import java.io.IOException;
import java.util.HashMap;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import net.mikegraf.game.audio.SoundEffectFactory;
import net.mikegraf.game.exceptions.ConfigFormatException;
import net.mikegraf.game.menus.FontFactory;
import net.mikegraf.game.menus.FontLoader;
import net.mikegraf.game.menus.IFontLoader;
import net.mikegraf.game.parsers.*;
import net.mikegraf.game.states.play.controls.PlayerInputHandler;
import net.mikegraf.game.states.play.entities.BehaviorBuilding;
import net.mikegraf.game.states.play.entities.GameEntityBuilding;
import net.mikegraf.game.states.play.entities.physics.BodyBuilding;
import net.mikegraf.game.states.play.entities.physics.ShapeFactory;
import net.mikegraf.game.states.play.entities.view.AnimationFactory;
import net.mikegraf.game.states.play.hud.HudAssetHelper;
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
        bind(IFontLoader.class).to(FontLoader.class);
        bind(BehaviorBuilding.class);
        bind(BodyBuilding.class);
        bind(ShapeFactory.class);
        bind(Play.class).toInstance(playState);
        bind(LevelFactory.class);
        bind(AnimationFactory.class).asEagerSingleton();
        bind(SoundEffectFactory.class).asEagerSingleton();
        bind(GameEntityBuilding.class);
        bind(PlayerInputHandler.class);
        bind(HudAssetHelper.class);
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
        try {
            return parser.parseFile(path);
        } catch (ConfigFormatException e) {
            throw new AssertionError(e.getMessage());
            //throw new IOException(e.getMessage());
        } catch (IOException e) {
            throw new AssertionError(e.getMessage());
        }
    }
}
