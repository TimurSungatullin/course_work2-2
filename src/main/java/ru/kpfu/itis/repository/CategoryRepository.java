package ru.kpfu.itis.repository;

import org.springframework.data.repository.CrudRepository;
import ru.kpfu.itis.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Category findByTitle(String name);
}
