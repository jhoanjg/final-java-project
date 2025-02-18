package org.example.minesweeper;

import java.util.Random;

public class MineField {
    Cell[][] cells;

    public MineField() {
        this.cells = new Cell[10][10];
        initializeCells();
    }

    void initializeCells() {
        clearCells();
        placeMines(10);
        calculateNumbers();
    }

    void clearCells() {
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[x].length; y++) {
                cells[x][y] = new Cell(CellValue.Empty);
            }
        }
    }

    void calculateNumbers() {
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[x].length; y++) {
                if (cells[x][y].value != CellValue.Mine) {
                    cells[x][y].value = mineCount(x, y);
                }
            }
        }
    }

    CellValue mineCount(int cx, int cy) {
        int count = 0;
        for (int x = Math.max(0, cx - 1); x <= Math.min(9, cx + 1); x++)
            for (int y = Math.max(0, cy - 1); y <= Math.min(9, cy + 1); y++)
                if (cells[x][y].value == CellValue.Mine)
                    count++;

        return CellValue.fromInt(count);
    }

    void placeMines(int i) {
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

    public void draw(TerminalDisplay t) {
        for (int x = 0; x < cells.length; x++)
            for (int y = 0; y < cells[x].length; y++)
                if (cells[x][y].isFlagged)
                    t.putCharacter(x, y, '#');
                else if (cells[x][y].isHidden)
                    t.putCharacter(x, y, '~');
                else
                    t.putCharacter(x, y, cells[x][y].value.glyph.charAt(0));


    }

    public void expand(int x, int y) {
        if (!cells[x][y].isHidden)
            return;

        cells[x][y].isHidden = false;
        if (cells[x][y].value == CellValue.Mine)
            showAllMines();
        if (cells[x][y].value == CellValue.Empty)
            for (int coll = Math.max(0, x - 1); coll <= Math.min(9, x + 1); coll++)
                for (int row = Math.max(0, y - 1); row <= Math.min(9, y + 1); row++)
                    expand(coll, row);
    }

    private void showAllMines() {
        for (int x = 0; x < cells.length; x++)
            for (int y = 0; y < cells[x].length; y++)
                if (cells[x][y].value == CellValue.Mine)
                    cells[x][y].isHidden = false;
    }

    boolean areAllMinesFlagged() {
        return false;
    }

    boolean isPlayerDead() {
        return false;
    }
}
