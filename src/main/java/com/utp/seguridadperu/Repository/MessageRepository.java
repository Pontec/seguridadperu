package com.utp.seguridadperu.Repository;

import com.utp.seguridadperu.modelo.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    // Método para obtener mensajes entre dos usuarios
    List<Message> findBySenderUsernameAndReceiverUsername(String senderName, String receiverName);

    // Método para obtener mensajes públicos
    List<Message> findByReceiverUsernameIsNull();
}
