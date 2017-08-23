package sotrh.games.just_run

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class JustRunGame : ApplicationAdapter() {
    private lateinit var batch: SpriteBatch
    private lateinit var runner: Runner

    override fun create() {
        batch = SpriteBatch()
        runner = Runner()
    }

    override fun render() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) Gdx.app.exit()

        val delta = Gdx.graphics.deltaTime
        runner.update(delta)

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch.begin()
        runner.draw(batch)
        batch.end()
    }

    override fun dispose() {
        batch.dispose()
        runner.dispose()
    }
}
