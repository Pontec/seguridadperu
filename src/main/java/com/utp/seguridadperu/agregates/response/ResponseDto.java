package com.utp.seguridadperu.agregates.response;


import com.utp.seguridadperu.modelo.Status;

public class ResponseDto {
    private String message;
    private String date;


    private Status status;  // Estado de la respuesta (ejemplo: "success", "error")


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
