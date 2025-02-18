package org.example.minesweeper;

public class Cell {
    public CellValue value;

    public boolean isFlagged;
    public boolean isHidden;

    public Cell(CellValue value) {
        this.value = value;
        this.isFlagged = false;
        this.isHidden = true;
    }


}
