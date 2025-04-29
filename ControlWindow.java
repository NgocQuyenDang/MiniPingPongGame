import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

public class ControlWindow extends JPanel implements ActionListener, KeyListener {

   private Ball ball;
   private Player leftPlayer, rightPlayer;
   private GameManager gameManager;
   private Timer timer = new Timer(30, this);
   private Render render;


    public ControlWindow() {
        this.setBackground(Color.BLACK);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow(true);
        this.addKeyListener(this);

        ball = new Ball(Constant.WIDTH / 2, Constant.HEIGHT / 2, 30);
        leftPlayer = new Player(0, Constant.HEIGHT / 2);
        rightPlayer = new Player(Constant.WIDTH - 40, Constant.HEIGHT / 2);

        render = new Render(ball, leftPlayer, rightPlayer);

        gameManager = new GameManager(ball, leftPlayer, rightPlayer, render);

        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        render.render(g2d, getWidth(), getHeight());
    }

    public void actionPerformed(ActionEvent e) {
        gameManager.update(getWidth(), getHeight());

        repaint();
    }

    public void keyPressed(KeyEvent e) {
        gameManager.handleKeyInput(e.getKeyCode(), getHeight());
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}