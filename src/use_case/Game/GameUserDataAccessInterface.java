package use_case.Game;

import entity.User;

public interface GameUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(User user);
}
