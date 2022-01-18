package eu.timerertim.coobra;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameScene;

public class Main extends GameApplication {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void initSettings(GameSettings settings) {
        // Get size of screen
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        settings.setWidth((int) size.getWidth() / 2);
        settings.setHeight((int) size.getHeight() / 2);
        settings.setTitle("Coobra - Snake Multiplayer");
        settings.setAppIcon("snake_icon.png");
        settings.setVersion("");
        settings.setManualResizeEnabled(true);
        settings.setPreserveResizeRatio(true);
    }

    @Override
    public void initUI() {
        getGameScene().getRoot().setCursor(Cursor.DEFAULT);
    }

    @Override
    protected void initGame() {
        FXGL.entityBuilder()
                .at(150, 150)
                .view(new Rectangle(40, 40, Color.BLUE))
                .buildAndAttach();
    }
}
