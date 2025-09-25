package io.github.jaikarans.tetris;

public class GameLogic {

    private static GameLogic instance;

    private static GameState state = GameState.getInstance();
    int row = state.row;
    int col = state.col;
    int color = state.color;
    int height = state.height;
    int[][] arr = state.arr;

    private GameLogic() {
    }

    public static GameLogic getInstance() {
        if (instance == null) {
            instance = new GameLogic();
        }
        return instance;
    }

    public void generateNewShape() {
        state.col = state.width / 2;
        col = state.col;
        color = (int) (Math.random() * (200 - 5 + 1)) + 5;
        state.color = color;
        arr[row][col] = 1;
        arr[row][col + 1] = 1;
        arr[row - 1][col] = 1;
        arr[row - 1][col + 1] = 1;
    }

    public void fallDown() {
        arr[row][col] = 0;
        arr[row][col + 1] = 0;
        arr[row - 1][col] = 0;
        arr[row - 1][col + 1] = 0;

        arr[row + 1][col] = 1;
        arr[row + 1][col + 1] = 1;
        arr[row][col] = 1;
        arr[row][col + 1] = 1;
        state.row++;
        row++;

        
        // if collision happens or shape goes on bottom for now only for o shape
        if (row >= height || arr[row+1][col] > 0 || arr[row + 1][col + 1] > 0) {
            state.row = 1;
            row = 1;
            generateNewShape();
        }

    }

    public void moveLeft() {
        if (col <= 0) return;
        arr[row][col] = 0;
        arr[row][col + 1] = 0;
        arr[row - 1][col] = 0;
        arr[row - 1][col + 1] = 0;

        arr[row][col - 1] = 1;
        arr[row][col - 1] = 1;
        arr[row - 1][col - 1] = 1;
        arr[row - 1][col - 1] = 1;
        state.col--;
        col--;
    }

    public void moveRight() {
        if (col >= state.width - 1) return;
        arr[row][col] = 0;
        arr[row][col + 1] = 0;
        arr[row - 1][col] = 0;
        arr[row - 1][col + 1] = 0;

        arr[row][col + 1] = 1;
        arr[row][col + 1] = 1;
        arr[row - 1][col + 1] = 1;
        arr[row - 1][col + 1] = 1;
        state.col++;
        col++;
    }

}
