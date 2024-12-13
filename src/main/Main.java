package main;

import javax.swing.*;

// Mêtodo principal de execução do projeto
public class Main {
    public static void main(String[] args) {
        // Inicialização da classe GameEngine que controla todo o game
        GameEngine game = new GameEngine();

        // Construção da janela
        JFrame window = new JFrame("## Rectangle Hunt");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        // Adicionar a classe GameEngine a janela criada
        window.add(game);
        window.pack();

        // Parametros importantes para centralizar a tela no monitor e a tornar visivel
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        // Adicionando tudo isso a uma Thread
        new Thread(game).start();
    }
}
