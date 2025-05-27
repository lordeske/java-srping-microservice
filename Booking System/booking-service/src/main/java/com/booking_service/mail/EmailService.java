package com.booking_service.mail;


import com.booking_service.entity.Customer;
import com.booking_service.service.QrCodeService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;


import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class EmailService {


    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;


    @Autowired
    private QrCodeService qrCodeService;


    @Async
    public void sendConfirmationEmail(String to, String name, BigDecimal total, String qrContent, Long orderRef) throws Exception {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());

        byte[] qrBytes = qrCodeService.generateQrCode(
                qrContent , 300, 300);

        String qrImageBase64 = Base64.getEncoder().encodeToString(qrBytes);



        Context context = new Context();
        context.setVariable("imeKorisnika", name);
        context.setVariable("ukupnaCena", total);
        context.setVariable("referencaPorudzbine", orderRef);
        context.setVariable("qrImage", "data:image/png;base64," + qrImageBase64);

        String htmlContent = templateEngine.process("confirmation", context);


        helper.setTo(to);
        helper.setSubject("Potvrda rezervacije");
        helper.setText(htmlContent, true);
        helper.setFrom("noreply@eske.local");

        mailSender.send(message);


    }


    @Async
    public void sendCancelationEmail(Long orderId, Customer customer) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());


        Context context = new Context();
        context.setVariable("imeKorisnika", customer.getName());
        context.setVariable("referencaPorudzbine" , orderId);

        String htmlContent = templateEngine.process("cancelation",context );

        helper.setTo(customer.getEmail());
        helper.setSubject("Potvrda odustanka od rezervacije");
        helper.setText(htmlContent, true);

        mailSender.send(message);

    }
}
