package org.example.minesweeper;

import java.util.Random;

public class MineField {
    Cell[][] cells;

    public MineField() {
        this.cells = new Cell[10][10];
        initializeCell();

    }

    private void initializeCell() {
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[x].length; y++) {
                cells[x][y] = new Cell(CellValue.Empty);
            }
        }
        placeMines(10);
        calculateNumbers();
    }


    private void calculateNumbers() {
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[x].length; y++) {
                if(cells[x][y].value != CellValue.Mine){
                    cells[x][y].value = mineCount(x,y);
                }
            }
        }

    }

    private CellValue mineCount(int x, int y) {

        return null;
    }

    private void placeMines(int i) {
        Random r = new Random();
        for (int z = 0; z < i; ) {
            int x = Math.abs(r.nextInt()) % 10;
            int y = Math.abs(r.nextInt()) % 10;
            if (cells[x][y].value == CellValue.Empty) {
                cells[x][y].value = CellValue.Mine;
                z++;
            }
        }
    }


}
