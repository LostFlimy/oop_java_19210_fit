package model;

public enum Role {
    CHIEF(1),
    WORKER(2);

    private long roleid;

    Role(long roleid) {
        this.roleid = roleid;
    }
}
