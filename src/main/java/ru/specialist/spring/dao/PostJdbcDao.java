package ru.specialist.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.specialist.spring.dto.PostDto;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class PostJdbcDao implements AbstractDao<PostDto> {

    private static final String UPDATE = """
            update
                post
            set
                user_test_id = ?,
            	title = ?,
            	content = ?,
            	dt_created = ?,
            	dt_updated = now()
            where 
                post_id = ?               
            """;

    private static final String DELETE = "delete from post where post_id = ?";

    private static String GET_ALL = """
            select
            	post_id,
            	user_test_id,
            	title,
            	content,
            	dt_created,
            	dt_updated
            from
            	post          
            """;

    private static String GET_BY_ID = """
            select
            	post_id,
            	user_test_id,
            	title,
            	content,
            	dt_created,
            	dt_updated
            from
            	post
            where 
                post_id = ?        
            """;

    private static String CREATE = """
                insert into post (user_test_id, title, content, dt_created)
                	values(?, ?, ?, ?);
            """;

    private static final RowMapper<PostDto> ROW_MAPPER = (rs, rowNum) -> {
        PostDto postDto = new PostDto();
        postDto.setPostId(rs.getInt("post_id"));
        postDto.setUserTestId(rs.getInt("user_test_id"));
        postDto.setTitle(rs.getString("title"));
        postDto.setContent(rs.getString("content"));
        postDto.setDtCreated(rs.getTimestamp("dt_created").toLocalDateTime());
        Timestamp ts = rs.getTimestamp("dt_updated");
        if (ts != null){
            postDto.setDtUpdated(ts.toLocalDateTime());
        }
        return postDto;
    };

    private final JdbcOperations jdbcOperations;

    @Autowired
    public PostJdbcDao(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void create(PostDto data) {
        jdbcOperations.update(CREATE,
                data.getUserTestId(),
                data.getTitle(),
                data.getContent(),
                data.getDtCreated());
    }

    @Override
    public List<PostDto> getAll() {
        return jdbcOperations.query(GET_ALL, ROW_MAPPER);
    }

    @Override
    public PostDto getById(long postId) {
        return jdbcOperations.queryForObject(GET_BY_ID, ROW_MAPPER, postId);
    }

    @Override
    public void update(PostDto data) {
        jdbcOperations.update(UPDATE,
                data.getUserTestId(),
                data.getTitle(),
                data.getContent(),
                data.getDtCreated(),
                data.getPostId());
    }

    @Override
    public void delete(long postId) {
        jdbcOperations.update(DELETE, postId);
    }
}
