package view;

import app.App;
import model.GameState;

import javax.swing.*;

public class GameFrame extends JFrame{
    private GamePanel panel;
    public GameFrame(GameState gameState, App app) {
        panel = new GamePanel(gameState, app);
        this.add(panel);
        this.setTitle("SnakeGame");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        panel.startView();
    }

     public void setSender(MessageSender sender) {
        panel.setSender(sender);
     }

    public void changeGameState(GameState state) {
        panel.changeGameState(state);
    }
}
