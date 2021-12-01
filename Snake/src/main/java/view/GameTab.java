package view;

import model.*;

import java.util.List;
import java.util.Set;

public class GameTab implements Runnable{
    private GameState state;
    private GameFrame frame;

    public GameTab(GameState state) {
        this.state = state;
    }

    public void changeGameState(GameState newState) {
        state = newState;
        frame.changeGameState(state);
    }
    @Override
    public void run() {
        frame = new GameFrame(state);
    }
}
