package com.gyf.dto;

import java.time.LocalDateTime;

public class PlatformActivityResponse {

    private Long id;

    private String type;

    private String title;

    private String message;

    private LocalDateTime createdDate;

    public PlatformActivityResponse() {
    }

    public PlatformActivityResponse(
            Long id,
            String type,
            String title,
            String message,
            LocalDateTime createdDate) {

        this.id = id;
        this.type = type;
        this.title = title;
        this.message = message;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(
            LocalDateTime createdDate) {

        this.createdDate = createdDate;
    }
}