package com.korisnik;

import java.time.LocalDateTime;

public class ApiGreske {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    // Getteri
    public LocalDateTime getTimestamp() { return timestamp; }
    public int getStatus() { return status; }
    public String getError() { return error; }
    public String getMessage() { return message; }
    public String getPath() { return path; }

    // Setteri
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    public void setStatus(int status) { this.status = status; }
    public void setError(String error) { this.error = error; }
    public void setMessage(String message) { this.message = message; }
    public void setPath(String path) { this.path = path; }
}
