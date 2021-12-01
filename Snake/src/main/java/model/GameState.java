package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class GameState {
    private int state_order;
    private List<Snake> snakes;
    private List<Coord> foods;
    private Set<GamePlayer> players;
    private GameConfig config;

    public GameConfig getConfig() {
        return config;
    }

    public int getState_order() {
        return state_order;
    }

    public List<Snake> getSnakes() {
        return snakes;
    }

    public List<Coord> getFoods() {
        return foods;
    }

    public void setFoods(LinkedList<Coord> where) {
        foods = where;
    }

    public Set<GamePlayer> getPlayers() {
        return players;
    }

    public GameState(
            int state_order,
            List<Snake> snakes,
            List<Coord> foods,
            Set<GamePlayer> players,
            GameConfig config
    ) {
        this.state_order = state_order;
        this.snakes = snakes;
        this.foods = foods;
        this.players = players;
        this.config = config;
    }
}
