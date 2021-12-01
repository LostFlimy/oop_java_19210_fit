package view;

import model.GameState;

import javax.swing.*;

public class GameFrame extends JFrame{
    private GamePanel panel;
    public GameFrame(GameState gameState) {
        panel = new GamePanel(gameState);
        this.add(panel);
        this.setTitle("SnakeGame");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        panel.startView();
    }
    public void changeGameState(GameState state) {
        panel.changeGameState(state);
    }
}
