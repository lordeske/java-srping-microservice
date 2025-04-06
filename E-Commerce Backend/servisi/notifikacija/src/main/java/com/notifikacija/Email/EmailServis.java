package com.notifikacija.Email;


import com.notifikacija.Kafka.Porudzbina.Proizvod;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;


import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class EmailServis {



    private JavaMailSender mailSender;


    private SpringTemplateEngine templateEngine;


    @Async
    public void posaljiPotvrduPlacanjaEmail(

            String destinacijaEmaila,
            String imeKorisnika,
            BigDecimal ukupnaCena,
            String referencaPorudzbine


    ) throws MessagingException {

        MimeMessage  mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_RELATED,
                StandardCharsets.UTF_8.name());

        mimeMessageHelper.setFrom("eskicmihajlo55@gmail.com");

        final String templateIme = EmailTemplate.POTVRDA_PLACANJA.getTemplate();


        Map<String, Object> varijable = new HashMap<>();

        varijable.put("imeKorisnika", imeKorisnika);
        varijable.put("destinacijaEmaila", destinacijaEmaila);
        varijable.put("ukupnaCena",ukupnaCena );
        varijable.put("referencaPorudzbine", referencaPorudzbine);


        Context kontext = new Context();
        kontext.setVariables(varijable);

        mimeMessageHelper.setSubject(EmailTemplate.POTVRDA_PLACANJA.getNaslov());


        try
        {

            String htmlTemplate = templateEngine.process(templateIme,kontext);

            mimeMessageHelper.setText(htmlTemplate, true);
            mimeMessageHelper.setTo(destinacijaEmaila);
            mailSender.send(mimeMessage);

            log.info(String.format("INFO - Mail uspjesno poslat na %s, sa templatom %s", destinacijaEmaila, templateIme));


        }
        catch (MessagingException exception)
        {

            log.warn("Ne mogu da posaljem email: {}", destinacijaEmaila);

        }






    }





    @Async
    public void posaljiPotvrduPorudzbineEmail(

            String destinacijaEmaila,
            String imeKorisnika,
            BigDecimal ukupnaCena,
            String referencaPorudzbine,
            List<Proizvod> proizvodi


    ) throws MessagingException {

        MimeMessage  mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_RELATED,
                StandardCharsets.UTF_8.name());

        mimeMessageHelper.setFrom("eskicmihajlo55@gmail.com");

        final String templateIme = EmailTemplate.POTVRDA_PORUDZBINE.getTemplate();


        Map<String, Object> varijable = new HashMap<>();

        varijable.put("imeKorisnika", imeKorisnika);
        varijable.put("destinacijaEmaila", destinacijaEmaila);
        varijable.put("ukupnaCena",ukupnaCena );
        varijable.put("referencaPorudzbine", referencaPorudzbine);
        varijable.put("proizvodi", proizvodi);


        Context kontext = new Context();
        kontext.setVariables(varijable);

        mimeMessageHelper.setSubject(EmailTemplate.POTVRDA_PORUDZBINE.getNaslov());


        try
        {

            String htmlTemplate = templateEngine.process(templateIme,kontext);

            mimeMessageHelper.setText(htmlTemplate, true);
            mimeMessageHelper.setTo(destinacijaEmaila);
            mailSender.send(mimeMessage);

            log.info(String.format("INFO - Mail uspjesno poslat na %s, sa templatom %s", destinacijaEmaila, templateIme));


        }
        catch (MessagingException exception)
        {

            log.warn("Ne mogu da posaljem email {}", destinacijaEmaila);

        }






    }




}
