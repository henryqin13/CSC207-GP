package use_case.Guest;

import entity.User;

public interface GuestUserDataAccessInterface {

    User get(String username);
}
