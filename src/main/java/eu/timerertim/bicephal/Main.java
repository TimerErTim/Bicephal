package eu.timerertim.bicephal;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameScene;

public class Main extends GameApplication {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void initSettings(GameSettings settings) {
        settings.setWidth(720);
        settings.setHeight(640);
        settings.setTitle("Kotlin Game - Target Practice");
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
