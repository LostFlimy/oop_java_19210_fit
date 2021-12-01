package model;

public enum NodeRole {
    NORMAL(0),
    MASTER(1),
    DEPUTY(2),
    VIEWER(3);

    private int val;

    private NodeRole(int val) {
        this.val = val;
    }
}
