package eu.timerertim.coobra;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.MenuItem;
import com.almasb.fxgl.entity.Entity;
import eu.timerertim.coobra.arguments.Argument;
import eu.timerertim.coobra.arguments.ArgumentTable;
import eu.timerertim.coobra.components.SnakeComponent;
import eu.timerertim.coobra.grid.Cell;
import eu.timerertim.coobra.grid.CellState;
import eu.timerertim.coobra.scene.SnakeSceneFactory;
import javafx.scene.Cursor;
import javafx.scene.input.KeyCode;

import java.util.EnumSet;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameScene;

public class Main extends GameApplication {

    public static final int GRID_CELL_SIZE = 25;
    private static final int CELL_SIZE = 40;

    public static final Cell[][] grid = new Cell[GRID_CELL_SIZE][GRID_CELL_SIZE];

    private Entity player;
    private Entity p2;

    private final int xPos = 5;
    private final int yPos = 5;

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

        settings.setTicksPerSecond(10);
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
                entityBuilder()
                        .at(x, y)
                        .view(cell)
                        .buildAndAttach();
            }
        }

        getGameWorld().addEntityFactory(new SnakeGameFactory());
        this.player = spawn("snakeHead");
        player.addComponent(new SnakeComponent(xPos, yPos));

        this.p2 = spawn("snakeHead");
        p2.addComponent(new SnakeComponent(0, 0));
    }

    @Override
    protected void initInput() {
        onKeyDown(KeyCode.LEFT, () -> this.player.getComponentOptional(SnakeComponent.class).ifPresent(
                SnakeComponent::left));
        onKeyDown(KeyCode.RIGHT, () -> this.player.getComponentOptional(SnakeComponent.class).ifPresent(
                SnakeComponent::right));
        onKeyDown(KeyCode.UP, () -> this.player.getComponentOptional(SnakeComponent.class).ifPresent(
                SnakeComponent::up));
        onKeyDown(KeyCode.DOWN, () -> this.player.getComponentOptional(SnakeComponent.class).ifPresent(
                SnakeComponent::down));

        onKeyDown(KeyCode.A, () -> this.p2.getComponentOptional(SnakeComponent.class).ifPresent(
                SnakeComponent::left));
        onKeyDown(KeyCode.D, () -> this.p2.getComponentOptional(SnakeComponent.class).ifPresent(
                SnakeComponent::right));
        onKeyDown(KeyCode.W, () -> this.p2.getComponentOptional(SnakeComponent.class).ifPresent(
                SnakeComponent::up));
        onKeyDown(KeyCode.S, () -> this.p2.getComponentOptional(SnakeComponent.class).ifPresent(
                SnakeComponent::down));
    }

}
