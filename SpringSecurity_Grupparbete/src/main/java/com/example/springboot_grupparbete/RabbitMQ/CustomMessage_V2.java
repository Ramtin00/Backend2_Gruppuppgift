package com.example.springboot_grupparbete.RabbitMQ;

import java.util.Date;

public class CustomMessage_V2 {

    private String messageId;
    private String message;
    private Date messageDate;

    public CustomMessage_V2() {
    }

    public CustomMessage_V2(String message) {
        this.message = message;
    }

    public CustomMessage_V2(String messageId, String message, Date messageDate) {
        this.messageId = messageId;
        this.message = message;
        this.messageDate = messageDate;
    }


    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }
}
