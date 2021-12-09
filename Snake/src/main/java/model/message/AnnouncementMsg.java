package model.message;

import model.GameConfig;
import model.GamePlayer;

import java.io.Serializable;
import java.util.Set;

public class AnnouncementMsg extends Message implements Serializable {
    private final Set<GamePlayer> players;
    private final GameConfig config;

    public Set<GamePlayer> getPlayers() {
        return players;
    }

    public GameConfig getConfig() {
        return config;
    }

    public AnnouncementMsg(long msgSeq, Set<GamePlayer> players, GameConfig config) {
        super(msgSeq);
        this.players = players;
        this.config = config;
        type = Type.ANNOUNCEMENT;
    }
}
