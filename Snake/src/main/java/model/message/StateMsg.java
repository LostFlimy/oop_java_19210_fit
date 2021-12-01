package model.message;

import model.GameState;

public class StateMsg extends Message {
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
