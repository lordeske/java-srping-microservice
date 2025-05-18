package com.booking_service.mail;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {


    @Autowired
    private JavaMailSender mailSender;





    public void sendTestMail(String to)
    {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreplay@eske.local");
        message.setTo(to);
        message.setSubject("Test Mail od Esketa");
        message.setText("Ovo je test mail da vidimo jel radi");


        mailSender.send(message);



    }




}
