package ru.specialist.spring.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.specialist.spring.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
