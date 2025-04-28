    public class Ball {

        public int x, y;
        private int diameter;
        public int speedX = 10;
        public int speedY = 10;

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
    }

