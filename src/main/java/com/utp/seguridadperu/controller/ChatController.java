package com.utp.seguridadperu.controller;

import com.utp.seguridadperu.Repository.MessageRepository;
import com.utp.seguridadperu.Repository.UserRepository;
import com.utp.seguridadperu.agregates.dto.MessageDTO;
import com.utp.seguridadperu.agregates.dto.UserDto;
import com.utp.seguridadperu.agregates.response.ResponseDto;
import com.utp.seguridadperu.agregates.response.UserNameMessage;
import com.utp.seguridadperu.modelo.Message;
import com.utp.seguridadperu.modelo.Status;
import com.utp.seguridadperu.modelo.User;
import com.utp.seguridadperu.modelo.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ChatController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    private MessageDTO recivePublicMessage(@Payload MessageDTO messageDto) {
        messageDto.setDate(LocalDateTime.now()); // O usa un formato adecuado
        messageDto.setStatus(Status.SENT.name());

        // User usexist = userRepository.findByUsername("johndoe");
        // message.setReceiver(usexist);
        // messageRepository.save(message);
        return messageDto;
    }

    @Transactional
    @MessageMapping("/private-message")
    public void receivePrivateMessage(@Payload MessageDTO messageDto) {
        Message message = new Message();
        message.setMessage(messageDto.getMessage());
        message.setDate(LocalDateTime.now().format(formatter));
        message.setStatus(Status.SENT);

        User sender = userRepository.findByUsername(messageDto.getSenderName());
        if (sender == null) {
            throw new IllegalArgumentException("Sender not found");
        }
        message.setSender(sender);

        User receiver = userRepository.findByUsername(messageDto.getReceiverName());
        if (receiver == null) {
            throw new IllegalArgumentException("Receiver not found");
        }
        message.setReceiver(receiver);

        messageRepository.save(message);

        // Enviar el mensaje al usuario receptor
        simpMessagingTemplate.convertAndSendToUser(receiver.getUsername(), "/private", messageDto);
    }

    @MessageMapping("/user/connect")
    public void connectUser(@Payload UserNameMessage message) {
        String username = message.getUsername();
        User user = userRepository.findByUsername(username);
        if (user != null) {
            user.setStatus(UserStatus.ONLINE);
            user.setLastSeen(LocalDateTime.now());
            userRepository.save(user);
        }

        // Enviar actualización del estado del usuario conectado
        updateConnectedUsers();
    }

    @MessageMapping("/user/disconnect")
    public void disconnectUser(@Payload UserNameMessage message) {
        String username = message.getUsername();
        User user = userRepository.findByUsername(username);
        if (user != null) {
            user.setStatus(UserStatus.OFFLINE);
            user.setLastSeen(LocalDateTime.now());
            userRepository.save(user);
        }

        // Enviar actualización del estado del usuario desconectado
        updateConnectedUsers();
    }

    private void updateConnectedUsers() {
        List<User> connectedUsers = userRepository.findAllByStatus(UserStatus.ONLINE);
        List<UserDto> connectedUserDTOs = connectedUsers.stream()
                .map(UserDto::new)
                .toList();
        simpMessagingTemplate.convertAndSend("/chatroom/users", connectedUserDTOs);
    }


    @MessageMapping("/activate-sound")
    public void activateSound() {
        simpMessagingTemplate.convertAndSend("/chatroom/activate-sound", "activar");
        System.out.println("Notificación de activación de sonido enviada a todos los clientes conectados.");
    }


}



