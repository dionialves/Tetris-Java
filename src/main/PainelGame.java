package main;

import java.awt.*;
import tetriminoes.*;

public class PainelGame {
    // Atributos da tela principal onde os tetris serão criados
    public static final int WIDHT = 360;
    public static final int HEIGHT = 600;
    public static final int LEFT = 20;
    public static final int TOP = 40;
    private int bottom;

    // Atributos para a manipulação do game
    private int timer = 0;
    public static int SHAPE_POSITION_X;
    public static int SHAPE_POSITION_Y;

    // A classe Score é responsável por gerenciar todas a pontuação do game
    public static final Score score = new Score(40);
    // A classe Puzzle por gerenciar todo o quebra cabeça montado pelos tetris
    public final Puzzle puzzle = new Puzzle();
    // A TetriminoManager gerencia a criação dos tetris
    TetriminoManager tetriminoManager = new TetriminoManager();

    public PainelGame() {

        // Construção da janela
        setBottom(PainelGame.TOP + HEIGHT);
        PainelGame.SHAPE_POSITION_X = (PainelGame.LEFT + WIDHT /2) - Tetrimino.SIZE;
        PainelGame.SHAPE_POSITION_Y = 20;

        // Inicialização dos atributos responsáveis para geração dos tetris
        tetriminoManager.getNextShape().setX(500);
        tetriminoManager.getNextShape().setY(550);
        tetriminoManager.getCurrentShape().setX(PainelGame.SHAPE_POSITION_X);
        tetriminoManager.getCurrentShape().setY(PainelGame.SHAPE_POSITION_Y);
    }

    // Nesse atributo toda a logica do game é construida, temos nela uma variável timer que é um contador que controla
    // o tempo de execução do game, ou seja, quantas vezes por segundo a logica do game irá rodas.
    // Temos também o score.speed que é velocidade atribuida a cada level do game assim quando o resto da
    // divisão de timer com score.speed for 0, roda toda a logica do game.
    public void update() {

        setTimer(getTimer() + 1);
        if (getTimer() % (int) score.getSpeed() == 0) {
            // Controle de gravidade do tetris adicionando um valor ao Y do mesmo
            PainelGame.SHAPE_POSITION_Y+= Tetrimino.SIZE;

            // Nesse bloco temos a verificação se existe uma colisão, de forma vertical ou seja de cima para baixo.
            // caso sim entra no bloco e executas as seguintes ações:
            //
            // 1-> Desenha o tetria a matrix que controla os blocos já fixos
            // 2-> Verifica se com esse novo tetris fixado a matrix existe alguma linha completa
            // 3-> Reseta as informações de X e Y
            // 4-> Gera um novo tetris
            if (this.puzzle.hasCollided(tetriminoManager.getCurrentShape(), "normal")) {
                puzzle.mergeShapeToMatrix(tetriminoManager.getCurrentShape());
                puzzle.hasCompleteRow();
                PainelGame.SHAPE_POSITION_X = PainelGame.LEFT + WIDHT /2 - Tetrimino.SIZE;
                PainelGame.SHAPE_POSITION_Y = 20;
                this.randomShape();
            }
        }
    }

    public void draw(Graphics2D g2d) {

        // Desenha na tela os limites do retângulo principal do game, onde os tetris irão aparecer
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(4f));
        g2d.drawRect(PainelGame.LEFT-4, PainelGame.TOP-4, WIDHT+8, HEIGHT+8);

        // Desenha o retângulo menor indicando qual será a próxima forma a aparecer
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(4f));
        g2d.drawRect(420, 494, 200, 150);

        // Desenha as escritas na tela, bem como score, linhas completadas e level
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        g2d.drawString("NEXT", 490, 530);

        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        g2d.drawString("Tetris Game in Java", 420, 50);

        g2d.setFont(new Font("Arial", Font.PLAIN, 22));
        g2d.drawString("SCORE", 420, 120);

        g2d.setFont(new Font("Arial", Font.PLAIN, 16));
        g2d.drawString(Integer.toString(score.getScore()), 420, 145);

        g2d.setFont(new Font("Arial", Font.PLAIN, 22));
        g2d.drawString("LINES", 420, 190);

        g2d.setFont(new Font("Arial", Font.PLAIN, 16));
        g2d.drawString(Integer.toString(score.getTotalLines()), 420, 215);

        g2d.setFont(new Font("Arial", Font.PLAIN, 22));
        g2d.drawString("LEVEL", 420, 260);

        g2d.setFont(new Font("Arial", Font.PLAIN, 16));
        g2d.drawString(Integer.toString(score.getLevel()), 420, 285);

        // Inicializa os com valores de X e Y a forma atual e a próxima;
        tetriminoManager.getNextShape().setX(500);
        tetriminoManager.getNextShape().setY(550);
        tetriminoManager.getCurrentShape().setX(PainelGame.SHAPE_POSITION_X);
        tetriminoManager.getCurrentShape().setY(PainelGame.SHAPE_POSITION_Y);

        // Constrói cada forma, atualizando a informação de X e Y de cada retângulo que compõe a forma!
        tetriminoManager.getCurrentShape().buildShape();
        tetriminoManager.getNextShape().buildShape();

        // Desenha a próxima forma no espaço destinado a ela
        tetriminoManager.getNextShape().draw(g2d);

        // Limpa a matrix excluindo todos as formas em movimento
        this.puzzle.clearBoard();
        // Adiciona a nova forma em movimento a matrix
        this.puzzle.addTemporaryShapeToMatrix(tetriminoManager.getCurrentShape());
        // Desenha a matrix no retângulo
        this.puzzle.draw(g2d);
    }

    // Mêtodo responsável por gerar uma nova forma
    public void randomShape() {
        tetriminoManager.updateShapes();
        tetriminoManager.getCurrentShape().setX(PainelGame.SHAPE_POSITION_X);
        tetriminoManager.getCurrentShape().setY(PainelGame.SHAPE_POSITION_Y);
        tetriminoManager.getCurrentShape().buildShape();
    }

    // Mêtodo responsvale por girar a forma em sentido horário
    public void changeRotatedShape() {
        tetriminoManager.getCurrentShape().changeRotated();
    }

    public int getBottom() {
        return bottom;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }
}
