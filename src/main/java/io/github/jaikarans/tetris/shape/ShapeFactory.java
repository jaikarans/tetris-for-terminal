package io.github.jaikarans.tetris.shape;

import io.github.jaikarans.tetris.state.GameState;
import org.springframework.stereotype.Component;

@Component
public class ShapeFactory {
    private final GameState s;

    public Shape currentShape;

    public ShapeFactory(GameState gameState) {
        this.s = gameState;
        System.out.println("Shape Created: "+ this);
    }

    public Shape generateNewShape(ShapeType shape) {
        return switch (shape) {
            case O -> new OShape(s);
            case I -> new IShape(s);
            case T -> new TShape(s);
            case L -> new LShape(s);
            case J -> new JShape(s);
            case S -> new SShape(s);
            case Z -> new ZShape(s);
        };
    }
}
