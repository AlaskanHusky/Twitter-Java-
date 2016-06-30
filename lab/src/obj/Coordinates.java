package obj;

/**
 * Класс, который описывает точку, откуда был сделан твит
 * содержит 2 поля, 2 конструктора и 2 метода
 * @author Yanushevsky Pavel
 * @version 1.1
 */
public class Coordinates {

    private double x;
    private double y;

    public Coordinates() {
    }

    public Coordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}

