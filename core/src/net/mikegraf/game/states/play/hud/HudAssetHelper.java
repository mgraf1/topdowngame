package net.mikegraf.game.states.play.hud;

import java.util.HashMap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.google.inject.Inject;

import net.mikegraf.game.parsers.AnimationIndexData;
import net.mikegraf.game.states.play.entities.view.AnimationFactory;
import net.mikegraf.game.states.play.entities.view.AnimationIndex;

public class HudAssetHelper {

    public static final String INVENTORY_SLOT_TYPE = "inventory";
    public static final String HEALTH_TYPE = "health";

    private AnimationFactory animationFactory;
    private HashMap<String, AnimationIndexData> animationDataMap;

    @Inject
    public HudAssetHelper(AnimationFactory animationFactory, HashMap<String, AnimationIndexData> animationDataMap) {
        this.animationFactory = animationFactory;
        this.animationDataMap = animationDataMap;
    }

    public TextureRegion getTextureRegion(String type, AssetManager assetManager) {
        AnimationIndex index = animationFactory.createAnimationIndex(type, assetManager);
        return index.getKeyFrame(0);
    }

    public AnimationIndex getAnimationIndex(String type, AssetManager assetManager) {
        return animationFactory.createAnimationIndex(type, assetManager);
    }

    public void loadAssets(AssetManager assetManager) {
        loadAnimationIndexData(assetManager, animationDataMap.get(INVENTORY_SLOT_TYPE));
        loadAnimationIndexData(assetManager, animationDataMap.get(HEALTH_TYPE));
    }

    private void loadAnimationIndexData(AssetManager assetManager, AnimationIndexData data) {
        assetManager.load(data.getTexturePath(), Texture.class);
    }
}
