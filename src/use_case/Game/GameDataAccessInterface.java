package use_case.Game;

import entity.City;
import entity.Player;

public interface GameDataAccessInterface {
    void saveCity(City city);

    City getCity();

    void save(Player player);
}
