package model;

//message GameConfig {
//        optional int32 width = 1 [default = 40];           // Ширина поля в клетках (от 10 до 100)
//        optional int32 height = 2 [default = 30];          // Высота поля в клетках (от 10 до 100)
//        optional int32 food_static = 3 [default = 1];      // Количество клеток с едой, независимо от числа игроков (от 0 до 100)
//        optional float food_per_player = 4 [default = 1];  // Количество клеток с едой, на каждого игрока (вещественный коэффициент от 0 до 100)
//        optional int32 state_delay_ms = 5 [default = 1000]; // Задержка между ходами (сменой состояний) в игре, в миллисекундах (от 1 до 10000)
//        optional float dead_food_prob = 6 [default = 0.1]; // Вероятность превращения мёртвой клетки в еду (от 0 до 1).
//        optional int32 ping_delay_ms = 7 [default = 100];   // Задержка между отправкой ping-сообщений, в миллисекундах (от 1 до 10000)
//        optional int32 node_timeout_ms = 8 [default = 800]; // Таймаут, после которого считаем что узел-сосед отпал, в миллисекундах (от 1 до 10000)
//}

public class GameConfig {
    private int width = 40;
    private int height = 30;
    private int food_static = 1;
    private float food_per_player = 1;
    private int state_delay_ms = 1000;
    private float dead_food_prob = 0.1f;
    private int ping_delay_ms = 100;
    private int node_timeout_ms = 800;

    public GameConfig(
            int width,
            int height,
            int food_static,
            float food_per_player,
            int state_delay_ms,
            float dead_food_prob,
            int ping_delay_ms,
            int node_timeout_ms
    ) {
        this.width = width;
        this.height = height;
        this.food_static = food_static;
        this.food_per_player = food_per_player;
        this.state_delay_ms = state_delay_ms;
        this.dead_food_prob = dead_food_prob;
        this.ping_delay_ms = ping_delay_ms;
        this.node_timeout_ms = node_timeout_ms;
    }

    public static GameConfig getDefaultGameConfig() {
        return new GameConfig(
                40,
                30,
                1,
                1,
                1000,
                0.1f,
                100,
                800
        );
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getFood_static() {
        return food_static;
    }

    public float getFood_per_player() {
        return food_per_player;
    }

    public int getState_delay_ms() {
        return state_delay_ms;
    }

    public float getDead_food_prob() {
        return dead_food_prob;
    }

    public int getPing_delay_ms() {
        return ping_delay_ms;
    }

    public int getNode_timeout_ms() {
        return node_timeout_ms;
    }
}
