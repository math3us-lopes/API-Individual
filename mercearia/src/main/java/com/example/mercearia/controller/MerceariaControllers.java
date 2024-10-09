package com.example.mercearia.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mercearia.services.EmailService;

@RestController
@RequestMapping("/Mercearia")
public class MerceariaControllers {

    @Autowired
    EmailService emailService;

    @GetMapping
    public String writerTeste() {
        emailService.writerTeste();
        return "Email enviado com sucesso";
    }

    @GetMapping("/envioemail")
    public String writerTeste2() {
        emailService.writerTeste2();
        return "Email enviado com sucesso";
    }
}