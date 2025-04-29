    public class Ball {

        public int x, y;
        private int diameter;
        public int speedX = 10;
        public int speedY = 10;
        private boolean collision = false;
        private int flashCount = 0;

        public Ball(int x, int y, int diameter){
            this.x = x;
            this.y = y;
            this.diameter = diameter;
        }

        public int getDiameter() {
            return diameter;
        }

        public void setDiameter(int diameter) {
            this.diameter = diameter;
        }

        public void move() {
            x += speedX;
            y+= speedY;
        }

        public void resetBall(int panelWidth, int panelHeight) {
            x = panelWidth / 2;
            y = panelHeight / 2;
        }

        public boolean isCollision() {
            return collision;
        }

        public void setCollision(boolean collision) {
            this.collision = collision;
        }

        public int getFlashCount() {
            return flashCount;
        }

        public void increaseFlashCount() {
            flashCount++;
        }

        public void resetFlashCount() {
            flashCount = 0;
        }
    }

