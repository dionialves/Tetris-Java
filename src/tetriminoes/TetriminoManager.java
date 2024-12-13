package tetriminoes;

import java.util.Random;

public class TetriminoManager {
    private Tetrimino currentShape;
    private Tetrimino nextShape;
    private final Random random;

    public TetriminoManager() {
        random = new Random();

        currentShape = getRandomTetrimino();
        nextShape = getRandomTetrimino();
    }

    // Método para pegar um novo Tetrimino aleatório
    private Tetrimino getRandomTetrimino() {
        int shapeIndex = random.nextInt(7);
        return switch (shapeIndex) {
            case 0 -> new ShapeI();
            case 1 -> new ShapeJ();
            case 2 -> new ShapeL();
            case 3 -> new ShapeO();
            case 4 -> new ShapeS();
            case 5 -> new ShapeT();
            case 6 -> new ShapeZ();
            default -> throw new IllegalStateException("Índice inválido para Tetrimino: " + shapeIndex);
        };
    }

    // Método para atualizar o currentShape e nextShape
    public void updateShapes() {
        currentShape = nextShape;
        nextShape = getRandomTetrimino();
    }

    // Getters para acessar as formas
    public Tetrimino getCurrentShape() {
        return currentShape;
    }

    public Tetrimino getNextShape() {
        return nextShape;
    }
}
