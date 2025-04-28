import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import javax.imageio.ImageIO;

public class IntroScreen extends JPanel implements KeyListener {
    private boolean isGameStarted = false;
    private Font titleFont = new Font("Consolas", Font.BOLD, 60);
    private Font instructionFont = new Font("Consolas", Font.ITALIC, 30);
    private Color backgroundColor = new Color(0, 0, 50);
    private BufferedImage backgroundImage;

    public IntroScreen() {
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();

        try {
            backgroundImage = ImageIO.read(new File("uu.jpg"));
        } catch (Exception e) {
            System.out.println("File not found");
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw the background
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint backgroundGradient = new GradientPaint(0, 0, backgroundColor
                                                            , 0, getHeight(), new Color(20, 20, 50));
        g2d.setPaint(backgroundGradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw background image
        if (backgroundImage != null) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));

            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        // Ve tieu de
        g2d.setFont(titleFont);
        g2d.setColor(Color.WHITE);
        String title = "It's Ping Pong Time";
        FontMetrics metrics = g2d.getFontMetrics(titleFont);
        int titleX = (getWidth() - metrics.stringWidth(title)) / 2;
        g2d.drawString(title, titleX, getHeight() / 3);

        g2d.setFont(instructionFont);
        g2d.setColor(Color.WHITE);
        String instruction = "Press any key to start";
        metrics = g2d.getFontMetrics(instructionFont);
        int instructionX = (getWidth() - metrics.stringWidth(instruction)) / 2;
        g2d.drawString(instruction, instructionX, getHeight() / 2);

    }

    public boolean isGameStarted() {
        return isGameStarted;
    }

    public void keyPressed(KeyEvent e) {
        isGameStarted = true;
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.getContentPane().removeAll();
        ControlWindow controlWindow = new ControlWindow();
        frame.add(controlWindow);
        frame.revalidate();
        frame.repaint();
        controlWindow.requestFocusInWindow();
    }

    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}


}
