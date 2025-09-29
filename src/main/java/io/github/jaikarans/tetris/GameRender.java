package io.github.jaikarans.tetris;

import java.util.Arrays;

public class GameRender {
    static GameState game = GameState.getInstance();


  /**
   * render the game in terminal
   */
  public static void renderGame() {
    long start = System.nanoTime();

    // Move cursor to top-left before drawing
    System.out.print("\033[H\033[2J");
    System.out.flush();
    for (int i = 0; i < game.height; i++) {
        System.out.println(Arrays.toString(game.arr[i]));
    }

    System.out.println("**************************************************");
    System.out.println();
//    System.out.println();
//    System.out.println();
//    System.out.println();

      // right border printing
      for (int i = 0; i < game.height; i++) {
        System.out.print(game.margin);
        if (i > 2) {
//            System.out.print("\033[38;5;236;48;5;236m█\033[0m");
            System.out.print("┃");
        } else {
            System.out.print(" ");
        }

        for (int j = 0; j < game.width; j++) {
            // empty cells
            if (game.arr[i][j] == 0) {
                // System.out.print("\033[38;5;0m██\033[0m");
                System.out.print(" .");
                continue;
            }

            String color;
            if (game.arr[i][j] == 1) {
                color = game.color.get(Shape.currentShape);
                System.out.print("\033["+color+"m██\033[0m");
            } else {
                color = game.color.get(ShapeType.fromId(game.arr[i][j]));
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
    System.out.print(game.margin);
    for (int i = 0; i <= 2*game.width + 1; i++) {
        System.out.print(i == 0 ? "┗" : i == 2 * game.width + 1  ? "┛" : "━");
    }
    System.out.println();

    // time to complete the render
    long end = System.nanoTime();
    System.out.println("Using cached length: " + (end - start) / 1_000_000 + " ms");

  }
}
