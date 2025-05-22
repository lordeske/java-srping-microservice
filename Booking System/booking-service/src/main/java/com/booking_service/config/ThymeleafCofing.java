package com.booking_service.config;

import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

public class ThymeleafCofing {

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(new ClassLoaderTemplateResolver() {{
            setPrefix("templates/");
            setSuffix(".html");
            setTemplateMode("HTML");
            setCharacterEncoding("UTF-8");
        }});
        return engine;
    }

}
