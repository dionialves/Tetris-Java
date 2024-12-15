package main;

import tetriminoes.Tetrimino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
Essa classe fica responsável por controlar todo o game, e possui atributos específicos de tela e do controle
do game como a captura do teclado.
*/
public class GameEngine extends JPanel implements Runnable, KeyListener {
    // Atributos das telas
    public static final int WIDTH = 680;
    public static final int HEIGHT = 720;
    private final int fps = 60;

    // Inicialização classe PainelGame que controla o fluxo do game
    private final PainelGame pm = new PainelGame();

    public GameEngine() {
        this.addKeyListener(this);

        this.setPreferredSize(new Dimension(GameEngine.WIDTH, GameEngine.HEIGHT));
        this.setFocusable(true);
        this.setLayout(null);
        this.requestFocusInWindow();
    }

    // Class update, onde serão atualizados a lógica do jogo
    public void update() {
        pm.update();
    }

    // Herança da classe paintComponent, responsável por desenhar os objetos na tela
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);

        // Nesse trecho de código, faço um cast da classe Graphics para Graphics2D
        // crio um retângulo na cor preta para definir todo o backgroud.
        // e chamo o método dram da classe PainelGame, dentro desse método irei desenhar
        // tudo que a tela irá apresentar.
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        pm.draw(g2d);
    }

    // Usando a implementação da interface Runnable, herdei o mêtodo run que será
    // responsável pela quantidade de frames na tela.
    // a logica utilizada não foi pensada por mim e sim pelo canal
    // RyiSnow link do youtube: https://www.youtube.com/@RyiSnow
    @Override
    public void run() {

        double drawInterval = 1000000000/getFps();
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (true) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                this.update();
                this.repaint();
                delta--;
            }
        }
    }

    public int getFps() {
        return fps;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    // Herança da interface KeyListener, para capturar as teclados do teclado.
    // São tratadas as seguintes teclas:
    //
    // Seta para cima: Para girar a forma em sentido horário
    // Seta para direita: Para movimentar a peça para a direita
    // Seta para baixo: Para descer a forma mais rapidamente
    // Seta para a esquerda: Para movimentar a peça para esquerda
    @Override
    public void keyPressed(KeyEvent e) {
        // Todos as condições abaixo são parecidas, mudando apenas a tecla de entrada eo atributo direction
        // que é passado a essa função, pm.puzzle.hasCollided.
        //
        // Podemos observar que o método pm.puzzle.hasCollided, retorna um booleano, e na construção da logica
        // foi colocado um ! na frente, sendo esta uma negação
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (!pm.puzzle.hasCollided(pm.tetriminoManager.getCurrentShape().block, "right")) {
                PainelGame.SHAPE_POSITION_X+= Tetrimino.SIZE;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (!pm.puzzle.hasCollided(pm.tetriminoManager.getCurrentShape().block, "left")) {
                PainelGame.SHAPE_POSITION_X-= Tetrimino.SIZE;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            Rectangle[] cloneRectangle = pm.tetriminoManager.getCurrentShape().cloneRectangle();
            cloneRectangle[0].y += Tetrimino.SIZE;
            cloneRectangle[1].y += Tetrimino.SIZE;
            cloneRectangle[2].y += Tetrimino.SIZE;
            cloneRectangle[3].y += Tetrimino.SIZE;

            if (!pm.puzzle.hasCollided(cloneRectangle, "down")) {
                if (!PainelGame.collided) PainelGame.SHAPE_POSITION_Y+= Tetrimino.SIZE;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            pm.changeRotatedShape();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
