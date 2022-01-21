package eu.timerertim.coobra.components;

import com.almasb.fxgl.entity.component.Component;
import eu.timerertim.coobra.Main;
import eu.timerertim.coobra.grid.Cell;
import eu.timerertim.coobra.grid.CellState;
import eu.timerertim.coobra.grid.Grid;
import javafx.util.Pair;

import java.util.LinkedList;

public class SnakeComponent extends Component {
    private final Grid grid;
    private int xPos;
    private int yPos;
    private String direction = "";
    private int length;
    private final LinkedList<Pair<Integer, Integer>> snakeParts = new LinkedList<>();

    public SnakeComponent(int xPos, int yPos, Grid grid) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.grid = grid;
        this.length = 2;
    }

    public void addParts(int parts) {
        length += parts;
    }

    @Override
    public void onUpdate(double tpf) {
        double speed = tpf * 60;
        switch (direction) {
            //position.translateX(5 * speed);
            case "up" -> yPos -= 1;
            case "down" -> yPos += 1;
            case "left" -> xPos -= 1;
            case "right" -> xPos += 1;
        }
        if (yPos < 0) yPos = Main.GRID_CELL_SIZE - 1;
        if (xPos < 0) xPos = Main.GRID_CELL_SIZE - 1;
        Pair<Integer, Integer> newHead = new Pair<>(xPos % grid.getWidth(), yPos % grid.getHeight());
        if (snakeParts.size() > length) {
            Pair<Integer, Integer> lastPart = snakeParts.poll();
            grid.getCells()[lastPart.getKey()][lastPart.getValue()].setCellState(CellState.Empty);
        }
        snakeParts.forEach(p -> {
            grid.getCells()[p.getKey()][p.getValue()].setCellState(CellState.SnakeBody);
        });
        Cell newHeadCell = grid.getCells()[newHead.getKey()][newHead.getValue()];
        if (newHeadCell.getCellState() == CellState.Food) addParts(2);
        newHeadCell.setCellState(CellState.SnakeHead);
        snakeParts.add(newHead);
    }

    public void up() {
        if(!direction.equals("down")) {
            direction = "up";
        }
    }

    public void down() {
        if(!direction.equals("up")) {
            direction = "down";
        }
    }

    public void left() {
        if(!direction.equals("right")) {
            direction = "left";
        }
    }

    public void right() {
        if(!direction.equals("left")) {
            direction = "right";
        }
    }
}
