package eu.timerertim.coobra.components;

import com.almasb.fxgl.entity.component.Component;
import eu.timerertim.coobra.Main;
import eu.timerertim.coobra.grid.CellState;
import javafx.util.Pair;

import java.util.LinkedList;

public class SnakeComponent extends Component {
    private int xPos;
    private int yPos;
    private String direction = "";
    private final LinkedList<Pair<Integer, Integer>> snakeParts = new LinkedList<>();

    public SnakeComponent(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;

        for (int i = 0; i < 10; i++) {
            snakeParts.add(new Pair<>(xPos, yPos));
        }
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
        Pair<Integer, Integer> newHead = new Pair<>(xPos % Main.GRID_CELL_SIZE, yPos % Main.GRID_CELL_SIZE);
        Pair<Integer, Integer> lastPart = snakeParts.poll();
        Main.grid[lastPart.getKey()][lastPart.getValue()].setCellState(CellState.Empty);
        snakeParts.forEach(p -> {
            Main.grid[p.getKey()][p.getValue()].setCellState(CellState.SnakeBody);
        });
        Main.grid[newHead.getKey()][newHead.getValue()].setCellState(CellState.SnakeHead);
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
