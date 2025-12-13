package io.github.jaikarans.tetris.shape;

import io.github.jaikarans.tetris.state.CurrentShapeCell;
import io.github.jaikarans.tetris.state.GameState;

public class OShape extends Shape{
    public OShape(GameState state) {
        super(state);
        degree = 0;
        generateShape();
    }

    @Override
    public ShapeType getType() {
        return ShapeType.O;
    }

    @Override
    public void generateShape() {
        int r = 1, c = 5;
        s.shapeCells[0] = new CurrentShapeCell(r, c, true);
        s.shapeCells[1] = new CurrentShapeCell(r, c + 1);
        s.shapeCells[2] = new CurrentShapeCell(r - 1, c);
        s.shapeCells[3] = new CurrentShapeCell(r - 1, c + 1);

    }

    @Override
    public void rotate() {}

}
