package org.example.minesweeper;

public class Main {
    public static void main(String[] args) {
        int y = 0;
        int x = 0;

        TerminalDisplay t = new TerminalDisplay();
        MineField n = new MineField();
        do {
            n.draw(t);
            t.setCursorPosition(x, y);
            t.flush();

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
                    break;
                case 'k':
                    if (y > 0) y--;
                    break;
                case 'l':
                    if (x < 9) x++;
                    break;
                case ' ':
                    n.expand(x, y);
                    break;
                case 'f':
                    n.toggleFlag(x, y);
                    break;
            }
        } while (!n.isPlayerDead() && !n.areAllMinesFlagged());
        if (n.isPlayerDead())
            System.out.println("You Lost!!");
        else System.out.println("You Won :)");
        System.exit(1);
    }
}
