package net.mikegraf.game.states.play.entities.view

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.glutils.ShaderProgram
import com.badlogic.gdx.math.Vector2

import net.mikegraf.game.main.helpers.Box2dHelper

class AnimationView(private val animationIndex: AnimationIndex, private val flashShader: ShaderProgram) : IView {
    private val height: Float
    private val width: Float
    private val renderVector: Vector2
    private var region: TextureRegion
    private var flashTime: Float
    private var isFlashing = false

    init {
        this.region = animationIndex.getKeyFrame(0f)
        this.height = region.regionHeight.toFloat()
        this.width = region.regionWidth.toFloat()
        this.renderVector = Vector2()
        this.flashTime = 0f
    }

    override fun render(batch: SpriteBatch, totalTime: Float, position: Vector2) {
        Box2dHelper.toRenderCoords(renderVector, position, width, height)
        region = animationIndex.getKeyFrame(totalTime)

        batch.begin()

        if (isFlashing) {
            performFlash(batch, totalTime)
        }
        batch.draw(region, renderVector.x, renderVector.y)
        batch.shader = null

        batch.end()
    }

    private fun performFlash(batch: SpriteBatch, totalTime: Float) {
        batch.shader = flashShader
        val flashAmount = pulse(flashTime)
        if (flashTime < FLASH_TIME) {
            flashShader.setUniformf("u_whiteness", flashAmount)
            flashTime += totalTime
        } else {
            isFlashing = false
            flashTime = 0f
        }
    }

    // Gets a value of a sin curve between -1 <-> 1
    private fun pulse(time: Float): Float {
        val pi = 3.14f
        return 0.5f * (1 + sinf((2f * pi * FLASH_FREQUENCY * (time + .25f))))
    }

    private fun sinf(num: Float): Float {
        return Math.sin(num.toDouble()).toFloat()
    }

    override fun render(batch: SpriteBatch, x: Float, y: Float, scale: Float) {
        batch.draw(region, x, y, width * scale, height * scale)
    }

    override fun render(batch: SpriteBatch, x: Float, y: Float) {
        batch.draw(region, x, y)
    }

    override fun setMode(mode: String) {
        when (mode) {
            FLASH_MODE -> isFlashing = true
            else -> animationIndex.setCurrentAnimation(mode)
        }
    }

    override fun dispose() {
        animationIndex.dispose()
    }

    companion object {
        private val FLASH_MODE = "flash"
        private val FLASH_TIME = .5f
        private val FLASH_FREQUENCY = .5f
    }
}
