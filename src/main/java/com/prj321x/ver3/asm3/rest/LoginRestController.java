package com.prj321x.ver3.asm3.rest;

import com.prj321x.ver3.asm3.entity.Account;
import com.prj321x.ver3.asm3.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class LoginRestController {

    private final AccountService accountService;

    public LoginRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDetails> login(@RequestBody Account account) {

        UserDetails loginAccount = accountService.loadUserByUsername(account.getEmail());
        return ResponseEntity.ok(loginAccount);
    }
}
