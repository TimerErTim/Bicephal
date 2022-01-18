package eu.timerertim.coobra.scene

import com.almasb.fxgl.app.scene.SceneFactory

object SnakeSceneFactory : SceneFactory() {
    override fun newStartup(width: Int, height: Int) = SnakeStartupScene(width, height)
}
