package eu.timerertim.coobra;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import eu.timerertim.coobra.components.SnakeComponent;
import eu.timerertim.coobra.grid.CellState;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SnakeGameFactory implements EntityFactory {
    @Spawns("snakeHead")
    public Entity newSnakeHead(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(CellState.SnakeHead)
                .collidable()
                .buildAndAttach();
    }
}
