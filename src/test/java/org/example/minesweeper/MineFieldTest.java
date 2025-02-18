package org.example.minesweeper;

import static org.junit.Assert.*;
import org.junit.Test;
//Arrange
//Act
//Assert

public class MineFieldTest {
    @Test
    public void creates_a_10x10_grid() {
        MineField n;

        n = new MineField();

        assertEquals(10, n.cells.length);
        assertEquals(10, n.cells[0].length);
    }

    @Test
    public void cells_are_hidden() {
        MineField n;

        n = new MineField();

        for (int x = 0; x < n.cells.length; x++) {
            for (int y = 0; y < n.cells[x].length; y++) {
                assertTrue(n.cells[x][y].isHidden);
            }
        }
    }

    @Test
    public void cells_are_not_flagged() {
        MineField n;
        n = new MineField();

        for (int x = 0; x < n.cells.length; x++) {
            for (int y = 0; y < n.cells[x].length; y++) {
                assertFalse(n.cells[x][y].isFlagged);
            }
        }
    }

    @Test
    public void there_are_10_mines() {
        MineField n;
        int numberOfMines = 0;

        n = new MineField();

        for (int x = 0; x < n.cells.length; x++) {
            for (int y = 0; y < n.cells[x].length; y++) {
                if (n.cells[x][y].value == CellValue.Mine)
                    numberOfMines++;
            }
        }
        assertEquals(10, numberOfMines);
    }

    @Test
    public void mineCount_returns_empty_where_there_are_no_mines() {
        MineField n = new MineField();
        n.clearCells();

        assertEquals(CellValue.Empty, n.mineCount(0, 0));
        assertEquals(CellValue.Empty, n.mineCount(5, 5));
        assertEquals(CellValue.Empty, n.mineCount(9, 9));
    }

    @Test
    public void mineCount_correctly_calculates_at_the_left_edge() {
        MineField n = new MineField();

        n.clearCells();

        n.cells[0][3].value = CellValue.Mine;
        assertEquals(CellValue.One, n.mineCount(0, 4));
        n.cells[0][5].value = CellValue.Mine;
        assertEquals(CellValue.Two, n.mineCount(0, 4));
    }

    @Test
    public void mineCount_correctly_calculates_at_the_right_edge() {
        MineField n = new MineField();
        n.clearCells();

        n.cells[9][3].value = CellValue.Mine;
        assertEquals(CellValue.One, n.mineCount(9, 4));

        n.cells[9][5].value = CellValue.Mine;
        assertEquals(CellValue.Two, n.mineCount(9, 4));
    }

    @Test
    public void mineCount_correctly_calculates_at_the_top() {
        MineField n = new MineField();
        n.clearCells();

        n.cells[3][0].value = CellValue.Mine;
        assertEquals(CellValue.One, n.mineCount(4, 0));

        n.cells[5][0].value = CellValue.Mine;
        assertEquals(CellValue.Two, n.mineCount(4, 0));
    }

    @Test
    public void mineCount_correctly_calculates_at_the_bottom() {
        MineField n = new MineField();
        n.clearCells();

        n.cells[3][9].value = CellValue.Mine;
        assertEquals(CellValue.One, n.mineCount(4, 9));

        n.cells[5][9].value = CellValue.Mine;
        assertEquals(CellValue.Two, n.mineCount(4, 9));
    }

    @Test
    public void mineCount_correctly_calculates_within_the_interior() {
        MineField n = new MineField();
        n.clearCells();

        n.cells[4][4].value = CellValue.Mine;
        assertEquals(CellValue.One, n.mineCount(5, 5));
        n.cells[5][4].value = CellValue.Mine;
        assertEquals(CellValue.Two, n.mineCount(5, 5));
        n.cells[6][4].value = CellValue.Mine;
        assertEquals(CellValue.Three, n.mineCount(5, 5));
        n.cells[4][5].value = CellValue.Mine;
        assertEquals(CellValue.Four, n.mineCount(5, 5));
        n.cells[6][5].value = CellValue.Mine;
        assertEquals(CellValue.Five, n.mineCount(5, 5));
        n.cells[4][6].value = CellValue.Mine;
        assertEquals(CellValue.Six, n.mineCount(5, 5));
        n.cells[5][6].value = CellValue.Mine;
        assertEquals(CellValue.Seven, n.mineCount(5, 5));
        n.cells[6][6].value = CellValue.Mine;
        assertEquals(CellValue.Eight, n.mineCount(5, 5));
    }
}
