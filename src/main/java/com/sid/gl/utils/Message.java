package com.sid.gl.utils;

import java.io.Serializable;

public record Message(String messageContent,MessageStatus messageStatus,String field) implements Serializable {
    public Message(String messageContent,MessageStatus messageStatus) {
        this(messageContent, messageStatus, null);
    }

}
