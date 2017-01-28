package net.mikegraf.game.states.play.levels;

import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.google.inject.Inject;

import net.mikegraf.game.main.constants.TiledConstants;
import net.mikegraf.game.parsers.models.AnimationIndexData;
import net.mikegraf.game.parsers.models.SoundData;
import net.mikegraf.game.parsers.models.SoundEffectData;

public class LevelAssetLoader {
	
	private HashMap<String, AnimationIndexData> animationDataMap;
	private HashMap<String, SoundData> soundDataMap;

	@Inject
	public LevelAssetLoader(HashMap<String, AnimationIndexData> animationDataMap, HashMap<String, SoundData> soundDataMap) {
		this.animationDataMap = animationDataMap;
		this.soundDataMap = soundDataMap;
	}
	
	public void loadAssets(MapLayers layers, AssetManager assetManager) {
		MapLayer objectLayer = layers.get(TiledConstants.LAYER_OBJECT);
		loadAssetsForLayer(objectLayer, assetManager);
		
		MapLayer itemLayer = layers.get(TiledConstants.LAYER_ITEM);
		loadAssetsForLayer(itemLayer, assetManager);
		
		MapLayer playerLayer = layers.get(TiledConstants.LAYER_PLAYER);
		loadAssetsForLayer(playerLayer, assetManager);
	}
	
	private void loadAssetsForLayer(MapLayer layer, AssetManager assetManager) {
		for (MapObject mo : layer.getObjects()) {
			MapProperties props = mo.getProperties();
			String textureProp = props.get(TiledConstants.ENTITY_TEXTURE, String.class);
			String entityType = props.get(TiledConstants.ENTITY_TYPE, String.class);
			
			AnimationIndexData animationData = animationDataMap.get(textureProp);		
			String texturePath = animationData.getTexturePath();
			
			assetManager.load(texturePath, Texture.class);
			
			if (soundDataMap.containsKey(entityType)) {
				SoundData soundData = soundDataMap.get(entityType);
				List<SoundEffectData> sfxDataList = soundData.getSoundEffectData();
				for (SoundEffectData sfxData : sfxDataList) {
					String sfxPath = sfxData.getPath();
					assetManager.load(sfxPath, Sound.class);
				}
			}
		}
	}
}
