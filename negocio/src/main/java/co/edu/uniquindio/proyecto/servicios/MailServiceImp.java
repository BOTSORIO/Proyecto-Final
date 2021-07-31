package co.edu.uniquindio.proyecto.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class MailServiceImp implements MailService{

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendMail(String from, String to, String subject, String body) {

        SimpleMailMessage mail = new SimpleMailMessage();

        System.out.println(javaMailSender);

        System.out.println(from);
        System.out.println(to);
        System.out.println(subject);
        System.out.println(body);
        mail.setFrom(from);
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(body);

        javaMailSender.send(mail);
    }

}
