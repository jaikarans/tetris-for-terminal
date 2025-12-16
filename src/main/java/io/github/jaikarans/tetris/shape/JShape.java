package io.github.jaikarans.tetris.shape;

import io.github.jaikarans.tetris.state.CurrentShapeCell;
import io.github.jaikarans.tetris.state.GameState;

import java.util.Arrays;

public class JShape extends Shape{
    public JShape(GameState state) {
        super(state);
        degree = 0;
        generateShape();
    }

    @Override
    public ShapeType getType() {
        return ShapeType.J;
    }

    @Override
    public void generateShape() {
        int col = 5, row = 2;
        s.shapeCells[0] = new CurrentShapeCell(row, col, true);
        s.shapeCells[1] = new CurrentShapeCell(row, col - 1);
        s.shapeCells[2] = new CurrentShapeCell(row, col + 1);
        s.shapeCells[3] = new CurrentShapeCell(row - 1, col - 1);

    }

    @Override
    public void rotate() {
        //center cell location this center cell will not move except for I shape
        int r = -1, c = -1;
        for (CurrentShapeCell cell: s.shapeCells) {
            if (cell.isCenterCell) {
                r = cell.x;
                c = cell.y;
                break;
            }
        }

        // edge case handing
        if (r - 1 < 0 || r + 1 >= s.height) return;
        if (c - 1 < 0 || c + 1 >= s.width) return;

        // 0 degree
        switch (degree) {
            case 0 -> {
                if (s.arr[r + 1][c] == 0 &&
                        s.arr[r - 1][c] == 0 &&
                        s.arr[r - 1][c + 1] == 0) {
                    Arrays.fill(s.shapeCells, null);
                    s.arr[r][c - 1] = 0;
                    s.arr[r][c + 1] = 0;
                    s.arr[r - 1][c - 1] = 0;
                    s.shapeCells[0] = new CurrentShapeCell(r, c, true);
                    s.shapeCells[1] = new CurrentShapeCell(r - 1, c);
                    s.shapeCells[2] = new CurrentShapeCell(r + 1, c);
                    s.shapeCells[3] = new CurrentShapeCell(r - 1, c + 1);
                    degree = 90;
                }
            }
            case 90 -> {
                if (s.arr[r][c - 1] == 0 && s.arr[r][c + 1] == 0 && s.arr[r + 1][c + 1] == 0) { // can rotate
                    Arrays.fill(s.shapeCells, null);
                    s.arr[r - 1][c] = 0;
                    s.arr[r + 1][c] = 0;
                    s.arr[r - 1][c + 1] = 0;
                    s.shapeCells[0] = new CurrentShapeCell(r, c, true);
                    s.shapeCells[1] = new CurrentShapeCell(r, c - 1);
                    s.shapeCells[2] = new CurrentShapeCell(r, c + 1);
                    s.shapeCells[3] = new CurrentShapeCell(r + 1, c + 1);
                    degree = 270;
                }
            }
            case 270 -> {
                if (s.arr[r - 1][c] == 0 && s.arr[r + 1][c] == 0 && s.arr[r + 1][c - 1] == 0) { // can rotate
                    Arrays.fill(s.shapeCells, null);
                    s.arr[r][c + 1] = 0;
                    s.arr[r][c - 1] = 0;
                    s.arr[r + 1][c + 1] = 0;
                    s.shapeCells[0] = new CurrentShapeCell(r, c, true);
                    s.shapeCells[1] = new CurrentShapeCell(r + 1, c - 1);
                    s.shapeCells[2] = new CurrentShapeCell(r - 1, c);
                    s.shapeCells[3] = new CurrentShapeCell(r + 1, c);
                    degree = 360;
                }
            }
            case 360 -> {
                if (s.arr[r][c - 1] == 0 && s.arr[r][c + 1] == 0 && s.arr[r - 1][c - 1] == 0) { // can rotate
                    Arrays.fill(s.shapeCells, null);
                    s.arr[r + 1][c] = 0;
                    s.arr[r - 1][c] = 0;
                    s.arr[r + 1][c - 1] = 0;
                    s.shapeCells[0] = new CurrentShapeCell(r, c, true);
                    s.shapeCells[1] = new CurrentShapeCell(r, c + 1);
                    s.shapeCells[2] = new CurrentShapeCell(r, c - 1);
                    s.shapeCells[3] = new CurrentShapeCell(r - 1, c - 1);
                    degree = 0;
                }
            }
        }
    }
}
