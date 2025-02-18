package org.example.minesweeper;

import org.junit.Test;

import static org.junit.Assert.*;
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
                assertTrue(n.cells[0][0].isHidden);
            }
        }
    }
}