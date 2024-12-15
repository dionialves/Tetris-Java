package main;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import tetriminoes.Tetrimino;

public class Puzzle {
    private final int width = (PainelGame.WIDHT / Tetrimino.SIZE);
    private final int height = ((PainelGame.HEIGHT + Tetrimino.SIZE) / Tetrimino.SIZE);
;
    private final RectangleBoard[][] board = new RectangleBoard[height][width];

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
    //
    // Esse método pega as coordenadas de cada bloco, converte para uma posição na matriz e registra na matriz
    private void buildMatrix(Tetrimino shape, int id) {
        // Recebendo o bloco contendo as informações dos 4 blocos que constituem a forma
        // e criando um for para trabalharmos com a mesma logica em todos os blocos
        for (Rectangle rectangle : shape.block) {
            // convertendo para coordenadas da matriz
            int x = this.coordinateConverter(rectangle.x);
            int y = this.coordinateConverter(rectangle.y);

            // Essa condição verifica se em algum momento entra uma coordenada que após a conversão saia dos limites da matriz.
            // Esse problema ocorre, pois na troca da forma atuação para a próxima forma, existe um momento onde esse
            // método é chamado com as coordenada da próxima forma, o que resultava em erro.
            if (y < (this.board.length) && x < (this.board[0].length)) {
                RectangleBoard copyRectangle = new RectangleBoard(x, y, rectangle.width, rectangle.height, shape.getColor());

                // Caso id seja 1 seta esse retângulo com moving como true, caso seja id 2, seta como build como true
                // Podemos dizer que a matriz tem dois tipos de forma, as em movimento e as já construídas, ou seja,
                // aquelas que já foram encaixadas
                if (id == 1) {
                    copyRectangle.setMoving(true);
                } else if (id == 2) {
                    copyRectangle.setBuild(true);
                }
                // Adiciona o retângulo a matriz
                this.board[y][x] = copyRectangle;
            }
        }
    }

    // Mêtodo usado para adicionar formas temporárias a matrix, as que seto o atribuido moving como true
    public void addTemporaryShapeToMatrix(Tetrimino shape) {
        this.buildMatrix(shape, 1);
    }

    // Mêtodo usado para adicionar formas permanentes a matrix, as que seto o atribuido build como true
    public void mergeShapeToMatrix(Tetrimino shape) {
        this.buildMatrix(shape, 2);
    }

    // Esse método é muito importante e sua construção me trouxe muitos desafios, agora que esta construido percebo o
    // quão simples ele é. Consiste em percorrer a matrix e apagar a linha que foi totalmente completada
    //
    // Acredito que esse método poderia ser dividido em outros três métodos.
    public void hasCompleteRow() {
        List<Integer> rows = new ArrayList<>();

        // Bloco responsável por encontrar as linha que estão totalmente preenchidas
        for (int i = 0; i < board.length; i++) {
            boolean isRowFull = true;
            // A Logica aqui é definir uma variável como true no caso a isRowFull, e testar todas as colunas de cada
            // linha, se alguma não estiver construída (isBuild()), isRowFull é atribuído como falso. Após terminar a
            // linha se isRowFull continuar como true, eu adicionado o numero da linha em um array que será usado no
            // proximo bloco.
            for (int j = 0; j < board[i].length; j++) {
                if (!board[i][j].isBuild()) isRowFull = false;
            }
            if (isRowFull) rows.add(i);
        }

        // Se a variável rows não estiver vazia entra no proximo bloco de codigo, que terá como objetivo, retirar a
        // informação de construção de cada retângulo, colocando com build como false.
        if (!rows.isEmpty()) {
            PainelGame.score.scoreCompletedLines(rows.size());
            // Zera a linha nesse for
            for (int i = 0; i < rows.size(); i++) {
                for (int j = i; j < board[i].length; j++) {
                    board[rows.get(i)][j].setBuild(false);
                }
            }

            // Nesse trecho de código eu simplesmente copio as INFORMAÇÕES da linha de cima, na linha que foi apagado
            // isso sucessivamente em todas as linhas, acima das linhas que foram apagadas
            // Pode observar que uso um método chamado cloneObject, justamente para copiar os dados e não o objeto.
            for (int x = 0; x < rows.size(); x++) {
                for (int i = rows.get(x); i > 0 ; i--) {
                    for (int j = 0; j < board[i].length ; j++) {
                        board[i][j] = board[i-1][j].cloneObject();
                        if (board[i][j].isBuild()) board[i][j].y++;
                    }
                }
            }
        }
    }
    // Esse método apaga da matriz todas os retângulos setados como moving=true.
    // Isso é necessário, pois esse método é chamado para limpar a matrix para depois adicionar a forma novamente,
    // contendo as informações de X e Y atualizadas. Assim se faz o movimento da forma descendo!
    public void clearBoard() {
        for (int y = 0; y < this.board.length; y++) {
            for (int x = 0; x < this.board[y].length; x++) {
                if (this.board[y][x].isMoving()) {
                    this.board[y][x].setMoving(false);
                    this.board[y][x].x = 0;
                    this.board[y][x].y = 0;
                    this.board[y][x].setColor(null);
                }
            }
        }
    }

    // Método também muito importante e que levou certo tempo para ser escrito, pois ele precisa um true ou false,
    // quando alguns retângulo da forma atingiu as extremidades (Esquerda, direita e fundo), e também saber quando
    // é colidido com outra forma já construída da matrix;
    //
    // Acredito que esse método poderia ter cido dividido em pelo menos três metodos, pois usei um atributo direction,
    // para saber de onde ele foi invocado e o que fazer em cada situação.
    public boolean hasCollided(Rectangle[] blocks, String direction) {

        // Quando invocado eu pego os blocos da forma atual e repasso todos, caso algum colida com alguma extremidade
        // ou mesmo outro bloco, o for e método são interrompidos com um return
        for (Rectangle rectangle : blocks) {

            // Converte para coordenadas da matrix
            int x = this.coordinateConverter(rectangle.x);
            int y = this.coordinateConverter(rectangle.y);

            // Essa condição verifica se em algum momento entra uma coordenada que após a conversão saia dos limites da matriz.
            // Esse problema ocorre, pois na troca da forma atuação para a próxima forma, existe um momento onde esse
            // método é chamado com as coordenada da próxima forma, o que resultava em erro.
            if (y < (this.board.length) && x < (this.board[0].length)) {
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


                // Verifica colisão na extremidade esquerda e com blocos na esquerda
                if (direction.equals("right")) {
                    if (x > this.board[y].length) {
                        return true;
                    }
                    if (board[y][x+1].isBuild()) {
                        return true;
                    }
                }
                // Verifica colisão na extremidade direita e com blocos na direita
                if (direction.equals("left")) {
                    if (x <= 0) {
                        return true;
                    }
                    if (board[y][x-1].isBuild()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // Método que converte as coordenadas de desenho em coordenadas da matrix
    private int coordinateConverter(int coordinate) {
        if (coordinate <= Tetrimino.SIZE) return 0;
        else return coordinate / Tetrimino.SIZE - 1;
    }

    // Método para desenhar a matrix na tela, ele percore todas as posições da matrix verificando se tem alguma posição
    // construída ou mesmo em movimento e desenha na tela
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
}
