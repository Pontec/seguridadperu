package com.utp.seguridadperu.configuracion;


import org.springframework.http.server.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

@Component
public class CustomHandshakeHandler  extends DefaultHandshakeHandler {

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return (auth != null) ? auth : null; // retorna el usuario autenticado
    }
}
