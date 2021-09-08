package snake.model;

import java.awt.*;
import java.io.InputStream;
import java.util.*;

public class GameMap {
    private Map<Point, State> map = new HashMap<Point, State>();
    private int sizex, sizey;

    public GameMap(InputStream stream) {


    }

    public int getSizex() {
        return sizex;
    }

    public int getSizey() {
        return sizey;
    }

    public State getStatus(int x, int y) {
        int _x, _y;
        _x = x % sizex;
        _y = y % sizey;
        return map.get(new Point(_x, _y));
    }
}
