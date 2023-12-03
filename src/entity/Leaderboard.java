package entity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Leaderboard {
    private final ArrayList<Player> players;

    public Leaderboard() {
        players = new ArrayList<Player>();
    }

    public void fillBoard(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    Player player = new Player(parts[0], parts[1]);
                    int score = Integer.parseInt(parts[2].trim());
                    player.setScore(score);
                    players.add(player);
                }
            }
            players.sort(Comparator.comparing(Player::getScore));
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Player> getPlayers(){return this.players;}
}