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
                assertTrue(n.cells[x][y].hidden);
            }
        }
    }

    @Test
    public void cells_are_not_flagged() {
        MineField n;
        n = new MineField();

        for (int x = 0; x < n.cells.length; x++) {
            for (int y = 0; y < n.cells[x].length; y++) {
                assertFalse(n.isFlagged(x, y));
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


    @Test
    public void expanding_a_cell_containing_a_number_expands_only_that_t_cell() {
        MineField n = new MineField();
        n.clearCells();

        n.cells[3][5].value = CellValue.One;
        n.expand(3, 5);

        for (int x = 0; x < n.cells.length; x++)
            for (int y = 0; y < n.cells[x].length; y++)
                if (x != 3 && y != 5)
                    assertTrue(n.cells[x][y].hidden);

        assertFalse(n.cells[3][5].hidden);

    }

    @Test
    public void expanding_a_mine_shows_all_mines() {
        MineField n = new MineField();
        n.clearCells();

        n.cells[1][5].value = CellValue.Mine;
        n.cells[4][5].value = CellValue.Mine;
        n.cells[8][5].value = CellValue.Mine;
        n.expand(4, 5);

        assertFalse(n.cells[1][5].hidden);
        assertFalse(n.cells[4][5].hidden);
        assertFalse(n.cells[8][5].hidden);

    }

    /**
     * Assume the grid looks like this:
     * <p>
     * -----------
     * |  *
     * |12121
     * |*1 1*
     * |22 22
     * |*1 1*
     * |12121
     * |  *
     */
    @Test
    public void expanding_an_empty_cell_recursively_shows_adjacent_empty_cells_including_the_numbers_around_them() {
        MineField n = new MineField();
        n.clearCells();
        n.cells[2][0].value = CellValue.Mine;
        n.cells[0][2].value = CellValue.Mine;
        n.cells[0][4].value = CellValue.Mine;
        n.cells[4][2].value = CellValue.Mine;
        n.cells[4][4].value = CellValue.Mine;
        n.cells[2][6].value = CellValue.Mine;
        n.calculateNumbers();

        n.expand(2, 4);

        for (int x = 0; x < n.cells.length; x++)
            for (int y = 0; y < n.cells[x].length; y++)
                if (x > 0 && x < 4 && y > 0 && y < 6)
                    assertFalse(n.cells[x][y].hidden);
                else
                    assertTrue(n.cells[x][y].hidden);

    }

    @Test
    public void toggleFlag_puts_a_flag_on_a_cell() {
        MineField n = new MineField();
        n.clearCells();

        n.toggleFlag(2, 2);

        assertTrue(n.isFlagged(2, 2));
    }

    @Test
    public void toggleFlag_prevents_more_flags_than_mines() {
        MineField n = new MineField(3);

        n.toggleFlag(1, 2);
        assertTrue(n.isFlagged(1, 2));
        n.toggleFlag(2, 2);
        assertTrue(n.isFlagged(2, 2));
        n.toggleFlag(3, 2);
        assertTrue(n.isFlagged(3, 2));
        n.toggleFlag(4, 2);
        assertFalse(n.isFlagged(4, 2));
    }

    @Test
    public void toggleFlag_removes_a_flag_if_it_was_there() {
        MineField n = new MineField();
        n.clearCells();

        n.toggleFlag(2, 2);
        n.toggleFlag(2, 2);

        assertFalse(n.isFlagged(2, 2));
    }

    @Test
    public void areAllMinesFlagged_returns_true_when_all_mines_have_a_flag() {
        MineField n = new MineField(3);
        n.clearCells();
        n.cells[2][0].value = CellValue.Mine;
        n.cells[0][2].value = CellValue.Mine;
        n.cells[0][4].value = CellValue.Mine;
        n.toggleFlag(2, 0);
        n.toggleFlag(0, 2);
        n.toggleFlag(0, 4);

        boolean actual = n.areAllMinesFlagged();

        assertTrue(actual);
    }

    @Test
    public void areAllMinesFlagged_return_false_when_mines_are_insufficiently_flagged() {
        MineField n = new MineField(3);
        n.clearCells();
        n.cells[2][0].value = CellValue.Mine;
        n.cells[0][2].value = CellValue.Mine;
        n.cells[0][4].value = CellValue.Mine;
        n.toggleFlag(2, 0);
        n.toggleFlag(0, 2);
        // Flag is in the WRONG spot
        n.toggleFlag(6, 0);

        boolean actual = n.areAllMinesFlagged();

        assertFalse(actual);
    }

    @Test
    public void isPlayerDead_return_true_when_expanding_a_mine() {
        MineField n = new MineField(1);
        n.clearCells();
        n.cells[2][0].value = CellValue.Mine;
        n.cells[2][0].hidden = false;

        boolean result = n.isPlayerDead();

        assertTrue(result);

    }

    @Test
    public void isPlayerDead_return_false_when_no_mines_are_exposed() {
        MineField n = new MineField(2);
        n.clearCells();
        n.cells[2][0].value = CellValue.Mine;
        n.cells[0][4].value = CellValue.Mine;
        n.cells[2][0].hidden = true;
        n.cells[0][4].hidden = true;

        boolean result = n.isPlayerDead();

        assertFalse(result);
    }
}
