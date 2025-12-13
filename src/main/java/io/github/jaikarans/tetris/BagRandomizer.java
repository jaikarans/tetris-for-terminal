package io.github.jaikarans.tetris;

import io.github.jaikarans.tetris.shape.ShapeType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BagRandomizer {
    private final List<ShapeType> bag = new ArrayList<>();

    public ShapeType next() {
        if (bag.isEmpty()) {
            refillBag();
        }
        return bag.remove(bag.size() - 1);
    }

    private void refillBag() {
        bag.clear();
        Collections.addAll(bag, ShapeType.values());
        Collections.shuffle(bag);
    }
}
