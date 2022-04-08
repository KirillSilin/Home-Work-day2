package ru.specilist.spring.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ru.specialist.spring.config.DataConfig;
import ru.specialist.spring.dao.repository.RoleRepository;
import ru.specialist.spring.entity.Role;
import ru.specialist.spring.entity.UserTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DataConfig.class)
@Sql(scripts = "classpath:blog.sql",
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Transactional
public class RoleRepositoryTest {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleRepositoryTest(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Test
    public void getAll(){
        List<Role> result = roleRepository.findAll();
        assertEquals(2, result.size());
    }

    @Test
    public void create(){
        Role role = new Role();
        role.setName("varvar");
        roleRepository.save(role);

        assertEquals(3, roleRepository.count());
        assertEquals("varvar", roleRepository.findById(3L).orElseThrow().getName());
    }

    @Test
    public void update(){
        Role role = roleRepository.findById(1L).orElseThrow();
        role.setName("newRole");

        roleRepository.save(role);
        assertEquals(2, roleRepository.count());
        assertEquals("newRole", roleRepository.findById(1L).orElseThrow().getName());
    }

    @Test
    void delete(){
        roleRepository.deleteById(1L);
        assertEquals(1, roleRepository.count());
    }

}
