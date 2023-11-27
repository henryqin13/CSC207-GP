package use_case.Guest;

import entity.Player;

public interface GuestUserDataAccessInterface {

    Player get(String username);
}
