package org.example.minesweeper;

import java.util.Objects;

public class Flag {
    int x, y;

    public Flag(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flag flag = (Flag) o;
        return x == flag.x && y == flag.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
