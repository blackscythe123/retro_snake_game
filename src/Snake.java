public class Snake {
    final int MAX_LENGTH = 400;
    int[] x = new int[MAX_LENGTH];
    int[] y = new int[MAX_LENGTH];
    int bodyParts = 6;
    char direction = 'R';

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        switch(direction) {
            case 'U': y[0]--; break;
            case 'D': y[0]++; break;
            case 'L': x[0]--; break;
            case 'R': x[0]++; break;
        }
    }

    public void grow() {
        bodyParts++;
    }

    public void setDirection(char newDir) {
        if ((direction == 'L' && newDir != 'R') ||
            (direction == 'R' && newDir != 'L') ||
            (direction == 'U' && newDir != 'D') ||
            (direction == 'D' && newDir != 'U')) {
            direction = newDir;
        }
    }

    public int getHeadX() {
        return x[0];
    }

    public int getHeadY() {
        return y[0];
    }
}
