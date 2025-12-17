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
//    long start = System.nanoTime();
    GameState state = this.s;
    StringBuilder frame = new StringBuilder();

    // Move cursor to top-left before drawing
    // System.out.print("\033[H\033[2J")
    frame.append("\033[H\033[2J");
    // System.out.flush();

    frame.append("\n\n\n\n\n\n");

      // right border printing
      for (int i = 0; i < state.height; i++) {
        frame.append(state.margin);
        if (i > 2) {
//            System.out.print("\033[38;5;236;48;5;236m█\033[0m");
//            System.out.print("┃");
            frame.append("┃");
        } else {
            frame.append(" ");
        }

        for (int j = 0; j < state.width; j++) {
            // empty cells
            if (state.arr[i][j] == 0) {
                // System.out.print("\033[38;5;0m██\033[0m");
                if (i < 3) frame.append("  ");
                else
                    frame.append(" .");
                continue;
            }

            String color;
            if (state.arr[i][j] == 1) {
                color = state.color.get(shapeFactory.currentShape.getType());
                frame.append("\033["+color+"m██\033[0m");
            } else {
                color = state.color.get(ShapeType.fromId(state.arr[i][j]));
                frame.append("\033["+color+"m██\033[0m");

            }

        }

        if (i > 2) {
            // right border
            frame.append("┃");
        }

        if (i == 3) {
            frame.append("  ┏━━━━━━Stats━━━━━┓");
        }
        if (i == 4) {
            if (s.score < 9) frame.append("  ┃ Score        "+ s.score +" ┃");
            else frame.append("  ┃ Score       "+s.score+" ┃");
        }
        if (i == 5) {
            frame.append("  ┗━━━━━━━━━━━━━━━━┛");
        }

        if (i == 7) {
            frame.append("  ┏━━━━━━Help━━━━━━┓");
        }
        if (i == 8) {
            frame.append("  ┃                ┃");
        }
        if (i == 9) {
            frame.append("  ┃ Left      h, ← ┃");
        }
        if (i == 10) {
            frame.append("  ┃ Right     l, → ┃");
        }
        if (i == 11) {
            frame.append("  ┃ Rotate    j, ↑ ┃");
        }
        if (i == 12) {
            frame.append("  ┃ Rotate    k, ↓ ┃");
        }
        if (i == 13) {
            frame.append("  ┃                ┃");
        }
        if (i == 14) {
            frame.append("  ┗━━━━━━━━━━━━━━━━┛");
        }


         frame.append("\n");
    }

    // bottom border
    frame.append(s.margin);
    for (int i = 0; i <= 2*s.width + 1; i++) {
        frame.append(i == 0 ? "┗" : i == 2 * s.width + 1  ? "┛" : "━");
    }
    frame.append("\n");

    // time to complete the render
//    long end = System.nanoTime();
//    frame.append("Using cached length: " + (end - start) / 1_000_000 + " ms");

    System.out.print(frame.toString());
  }
}
