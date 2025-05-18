package com.booking_service.controller;


import com.booking_service.mail.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mail")
public class MailController {

    @Autowired
    private EmailService emailService;


    @PostMapping("/test")
    public ResponseEntity<String> testMail()
    {

        emailService.sendTestMail("test@localhost.com");
        return ResponseEntity.ok("Mejl poslat") ;


    }



}
