package model;

//message Snake {
//// Статус змеи в игре
//  enum SnakeState {
//    ALIVE = 0;  // Змея управляется игроком
//    ZOMBIE = 1; // Змея принадлежала игроку, который вышел из игры, она продолжает движение куда глаза глядят
//  }
//    required int32 player_id = 1; // Идентификатор игрока-владельца змеи, см. GamePlayer.id
//        /* Список "ключевых" точек змеи. Первая точка хранит координаты головы змеи.
//         * Каждая следующая - смещение следующей "ключевой" точки относительно предыдущей,
//         * в частности последняя точка хранит смещение хвоста змеи относительно предыдущей "ключевой" точки. */
//        repeated Coord points = 2;
//        required SnakeState state = 3 [default = ALIVE]; // статус змеи в игре
//        required Direction head_direction = 4; // Направление, в котором повёрнута голова змейки в текущий момент
//}

import java.util.LinkedList;
import java.util.List;

public class Snake {
    private SnakeStatus state = SnakeStatus.ALIVE;
    private int player_id;
    private LinkedList<Coord> points = new LinkedList<>();
    private Direction head_direction;

    private Snake(){};

    private Snake(
            SnakeStatus state,
            int player_id,
            Direction head_direction
    ) {
        this.state = state;
        this.player_id = player_id;
        this.head_direction = head_direction;
    }

    public void addPoint(Coord coord) {
        points.addLast(coord);
    }

    public LinkedList<Coord> getPoints() {
        return points;
    }

    public void setState(SnakeStatus state) {
        this.state = state;
    }

    public void setPoints(LinkedList<Coord> points) {
        this.points = points;
    }

    public void setHead_direction(Direction head_direction) {
        this.head_direction = head_direction;
    }

    public int getPlayer_id() {
        return player_id;
    }

    public Direction getDirection() {
        return head_direction;
    }

    public static class Builder {
        private Snake result;

        public Builder() {
            result = new Snake();
        }

        public Builder setState(SnakeStatus status) {
            result.state = status;
            return this;
        }

        public Builder setPlayerId(int id) {
            result.player_id = id;
            return this;
        }

        public Builder setDirection(Direction direction) {
            result.head_direction = direction;
            return this;
        }

        public Builder addPoint(Coord point) {
            result.points.addLast(point);
            return this;
        }

        public Snake build() {
            return result;
        }
    }

}
