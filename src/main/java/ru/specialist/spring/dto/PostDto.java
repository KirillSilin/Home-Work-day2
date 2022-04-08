package ru.specialist.spring.dto;

import java.time.LocalDateTime;

public class PostDto {

    //DTO - Data Transfer Object
    //POJO - Plain Old Java Object

    private long postId;
    private long userTestId;
    private String title;
    private String content;
    private LocalDateTime dtCreated;
    private LocalDateTime dtUpdated;

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getUserTestId() {
        return userTestId;
    }

    public void setUserTestId(long userTestId) {
        this.userTestId = userTestId;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDtCreated() {
        return dtCreated;
    }

    public void setDtCreated(LocalDateTime dtCreated) {
        this.dtCreated = dtCreated;
    }

    public LocalDateTime getDtUpdated() {
        return dtUpdated;
    }

    public void setDtUpdated(LocalDateTime dtUpdated) {
        this.dtUpdated = dtUpdated;
    }
}
