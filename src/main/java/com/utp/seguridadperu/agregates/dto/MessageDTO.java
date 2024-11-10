package com.utp.seguridadperu.agregates.dto;

import java.time.LocalDateTime;

public class MessageDTO {

    private String senderName;       // El nombre del usuario que envió el mensaje
    private String message;          // El contenido del mensaje
    private String receiverName;     // El destinatario del mensaje (puede ser null para mensajes públicos)
    private LocalDateTime date;      // La fecha y hora en que se envió el mensaje
    private String status;           // El estado del mensaje, por ejemplo "SENT"

    public MessageDTO(String senderName, String message, String receiverName, LocalDateTime date, String status) {
        this.senderName = senderName;
        this.message = message;
        this.receiverName = receiverName;
        this.date = date;
        this.status = status;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
