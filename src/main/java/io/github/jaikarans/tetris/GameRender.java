package io.github.jaikarans.tetris;

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

    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println();

      System.out.print(game.margin);
      for (int i = 0; i <= 2*game.width + 3; i++) {
          System.out.print("\033[38;5;34;48;5;236m \033[0m");
      }
      System.out.println();

      for (int i = 0; i <= game.height; i++) {
        System.out.print(game.margin);

        // border
        System.out.print("\033[38;5;34;48;5;236m \033[0m");

        for (int j = 0; j <= game.width; j++) {
            // empty cells
            if (game.arr[i][j] == 0) {
                // System.out.print("\033[38;5;0m██\033[0m");
                System.out.print("\033[38;5;0m█\033[38;5;240m.\033[0m");
                continue;
            }

            // cells with shape
            System.out.print("\033[38;5;"+(game.arr[i][j] == 1 ? game.color : game.arr[i][j])+"m██\033[0m");

        }
        // border
        System.out.println("\033[38;5;34;48;5;236m \033[0m");
    }

    // bottom border
    System.out.print(game.margin);
    for (int i = 0; i <= 2*game.width + 3; i++) {
        System.out.print("\033[38;5;34;48;5;236m \033[0m");
    }
    System.out.println();

    // time to complete the render
    long end = System.nanoTime();
    System.out.println("Using cached length: " + (end - start) / 1_000_000 + " ms");

  }
}
