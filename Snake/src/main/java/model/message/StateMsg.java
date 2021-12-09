package model.message;

import model.GameState;

import java.io.Serializable;

public class StateMsg extends Message implements Serializable {
    private final GameState state;

    public GameState getState() {
        return state;
    }

    public StateMsg(long msgSeq, GameState state) {
        super(msgSeq);
        this.state = state;
        type = Type.STATE;
    }
}
