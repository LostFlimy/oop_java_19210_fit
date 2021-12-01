package net;

import java.util.HashMap;

public class PlayersTab {
    private final HashMap<Integer, Long> playersIdToTimeFromLastPing = new HashMap<>();

    public void setNewPlayer(Integer id) {
        playersIdToTimeFromLastPing.put(id, System.currentTimeMillis());
    }

    public Long getLastTimeForId(Integer playerId) {
        return playersIdToTimeFromLastPing.get(playerId);
    }

    public void updatePlayer(Integer playerId) {
        playersIdToTimeFromLastPing.put(playerId, System.currentTimeMillis());
    }

    public void deletePlayer(Integer playerId) {
        playersIdToTimeFromLastPing.remove(playerId);
    }
}
