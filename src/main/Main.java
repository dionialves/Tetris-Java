package main;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        GameEngine game = new GameEngine();

        // Bloco de código responsável por construir a janela
        JFrame window = new JFrame("## Rectangle Hunt");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        window.add(game);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        new Thread(game).start();
    }
}
