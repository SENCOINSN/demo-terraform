package com.sid.gl.utils;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record Result<T>(int status,
@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
LocalDateTime createdAt,
Message message,
T data
) {

    public static <T> Result<T> createResultWithBody(int status, Message message, T data) {
        return new Result<T>(status, LocalDateTime.now(), message, data);
        
    }

    public static <T> Result<T> createResultWithoutBody(int status, Message message) {
        return new Result<T>(status, LocalDateTime.now(), message, null);
        
    }

}
