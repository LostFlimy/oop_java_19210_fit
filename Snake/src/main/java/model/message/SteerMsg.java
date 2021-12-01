package model.message;

import model.Direction;

public class SteerMsg extends Message{
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
