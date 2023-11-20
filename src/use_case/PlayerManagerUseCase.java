package use_case;

import entity.Player;
import entity.Role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerManagerUseCase {
    private Map<String, Player> players;

    public PlayerManagerUseCase() {
        this.players = new HashMap<>();
    }

    public void addPlayer(String playerName, List<Role> roles) {
        if (!players.containsKey(playerName)) {
            players.put(playerName, new Player(playerName, roles));
            System.out.println("Player " + playerName + " created.");
        } else {
            System.out.println("Player " + playerName + " already exists");
        }
    }

    public Player getPlayer(String playerName) {
        return players.getOrDefault(playerName, null);
    }
}
