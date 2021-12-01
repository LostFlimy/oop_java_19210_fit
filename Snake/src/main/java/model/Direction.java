package model;

//enum Direction {
//    UP = 1;     // Вверх (в отрицательном направлении оси y)
//    DOWN = 2;   // Вниз (в положительном направлении оси y)
//    LEFT = 3;   // Влево (в отрицательном направлении оси x)
//    RIGHT = 4;  // Вправо (в положительном направлении оси x)
//}

public enum Direction {
    UP(1),
    DOWN(2),
    LEFT(3),
    RIGHT(4);


    private int val;

    private Direction(int val) {
        this.val = val;
    }
}
