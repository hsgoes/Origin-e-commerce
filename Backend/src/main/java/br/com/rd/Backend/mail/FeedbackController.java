package br.com.rd.Backend.mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ValidationException;
import java.util.Properties;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private EmailConf emailConf;

    public FeedbackController(EmailConf emailConf){
        this.emailConf = emailConf;
    }

    @PostMapping
    public void sendFeedback(@RequestBody Feedback feedback,
                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new ValidationException("Feedback is not valid!");
        }

        // Create a mail sender
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(emailConf.getHost());
        javaMailSender.setPort(emailConf.getPort());
        javaMailSender.setUsername(emailConf.getUsername());
        javaMailSender.setPassword(emailConf.getPassword());

        Properties properties = new Properties();

        properties.put("spring.mail.properties.mail.smtp.auth", "true");
        properties.put("spring.mail.properties.mail.smtp.starttls.enable", "true");

        javaMailSender.setJavaMailProperties(properties);

        // Create a mail instance
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(feedback.getEmail());
        mailMessage.setTo("hugo.henkel@hotmail.com");
        mailMessage.setSubject("New feedback from " + feedback.getName());
        mailMessage.setText(feedback.getFeedback());

        // Send mail
        javaMailSender.send(mailMessage);
    }
}