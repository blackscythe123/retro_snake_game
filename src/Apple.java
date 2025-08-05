import java.util.Random;

public class Apple {
    int x, y;
    Random random = new Random();

    public void spawnApple(int gridWidth, int gridHeight, Snake snake) {
        boolean valid = false;
        while (!valid) {
            x = random.nextInt(gridWidth);
            y = random.nextInt(gridHeight);
            valid = true;
            for (int i = 0; i < snake.bodyParts; i++) {
                if (snake.x[i] == x && snake.y[i] == y) {
                    valid = false;
                    break;
                }
            }
        }
    }
}
