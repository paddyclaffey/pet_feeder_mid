package com.claffey.petminder.email;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service("emailService")
public class EmailService
{
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SimpleMailMessage preConfiguredMessage;

    /**
     * This method will send compose and send a new message
     * */
    public void sendNewMail(String to, String subject, String body)
    {
        MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                message.setFrom("me@mail.com");
                message.setTo("you@mail.com");
                message.setSubject("Mail subject");
                message.setText("some text <img src='cid:logo'>", true);
                message.addInline("logo", new ClassPathResource("img/logo.gif"));
                message.addAttachment("myDocument.pdf", new ClassPathResource("uploads/document.pdf"));
            }
        };
        mailSender.send(messagePreparator);
    }

    /**
     * This method will send a pre-configured message
     * */
    public void sendPreConfiguredMail(String message)
    {
        SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }

    @Async
    public void sendEmail(SimpleMailMessage email) {
        mailSender.send(email);
    }
}