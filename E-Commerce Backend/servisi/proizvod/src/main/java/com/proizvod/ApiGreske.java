package com.proizvod;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiGreske {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

}
