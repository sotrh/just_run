package sotrh.games.just_run

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture

class Enemy : Entity(Texture(Gdx.files.internal("runner.png"))) {

    init {
        velocity.set(-100f, 0f)
        position.set(Gdx.graphics.width.toFloat(), 0f)
    }

    override fun update(delta: Float) {
        super.update(delta)
    }
}