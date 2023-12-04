package entity;

import java.util.List;

public class Player {
    private String name;
    private final String password;
    private int score;


    public Player(String name, String password) {
        this.name = name;
        this.password = password;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPassword(){
        return this.password;
    }


    public String toString() {
        return "Player{ " + "name='" + name + '\'' + ", score=" + score  + '}';
    }
}