package ru.kpfu.itis.repository;

import org.springframework.data.repository.CrudRepository;
import ru.kpfu.itis.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findRoleByRole(String role_user);
}
