package eu.timerertim.coobra.scene

import com.almasb.fxgl.app.scene.StartupScene
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.text.Font
import javafx.scene.text.Text

class SnakeStartupScene(appWidth: Int, appHeight: Int) : StartupScene(appWidth, appHeight) {
    init {
        val image = Image("assets/textures/snake_icon.png")
        val imageView = ImageView(image).apply {
            resize(appWidth / 2.0, appHeight / 2.0)
            relocate(appWidth / 5.0, appHeight / 5.0)
        }
        val text = Text("Coobra").apply {
            font = Font.font(appHeight / 15.0)
            relocate((appWidth - width) / 2.5, appHeight - (appHeight - height) / 7.5)
        }

        contentRoot.children.addAll(imageView, text)
    }
}
