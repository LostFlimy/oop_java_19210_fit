package model.message;

import model.Direction;

import java.io.Serializable;

public class SteerMsg extends Message implements Serializable {
    private final Direction direction;

    public Direction getDirection() {
        return direction;
    }

    public SteerMsg(
            long msgSeq,
            Direction direction
    ) {
        super(msgSeq);
        this.direction = direction;
        type = Type.STEER;
    }
}
