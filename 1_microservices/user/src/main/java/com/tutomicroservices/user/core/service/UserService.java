package com.tutomicroservices.user.core.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    public String getAllUsers() {
        log.info(">>> Start method getAllUsers");
        String result = "Se esta consultando la informacion de todos los usuarios";
        log.info("<<< End method getAllUsers");
        return result;
    }

    public String getUserById(int userId) {
        log.info(">>> Start method getUserById");
        String result = String.format("Se esta consultando la informacion del usuario con el id:%d", userId);
        log.info("<<< End method getAllUsers");
        return result;
    }
}
