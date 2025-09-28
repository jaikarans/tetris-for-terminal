package io.github.jaikarans.tetris;

import java.util.HashSet;

public class GameState {
  private static GameState instance; 

  public int width = 15;
  public int height = 25;
  public int[][] arr = new int[height+1][width+1];
  public String margin = "                               ";
  public int col = width / 2;
  public int row = 0;
  public int color =  (int) (Math.random() * (200 - 5 + 1)) + 5;
  public HashSet<CurrentShapeCell> shapeCells = new HashSet<>();

  private GameState () {}

  public static synchronized GameState getInstance() {
    if (instance == null) {
      instance = new GameState();
    }
    return instance;
  }

}
