package model.message;

import model.GameConfig;
import model.GamePlayer;

import java.util.List;

public class AnnouncementMsg extends Message {
    private final List<GamePlayer> players;
    private final GameConfig config;

    public List<GamePlayer> getPlayers() {
        return players;
    }

    public GameConfig getConfig() {
        return config;
    }

    public AnnouncementMsg(long msgSeq, List<GamePlayer> players, GameConfig config) {
        super(msgSeq);
        this.players = players;
        this.config = config;
        type = Type.ANNOUNCEMENT;
    }
}
