package ru.specialist.spring.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.specialist.spring.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
