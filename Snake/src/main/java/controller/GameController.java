package controller;

import model.Coord;
import model.GameState;

import java.util.LinkedList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class GameController {
    private GameState state;

    public void setState(GameState state) {
        this.state = state;
    }

    public GameState changeSnakesPosition() {
        state.getSnakes().forEach(snake -> {
            Coord nextCoord = snake.getPoints().get(0);
            switch (snake.getDirection()) {
                case LEFT -> {
                    if (nextCoord.getX() > 0) {
                        nextCoord.setX(nextCoord.getX() - 1);
                    } else {
                        nextCoord.setX(state.getConfig().getWidth() - 1);
                    }
                }
                case UP -> {
                    if (nextCoord.getY() > 0) {
                        nextCoord.setY(nextCoord.getY() - 1);
                    } else {
                        nextCoord.setY(state.getConfig().getHeight() - 1);
                    }
                }
                case DOWN -> {
                    if (nextCoord.getY() < state.getConfig().getHeight() - 1) {
                        nextCoord.setY(nextCoord.getY() + 1);
                    } else {
                        nextCoord.setY(0);
                    }
                }
                case RIGHT -> {
                    if (nextCoord.getX() < state.getConfig().getWidth() - 1) {
                        nextCoord.setX(nextCoord.getX() + 1);
                    } else {
                        nextCoord.setX(0);
                    }
                }
            }

            AtomicBoolean haveApple = new AtomicBoolean(false);
            LinkedList<Coord> foodCoords = new LinkedList<>(state.getFoods());
            Optional<Coord> coord = foodCoords
                    .stream()
                    .filter(food -> food.equals(nextCoord))
                    .findAny();
            if (coord.isPresent()) {
                foodCoords.remove(coord.get());
                state.setFoods(foodCoords);
                haveApple.set(true);
            }

            LinkedList<Coord> points = snake.getPoints();
            points.set(0, nextCoord);
            if (!haveApple.get()) {
                switch (snake.getDirection()) {
                    case RIGHT -> {
                        if (points.get(1).getX() < 0) {
                            if (points.size() != 2) {
                                points.get(1).setX(points.get(1).getX() - 1);
                            }
                        }
                        if (points.get(1).getY() != 0) {
                            points.addFirst(new Coord(points.get(0).getX(), points.get(0).getY()));
                            points.get(1).setX(-1);
                            points.get(1).setY(0);
                        }
                    }
                    case DOWN -> {
                        if (points.get(1).getY() < 0) {
                            if (points.size() != 2) {
                                points.get(1).setY(points.get(1).getY() - 1);
                            }
                        }
                        if (points.get(1).getX() != 0) {
                            points.addFirst(new Coord(points.get(0).getX(), points.get(0).getY()));
                            points.get(1).setY(-1);
                            points.get(1).setX(0);
                        }
                    }
                    case UP -> {
                        if (points.get(1).getY() > 0) {
                            if (points.size() != 2) {
                                points.get(1).setY(points.get(1).getY() + 1);
                            }
                        }
                        if (points.get(1).getX() != 0) {
                            points.addFirst(new Coord(points.get(0).getX(), points.get(0).getY()));
                            points.get(1).setY(1);
                            points.get(1).setX(0);
                        }
                    }
                    case LEFT -> {
                        if (points.get(1).getX() > 0) {
                            if (points.size() != 2) {
                                points.get(1).setX(points.get(1).getX() + 1);
                            }
                        }
                        if (points.get(1).getY() != 0) {
                            points.addFirst(new Coord(points.get(0).getX(), points.get(0).getY()));
                            points.get(1).setX(1);
                            points.get(1).setY(0);
                        }
                    }
                }
                if (points.size() > 2) {
                    boolean removeLast = false;
                    if (points.getLast().getX() != 0) {
                        if (points.getLast().getX() > 0) {
                            points.getLast().setX(points.getLast().getX() - 1);
                            if (points.getLast().getX() == 0) {
                                removeLast = true;
                            }
                        }
                        if (points.getLast().getX() < 0) {
                            points.getLast().setX(points.getLast().getX() + 1);
                            if (points.getLast().getX() == 0) {
                                removeLast = true;
                            }
                        }
                    }
                    if (points.getLast().getY() != 0) {
                        if (points.getLast().getY() > 0) {
                            points.getLast().setY(points.getLast().getY() - 1);
                            if (points.getLast().getY() == 0) {
                                removeLast = true;
                            }
                        }
                        if (points.getLast().getY() < 0) {
                            points.getLast().setY(points.getLast().getY() + 1);
                            if (points.getLast().getY() == 0) {
                                removeLast = true;
                            }
                        }
                    }
                    if (removeLast) {
                        points.removeLast();
                    }
                }
            }

            if (haveApple.get()) {
                switch (snake.getDirection()) {
                    case RIGHT -> {
                        if (points.get(1).getX() < 0) {
                            points.get(1).setX(points.get(1).getX() - 1);
                        }
                        if (points.get(1).getY() != 0) {
                            points.addFirst(new Coord(points.get(0).getX(), points.get(0).getY()));
                            points.get(1).setX(-1);
                            points.get(1).setY(0);
                        }
                    }
                    case DOWN -> {
                        if (points.get(1).getY() < 0) {
                            points.get(1).setY(points.get(1).getY() - 1);
                        }
                        if (points.get(1).getX() != 0) {
                            points.addFirst(new Coord(points.get(0).getX(), points.get(0).getY()));
                            points.get(1).setY(-1);
                            points.get(1).setX(0);
                        }
                    }
                    case UP -> {
                        if (points.get(1).getY() > 0) {
                            points.get(1).setY(points.get(1).getY() + 1);
                        }
                        if (points.get(1).getX() != 0) {
                            points.addFirst(new Coord(points.get(0).getX(), points.get(0).getY()));
                            points.get(1).setY(1);
                            points.get(1).setX(0);
                        }
                    }
                    case LEFT -> {
                        if (points.get(1).getX() > 0) {
                            points.get(1).setX(points.get(1).getX() + 1);
                        }
                        if (points.get(1).getY() != 0) {
                            points.addFirst(new Coord(points.get(0).getX(), points.get(0).getY()));
                            points.get(1).setX(1);
                            points.get(1).setY(0);
                        }
                    }
                }
            }
        });
        return state;
    }
}
