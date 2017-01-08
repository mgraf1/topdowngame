package net.mikegraf.game.states.play;

import java.io.IOException;
import java.util.HashMap;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import net.mikegraf.game.audio.SoundEffectFactory;
import net.mikegraf.game.exceptions.ConfigFormatException;
import net.mikegraf.game.parsers.AnimationParser;
import net.mikegraf.game.parsers.BodyParser;
import net.mikegraf.game.parsers.Parser;
import net.mikegraf.game.parsers.SoundParser;
import net.mikegraf.game.parsers.WorldParser;
import net.mikegraf.game.parsers.models.AnimationIndexData;
import net.mikegraf.game.parsers.models.BodyData;
import net.mikegraf.game.parsers.models.LevelData;
import net.mikegraf.game.parsers.models.SoundData;
import net.mikegraf.game.states.play.controls.PlayerInputHandler;
import net.mikegraf.game.states.play.entities.GameEntityBuilding;
import net.mikegraf.game.states.play.entities.behavior.BehaviorBuilding;
import net.mikegraf.game.states.play.entities.behavior.rendering.AnimationFactory;
import net.mikegraf.game.states.play.entities.bodies.BodyBuilding;
import net.mikegraf.game.states.play.entities.bodies.ShapeFactory;
import net.mikegraf.game.states.play.levels.LevelFactory;

public class PlayModule extends AbstractModule {

    private static final String ANIMATION_DEF_PATH = "xml/animationDef.xml";
    private static final String BODY_DEF_PATH = "xml/bodyDef.xml";
    private static final String SOUND_DEF_PATH = "xml/soundDef.xml";
    private static final String WORLD_DEF_PATH = "xml/worldDef.xml";

    private Play playState;

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
    }

    @Provides
    public HashMap<String, AnimationIndexData> provideStringToAnimationIndexDataMap() {
        AnimationParser animationParser = new AnimationParser();
        return parseConfigFile(animationParser, ANIMATION_DEF_PATH);
    }

    @Provides
    public HashMap<String, BodyData> provideStringToBodyDataMap() {
        BodyParser bodyParser = new BodyParser();
        return parseConfigFile(bodyParser, BODY_DEF_PATH);
    }

    @Provides
    public LevelData[][] provideLevelDataArray() {
        WorldParser worldParser = new WorldParser();
        return parseConfigFile(worldParser, WORLD_DEF_PATH);
    }

    @Provides
    public HashMap<String, SoundData> provideTypeToSoundDataMap() {
        SoundParser soundParser = new SoundParser();
        return parseConfigFile(soundParser, SOUND_DEF_PATH);
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
