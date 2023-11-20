//package data_access;
//
//import entity.City;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//public class CityRepositoryImpl implements CityRepository {
//    private List<City> cities = new ArrayList<>();
//
//    @Override
//    public City save(City city) {
//        cities.add(city);
//        return city;
//    }
//
//    @Override
//    public Optional<City> findById(int id) {
//        return cities.stream().filter(city -> city.getId() == id).findFirst();
//    }
//
//    @Override
//    public List<City> findAll() {
//        return cities;
//    }
//
//    @Override
//    public void delete(City city) {
//        cities.remove(city);
//    }
//}
