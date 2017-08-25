package sotrh.games.just_run

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2

abstract class Entity(val texture: Texture) {

    var bounds = Rectangle(0f, 0f, texture.width.toFloat(), texture.height.toFloat())
    val position = Vector2()
    val velocity = Vector2()
    val acceleration = Vector2()

    open fun isCollidingWith(other: Entity): Boolean {
        bounds.setPosition(position)
        other.bounds.setPosition(other.position)
        Gdx.app.log("isCollidingWith($other)", "bounds = $bounds")
        Gdx.app.log("isCollidingWith($other)", "other.bounds = ${other.bounds}")
        Gdx.app.log("isCollidingWith($other)", "bounds.contains(other.bounds) = ${bounds.contains(other.bounds)}")
        return bounds.overlaps(other.bounds)
    }

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