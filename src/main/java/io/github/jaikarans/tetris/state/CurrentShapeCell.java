package io.github.jaikarans.tetris.state;

public class CurrentShapeCell {
    public int x;
    public int y;
    public boolean isCenterCell;
    public CurrentShapeCell(int x, int y) {
        this.x = x;
        this.y = y;
        this.isCenterCell = false;
    }

    public CurrentShapeCell(int x, int y, boolean isCenterCell) {
        this.x = x;
        this.y = y;
        this.isCenterCell = isCenterCell;
    }
}
