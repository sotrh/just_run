package sotrh.games.just_run

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture

class Runner : Entity(Texture("runner.png")) {

    override fun update(delta: Float) {

        val isJumpPressed = Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isButtonPressed(Input.Buttons.LEFT)

        // if the player is below the floor, snap them to it and set
        // vertical velocity and acceleration to 0
        if (position.y < 0f) {
            position.y = 0f
            velocity.y = 0f
            acceleration.set(0f, 0f)
        } else if (position.y == 0f && isJumpPressed) {
            velocity.y = texture.height * 4.0f
            acceleration.y = texture.height * -4.0f
        }

        super.update(delta)
    }
}