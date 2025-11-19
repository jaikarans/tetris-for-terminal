package io.github.jaikarans.tetris;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.NonBlockingReader;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static io.github.jaikarans.tetris.App.shapeOutOfBox;

@Component
public class Game {
    private final Shape shape;
    private final GameLogic gameLogic;
    private final GameRender gameRender;
    private final CollisionDetector collisionDetector;


    public static boolean gameOver = false;

    Game(
            Shape shape,
            GameLogic gameLogic,
            GameRender gameRender,
            CollisionDetector collisionDetector) {
        this.shape = shape;
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
            shape.generateNewShape();
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
                            }
                        }
                    } else {
                        switch (key) {
                            case 'h' -> gameLogic.moveLeft();
                            case 'l' -> gameLogic.moveRight();
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
                shape.generateNewShape();
            }
        }
    }
}
