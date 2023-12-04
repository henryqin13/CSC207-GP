package data_access;

import entity.Leaderboard;
import entity.Player;
import entity.PlayerFactory;
import use_case.Guest.GuestUserDataAccessInterface;
import use_case.Login.LoginUserDataAccessInterface;
import use_case.Signup.SignupUserDataAccessInterface;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface, GuestUserDataAccessInterface {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, Player> accounts = new HashMap<>();

    private PlayerFactory playerFactory;


    public FileUserDataAccessObject(String csvPath, PlayerFactory playerFactory) throws IOException {
        this.playerFactory = playerFactory;

        csvFile = new File(csvPath);
        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("score", 2);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("username,password,score");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    int score = Integer.parseInt(String.valueOf(col[headers.get("score")]));
                    Player player = playerFactory.create(username, password);
                    player.setScore(score);
                    accounts.put(username, player);


                }
            }
        }
    }

    @Override
    public void save(Player user) {
        accounts.put(user.getName(), user);
        this.save();
    }

    @Override
    public Player get(String username) {
        return accounts.get(username);
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (Player player : accounts.values()) {
                String line = String.format("%s,%s,%s", player.getName(), player.getPassword(), player.getScore());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

    public Leaderboard createLeaderboard() {
        Leaderboard leaderboard = new Leaderboard();
        leaderboard.updateLeaderboard(accounts);
        return leaderboard;


    }
}