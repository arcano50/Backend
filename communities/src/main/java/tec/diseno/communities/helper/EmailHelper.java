package tec.diseno.communities.helper;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class EmailHelper {
	@Autowired
    private JavaMailSender javaMailSender;

	public void sendEmail(String email) {
		
		System.out.println("javaMailSender: " + javaMailSender);
		
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);

        msg.setSubject("Noticia nueva");
        msg.setText("Una nueva noticia se ha publicado");
        

        javaMailSender.send(msg);

    }
}
