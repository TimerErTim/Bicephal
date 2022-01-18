package eu.timerertim.coobra;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import eu.timerertim.coobra.grid.Cell;
import eu.timerertim.coobra.grid.CellState;
import javafx.scene.Cursor;

import java.awt.*;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameScene;

public class Main extends GameApplication {

    private final int GRID_CELL_SIZE = 10;
    private final int CELL_SIZE = 44;

    private Cell[][] grid = new Cell[GRID_CELL_SIZE][GRID_CELL_SIZE];

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void initSettings(GameSettings settings) {
        // Get size of screen
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        settings.setWidth((int) size.getWidth() / 2);
        settings.setHeight((int) size.getHeight() / 2);
        settings.setWidthFromRatio(1);
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
        for (int y = 0; y < GRID_CELL_SIZE; y++) {
            for (int x = 0; x < GRID_CELL_SIZE; x++) {
                Cell cell = new Cell(x, y, CellState.Empty, CELL_SIZE);
                grid[x][y] = cell;
                FXGL.entityBuilder()
                        .at(x, y)
                        .view(cell)
                        .buildAndAttach();
            }
        }
    }

}
