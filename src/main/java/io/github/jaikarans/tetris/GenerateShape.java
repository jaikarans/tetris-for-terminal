package io.github.jaikarans.tetris;


public class GenerateShape {
    private static GameState s = GameState.getInstance();

        public static void generateAShape() {
        s.color = (int) (Math.random() * (200 - 5 + 1)) + 5;

        // for testing generating the O shape first
        s.shapeCells.add(new CurrentShapeCell(s.row, s.col));
        s.shapeCells.add(new CurrentShapeCell(s.row, s.col + 1));
        s.shapeCells.add(new CurrentShapeCell(s.row - 1, s.col + 1));
        s.shapeCells.add(new CurrentShapeCell(s.row - 1, s.col));

        for (CurrentShapeCell cell: s.shapeCells) {
            s.arr[cell.x][cell.y] = 1;
        }
    }
}
