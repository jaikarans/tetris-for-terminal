package io.github.jaikarans.tetris;

public enum ShapeType {
    O(2), I(3), T(4), L(5), J(6), S(7), Z(8);

    private final int id;

    ShapeType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    // Get ShapeType from ID
    public static ShapeType fromId(int id) {
        for (ShapeType shape : ShapeType.values()) {
            if (shape.getId() == id) {
                return shape;
            }
        }
        throw new IllegalArgumentException("No shape with ID " + id);
    }
}
