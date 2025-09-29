package io.github.jaikarans.tetris;

import java.util.Random;

public class Shape {
    private static final GameState s = GameState.getInstance();

    public static ShapeType currentShape;

    public static ShapeType getRandomShape() {
        ShapeType[] values = ShapeType.values();
        Random rand = new Random();
        // Start from index 1 to skip 'empty'
        return values[rand.nextInt(values.length - 1)];
    }

    public static void generateNewShape() {
        currentShape = getRandomShape();
//        currentShape = ShapeType.O;

        switch (currentShape) {
            case O -> {
                s.col = 5;
                s.row = 1;
                s.shapeCells[0] = new CurrentShapeCell(s.row, s.col);
                s.shapeCells[1] = new CurrentShapeCell(s.row, s.col + 1);
                s.shapeCells[2] = new CurrentShapeCell(s.row - 1, s.col);
                s.shapeCells[3] = new CurrentShapeCell(s.row - 1, s.col + 1);
            }
            case I -> {
                s.col = 4;
                s.row = 2;
                s.shapeCells[0] = new CurrentShapeCell(s.row, s.col - 1);
                s.shapeCells[1] = new CurrentShapeCell(s.row, s.col);
                s.shapeCells[2] = new CurrentShapeCell(s.row, s.col + 1);
                s.shapeCells[3] = new CurrentShapeCell(s.row, s.col + 2);
            }
            case T -> {
                s.col = 4;
                s.row = 2;
                s.shapeCells[0] = new CurrentShapeCell(s.row, s.col);
                s.shapeCells[1] = new CurrentShapeCell(s.row, s.col + 1);
                s.shapeCells[2] = new CurrentShapeCell(s.row, s.col + 2);
                s.shapeCells[3] = new CurrentShapeCell(s.row - 1, s.col + 1);
            }
            case L -> {
                s.col = 4;
                s.row = 2;
                s.shapeCells[0] = new CurrentShapeCell(s.row, s.col);
                s.shapeCells[1] = new CurrentShapeCell(s.row - 1, s.col);
                s.shapeCells[2] = new CurrentShapeCell(s.row - 2, s.col);
                s.shapeCells[3] = new CurrentShapeCell(s.row, s.col + 1);
            }
            case J -> {
                s.col = 4;
                s.row = 2;
                s.shapeCells[0] = new CurrentShapeCell(s.row, s.col);
                s.shapeCells[1] = new CurrentShapeCell(s.row - 1, s.col);
                s.shapeCells[2] = new CurrentShapeCell(s.row - 2, s.col);
                s.shapeCells[3] = new CurrentShapeCell(s.row, s.col - 1);
            }
            case S -> {
                s.col = 4;
                s.row = 2;
                s.shapeCells[0] = new CurrentShapeCell(s.row - 1, s.col);
                s.shapeCells[1] = new CurrentShapeCell(s.row - 1, s.col + 1);
                s.shapeCells[2] = new CurrentShapeCell(s.row, s.col);
                s.shapeCells[3] = new CurrentShapeCell(s.row, s.col - 1);
            }
            case Z -> {
                s.col = 4;
                s.row = 2;
                s.shapeCells[0] = new CurrentShapeCell(s.row - 1, s.col);
                s.shapeCells[1] = new CurrentShapeCell(s.row - 1, s.col - 1);
                s.shapeCells[2] = new CurrentShapeCell(s.row, s.col);
                s.shapeCells[3] = new CurrentShapeCell(s.row, s.col + 1);
            }
        }
    }
}
