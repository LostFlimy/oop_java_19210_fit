package view;

import app.App;
import model.*;

public class GameTab implements Runnable{
    private GameState state;
    private GameFrame frame;
    private final App app;

    public GameTab(GameState state, App app) {
        this.state = state;
        this.app = app;
    }

    public void changeGameState(GameState newState) {
        state = newState;
        frame.changeGameState(state);
    }
    @Override
    public void run() {
        frame = new GameFrame(state, app);
    }
}
