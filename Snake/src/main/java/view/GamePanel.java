package view;

import app.App;
import model.Coord;
import model.Direction;
import model.GameState;
import model.message.SteerMsg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    private final int UNIT_SIZE = 20;
    private int HEIGHT;
    private int WEIGHT;
    private final Random random;
    private GameState state;
    private Timer timer;
    private final App app;

    public GamePanel(GameState gameState, App app) {
        this.state = gameState;
        this.app = app;
        random = new Random();
        WEIGHT = gameState.getConfig().getWidth();
        HEIGHT = gameState.getConfig().getHeight();
        this.setPreferredSize(
                new Dimension(
                        gameState.getConfig().getWidth() * UNIT_SIZE + 350,
                        gameState.getConfig().getHeight() * UNIT_SIZE + 10
                ));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new GameKeyAdapter());
    }

    public void changeGameState(GameState newState) {
        state = newState;
        WEIGHT = newState.getConfig().getWidth();
        HEIGHT = newState.getConfig().getHeight();
        this.setPreferredSize(
                new Dimension(
                        newState.getConfig().getWidth() * UNIT_SIZE + 350,
                        newState.getConfig().getHeight() * UNIT_SIZE + 10
                ));
    }

    public void startView() {
        timer = new Timer(state.getConfig().getState_delay_ms(), this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        for (int i = 0; i <= WEIGHT; ++i) {
            g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, HEIGHT * UNIT_SIZE);
        }
        for (int i = 0; i <= HEIGHT; ++i) {
            g.drawLine(0, i * UNIT_SIZE, WEIGHT * UNIT_SIZE, i * UNIT_SIZE);
        }
        g.setColor(Color.red);
        state.getFoods().forEach(apple -> {
            g.fillOval(apple.getX() * UNIT_SIZE, apple.getY() * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
        });
        state.getSnakes().forEach(snake -> {
            g.setColor(
                    new Color(
                            (100 * snake.getPlayer_id() + 25) % 255,
                            (50 * snake.getPlayer_id() + 30) % 255,
                            (75 * snake.getPlayer_id() + 45) % 255
                    )
            );
            LinkedList<Coord> points = snake.getPoints();
            g.fillRect(
                    points.get(0).getX() * UNIT_SIZE,
                    points.get(0).getY() * UNIT_SIZE,
                    UNIT_SIZE,
                    UNIT_SIZE
            );
            int prevX = points.get(0).getX();
            int prevY = points.get(0).getY();
            for (int j = 1; j < points.size(); ++j) {
                int dx = points.get(j).getX();
                int dy = points.get(j).getY();
                int curX = (dx + prevX) % WEIGHT;
                int curY = (dy + prevY) % HEIGHT;
                if (dy > 0) {
                    for (int i = 1; i <= dy; i++) {
                        g.fillRect(curX * UNIT_SIZE, ((prevY + i) % HEIGHT) * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
                    }
                }
                if (dy < 0) {
                    for (int i = -1; i >= dy; i--) {
                        g.fillRect(curX * UNIT_SIZE, (prevY + i >= 0 ? prevY + i : HEIGHT + (prevY + i)) * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
                    }
                }
                if (dx > 0) {
                    for (int i = 1; i <= dx; i++) {
                        g.fillRect(((prevX + i) % WEIGHT) * UNIT_SIZE, prevY * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
                    }
                }
                if (dx < 0) {
                    for (int i = -1; i >= dx; i--) {
                        g.fillRect((prevX + i >= 0 ? prevX + i : WEIGHT + (prevX + i)) * UNIT_SIZE, prevY * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
                    }
                }
                prevX = curX;
                prevY = curY;
            }
        });
        state.getPlayers().forEach(gamePlayer -> {
            g.setFont(new Font("Default", Font.BOLD, 15));
            g.drawString(
                    gamePlayer.getName() + " : " + Integer.valueOf(gamePlayer.getScore()).toString(),
                    WEIGHT * UNIT_SIZE + 20,
                    gamePlayer.getId() * UNIT_SIZE + 20
            );
        });

        JButton exitButton = new JButton();
        exitButton.setName("EXIT");
        exitButton.setText("EXIT");
        exitButton.setBounds(WEIGHT * UNIT_SIZE + 10, HEIGHT * UNIT_SIZE - 40, 100, 40);
        exitButton.addActionListener(this);
        this.add(exitButton);

        JButton newGameButton = new JButton();
        newGameButton.setName("New Game");
        newGameButton.setText("NEW GAME");
        newGameButton.addActionListener(this);
        newGameButton.setBounds(WEIGHT * UNIT_SIZE + 110, HEIGHT * UNIT_SIZE - 40, 100, 40);
        this.add(newGameButton);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case "NEW GAME" : {

            }

        }
        repaint();
    }

    public class GameKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent event) {
            if (event.getKeyCode() == KeyEvent.VK_W) {
            }
            if (event.getKeyCode() == KeyEvent.VK_A) {
            }
            if (event.getKeyCode() == KeyEvent.VK_D) {
            }
            if (event.getKeyCode() == KeyEvent.VK_S) {
            }
            repaint();
        }
    }
}
