package com.utp.seguridadperu.agregates.dto;

import com.utp.seguridadperu.modelo.User;
import com.utp.seguridadperu.modelo.UserStatus;

public class UserDto {
    private Long id;
    private String username;
    private String status;
    private String lastSeen;

    public UserDto(String username, String status) {
        this.username = username;
        this.status = status;
    }
    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.status = user.getStatus().toString();
        this.lastSeen = user.getLastSeen().toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(String lastSeen) {
        this.lastSeen = lastSeen;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
