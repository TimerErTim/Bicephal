package eu.timerertim.coobra.components;

import com.almasb.fxgl.entity.component.Component;
import eu.timerertim.coobra.grid.Cell;
import eu.timerertim.coobra.grid.CellState;
import eu.timerertim.coobra.grid.Grid;

import java.util.ArrayList;
import java.util.List;

public class FoodComponent extends Component {
    private final Grid grid;
    private final int maxFood;

    public FoodComponent(int food, Grid grid) {
        this.grid = grid;
        this.maxFood = food;
    }

    @Override
    public void onUpdate(double tpf) {
        int foodFound = 0;
        List<Cell> possibleFoodCells = new ArrayList<>();

        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                Cell cell = grid.getCells()[x][y];
                if (cell.getCellState() == CellState.Food) {
                    foodFound++;
                }
                if (cell.getCellState() == CellState.Empty) {
                    possibleFoodCells.add(cell);
                }
            }
        }

        while (foodFound < maxFood && possibleFoodCells.size() > 0) {
            int randomIndex = (int) (Math.random() * possibleFoodCells.size());
            Cell cell = possibleFoodCells.remove(randomIndex);
            cell.setCellState(CellState.Food);
            foodFound++;
        }
    }
}
