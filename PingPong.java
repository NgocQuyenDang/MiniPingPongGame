import javax.swing.*;
import java.awt.*;

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
        Image icon = Toolkit.getDefaultToolkit().getImage("/images/mainlogo.jpg");
        setIconImage(icon);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PingPong();
        });
    }
}

