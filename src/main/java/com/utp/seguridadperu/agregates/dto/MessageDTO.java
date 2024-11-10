package com.utp.seguridadperu.agregates.dto;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

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

    public static class DenunciaDto {
        public Long usuarioId;
        public String asunto;
        public String descripcion;
        public double latitud;
        public double longitud;

        List<MultipartFile> imagenes;

        public Long getUsuarioId() {
            return usuarioId;
        }

        public void setUsuarioId(Long usuarioId) {
            this.usuarioId = usuarioId;
        }

        public String getAsunto() {
            return asunto;
        }

        public void setAsunto(String asunto) {
            this.asunto = asunto;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public double getLatitud() {
            return latitud;
        }

        public void setLatitud(double latitud) {
            this.latitud = latitud;
        }

        public double getLongitud() {
            return longitud;
        }

        public void setLongitud(double longitud) {
            this.longitud = longitud;
        }

        public List<MultipartFile> getImagenes() {
            return imagenes;
        }

        public void setImagenes(List<MultipartFile> imagenes) {
            this.imagenes = imagenes;
        }
    }
}
