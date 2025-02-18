package org.example.minesweeper;

public class Main {
    public static void main(String[] args) {
        int y = 0;
        int x = 0;

        TerminalDisplay t = new TerminalDisplay();
        MineField n = new MineField();
        do {
            t.setCursorPosition(x, y);
            n.draw(t);

            char keyPress = t.getNextKeypress();

            switch (keyPress) {
                case 'q':
                    System.exit(1);
                case 'h':
                    if (x > 0)
                        x--;
                    break;
                case 'j':
                    if (y < 9) y++;
            }


        } while (!n.isPlayerDead() || !n.areAllMinesFlagged());
    }
}
