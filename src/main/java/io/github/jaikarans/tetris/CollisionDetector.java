package io.github.jaikarans.tetris;

public class CollisionDetector {
    private static GameState s = GameState.getInstance();

    public static final int LEFT_DIRECTION = -1;
    public static final int RIGHT_DIRECTION = 1;

    public static boolean pieceCanMoveDown() {
        // if there is no shape we need to generate a new
        // that's why returning false
        for (CurrentShapeCell cell: s.shapeCells) {
            if (cell.x == s.height - 1 || (s.arr[cell.x + 1][cell.y] > 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * check if cell collides
     * @param dir direction left -1 and right +1
     */
    public static boolean isSideCollision(int dir) {
        for (CurrentShapeCell cell: s.shapeCells) {
            if (dir == LEFT_DIRECTION && cell.y <= 0) return true;
            if (dir == RIGHT_DIRECTION && cell.y + 1 > s.width - 1) return true;

            if (cell.x >= 0 && s.arr[cell.x][cell.y + dir] > 1) return true;
        }
        return false;
    }
}
