package eu.timerertim.coobra.grid

import javafx.scene.layout.StackPane

class Grid(val width: Int, val height: Int, val cellSize: Int) : StackPane() {
    val cells = Array(width) { x ->
        Array(height) { y ->
            val cell = Cell(x, y, CellState.Empty, cellSize)
            children += cell
            cell
        }
    }
}
