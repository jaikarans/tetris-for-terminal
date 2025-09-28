package io.github.jaikarans.tetris;

import java.io.IOException;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.NonBlockingReader;


public class App {
    private static GameState s = GameState.getInstance();
    public static boolean shapeOutOfBox = false;
    public static boolean gameOver = false;

    public static void main(String[] args) throws InterruptedException {
        int FRAME_DURATION = 150;
        try {
            // Create a terminal
            Terminal terminal = TerminalBuilder.builder().system(true).build();
            terminal.enterRawMode();

            NonBlockingReader reader = terminal.reader();
            Shape.generateNewShape();
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
                                case 'D' -> GameLogic.moveLeft();   // ←
                                case 'C' -> GameLogic.moveRight();  // →
                            }
                        }
                    } else {
                        switch (key) {
                            case 'h' -> GameLogic.moveLeft();
                            case 'l' -> GameLogic.moveRight();
                        }
                    }
                }

                updateGame();
                GameRender.renderGame();

                long frameTime = System.currentTimeMillis() - startTime;
                long sleepTime = FRAME_DURATION - frameTime;
                if (sleepTime > 0) {
                    Thread.sleep(sleepTime);
                }
            }
        } catch (IOException e) {
            System.err.println("Error creating terminal: " + e.getMessage());
        }
    }

    public static void updateGame() {
        if (CollisionDetector.pieceCanMoveDown()) {
            GameLogic.movePieceDown();
        } else {
            GameLogic.lockPiece();
            GameLogic.clearFullRows();
            if (shapeOutOfBox) {
                gameOver = true;
            } else {
                Shape.generateNewShape();
            }
        }
    }
}
