package main;

import java.awt.*;

// Essa classe foi construída para ser utilizada nas posições da matrix, então cada posição da matrix tem um objeto
// dessa classe instanciado.
public class RectangleBoard extends Rectangle {
    private boolean build = false;
    private boolean isMoving = false;
    private Color color;

    public RectangleBoard(int x, int y, int width, int height, Color color) {
        super(x, y, width, height);
        this.color = color;
    }

    // Método clone para clonar as informações do objeto atual em outro.
    public RectangleBoard cloneObject() {
        RectangleBoard copy = new RectangleBoard(this.x, this.y, this.width, this.height, this.color);
        copy.build = this.build;
        copy.isMoving = this.isMoving;
        return copy;
    }

    public RectangleBoard(int size, int size1) {
        super(size, size1);
    }

    public boolean isBuild() {
        return build;
    }

    public void setBuild(boolean build) {
        this.build = build;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }
}
