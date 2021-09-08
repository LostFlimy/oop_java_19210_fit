package snake.model;

public enum State {
    CLEAR(0), SNAKE(2), APPLE(1), BLOCK(3);
    private int x;

    State(int i) {
        x = i;
    }
}
