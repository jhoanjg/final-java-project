package org.example.minesweeper;

public class Main {
    public static void main(String[] args) {
        TerminalDisplay t = new TerminalDisplay();
        MineField n = new MineField();

        n.draw(t);
        t.flush();

    }
}
