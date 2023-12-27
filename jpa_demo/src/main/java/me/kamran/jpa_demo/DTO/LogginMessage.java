package me.kamran.jpa_demo.DTO;

import lombok.Data;


@Data
public class LogginMessage {
    String message;
    Boolean status;

    public LogginMessage(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }
}
