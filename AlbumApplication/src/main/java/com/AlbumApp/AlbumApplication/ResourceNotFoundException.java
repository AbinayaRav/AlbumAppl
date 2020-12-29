package com.AlbumApp.AlbumApplication;

public class ResourceNotFoundException extends RuntimeException{
    private String message;
    private String fieldValue;
    private Long id;

    public ResourceNotFoundException(String message, String fieldValue, Long id) {
        super(message);
        this.fieldValue = fieldValue;
        this.id = id;
    }
    public ResourceNotFoundException(String message, String fieldValue) {
        super(message);
        this.fieldValue = fieldValue;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public Long getId() {
        return id;
    }
}
