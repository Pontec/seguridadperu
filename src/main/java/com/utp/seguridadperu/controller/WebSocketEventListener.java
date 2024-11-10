package com.utp.seguridadperu.controller;


import com.utp.seguridadperu.Repository.UserRepository;
import com.utp.seguridadperu.agregates.dto.UserDto;
import com.utp.seguridadperu.modelo.User;
import com.utp.seguridadperu.modelo.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WebSocketEventListener {

    @Autowired
    private  SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private UserRepository userRepository; // Repositorio de usuario para acceder a la base de datos

   /*
    @EventListener
    public void handleSessionConnected(SessionConnectedEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = headerAccessor.getFirstNativeHeader("username");


        // Log para depuración
        System.out.println("Encabezado recibido: " + headerAccessor.toNativeHeaderMap());
        System.out.println("Usuario en encabezado: " + username);
        // Verificar que se haya recibido un nombre de usuario
        if (username == null) {
            System.out.println("Error: el nombre de usuario es nulo");
            return;
        }

        System.out.println("Usuario conectado: " + username);

        // Actualizar el estado del usuario en la base de datos
        User user = userRepository.findByUsername(username);
        if (user != null) {
            user.setStatus(UserStatus.ONLINE);
            user.setLastSeen(LocalDateTime.now());
            userRepository.save(user);
        } else {
            System.out.println("El usuario " + username + " no existe en la base de datos.");
            return;
        }

        // Obtener y enviar la lista de usuarios conectados a todos los clientes
        List<UserDto> connectedUsers = userRepository.findAllByStatus(UserStatus.ONLINE).stream()
                .map(u -> new UserDto(u.getUsername(), u.getStatus()))
                .collect(Collectors.toList());

        simpMessagingTemplate.convertAndSend("/chatroom/users", connectedUsers);
    }
*/

/*
    @EventListener
    public void handleSessionDisconnected(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = headerAccessor.getUser() != null ? headerAccessor.getUser().getName() : "Anónimo";

        System.out.println("Usuario desconectado: " + username);

        // Actualizar el estado del usuario a OFFLINE
        User user = userRepository.findByUsername(username);
        if (user != null) {
            user.setStatus(UserStatus.OFFLINE);
            user.setLastSeen(LocalDateTime.now());
            userRepository.save(user);
        }

        // Crear una lista de DTOs con los usuarios conectados
        List<UserDto> connectedUsers = userRepository.findAllByStatus(UserStatus.ONLINE).stream()
                .map(u -> new UserDto(u.getUsername(), u.getStatus()))
                .collect(Collectors.toList());

        // Enviar la lista actualizada de usuarios a todos los clientes
        simpMessagingTemplate.convertAndSend("/chatroom/users", connectedUsers);
    }

    */

}
