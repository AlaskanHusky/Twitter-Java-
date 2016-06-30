package draw;

import obj.Coordinates;
import obj.IntegerPoint;
import obj.State;
import obj.States;

import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame {

    private static final String title = "Map";
    private final Integer MULTI = 1000000;
    States listOfStates = new States();

    public Frame(States listOfStates) {
        super(title);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        this.listOfStates = listOfStates;
    }
    public void paint(Graphics g) {
        super.paint(g);
        Dimension size = this.getSize();

        for (State st : listOfStates.getStates()) {
            if (!st.getName().equals("AK")) {
                Polygon polygon = new Polygon();
                for (Coordinates location : st.getCoordinates()) {
                    IntegerPoint integerPoint = toIntegerPoints(location);
                    polygon.addPoint((integerPoint.getX() / 100000) + 1500, -1 * (integerPoint.getY() / 100000) + 700);
                }

                g.setColor(new Color((int) (Math.random() * 0x1000000)));
                g.fillPolygon(polygon);
            }
        }
    }
    private IntegerPoint toIntegerPoints(Coordinates doublePoints) {
        return (new IntegerPoint((int)(doublePoints.getX())*MULTI, (int) (doublePoints.getY()*MULTI)));
    }
}
