import java.awt.*;
import java.awt.event.KeyEvent;

public class GameManager {
    private Ball ball;
    private Player leftPlayer;
    private Player rightPlayer;
    private Render render;

    public GameManager(Ball ball, Player leftPlayer, Player rightPlayer,  Render render) {
        this.ball = ball;
        this.leftPlayer = leftPlayer;
        this.rightPlayer = rightPlayer;
        this.render = new Render(ball, leftPlayer, rightPlayer);
    }

    public void update(int panelWidth, int panelHeight) {
        ball.move();

        Rectangle rectBall = new Rectangle(ball.x, ball.y, ball.getDiameter(), ball.getDiameter());
        Rectangle rectLp = new Rectangle(leftPlayer.getX() + 9, leftPlayer.getY(), leftPlayer.getWidth(), leftPlayer.getHeight());
        Rectangle rectRp = new Rectangle(panelWidth - 40, rightPlayer.getY(), rightPlayer.getWidth(), rightPlayer.getHeight());

        if (rectBall.intersects(rectRp)) {
            ball.speedX = -Math.abs(ball.speedX);
            //ball.speedY = getRandomBounce(ball.speedY);
            ball.setCollision(true);
        }

        if (rectBall.intersects(rectLp)) {
            ball.speedX = Math.abs(ball.speedX);
            //ball.speedY = getRandomBounce(ball.speedY);
            ball.setCollision(true);
        }

        if (ball.y >= panelHeight  - ball.getDiameter() * 2 || ball.y <= 0) {
            ball.speedY *= -1;
        }

        if (ball.x < 0) {
            rightPlayer.setScore(rightPlayer.getScore() + 1);
            ball.resetBall(panelWidth, panelHeight);
        }

        if (ball.x > panelWidth - ball.getDiameter()) {
            leftPlayer.setScore(leftPlayer.getScore() + 1);
            ball.resetBall(panelWidth, panelHeight);
        }
    }

//    private int getRandomBounce(int speedY) {
//        int random = (int) (Math.random() * 6) - 3;
//        return speedY + random;
//    }

    public void handleKeyInput(int keyCode, int panelHeight) {
        if (keyCode == KeyEvent.VK_1) {
            if (leftPlayer.getY() - leftPlayer.getSpeedY() >= 0) {
                leftPlayer.setY(leftPlayer.getY() - leftPlayer.getSpeedY());
            }
        }

        if (keyCode == KeyEvent.VK_Q) {
            if (leftPlayer.getY() + leftPlayer.getHeight() <= panelHeight - leftPlayer.getSpeedY()) {
                leftPlayer.setY(leftPlayer.getY() + leftPlayer.getSpeedY());
            }
        }

        if (keyCode == KeyEvent.VK_UP) {
            if (rightPlayer.getY() - rightPlayer.getSpeedY() >= 0) {
                rightPlayer.setY(rightPlayer.getY() - rightPlayer.getSpeedY());
            }
        }

        if (keyCode == KeyEvent.VK_DOWN) {
            if (rightPlayer.getY() + rightPlayer.getHeight() <= panelHeight - rightPlayer.getSpeedY()) {
                rightPlayer.setY(rightPlayer.getY() + rightPlayer.getSpeedY());
            }
        }
    }
}
