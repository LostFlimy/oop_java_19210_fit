package model;

public enum SnakeStatus {
    ALIVE(0),
    ZOMBIE(1);

    private int val;

    private SnakeStatus(int val) {
        this.val = val;
    }
}
