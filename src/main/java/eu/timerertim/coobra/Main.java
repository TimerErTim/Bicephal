package eu.timerertim.coobra;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.MenuItem;
import com.almasb.fxgl.dsl.FXGL;
import eu.timerertim.coobra.arguments.Argument;
import eu.timerertim.coobra.arguments.ArgumentTable;
import eu.timerertim.coobra.grid.Cell;
import eu.timerertim.coobra.grid.CellState;
import eu.timerertim.coobra.scene.SnakeSceneFactory;
import javafx.scene.Cursor;

import java.util.EnumSet;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameScene;

public class Main extends GameApplication {

    private final int GRID_CELL_SIZE = 25;
    private final int CELL_SIZE = 40;

    private final Cell[][] grid = new Cell[GRID_CELL_SIZE][GRID_CELL_SIZE];

    public static void main(String[] args) {
        ArgumentTable.parse(args);
        launch(args);
    }

    @Override
    public void initSettings(GameSettings settings) {
        settings.setTitle(ArgumentTable.get(Argument.TITLE));
        settings.setAppIcon("snake_icon.png");
        settings.setVersion(ArgumentTable.get(Argument.VERSION));

        settings.setHeight(1024);
        settings.setWidthFromRatio(1);

        settings.setManualResizeEnabled(true);
        settings.setPreserveResizeRatio(true);

        settings.setSceneFactory(SnakeSceneFactory.INSTANCE);
        settings.setNative(true);

        settings.setEnabledMenuItems(EnumSet.of(MenuItem.EXTRA));
        settings.setCredits(ArgumentTable.get(Argument.CREDITS));
        settings.setDeveloperMenuEnabled(ArgumentTable.get(Argument.DEVELOPER_MODE));
    }

    @Override
    public void initUI() {
        getGameScene().setCursor(Cursor.DEFAULT);
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
