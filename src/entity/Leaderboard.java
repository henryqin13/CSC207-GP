package entity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Leaderboard {
    private  ArrayList<Player> players;
    public Leaderboard(){
        this.players = new ArrayList<>();
    }
    public void updateLeaderboard(Map<String, Player> players){this.players = new ArrayList<>(players.values());}
    public ArrayList<Player> getPlayers(){return this.players;}
}