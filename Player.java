public class Player {
    private int x, y;
    private int width = 30;
    private int height = 150;
    private int speedY = 20;
    private int score = 0;

    public Player (int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSpeedY() {
        return speedY;
    }

    public int getScore() {
        return score;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public void setScore(int score) {
        this.score = score;
    }
}