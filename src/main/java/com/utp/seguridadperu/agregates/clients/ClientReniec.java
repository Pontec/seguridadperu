package com.utp.seguridadperu.agregates.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "client-reniec", url = "https://api.apis.net.pe/v2/reniec")
public interface ClientReniec {

    //Se configura el apis al cual se va a invocar(solicitar informacion)
    @GetMapping("/dni")
    com.utp.seguridadperu.agregates.response.ResponseReniec getInfoReniec (@RequestParam("numero") String numero,
                                                                           @RequestHeader("Authorization")String authorization);


}
