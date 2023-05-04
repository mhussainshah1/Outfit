package com.outfit.business.services;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Properties;

@Service
public class EmailService {

    @Autowired
    Environment env;
    private TemplateEngine templateEngine;

    @Autowired
    public EmailService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    private Properties getProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", env.getProperty("mail.smtp.starttls.enable"));
        props.put("mail.smtp.auth", env.getProperty("mail.smtp.auth"));
        props.put("mail.smtp.host", env.getProperty("mail.smtp.host"));
        props.put("mail.smtp.port", env.getProperty("mail.smtp.port"));
        return props;
    }

    private Session getSession() {
        //create session with username and password
        return Session.getInstance(getProperties(), new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(env.getProperty("app.email_address"), env.getProperty("app.password"));
            }
        });
    }

    public String buildTemplateWithContent(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        return templateEngine.process("mailtemplate", context);
    }

    public void sendSimpleEmail(String to, String subject, String body) {
        try {
            Message message = new MimeMessage(getSession());

            //email address you're sending from
            message.setFrom(new InternetAddress(env.getProperty("app.email_address")));

            //email address you're sending email to ( to user email )
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            //email  subject
            message.setSubject(subject);

            //email content
            message.setText(body);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}