package io.github.jaikarans.tetris;

public class GameState {
  private static GameState instance; 

  public int width = 15;
  public int height = 25;
  public int[][] arr = new int[height+1][width+1];
  public String margin = "                               ";
  public int col = width / 2;
  public int row = 1;
  public int color =  (int) (Math.random() * (200 - 5 + 1)) + 5;

  private GameState () {}

  public static synchronized GameState getInstance() {
    if (instance == null) {
      instance = new GameState();
    }
    return instance;
  }



}
