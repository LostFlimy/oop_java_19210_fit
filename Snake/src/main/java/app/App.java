package app;

import model.*;
import view.GameTab;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static volatile boolean running;
    private ExecutorService threadPool;
    private GameState state;
    private GameTab tab;
    public static volatile long msgSeq = 0;

    public App() {
        try {
            Reader reader = new FileReader("C:\\oop\\oop_sinitsyn_19210_java\\Snake\\src\\main\\resources\\mapSettings.properties");
            Properties prop = new Properties();
            prop.load(reader);
            state = new GameState(
                    0,
                    new LinkedList<>(),
                    new LinkedList<>(),
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
        tab = new GameTab(state, this);
    }

    public GameState getState() {
        return state;
    }

    public void submitTask(Runnable task) {
        threadPool.submit(task);
    }

    public void go() {
        threadPool.submit(tab);
    }
}
