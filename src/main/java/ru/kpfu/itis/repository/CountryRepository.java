package ru.kpfu.itis.repository;

import org.springframework.data.repository.CrudRepository;
import ru.kpfu.itis.model.Country;

public interface CountryRepository extends CrudRepository<Country, Long> {
}
