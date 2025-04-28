import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PingPong extends JFrame {

    private ControlWindow controlWindow = new ControlWindow();
    private JFrame frame;
    private IntroScreen introScreen;
    public PingPong() {
        frame = new JFrame("Ping Pong Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Constant.WIDTH, Constant.HEIGHT);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);

        introScreen = new IntroScreen();
        frame.add(introScreen);

        frame.setVisible(true);
//        this.add(controlWindow);
//        this.pack();
//        this.setTitle("Ping Pong");
//        this.setSize(this.getWidth(), this.getHeight());
//        this.setLocationRelativeTo(null);
//        this.setResizable(true);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PingPong();
        });
    }
}

