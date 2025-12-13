package io.github.jaikarans.tetris;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App {
    public static boolean shapeOutOfBox = false;
    public static boolean gameOver = false;

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Game game = context.getBean(Game.class);
        game.run();

    }
}
