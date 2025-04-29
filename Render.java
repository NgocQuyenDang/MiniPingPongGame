import java.awt.*;

public class Render {
    private Ball ball;
    private Player leftPlayer;
    private Player rightPlayer;

    private Font gameFont = new Font("Consolas", Font.BOLD, 40);
    private Font infoFont = new Font("Consolas", Font.ITALIC, 20);

    private Color backgroundColor = new Color(0, 0, 20);
    private Color ballColor = new Color(255, 215, 0);
    private Color leftPlayerColor = new Color(30, 144, 255);
    private Color rightPlayerColor = new Color(220, 20, 60);
    private Color netColor = new Color(200, 200, 200, 100);

    public Render(Ball ball, Player leftPlayer, Player rightPlayer) {
        this.ball = ball;
        this.leftPlayer = leftPlayer;
        this.rightPlayer = rightPlayer;
    }

    public void render(Graphics2D g2d, int panelWidth, int panelHeight) {

        drawBackground(g2d, panelWidth, panelHeight);
        drawNet(g2d, panelWidth, panelHeight);
        drawMargin(g2d, panelWidth, panelHeight);

        drawPlayers(g2d, leftPlayer, leftPlayerColor, leftPlayer.getX() + 9, leftPlayer.getY());
        drawPlayers(g2d, rightPlayer, rightPlayerColor, panelWidth - 40, rightPlayer.getY());

        drawBall(g2d, ball, ballColor);
        drawScoreTable(g2d, panelWidth);
        drawGameInstruction(g2d, panelWidth, panelHeight);
    }

    public void drawBackground(Graphics2D g2d, int  panelWidth, int panelHeight) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint backgroundGradient = new GradientPaint(0, 0, backgroundColor,0,
                                                            panelHeight, new Color(20, 20, 50));
        g2d.setPaint(backgroundGradient);
        g2d.fillRect(0,0, panelWidth, panelHeight);
    }

    public void drawNet(Graphics2D g2d, int  panelWidth, int panelHeight) {
        g2d.setColor(netColor);
        g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{10}, 0));
        g2d.drawLine(panelWidth / 2, 0, panelWidth / 2, panelHeight);
    }

    public void drawMargin(Graphics2D g2d, int  panelWidth, int panelHeight) {
        g2d.setColor(new Color(100, 100, 100));
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRect(2,2, panelWidth - 4, panelHeight - 4);
    }

    public void drawPlayers(Graphics2D g2d, Player player, Color baseColor, int xPos, int xEnd) {
        GradientPaint gradient = new GradientPaint(xPos, 0, baseColor, xEnd + player.getWidth(), 0, new Color(255, 255, 255, 100));
        g2d.setPaint(gradient);
        g2d.fillRoundRect(xPos, player.getY(), player.getWidth(), player.getHeight(), 15, 15);
        g2d.setColor(Color.WHITE);
        g2d.drawRoundRect(xPos, player.getY(), player.getWidth(), player.getHeight(), 15, 15);
    }

    public void drawBall(Graphics2D g2d, Ball ball, Color ballColor) {
        if (ball.isCollision() && ball.getFlashCount() < 3) {
            g2d.setColor(Color.WHITE);
            ball.increaseFlashCount();
        } else {
            g2d.setColor(ballColor);
            ball.setCollision(false);
            ball.resetFlashCount();
        }

        // Ve bong voi hieu ung anh sang
        g2d.fillOval(ball.x, ball.y, ball.getDiameter(), ball.getDiameter());

        // Hieu ung anh sang cho bong
        Color ballHightlight = new Color(255, 255, 255, 150);
        g2d.setColor(ballHightlight);
        g2d.fillOval(ball.x + 5, ball.y + 5, ball.getDiameter() / 3, ball.getDiameter() / 3);
    }

    public void drawScoreTable(Graphics2D g2d, int panelWidth) {
        g2d.setFont(gameFont);
        g2d.setColor(Color.WHITE);
        g2d.drawString("LEFT" + " : " + leftPlayer.getScore(), panelWidth / 5, 60);
        g2d.drawString("RIGHT" + " : " + rightPlayer.getScore(), 4 * panelWidth / 6, 60);
    }

    public void drawGameInstruction(Graphics2D g2d, int panelWidth, int panelHeight) {
        g2d.setFont(infoFont);
        g2d.setColor(new Color(200, 200, 200));
        g2d.drawString("W/S: Left Player", 10, panelHeight - 20);
        g2d.drawString("↑/↓: Right Player", panelWidth - 200, panelHeight - 20);
    }
}
