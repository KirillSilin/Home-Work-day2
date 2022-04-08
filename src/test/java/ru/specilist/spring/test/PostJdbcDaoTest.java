package ru.specilist.spring.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.specialist.spring.config.DataConfig;
import ru.specialist.spring.dao.AbstractDao;
import ru.specialist.spring.dto.PostDto;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DataConfig.class)
@Sql(scripts = "classpath:blog.sql",
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class PostJdbcDaoTest {

    private final AbstractDao<PostDto> postDao;

    @Autowired
    public PostJdbcDaoTest(AbstractDao<PostDto> dao) {
        this.postDao = dao;
    }

    @Test
    public void getAll(){
        List<PostDto> result = postDao.getAll();
        assertEquals(3, result.size());
    }

    @Test
    public void create(){
        PostDto postDto = new PostDto();
        postDto.setTitle("Day 4");
        postDto.setContent("All is ok again");
        postDto.setDtCreated(LocalDateTime.now());

        postDao.create(postDto);
        assertEquals(4, postDao.getAll().size());
        assertEquals("Day 4", postDao.getById(4).getTitle());
    }

    @Test
    public void update(){
        PostDto postDto = postDao.getById(1);
        postDto.setTitle("Day 4");

        postDao.update(postDto);
        assertEquals(3, postDao.getAll().size());
        assertEquals("Day 4", postDao.getById(1).getTitle());
        assertNotNull(postDao.getById(1).getDtUpdated());
    }

    @Test
    void delete(){
        postDao.delete(1);
        assertEquals(2, postDao.getAll().size());
    }

}
