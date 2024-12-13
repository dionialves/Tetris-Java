package main;

// Classe responsável por controlar a pontuação do game, o level, velocidade a partir do level e também quantas linhas
// foram completadas.
public class Score {
    private double speed;
    private int score;
    private int lines;
    private int level;
    private int totalLines;
    // Esse atributo é um lista contendo os pontos por linha que serão atribuidos.
    //
    // 1 linha: 40 pontos
    // 2 linhas: 100 pontos
    // 3 linhas: 300 pontos
    // 4 linhas: 1200 pontos
    private int[] scoreForLines = {40, 100, 300, 1200};

    public Score(int speed) {
        this.setSpeed(speed);
        this.setScore(0);
        this.setLines(0);
        this.setLevel(1);
    }

    // Responsável por adicionar pontuação quando uma ou mais linhas são completadas
    public void scoreCompletedLines(int lines) {
        // Calcula os pontos, levando em consideração o número de linhas cmpletadas
        this.setScore((this.getScore() + this.scoreForLines[lines-1] * this.getLevel()));
        // Adiciona as linhas em um contador geral
        this.setTotalLines(this.getTotalLines() + lines);
        // Neste bloco, verifico se a quantidade de linhas é maio ou igual a 10, se sim o jogado passa de level.
        if (this.getLines() + lines >= 10) {
            // Na instrução abaixo, atualizo o número de linhas que falta para completar 10. Por que isso? Pois, pode
            // ser que o jogador tenha 9 linhas e complete 4 em uma ação, então ele passa de nível e tem 3 linhas
            // atuais que serão contabilizadas para o próximo nível
            this.setLines((this.getLines() + lines) - 10);
            // Sobe de nível
            this.setLevel(this.getLevel() + 1);
            // Chama o método responsável por aumentar a velocidade
            this.newSpeed();
        } else {
            // Caso não suba de nível apenas aumenta o contador de linhas
            this.setLines(this.getLines() + lines);
        }
    }

    // Método responsável por atribuir uma velocidade logarítmica dependendo do level do jogado.
    public void newSpeed() {
        // Função Logarítmica
        // Posso apenas manipular essas variáveis para ser mais agressivo no aumento de velocidade ou menos agressivo
        // double this.getSpeed() = 40; Velocidade inicial (frames)
        // double minimumSpeed = 5; Velocidade mínima (frames)
        // double reductionIntensity = 10; Controle da intensidade de redução
        // double scale = 1; Escala do nível
        // double horizontalDisplacement = 1; Deslocamento horizontal da função logarítmica

        double minimumSpeed = 5;
        double reductionIntensity = 10;
        double scale = 1;
        double horizontalDisplacement = 1;

        // Aplica a função logarítmica
        double log = Math.log(scale * this.getLevel() + horizontalDisplacement);
        double newSpeed = this.getSpeed() - reductionIntensity * log;

        // Define uma velocidade minima para ser usada
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
