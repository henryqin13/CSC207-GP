package use_case.Login;

import entity.Player;

public interface LoginUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(Player player);

    Player get(String username);
}
