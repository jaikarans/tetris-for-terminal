package io.github.jaikarans.tetris;

public class GameLogic {
    private static GameState s = GameState.getInstance();

    public static void lockPiece() {
        for (CurrentShapeCell cell : s.shapeCells) {
            if (cell.x >= 0)
                s.arr[cell.x][cell.y] = s.color;
            else
            App.shapeOutOfBox = true;
        }
        s.shapeCells.clear();
    }

    public static void clearFullRows() {
        boolean rowFilled;
        for (int i = s.height; i >= 0; --i) {
            rowFilled = true;
            for (int j = 0; j <= s.width; ++j) {
                if (s.arr[i][j] == 0) {
                    rowFilled = false;
                    break;
                }
            }
            if (rowFilled) {
                int r = i;
                for (int row = i - 1; row >= 0; --row) {
                    for (int j = 0; j <= s.width; ++j) {
                        s.arr[r][j] = s.arr[row][j];
                    }
                    --r;
                }
                ++i;
            }
        }
    }

    public static void movePieceDown() {
        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh"+CollisionDetector.pieceCanMoveDown());
        System.out.println(s.shapeCells.isEmpty());
        for (CurrentShapeCell cell : s.shapeCells) {
            System.out.println("cell"+cell.x+" "+cell.y);
        }

        // clear old positions
        for (CurrentShapeCell cell : s.shapeCells) {
            if (cell.x >= 0)
                s.arr[cell.x][cell.y] = 0;
        }
    
        // move all shape cells down
        for (CurrentShapeCell cell : s.shapeCells) {
            cell.x += 1;
        }

        // draw new positions
        for (CurrentShapeCell cell : s.shapeCells) {
            if (cell.x >= 0)
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
