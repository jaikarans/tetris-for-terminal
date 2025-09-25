package io.github.jaikarans.tetris;

import java.io.IOException;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.NonBlockingReader;


public class App {

    static GameRender renderer = GameRender.getInstance();
    static GameLogic logic = GameLogic.getInstance();

    public static void main(String[] args) throws InterruptedException {
        try {
            // Create a terminal
            Terminal terminal = TerminalBuilder.builder().system(true).build();
            terminal.enterRawMode();

            NonBlockingReader reader = terminal.reader();
            while (true) {

                int key = -1;
                if (reader.ready()) {   // non-blocking check
                    key = reader.read();
                }
                if (key != -1) {
                    // System.out.println(key);
                    // // Thread.sleep(1000);
                    switch (key) {
                        case 'h':
                            logic.moveLeft();
                            break;
                        case 'l':
                            logic.moveRight();
                            break;
                    }
                }


                logic.fallDown();
                renderer.renderGame();
                Thread.sleep(200);

            }

        } catch (IOException e) {
            System.err.println("Error creating terminal: " + e.getMessage());
        }

    }

}
