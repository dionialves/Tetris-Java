package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
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
