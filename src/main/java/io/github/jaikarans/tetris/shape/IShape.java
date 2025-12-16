package io.github.jaikarans.tetris.shape;

import io.github.jaikarans.tetris.state.CurrentShapeCell;
import io.github.jaikarans.tetris.state.GameState;

import java.util.Arrays;

public class IShape extends Shape{
    public IShape(GameState state) {
        super(state);
        degree = 0;
        generateShape();
    }

    @Override
    public ShapeType getType() {
        return ShapeType.I;
    }

    @Override
    public void generateShape() {
        int r = 2, c = 5;
        s.shapeCells[1] = new CurrentShapeCell(r, c, true);
        s.shapeCells[2] = new CurrentShapeCell(r, c + 1);
        s.shapeCells[0] = new CurrentShapeCell(r, c - 1);
        s.shapeCells[3] = new CurrentShapeCell(r, c - 2);

    }

    @Override
    public void rotate() {
        int r = -1, c = -1;
        for (CurrentShapeCell cell: s.shapeCells) {
            if (cell.isCenterCell) {
                r = cell.x;
                c = cell.y;
                break;
            }
        }

        switch (degree) {
            case 0 -> {
                // edge case
                if (r - 1 < 0 || r + 1 >= s.height || r + 2 >= s.height) return;
                if (c - 2 < 0 || c - 2 < 0 || c + 1 >= s.width) return;

                if (s.arr[r - 1][c] == 0 && s.arr[r + 1][c] == 0 && s.arr[r + 2][c] == 0) {
                    Arrays.fill(s.shapeCells, null);
                    s.arr[r][c - 1] = 0;
                    s.arr[r][c - 2] = 0;
                    s.arr[r][c + 1] = 0;

                    s.shapeCells[0] = new CurrentShapeCell(r, c);
                    s.shapeCells[1] = new CurrentShapeCell(r - 1, c);
                    s.shapeCells[2] = new CurrentShapeCell(r + 1, c, true);
                    s.shapeCells[3] = new CurrentShapeCell(r + 2, c);
                    degree = 90;
                }
            }
            case 90 -> {
                //edge case handling
                if (r - 1 < 0 || r - 2 < 0 || r + 1 >= s.height) return;
                if (c - 1 < 0 || c - 2 < 0 || c + 1 >= s.width) return;

                if (s.arr[r][c - 1] == 0 && s.arr[r][c - 2] == 0 && s.arr[r][c + 1] == 0) {
                    Arrays.fill(s.shapeCells, null);
                    s.arr[r - 1][c] = 0;
                    s.arr[r - 2][c] = 0;
                    s.arr[r + 1][c] = 0;

                    s.shapeCells[0] = new CurrentShapeCell(r, c);
                    s.shapeCells[1] = new CurrentShapeCell(r, c - 1, true);
                    s.shapeCells[2] = new CurrentShapeCell(r, c - 2);
                    s.shapeCells[3] = new CurrentShapeCell(r, c + 1);
                    degree = 270;
                }
            }
            case 270 -> {
                //edge case handling
                if (r - 1 < 0 || r - 2 < 0 || r + 1 >= s.height) return;
                if (c - 1 < 0 || c + 1 >= s.width || c + 2 >= s.width) return;

                if (s.arr[r - 1][c] == 0 && s.arr[r - 2][c] == 0 && s.arr[r + 1][c] == 0) {
                    Arrays.fill(s.shapeCells, null);
                    s.arr[r][c - 1] = 0;
                    s.arr[r][c + 1] = 0;
                    s.arr[r][c + 2] = 0;

                    s.shapeCells[0] = new CurrentShapeCell(r, c);
                    s.shapeCells[1] = new CurrentShapeCell(r - 1, c, true);
                    s.shapeCells[2] = new CurrentShapeCell(r - 2, c);
                    s.shapeCells[3] = new CurrentShapeCell(r + 1, c);
                    degree = 360;
                }
            }
            case 360 -> {
                //edge case handling
                if (r - 1 < 0 || r + 1 >= s.height || r + 2 >= s.height) return;
                if (c - 1 < 0 || c + 1 >= s.width || c + 2 >= s.width) return;

                if (s.arr[r][c - 1] == 0 && s.arr[r][c + 1] == 0 && s.arr[r][c + 2] == 0) {
                    Arrays.fill(s.shapeCells, null);
                    s.arr[r - 1][c] = 0;
                    s.arr[r + 1][c] = 0;
                    s.arr[r + 2][c] = 0;

                    s.shapeCells[0] = new CurrentShapeCell(r, c);
                    s.shapeCells[1] = new CurrentShapeCell(r, c + 1, true);
                    s.shapeCells[2] = new CurrentShapeCell(r, c + 2);
                    s.shapeCells[3] = new CurrentShapeCell(r, c - 1);
                    degree = 0;
                }
            }
        }
    }

}
