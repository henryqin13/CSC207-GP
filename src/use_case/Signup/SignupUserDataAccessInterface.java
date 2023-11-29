package use_case.Signup;

import entity.Player;

public interface SignupUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(Player player);
}
