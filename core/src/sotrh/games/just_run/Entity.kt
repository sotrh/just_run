package sotrh.games.just_run

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2

abstract class Entity {
    lateinit var texture: Texture

    val position = Vector2()
    val velocity = Vector2()
    val acceleration = Vector2()

    open fun update(delta: Float) {
        val scaledVelocity = velocity.cpy().scl(delta)
        position.add(scaledVelocity)

        val scaledAcceleration = acceleration.cpy().scl(delta)
        velocity.add(scaledAcceleration)
    }

    open fun draw(batch: SpriteBatch) {
        batch.draw(texture, position.x, position.y)
    }

    open fun dispose() {
        texture.dispose()
    }
}