package io.github.jaikarans.tetris;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GameState {

  public int width = 10;
  public int height = 23;
  public int[][] arr = new int[height][width];
  public String margin = "                               ";
  public int col = 5;
  public int row = 2;
  public Map<ShapeType, String> color = new HashMap<>();
  public CurrentShapeCell[] shapeCells = new CurrentShapeCell[4];

  GameState () {
      color.put(ShapeType.O, "93");
      color.put(ShapeType.I, "94");
      color.put(ShapeType.T, "95");
      color.put(ShapeType.L, "33");
      color.put(ShapeType.J, "34");
      color.put(ShapeType.S, "32");
      color.put(ShapeType.Z, "91");

      System.out.println("GameState Created: "+ this);
  }

}
