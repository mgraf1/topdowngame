package net.mikegraf.game.parsers

data class AnimationData(val name: String, val startingFrame: Int, val endingFrame: Int)

data class AnimationIndexData(val type: String, val texturePath: String, val sheetWidth: Int, val sheetHeight: Int,
                              val animationData: List<AnimationData>?)

data class BodyData(val type: String, val bodyType: String, val damp: Float, val velocity: Float)

data class FontData(val name: String, val path: String)

data class LevelData(val name: String, val fileName: String)

data class SoundData(val type: String, val soundEffectData: List<SoundEffectData>)

data class SoundEffectData(val name: String, val path: String)