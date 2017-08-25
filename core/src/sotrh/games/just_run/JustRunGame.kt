package sotrh.games.just_run

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class JustRunGame : ApplicationAdapter() {
    private lateinit var batch: SpriteBatch
    private lateinit var runner: Runner

    enum class GameState {
        Playing, Paused, GameOver
    }
    private var gameState = GameState.Playing

    private val enemies = mutableListOf<Enemy>()
    private val enemiesToDelete = mutableListOf<Enemy>()
    private val maxEnemies = 5
    private val enemySpanRate = 5.0f // enemy spawns per second
    private var timeSinceLastEnemySpawned = 0.0f

    override fun create() {
        batch = SpriteBatch()
        runner = Runner()
    }

    override fun render() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) Gdx.app.exit()

        val delta = Gdx.graphics.deltaTime

        if (gameState == GameState.Playing) {
            runner.update(delta)

            enemies.forEach {
                it.update(delta)
                if (it.position.x <= -it.texture.width) enemiesToDelete.add(it)
                if (it.isCollidingWith(runner)) {
                    Gdx.app.log("enemies.forEach", "enemy collided with player")
                    gameState = GameState.GameOver
                }
            }
            enemies.removeAll(enemiesToDelete)
            enemiesToDelete.forEach { it.dispose() }
            enemiesToDelete.clear()

            timeSinceLastEnemySpawned += delta
            if (timeSinceLastEnemySpawned >= enemySpanRate && enemies.size < maxEnemies) {
                val enemy = Enemy()
                enemies.add(enemy)
                timeSinceLastEnemySpawned = 0f
            }
        }

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch.begin()
        runner.draw(batch)
        enemies.forEach { it.draw(batch) }
        batch.end()
    }

    override fun dispose() {
        batch.dispose()
        runner.dispose()

        enemies.forEach { it.dispose() }
        enemies.clear()
    }
}
