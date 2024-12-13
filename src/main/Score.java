package main;

public class Score {
    private double speed;
    private int score;
    private int lines;
    private int level;
    private int totalLines;
    private int[] scoreForLines = {40, 100, 300, 1200};

    public Score(int speed) {
        this.setSpeed(speed);
        this.setScore(0);
        this.setLines(0);
        this.setLevel(1);
    }

    public void scoreCompletedLines(int lines) {
        // Calcula os pontos
        this.setScore((this.getScore() + this.scoreForLines[lines-1] * this.getLevel()));
        // Seta as linhas em um contador geral
        this.setTotalLines(this.getTotalLines() + lines);
        // Aumenta o número de linhas, se atingir 10 linhas sobe de level
        if (this.getLines() + lines >= 10) {
            this.setLines(this.getLines() - lines);
            this.setLevel(this.getLevel() + 1);
            this.newSpeed();
            System.out.println("New speed is " + this.getSpeed());
        } else {
            this.setLines(this.getLines() + lines);
        }
    }

    public void newSpeed() {
        /*
         Função Logarítmica
          double this.getSpeed() = 40;         Velocidade inicial (frames)
          double minimumSpeed = 5;             Velocidade mínima (frames)
          double reductionIntensity = 10;      Controle da intensidade de redução
          double scale = 1;                    Escala do nível
          double horizontalDisplacement = 1;   Deslocamento horizontal da função logarítmica
        */

        double minimumSpeed = 5;
        double reductionIntensity = 10;
        double scale = 1;
        double horizontalDisplacement = 1;

        double log = Math.log(scale * this.getLevel() + horizontalDisplacement);
        double newSpeed = this.getSpeed() - reductionIntensity * log;


        this.setSpeed(Math.max(minimumSpeed, newSpeed));
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLines() {
        return lines;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getTotalLines() {
        return totalLines;
    }

    public void setTotalLines(int totalLines) {
        this.totalLines = totalLines;
    }

    public int[] getScoreForLines() {
        return scoreForLines;
    }

    public void setScoreForLines(int[] scoreForLines) {
        this.scoreForLines = scoreForLines;
    }
}
