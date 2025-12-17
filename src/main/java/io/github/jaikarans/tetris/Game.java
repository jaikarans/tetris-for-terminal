package io.github.jaikarans.tetris;

import io.github.jaikarans.tetris.collision.CollisionDetector;
import io.github.jaikarans.tetris.shape.ShapeFactory;
import io.github.jaikarans.tetris.ui.terminal.GameRender;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.NonBlockingReader;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static io.github.jaikarans.tetris.App.shapeOutOfBox;

@Component
public class Game {
    private final ShapeFactory shapeFactory;
    private final GameLogic gameLogic;
    private final GameRender gameRender;
    private final CollisionDetector collisionDetector;
    private final BagRandomizer bagRandomizer = new BagRandomizer();


    public static boolean gameOver = false;

    public Game(
            ShapeFactory shapeFactory,
            GameLogic gameLogic,
            GameRender gameRender,
            CollisionDetector collisionDetector) {
        this.shapeFactory = shapeFactory;
        this.gameLogic = gameLogic;
        this.gameRender = gameRender;
        this.collisionDetector = collisionDetector;
        System.out.println("Game Created: "+ this);
    }

    public void run() {
        int FRAME_DURATION = 200;
        try {
            // Create a terminal
            Terminal terminal = TerminalBuilder.builder().system(true).build();
            terminal.enterRawMode();

            NonBlockingReader reader = terminal.reader();
            shapeFactory.currentShape = shapeFactory.generateNewShape(bagRandomizer.next());
//            shapeFactory.currentShape = shapeFactory.generateNewShape(ShapeType.I);
            while (!gameOver) {
                long startTime = System.currentTimeMillis();
                int key = -1;
                if (reader.ready()) {   // non-blocking check
                    key = reader.read();
                }
                if (key != -1) {
                    if (key == 27) { // ESC
                        if (reader.ready() && reader.read() == 91) { // '['
                            int arrow = reader.read();
                            switch (arrow) {
                                case 'D' -> gameLogic.moveLeft();   // ←
                                case 'C' -> gameLogic.moveRight();  // →
                                case 'A' -> shapeFactory.currentShape.rotate();  // up arrow
                                case 'B' -> shapeFactory.currentShape.rotate();  // down arrow
                            }
                        }
                    } else {
                        switch (key) {
                            case 'h' -> gameLogic.moveLeft();
                            case 'l' -> gameLogic.moveRight();
                            case 'j' -> shapeFactory.currentShape.rotate();
                            case 'k' -> shapeFactory.currentShape.rotate();
                        }
                    }
                }

                updateGame();
                gameRender.renderGame();

                long frameTime = System.currentTimeMillis() - startTime;
                long sleepTime = FRAME_DURATION - frameTime;
                if (sleepTime > 0) {
                    Thread.sleep(sleepTime);
                }
            }
        } catch (IOException e) {
            System.err.println("Error creating terminal: " + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateGame() {
        if (collisionDetector.pieceCanMoveDown()) {
            gameLogic.movePieceDown();
        } else {
            gameLogic.lockPiece();
            gameLogic.clearFullRows();
            if (shapeOutOfBox) {
                gameOver = true;
            } else {
                shapeFactory.currentShape = shapeFactory.generateNewShape(bagRandomizer.next());
//                shapeFactory.currentShape = shapeFactory.generateNewShape(ShapeType.I);
            }
        }
    }
}
