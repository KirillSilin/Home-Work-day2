package ru.specialist.spring.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.specialist.spring.entity.UserTest;

public interface UserTestRepository extends JpaRepository<UserTest, Long> {
}
