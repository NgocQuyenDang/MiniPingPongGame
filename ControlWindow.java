import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

public class ControlWindow extends JPanel implements ActionListener, KeyListener {

    private Ball ball = new Ball(Constant.WIDTH / 2, Constant.HEIGHT / 2, 30);
    private Timer timer = new Timer(30, this);
    private Player leftPlayer = new Player(0, Constant.HEIGHT / 2);
    private Player rightPlayer = new Player(Constant.WIDTH- 40, Constant.HEIGHT/ 2);
    private Font gameFont = new Font("Consolas", Font.BOLD, 40);
    private Font infoFont = new Font("Consolas", Font.ITALIC, 20);
    private Font titleFont = new Font("Consolas", Font.BOLD, 80);
    private Font inputFont = new Font("Consolas", Font.PLAIN, 24);
    private Font buttonFont = new Font("Consolas", Font.BOLD, 20);

    private Color backgroundColor = new Color(0, 0, 20);
    private Color ballColor = new Color(255, 215, 0);
    private Color leftPlayerColor = new Color(30, 144, 255);
    private Color rightPlayerColor = new Color(220, 20, 60);
    private Color netColor = new Color(200, 200, 200, 100);

    //Gradient for bar
    private GradientPaint leftGradient;
    private GradientPaint rightGradient;

    private int gridSpacing = 50;

    // Hiệu ứng bóng va chạm
    private boolean collision = false;
    private int flashCount = 0;

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int panelWidth = this.getWidth();
        int panelHeight = this.getHeight();

        // Draw the background
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint backgroundGradient = new GradientPaint(0,0, backgroundColor,
                                                            0, panelHeight, new Color(20, 20, 50));

        g2d.setPaint(backgroundGradient);
        g2d.fillRect(0, 0, panelWidth, panelHeight);

        // Draw the net
        g2d.setColor(netColor);
        g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{10}, 0));
        g2d.drawLine(panelWidth / 2, 0, panelWidth / 2, panelHeight);

        // Draw the margin
        g2d.setColor(new Color(100, 100, 100));
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRect(2,2, panelWidth - 4, panelHeight - 4);

        // Draw 2 players

        leftGradient = new GradientPaint(leftPlayer.getX(), 0, leftPlayerColor,
                                    leftPlayer.getY() + leftPlayer.getWidth(), 0,
                                        new Color(100, 200, 255));

        rightGradient = new GradientPaint(rightPlayer.getX(), 0, rightPlayerColor,
                                    rightPlayer.getX() + rightPlayer.getWidth() - 5,panelHeight,
                                        new Color(255, 100, 100));

        // Draw the left player
        g2d.setPaint(leftGradient);
        g2d.fillRoundRect(leftPlayer.getX() + 9, leftPlayer.getY(), leftPlayer.getWidth(), leftPlayer.getHeight(), 15, 15);
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRoundRect(leftPlayer.getX() + 9, leftPlayer.getY(), leftPlayer.getWidth(), leftPlayer.getHeight(), 15, 15);

        // Draw the right player
        g2d.setPaint(rightGradient);
        g2d.fillRoundRect(panelWidth - 40, rightPlayer.getY(), rightPlayer.getWidth(), rightPlayer.getHeight(), 15, 15);
        g2d.setColor(Color.WHITE);
        g2d.drawRoundRect(panelWidth - 40, rightPlayer.getY(), rightPlayer.getWidth(), rightPlayer.getHeight(), 15, 15);

        // Hieu ung va cham cho qua bong
        if (collision && flashCount < 3) {
            g2d.setColor(Color.WHITE);
            flashCount++;
        } else {
            g2d.setColor(ballColor);
            collision = false;
            flashCount = 0;
        }

        // Ve bong voi hieu ung anh sang
        g2d.fillOval(ball.x, ball.y, ball.getDiameter(), ball.getDiameter());
        // Hieu ung anh sang cho bong
        Color ballHightlight = new Color(255, 255, 255, 150);
        g2d.setColor(ballHightlight);
        g2d.fillOval(ball.x + 5, ball.y + 5, ball.getDiameter() / 3, ball.getDiameter() / 3);

        // Draw the score table
        g2d.setFont(gameFont);
        g2d.setColor(Color.WHITE);
        g2d.drawString("LEFT" + " : " + leftPlayer.getScore(), panelWidth / 5, 60);
        g2d.drawString("RIGHT" + " : " + rightPlayer.getScore(), 4 * panelWidth / 6, 60);

        // Game instruction
        g2d.setFont(infoFont);
        g2d.setColor(new Color(200, 200, 200));
        g2d.drawString("W/S: Left Player", 10, panelHeight - 20);
        g2d.drawString("↑/↓: Right Player", panelWidth - 200, panelHeight - 20);
    }

    private void resetBall() {
        ball.x = this.getWidth() / 2;
        ball.y = this.getHeight() / 2;
    }


    // Method to make the ball bounce back
    public void actionPerformed(ActionEvent e) {
        ball.x += ball.speedX;
        ball.y += ball.speedY;

        int panelWidth = this.getWidth();
        int panelHeight = this.getHeight();

        Rectangle rectBall = new Rectangle(ball.x, ball.y, ball.getDiameter(), ball.getDiameter());
        Rectangle rectLp = new Rectangle(leftPlayer.getX() + 9, leftPlayer.getY(), leftPlayer.getWidth(), leftPlayer.getHeight());
        Rectangle rectRp = new Rectangle(panelWidth - 40, rightPlayer.getY(), rightPlayer.getWidth(), rightPlayer.getHeight());

        if (rectBall.intersects(rectRp)) {
            ball.speedX = -Math.abs(ball.speedX);
        }

        if (rectBall.intersects(rectLp)) {
            ball.speedX = Math.abs(ball.speedX);
        }

        // Make the ball bouncing back when reach the margin

        if (ball.y >= panelHeight  - ball.getDiameter() * 2) {
            ball.speedY *= -1;
        }

        if (ball.y <= 0) {
            ball.speedY *= -1;
        }

        if (ball.x <= 0) {
            rightPlayer.setScore(rightPlayer.getScore() + 1);
            resetBall();
        }

        if (ball.x >= panelWidth - ball.getDiameter() * 2) {
            leftPlayer.setScore(leftPlayer.getScore() + 1);
            resetBall();
        }
        repaint();
    }

    public void keyPressed(KeyEvent e) {

        int panelHeight = this.getHeight();
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_1) {
            if (leftPlayer.getY() - leftPlayer.getSpeedY() >= 0) {
                leftPlayer.setY(leftPlayer.getY() - leftPlayer.getSpeedY());
            }
        }

        if (key == KeyEvent.VK_Q) {
            if (leftPlayer.getY() + leftPlayer.getHeight() <= panelHeight - leftPlayer.getSpeedY() ) {
                leftPlayer.setY(leftPlayer.getY() + leftPlayer.getSpeedY());
            }
        }

        if (key == KeyEvent.VK_UP) {
            if (rightPlayer.getY() - rightPlayer.getSpeedY() >= 0) {
                rightPlayer.setY(rightPlayer.getY() - rightPlayer.getSpeedY());
            }
        }
        if (key == KeyEvent.VK_DOWN) {
            if (rightPlayer.getY() + rightPlayer.getHeight() <= panelHeight - rightPlayer.getSpeedY() ) {
                rightPlayer.setY(rightPlayer.getY() + rightPlayer.getSpeedY());
            }
        }

        repaint();
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }

    public ControlWindow() {
        timer.start();
        this.setBackground(Color.black);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow(true);
        this.addKeyListener(this);
    }
}