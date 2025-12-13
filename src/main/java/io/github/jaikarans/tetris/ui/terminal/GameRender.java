package io.github.jaikarans.tetris.ui.terminal;

import io.github.jaikarans.tetris.shape.ShapeFactory;
import io.github.jaikarans.tetris.shape.ShapeType;
import io.github.jaikarans.tetris.state.GameState;
import org.springframework.stereotype.Component;

@Component
public class GameRender {
    private final GameState s;
    private final ShapeFactory shapeFactory;

    public GameRender(GameState gameState, ShapeFactory shapeFactory) {
        this.s = gameState;
        this.shapeFactory = shapeFactory;
        System.out.println("GameRender Created: "+ this);
    }


  /**
   * render the game in terminal
   */
  public void renderGame() {
    long start = System.nanoTime();

    // Move cursor to top-left before drawing
    System.out.print("\033[H\033[2J");
    System.out.flush();

    System.out.println();
    System.out.println();
    System.out.println();
//    System.out.println("h -> left");
//    System.out.println("l -> right");
//    System.out.println("j -> rotate");
    System.out.println("\t\t\th -> left");
    System.out.println("\t\t\tl -> right");
    System.out.println("\t\t\tj -> rotate");
    System.out.println();
    System.out.println();
    System.out.println();

      // right border printing
      for (int i = 0; i < s.height; i++) {
        System.out.print(s.margin);
        if (i > 2) {
//            System.out.print("\033[38;5;236;48;5;236m█\033[0m");
            System.out.print("┃");
        } else {
            System.out.print(" ");
        }

        for (int j = 0; j < s.width; j++) {
            // empty cells
            if (s.arr[i][j] == 0) {
                // System.out.print("\033[38;5;0m██\033[0m");
                if (i < 3) System.out.print("  ");
                else
                    System.out.print(" .");
                continue;
            }

            String color;
            if (s.arr[i][j] == 1) {
                color = s.color.get(shapeFactory.currentShape.getType());
                System.out.print("\033["+color+"m██\033[0m");
            } else {
                color = s.color.get(ShapeType.fromId(s.arr[i][j]));
                System.out.print("\033["+color+"m██\033[0m");

            }

        }

        if (i > 2) {
            // right border
            System.out.print("┃");
        }
          System.out.println();
    }

    // bottom border
    System.out.print(s.margin);
    for (int i = 0; i <= 2*s.width + 1; i++) {
        System.out.print(i == 0 ? "┗" : i == 2 * s.width + 1  ? "┛" : "━");
    }
    System.out.println();

    // time to complete the render
    long end = System.nanoTime();
    System.out.println("Using cached length: " + (end - start) / 1_000_000 + " ms");

  }
}
