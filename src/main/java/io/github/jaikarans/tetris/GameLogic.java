package io.github.jaikarans.tetris;

import java.util.Arrays;

public class GameLogic {
    private static GameState s = GameState.getInstance();

    // when shape hits ground
    public static void lockPiece() {
        for (CurrentShapeCell cell : s.shapeCells) {
            s.arr[cell.x][cell.y] = Shape.currentShape.getId();
            if (cell.x < 3) {
                App.shapeOutOfBox = true;
            }
        }
        Arrays.fill(s.shapeCells, null);
    }

    public static void clearFullRows() {
        boolean rowFilled;
        for (int i = s.height - 1; i >= 0; --i) {
            rowFilled = true;
            for (int j = 0; j < s.width; ++j) {
                if (s.arr[i][j] == 0) {
                    rowFilled = false;
                    break;
                }
            }
            if (rowFilled) {
                int r = i;
                for (int row = i - 1; row >= 0; --row) {
                    for (int j = 0; j < s.width; ++j) {
                        s.arr[r][j] = s.arr[row][j];
                    }
                    --r;
                }
                ++i;
            }
        }
    }

    public static void movePieceDown() {
        // only clear before in seprate loop
        // otherwise it will be problematic for
        // shape like S and Z
        for (CurrentShapeCell cell: s.shapeCells) {
            // clear old positions
            s.arr[cell.x][cell.y] = 0;
        }
        for (CurrentShapeCell cell : s.shapeCells) {
            // move all shape cells down
            cell.x += 1;

            // draw new positions
            s.arr[cell.x][cell.y] = 1;
        }
    }

    public static void moveLeft() {
        for (CurrentShapeCell cell : s.shapeCells) {
            if (cell.y <= 0 || CollisionDetector.isSideCollision(-1)){
                return;
            }
        }

        for (CurrentShapeCell cell : s.shapeCells) {
            if (cell.x >= 0)
                s.arr[cell.x][cell.y] = 0;
        }

        for (CurrentShapeCell cell: s.shapeCells) {
            cell.y -= 1;
        }

    }

    public static void moveRight() {
        for (CurrentShapeCell cell : s.shapeCells) {
            if (cell.y >= s.width || CollisionDetector.isSideCollision(1)){
                return;
            }
        }

        for (CurrentShapeCell cell : s.shapeCells) {
            if (cell.x >= 0)
                s.arr[cell.x][cell.y] = 0;
        }

        for (CurrentShapeCell cell: s.shapeCells) {
            cell.y += 1;
        }
    }
}
