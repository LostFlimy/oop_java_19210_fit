package app;

import model.*;
import view.GameTab;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static volatile boolean running;
    private ExecutorService threadPool;
    private GameState state;

    public App() {
        try {
            Reader reader = new FileReader("C:\\oop\\oop_sinitsyn_19210_java\\Snake\\src\\main\\resources\\mapSettings.properties");
            Properties prop = new Properties();
            prop.load(reader);
            state = new GameState(
                    0,
                    List.of(
                            new Snake.Builder()
                                    .setState(SnakeStatus.ALIVE)
                                    .setPlayerId(1)
                                    .setDirection(Direction.RIGHT)
                                    .addPoint(new Coord(1, 1))
                                    .addPoint(new Coord(0,1))
                                    .build()
                    ),
                    List.of(
                            new Coord(5, 5),
                            new Coord(6, 6),
                            new Coord(7, 7),
                            new Coord(8, 8),
                            new Coord(9, 9)
                    ),
                    new HashSet<>(),
                    new GameConfig(
                            Integer.parseInt(prop.getProperty("width")),
                            Integer.parseInt(prop.getProperty("height")),
                            Integer.parseInt(prop.getProperty("food_static")),
                            Float.parseFloat(prop.getProperty("food_per_player")),
                            Integer.parseInt(prop.getProperty("state_delay_ms")),
                            Float.parseFloat(prop.getProperty("dead_food_prob")),
                            Integer.parseInt(prop.getProperty("ping_delay_ms")),
                            Integer.parseInt(prop.getProperty("node_timeout_ms"))
                    )
            );
        } catch (IOException e) {
            System.out.println("Can not load config will be used default settings");
            state = new GameState(
                    0,
                    new LinkedList<>(),
                    new LinkedList<>(),
                    new HashSet<>(),
                    GameConfig.getDefaultGameConfig()
            );
        }
        System.out.println(state.getConfig().getWidth() + " : " + state.getConfig().getHeight());
        threadPool = Executors.newFixedThreadPool(10);
        running = true;
    }

    public void go() {
        threadPool.submit(new GameTab(state));
    }
}
