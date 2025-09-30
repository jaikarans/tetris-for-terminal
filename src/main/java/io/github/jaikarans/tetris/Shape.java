package io.github.jaikarans.tetris;

import java.util.ArrayList;
import java.util.Random;

public class Shape {
    private static final GameState s = GameState.getInstance();

    public static ShapeType currentShape;
    public static ArrayList<ShapeType> shuffleBag = new ArrayList<>();

    public static ShapeType getRandomShape() {
        if (shuffleBag.isEmpty()) {
            for (ShapeType shape: ShapeType.values()) {
                shuffleBag.add(shape);
            }
        }
        if (shuffleBag.size() == 1) {
            ShapeType selectedShape = shuffleBag.get(0);
            shuffleBag.clear();
            return selectedShape;
        }
        
        Random rand = new Random();
        int selectedIndex = rand.nextInt(shuffleBag.size() - 1);
        ShapeType selectedShape = shuffleBag.get(selectedIndex);
        shuffleBag.remove(selectedIndex);
        return selectedShape;
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
