package ru.kpfu.itis.repository;

import org.springframework.data.repository.CrudRepository;
import ru.kpfu.itis.model.Category;
import ru.kpfu.itis.model.Product;

import java.util.Set;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Set<Product> findProductsByCategory(Category category);
}
