package ball;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm {
    private JPanel panel1;
    private JTextField radiusField;
    private JTextField xField;
    private JTextField yField;
    private JTextField dxField;
    private JTextField dyField;
    private JTextField kField;
    private JTextField timeField;
    private JTextField dtField;
    private JTextArea logArea;
    private JButton simulateButton;

    public MainForm() {
        simulateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simulate();
            }
        });
    }

    public void simulate() {
        logArea.setText("");

        float radius = Float.parseFloat(radiusField.getText());
        float x = Float.parseFloat(xField.getText());
        float y = Float.parseFloat(yField.getText());
        float dx = Float.parseFloat(dxField.getText());
        float dy = Float.parseFloat(dyField.getText());
        float time = Float.parseFloat(timeField.getText()) * 1000;
        float dt = Float.parseFloat(dtField.getText()) * 1000;
        float k = Float.parseFloat(kField.getText());

        float leftBorder = 0;
        float rightBorder = 10;

        float dtActual = 20;
        float multiplier = dtActual/1000;

        float timeTillNextLog = 0;
        int counter = 1;

        for (int i = 0; i <= time; i+=dtActual) {
            if(timeTillNextLog < 1) {
                timeTillNextLog = dt;
                logArea.append(String.format("t%d: x = %.2f , y = %.2f , height = %.2f , speed = %.2f \n",counter,x,y,y-radius,Math.sqrt(dx*dx + dy*dy)));
                counter++;
            }
            timeTillNextLog -= dtActual;

            dy -= 9.8*multiplier;

            x += dx*multiplier;
            y += dy*multiplier;

            if (y - radius < 0) {
                y = radius;
                dy *= -k;
                if (Math.abs(dy) < 0.01) {
                    dy = 0;
                }
            }

            if (x - radius < leftBorder) {
                x = radius;
                dx *= -k;
            }

            if (x + radius > rightBorder) {
                x = rightBorder - radius;
                dx *= -k;
            }
        }

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ball Simulation");

        frame.setContentPane(new MainForm().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }


}
