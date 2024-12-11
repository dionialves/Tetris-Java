package main;

import tetriminoes.Tetrimino;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Puzzle {
    private final int width = (PainelGame.WIDHT / Tetrimino.SIZE);
    private final int height = ((PainelGame.HEIGHT + Tetrimino.SIZE) / Tetrimino.SIZE);
    private final int limitBottom = PainelGame.TOP + PainelGame.HEIGHT;
    private final int limitLeft = PainelGame.LEFT;
    private int[][] area = new int[height][width];
    private RectangleBoard[][] board = new RectangleBoard[height][width];

    public Puzzle() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new RectangleBoard(Tetrimino.SIZE, Tetrimino.SIZE);
            }
        }
    }



    // Método responsável por construir a matrix
    // Quando o id for 1, significa o shape que esta em movimento e será apagado e reescrito em outra posição
    // quando o id for 2, significa que o shape colidiu e se fixou na matrix, só sairá quando uma linha completa
    // for construída
    private void buildMatrix(Tetrimino shape, int id) {

        for (Rectangle rectangle : shape.block) {
            int x = this.coordinateConverter(rectangle.x);
            int y = this.coordinateConverter(rectangle.y);

            RectangleBoard copyRectangle = new RectangleBoard(x, y, rectangle.width, rectangle.height, shape.getColor());

            if (id == 1) {
                copyRectangle.setMoving(true);
            } else if (id == 2) {
                copyRectangle.setBuild(true);
            }

            this.board[y][x] = copyRectangle;
        }
    }

    public void addTemporaryShapeToMatrix(Tetrimino shape) {
        this.buildMatrix(shape, 1);

    }

    public void mergeShapeToMatrix(Tetrimino shape) {
        this.buildMatrix(shape, 2);
    }

    public void hasCompleteRow() {
        List<Integer> rows = new ArrayList<>();

        // Encontrar as linhas completas
        for (int i = 0; i < board.length; i++) {
            boolean isRowFul = true;

            for (int j = 0; j < board[i].length; j++) {
                if (!board[i][j].isBuild()) {
                    isRowFul = false;
                }
            }

            if (isRowFul) {
                rows.add(i);
            }
        }

        // zeras as linhas completas
        for (int i = 0; i < rows.size(); i++) {
            for (int j = i; j < board[i].length; j++) {
                board[rows.get(i)][j].setBuild(false);
            }
        }

        // pegar as linhas acima da linha completa e baixar todas
        for (int x = 0; x < rows.size(); x++) {
            for (int i = rows.get(x); i > 0 ; i--) {


                //board[i] = board[i - 1];

                for (int j = 0; j < board[i].length ; j++) {

                    board[i][j] = board[i-1][j].cloneObject();

                    if (board[i][j].isBuild()) {
                        board[i][j].y++;
                    }
                }
            }
        }
    }

    public void clearBoard() {
        for (int y = 0; y < this.board.length; y++) {
            for (int x = 0; x < this.board[y].length; x++) {

                // Faz a limpeza do quadro, resetando algumas informações que ficam nos retângulos
                if (this.board[y][x].isMoving()) {
                    this.board[y][x].setMoving(false);
                    this.board[y][x].x = 0;
                    this.board[y][x].y = 0;
                    this.board[y][x].setColor(null);
                }
            }
        }
    }

    public boolean hasCollided(Tetrimino shape, String direction) {

        for (int i = 0; i < board.length; i++) {
            System.out.print(i+": ");
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].isMoving()) {
                    System.out.print("1 ");
                } else if (board[i][j].isBuild()) {
                    System.out.print("2 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println(" ");
        }

        for (Rectangle rectangle : shape.getBlock()) {

            int x = this.coordinateConverter(rectangle.x);
            int y = this.coordinateConverter(rectangle.y);

            // Colisão com o fundo
            if (direction.equals("down") || direction.equals("normal")) {
                if (y >= (this.board.length - 1)) {
                    return true;
                }
                // Verifica colisão com outro bloco no sentido vertical
                if (board[y+1][x].isBuild()) {
                    return true;
                }
            }
            if (direction.equals("right")) {
                System.out.println(this.board[y].length);
                if (x > this.board[y].length) {
                    return true;
                }
                if (board[y][x+1].isBuild()) {
                    return true;
                }
            }
            if (direction.equals("left")) {
                if (x <= 0) {
                    return true;
                }
                if (board[y][x-1].isBuild()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void draw(Graphics2D g2d) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                if ((this.board[i][j].isMoving() || this.board[i][j].isBuild()) && i > 0) {

                    g2d.setColor(board[i][j].getColor());
                    g2d.fillRect(
                            (board[i][j].x * Tetrimino.SIZE) + Tetrimino.SIZE,
                            (board[i][j].y * Tetrimino.SIZE) + Tetrimino.SIZE,
                            board[i][j].width,
                            board[i][j].height
                    );

                    g2d.setColor(Color.black);
                    g2d.drawRect(
                            (board[i][j].x * Tetrimino.SIZE) + Tetrimino.SIZE,
                            (board[i][j].y * Tetrimino.SIZE) + Tetrimino.SIZE,
                            board[i][j].width,
                            board[i][j].height
                    );
                }
            }
        }
    }

    private int coordinateConverter(int coordinate) {
        // Preciso melhor esse mêtodo, não estou gostando da construção dessas condições
        if (coordinate <= Tetrimino.SIZE) {
            return 0;
        } else {
            return coordinate / Tetrimino.SIZE - 1;
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
