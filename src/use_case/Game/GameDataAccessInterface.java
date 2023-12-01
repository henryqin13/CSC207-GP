package use_case.Game;

import entity.City;
import entity.User;

public interface GameDataAccessInterface {
    void saveCity(City city);

    City getCity();

    void save(User user);
}