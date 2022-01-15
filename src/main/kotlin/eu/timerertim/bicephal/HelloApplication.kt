package eu.timerertim.bicephal

import com.almasb.fxgl.app.GameApplication
import com.almasb.fxgl.app.GameSettings

class HelloApplication : GameApplication() {
    override fun initSettings(settings: GameSettings) {
        with(settings) {
            width = 720
            height = 640
            title = "Kotlin Game - Target Practice"
        }
    }
}

fun main() {
    GameApplication.launch(HelloApplication::class.java, emptyArray())
}
