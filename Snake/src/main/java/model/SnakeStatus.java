package model;

import java.io.Serializable;

public enum SnakeStatus implements Serializable {
    ALIVE(0),
    ZOMBIE(1);

    private int val;

    private SnakeStatus(int val) {
        this.val = val;
    }
}
