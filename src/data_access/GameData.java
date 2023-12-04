package data_access;

import entity.City;
import entity.Player;
import use_case.Game.GameDataAccessInterface;

public class GameData implements GameDataAccessInterface {

    private City currentCity;
    @Override
    public void saveCity(City city) {
        currentCity = city;
    }

    @Override
    public City getCity() {
        return currentCity;
    }

    @Override
    public void save(Player player) {

    }
}
