package ru.kpfu.itis.repository;

import org.springframework.data.repository.CrudRepository;
import ru.kpfu.itis.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
