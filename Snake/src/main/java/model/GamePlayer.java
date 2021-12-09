package model;

// Игрок
//message GamePlayer {
//        required string name = 1;       // Имя игрока (для отображения в интерфейсе)
//        required int32 id = 2;          // Уникальный идентификатор игрока в пределах игры
//        required string ip_address = 3; // IPv4 или IPv6 адрес игрока в виде строки (отправитель не знает свой IP, поэтому указывает тут пустую строку)
//        required int32 port = 4;        // Порт UDP-сокета игрока
//        required NodeRole role = 5;     // Роль узла в топологии
//        optional PlayerType type = 6 [default = HUMAN]; // Тип игрока
//        required int32 score = 7;       // Число очков, которые набрал игрок
//        }

import java.io.Serializable;

public class GamePlayer implements Serializable {
    private String name;
    private int id;
    private String IP;
    private int port;
    private NodeRole role;
    private int score;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getIP() {
        return IP;
    }

    public int getPort() {
        return port;
    }

    public NodeRole getRole() {
        return role;
    }

    public int getScore() {
        return score;
    }

    public void incScore(int incVal) {
        score += incVal;
    }

    public void setZeroScore() {
        score = 0;
    }

    public static class Builder {
        private GamePlayer player;

        public Builder() {
            player = new GamePlayer();
        }

        public Builder setPort(int port) {
            player.port = port;
            return this;
        }

        public Builder setIp(String Ip) {
            player.IP = Ip;
            return this;
        }

        public Builder setId(int id) {
            player.id = id;
            return this;
        }

        public Builder setName(String name) {
            player.name = name;
            return this;
        }

        public Builder setScore(int score) {
            player.score = score;
            return this;
        }

        public Builder setRole(NodeRole role) {
            player.role = role;
            return this;
        }

        public GamePlayer build() {
            return player;
        }
    }
}
