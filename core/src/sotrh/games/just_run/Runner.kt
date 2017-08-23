package sotrh.games.just_run

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2

class Runner : Entity() {
    init {
        texture = Texture("badlogic.jpg")
    }

    override fun update(delta: Float) {

        val isJumpPressed = Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isButtonPressed(Input.Buttons.LEFT)

        // if the player is below the floor, snap them to it and set
        // vertical velocity and acceleration to 0
        if (position.y < 0f) {
            position.y = 0f
            velocity.y = 0f
            acceleration.set(0f, 0f)
        } else if (position.y == 0f && isJumpPressed) {
            velocity.y = texture.height * 0.5f
            acceleration.y = texture.height * -0.5f
        }

        super.update(delta)
    }
}