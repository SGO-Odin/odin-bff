package com.odin.odinbff.controller.authentication;

import com.odin.odinbff.controller.Api;
import com.odin.odinbff.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Api.Authentication.AUTHENTICATION_RESOURCE)
public class AuthenticationController {

    @Autowired
    AuthenticationManager authManager;

    final AuthenticationService authenticationService;

    public AuthenticationController(
                                    @Autowired final AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<String> authenticate(@Valid @RequestBody LoginFormRequest loginFormRequest) {
        final var user = loginFormRequest.converte();


            final var authenticate = authManager.authenticate(user);
            final var  token = authenticationService.generateToken(authenticate);
            return ResponseEntity.ok(token);


    }
}
