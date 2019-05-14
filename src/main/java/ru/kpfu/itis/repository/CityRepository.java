package ru.kpfu.itis.repository;

import org.springframework.data.repository.CrudRepository;
import ru.kpfu.itis.model.City;

public interface CityRepository extends CrudRepository<City, Long> {
}
