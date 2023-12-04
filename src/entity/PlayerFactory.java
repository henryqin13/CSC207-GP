package entity;


public class PlayerFactory {
    /**
     * Requires: password is valid.
     * @param name
     * @param password
     * @return
     */


    public Player create(String name, String password) {
        return new Player(name, password);
    }
}