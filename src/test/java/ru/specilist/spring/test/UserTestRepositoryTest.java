package ru.specilist.spring.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ru.specialist.spring.config.DataConfig;
import ru.specialist.spring.dao.repository.UserTestRepository;
import ru.specialist.spring.entity.Post;
import ru.specialist.spring.entity.UserTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DataConfig.class)
@Sql(scripts = "classpath:blog.sql",
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Transactional
public class UserTestRepositoryTest {
    private final UserTestRepository userTestRepository;

    @Autowired
    public UserTestRepositoryTest(UserTestRepository userTestRepository) {
        this.userTestRepository = userTestRepository;
    }

    @Test
    public void getAll(){
        List<UserTest> result = userTestRepository.findAll();
        assertEquals(2, result.size());
    }

    @Test
    public void create(){
        UserTest userTest = new UserTest();
        userTest.setUserName("username");
        userTest.setPassword("password");
        userTest.setFirstName("firstname");
        userTest.setLastName("lastname");
        userTest.setCreatedAt(new Date());
        userTest.setActive(true);
        userTestRepository.save(userTest);

        assertEquals(3, userTestRepository.count());
        assertEquals("username", userTestRepository.findById(3L).orElseThrow().getUserName());
    }

    @Test
    public void update(){
        UserTest userTest = userTestRepository.findById(1L).orElseThrow();
        userTest.setUserName("newusername");

        userTestRepository.save(userTest);
        assertEquals(2, userTestRepository.count());
        assertEquals("newusername", userTestRepository.findById(1L).orElseThrow().getUserName());
    }

    @Test
    void delete(){
        userTestRepository.deleteById(1L);
        assertEquals(1, userTestRepository.count());
    }

    @Test
    void userRole(){
        UserTest userTest = userTestRepository.findById(1L).orElseThrow();
        assertEquals(2, userTest.getRoles().size());
    }

    @Test
    void userPost(){
        UserTest userTest = userTestRepository.findById(1L).orElseThrow();
        assertEquals(1, userTest.getPosts().size());
    }

    @Test
    void userComment(){
        UserTest userTest = userTestRepository.findById(1L).orElseThrow();
        assertEquals(3, userTest.getComments().size());
    }

}
