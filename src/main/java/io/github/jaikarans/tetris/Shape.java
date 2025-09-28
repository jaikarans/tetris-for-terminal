package io.github.jaikarans.tetris;

public class Shape {
    private static GameState s = GameState.getInstance();
    public static final int I = 0;
    public static final int O = 1;
    public static final int T = 2;
    public static final int L = 3;
    public static final int J = 4;
    public static final int S = 5;
    public static final int Z = 6;

    public static void generateNewShape() {
        int shape = (int) (Math.random() * 7);
        s.col = s.width / 2;
        s.color = (int) (Math.random() * (200 - 5 + 1)) + 5;

        switch (shape) {
            case O -> {
                s.shapeCells.add(new CurrentShapeCell(s.row, s.col));
                s.shapeCells.add(new CurrentShapeCell(s.row, s.col + 1));
                s.shapeCells.add(new CurrentShapeCell(s.row - 1, s.col));
                s.shapeCells.add(new CurrentShapeCell(s.row - 1, s.col + 1));
            }
            case I -> {
                s.shapeCells.add(new CurrentShapeCell(s.row, s.col));
                s.shapeCells.add(new CurrentShapeCell(s.row - 1, s.col));
                s.shapeCells.add(new CurrentShapeCell(s.row - 2, s.col));
                s.shapeCells.add(new CurrentShapeCell(s.row - 3, s.col));
            }
            case T -> {
                s.shapeCells.add(new CurrentShapeCell(s.row, s.col));
                s.shapeCells.add(new CurrentShapeCell(s.row, s.col + 1));
                s.shapeCells.add(new CurrentShapeCell(s.row, s.col + 2));
                s.shapeCells.add(new CurrentShapeCell(s.row - 1, s.col + 1));
            }
            case L -> {
                s.shapeCells.add(new CurrentShapeCell(s.row, s.col));
                s.shapeCells.add(new CurrentShapeCell(s.row - 1, s.col));
                s.shapeCells.add(new CurrentShapeCell(s.row - 2, s.col));
                s.shapeCells.add(new CurrentShapeCell(s.row, s.col + 1));
            }
            case J -> {
                s.shapeCells.add(new CurrentShapeCell(s.row, s.col));
                s.shapeCells.add(new CurrentShapeCell(s.row - 1, s.col));
                s.shapeCells.add(new CurrentShapeCell(s.row - 2, s.col));
                s.shapeCells.add(new CurrentShapeCell(s.row, s.col - 1));
            }
            case S -> {
                s.shapeCells.add(new CurrentShapeCell(s.row - 1, s.col));
                s.shapeCells.add(new CurrentShapeCell(s.row - 1, s.col + 1));
                s.shapeCells.add(new CurrentShapeCell(s.row, s.col));
                s.shapeCells.add(new CurrentShapeCell(s.row, s.col - 1));
            }
            case Z -> {
                s.shapeCells.add(new CurrentShapeCell(s.row - 1, s.col));
                s.shapeCells.add(new CurrentShapeCell(s.row - 1, s.col - 1));
                s.shapeCells.add(new CurrentShapeCell(s.row, s.col));
                s.shapeCells.add(new CurrentShapeCell(s.row, s.col + 1));
            }
        }
    }
}
