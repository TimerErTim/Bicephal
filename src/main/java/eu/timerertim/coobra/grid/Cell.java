package eu.timerertim.coobra.grid;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Cell extends StackPane {
    private final int x;
    private final int y;
    private CellState state;
    private final Rectangle content;

    public Cell(int x, int y, CellState state, int cellSize) {
        this.x = x;
        this.y = y;
        this.state = state;

        setTranslateX(x * cellSize);
        setTranslateY(y * cellSize);

        Rectangle rect = new Rectangle(cellSize, cellSize, Color.WHITE);
        rect.setStroke(Color.color(0.75, 0.75, 0.75, 0.5));
        content = rect;
        getChildren().add(rect);
    }

    public CellState getCellState() {
        return state;
    }

    public void setCellState(CellState state) {
        switch (state) {
            default -> content.setFill(Color.WHITE);
            case SnakeHead -> content.setFill(Color.BLUE);
            case SnakeBody -> content.setFill(Color.GREEN);
            case Food -> content.setFill(Color.RED);
        }
        this.state = state;
    }
}
