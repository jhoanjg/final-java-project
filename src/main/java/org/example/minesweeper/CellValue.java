package org.example.minesweeper;

public enum CellValue {
    Empty(" "), One("1"), Two("2"), Three("3"), Four("4"), Five("5"), Six("6"), Seven("7"), Eight("8"), Mine("*");

    public final String glyph;

    CellValue(String glyph) {
        this.glyph = glyph;
    }

    public static CellValue fromInt(int i) {
        for (CellValue c : CellValue.values())
            if (c.ordinal() == i)
                return c;
        return null;
    }
}
