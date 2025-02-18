package org.example.minesweeper;

public class Cell {
    public CellValue value;

    public boolean hidden;

    public Cell(CellValue value) {
        this.value = value;
        this.hidden = true;
    }
}
