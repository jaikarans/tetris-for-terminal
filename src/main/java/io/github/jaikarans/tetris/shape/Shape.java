package io.github.jaikarans.tetris.shape;

import io.github.jaikarans.tetris.state.GameState;

public abstract class Shape {
    protected final GameState s;
    protected int degree;

    public Shape(GameState state) {
        this.s = state;
    }

    public abstract void rotate();
    public abstract void generateShape();
    public abstract ShapeType getType();
}
